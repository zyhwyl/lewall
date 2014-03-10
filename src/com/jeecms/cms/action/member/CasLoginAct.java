package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;
import static com.jeecms.core.action.front.LoginAct.MESSAGE;
import static com.jeecms.core.action.front.LoginAct.PROCESS_URL;
import static com.jeecms.core.action.front.LoginAct.RETURN_URL;
import static com.jeecms.core.manager.AuthenticationMng.AUTH_KEY;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import com.jeecms.cms.entity.main.AcUserCheckin;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.security.BadCredentialsException;
import com.jeecms.common.security.DisabledException;
import com.jeecms.common.security.UsernameNotFoundException;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.Authentication;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.entity.Config.ConfigLogin;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.core.manager.ConfigMng;
import com.jeecms.core.manager.UnifiedUserMng;
import com.jeecms.core.web.WebErrors;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.qq.connect.AccessToken;
import com.qq.connect.InfoToken;
import com.qq.util.ParseString;
import com.qq.util.Verify;
import com.qq.util.XMLHelper;

@Controller
public class CasLoginAct {
	private static final Logger log = LoggerFactory
			.getLogger(CasLoginAct.class);

	public static final String COOKIE_ERROR_REMAINING = "_error_remaining";
	public static final String LOGIN_INPUT = "tpl.loginInput";
	public static final String LOGIN_STATUS = "tpl.loginStatus";
	private static String PRE_PATH="/WEB-INF/t/cms/www/red/index/";
	
	@RequestMapping(value = "/login.jspx", method = RequestMethod.GET)
	public String input(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		String sol = site.getSolutionPath();
		String processUrl = RequestUtils.getQueryParam(request, PROCESS_URL);
		String returnUrl = RequestUtils.getQueryParam(request, RETURN_URL);
		String message = RequestUtils.getQueryParam(request, MESSAGE);
		String authId = (String) session.getAttribute(request, AUTH_KEY);
		if (authId != null) {
			// 存在认证ID
			Authentication auth = authMng.retrieve(authId);
			// 存在认证信息，且未过期
			if (auth != null) {
				String view = getView(processUrl, returnUrl, auth.getId());
				if (view != null) {
					return view;
				} else {
					FrontUtils.frontData(request, model, site);
					model.addAttribute("auth", auth);
					return FrontUtils.getTplPath(request, sol, TPLDIR_MEMBER,
							LOGIN_STATUS);
				}
			}
		}
		FrontUtils.frontData(request, model, site);
		if (!StringUtils.isBlank(processUrl)) {
			model.addAttribute(PROCESS_URL, processUrl);
		}
		if (!StringUtils.isBlank(returnUrl)) {
			model.addAttribute(RETURN_URL, returnUrl);
		}
		if (!StringUtils.isBlank(message)) {
			model.addAttribute(MESSAGE, message);
		}
		return FrontUtils.getTplPath(request, sol, TPLDIR_MEMBER, LOGIN_INPUT);
	}

	@RequestMapping(value = "/login.jspx", method = RequestMethod.POST)
	public String submit(String email, String password, String captcha,
			String processUrl, String returnUrl, String message,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		Integer errorRemaining = unifiedUserMng.errorRemaining(email);
		CmsSite site = CmsUtils.getSite(request);
		String sol = site.getSolutionPath();
		WebErrors errors = validateSubmit(email, password, captcha,
				errorRemaining, request, response);
		if (!errors.hasErrors()) {
			try {
				String ip = RequestUtils.getIpAddr(request);
				Authentication auth = authMng.login(email, password, ip,
						request, response, session);
				
				// 是否需要在这里加上登录次数的更新？按正常的方式，应该在process里面处理的，不过这里处理也没大问题。
				cmsUserMng.updateLoginInfo(auth.getUid(), ip);
				CmsUser user = cmsUserMng.findById(auth.getUid());
				if (user.getDisabled()) {
					// 如果已经禁用，则推出登录。
					authMng.deleteById(auth.getId());
					session.logout(request, response);
					throw new DisabledException("user disabled");
				}
				removeCookieErrorRemaining(request, response);
				String view = getView(processUrl, returnUrl, auth.getId());
				if (view != null) {
					return view;
				} else {
					FrontUtils.frontData(request, model, site);
					return "redirect:login.jspx";
				}
			} catch (UsernameNotFoundException e) {
				errors.addErrorString(e.getMessage());
			} catch (BadCredentialsException e) {
				errors.addErrorString(e.getMessage());
			} catch (DisabledException e) {
				errors.addErrorString(e.getMessage());
			}
		}
		// 登录失败
		writeCookieErrorRemaining(errorRemaining, request, response, model);
		errors.toModel(model);
		FrontUtils.frontData(request, model, site);
		if (!StringUtils.isBlank(processUrl)) {
			model.addAttribute(PROCESS_URL, processUrl);
		}
		if (!StringUtils.isBlank(returnUrl)) {
			model.addAttribute(RETURN_URL, returnUrl);
		}
		if (!StringUtils.isBlank(message)) {
			model.addAttribute(MESSAGE, message);
		}
		return FrontUtils.getTplPath(request, sol, TPLDIR_MEMBER, LOGIN_INPUT);
	}

	@RequestMapping(value = "/emaillogin.jspx", method = RequestMethod.POST)
	public String emailSubmit(String email, String password, String captcha,
			String processUrl, String returnUrl, String message,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) { 
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		String sol = site.getSolutionPath();
		WebErrors errors = validateSubmit(email, password, captcha,request, response);
		if (!errors.hasErrors()) {
			try {
				String ip = RequestUtils.getIpAddr(request);
				Authentication auth = authMng.login(email, password, ip,
						request, response, session);
				//如果用户还没有通过邮箱验证 则进入邮箱验证页面
				if(auth==null){
					UnifiedUser user = unifiedUserMng.login(email, password, ip);
					model.addAttribute("user",user);
					return "/WEB-INF/t/cms/www/red/member/邮箱激活页.html";
				}else{
					// 是否需要在这里加上登录次数的更新？按正常的方式，应该在process里面处理的，不过这里处理也没大问题。
					cmsUserMng.updateLoginInfo(auth.getUid(), ip);
					CmsUser user = cmsUserMng.findById(auth.getUid());
					if (user.getDisabled()) {
						// 如果已经禁用，则推出登录。
						authMng.deleteById(auth.getId());
						session.logout(request, response); 	
					}
					//如果用户还没选择学校
					if (user.getSchoolId()==null) {
						return "redirect:/member/portrait.jspx";
					}
					
					removeCookieErrorRemaining(request, response);
					String view = getView(processUrl, returnUrl, auth.getId());
					if (view != null) {
						return view;
					} else {
						FrontUtils.frontData(request, model, site);
						return "redirect:index.jhtml";
					}
				}
				
			} catch (UsernameNotFoundException e) {
				errors.addErrorString(e.getMessage());
			} catch (BadCredentialsException e) {
				errors.addErrorString(e.getMessage());
			} 
		}
		// 登录失败
		writeCookieErrorRemaining(request, response, model);
		errors.toModel(model);
		FrontUtils.frontData(request, model, site);
		if (!StringUtils.isBlank(processUrl)) {
			model.addAttribute(PROCESS_URL, processUrl);
		}
		if (!StringUtils.isBlank(returnUrl)) {
			model.addAttribute(RETURN_URL, returnUrl);
		}
		if (!StringUtils.isBlank(message)) {
			model.addAttribute(MESSAGE, message);
		}
		return FrontUtils.getTplPath(request, sol, TPLDIR_MEMBER, LOGIN_INPUT);
	}
	
	@RequestMapping(value = "/logout.jspx")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		String authId = (String) session.getAttribute(request, AUTH_KEY);
		if (authId != null) {
			authMng.deleteById(authId);
			session.logout(request, response);
		}
		String processUrl = RequestUtils.getQueryParam(request, PROCESS_URL);
		String returnUrl = RequestUtils.getQueryParam(request, RETURN_URL);
		String view = getView(processUrl, returnUrl, authId);
		if (view != null) {
			return view;
		} else {
			return "redirect:login.jspx";
		}
	}
	
	/**
	 * qq登录回调
	 * 
	 * */
	@RequestMapping(value = "/connect/qq/callback.jspx")
	public String qqLoginCallBack(HttpServletRequest request,
			HttpServletResponse response,ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		return PRE_PATH+"qq_return.html";
	}
	
	/**
	 * qq登录
	 * 
	 * */
	@RequestMapping(value = "/connect/qq/qqlogin.jspx")
	public String qqLogin(HttpServletRequest request,
			HttpServletResponse response,ModelMap model,
			String openId,String gender) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		//先查找此会员是否使用过qq登录 如果存在则直接登录 否则让其完善资料后注册
		UnifiedUser uniUser=unifiedUserMng.findByOpenid(openId);
		if(uniUser==null){
			model.addAttribute("openId",openId);
			model.addAttribute("gender",gender);
			return PRE_PATH+"qq_register.html";
		}else{
			//已存在 直接登录 
			String ip = RequestUtils.getIpAddr(request);
			
			//如果用户还没有通过邮箱验证 则进入邮箱验证页面
			if(!uniUser.getActivation()){
				UnifiedUser user = unifiedUserMng.login(uniUser, ip);
				model.addAttribute("user",user);
				return "/WEB-INF/t/cms/www/red/member/邮箱激活页.html";
			}else{
				Authentication auth = authMng.login(uniUser, ip,
						request, response, session);
				unifiedUserMng.login(uniUser, ip);
				// 是否需要在这里加上登录次数的更新？按正常的方式，应该在process里面处理的，不过这里处理也没大问题。
				cmsUserMng.updateLoginInfo(auth.getUid(), ip);
				CmsUser user = cmsUserMng.findById(auth.getUid());
				if (user.getDisabled()) {
					// 如果已经禁用，则推出登录。
					authMng.deleteById(auth.getId());
					session.logout(request, response); 	
				}
				//如果用户还没选择学校
				if (user.getSchoolId()==null) {
					return "redirect:/member/portrait.jspx";
				} 
				removeCookieErrorRemaining(request, response); 
				FrontUtils.frontData(request, model, site);
				return "redirect:/index.jhtml";
			}
		}
	}

	private WebErrors validateSubmit(String username, String password,
			String captcha, Integer errorRemaining, HttpServletRequest request,
			HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifOutOfLength(username, "username", 1, 100)) {
			return errors;
		}
		if (errors.ifOutOfLength(password, "password", 1, 32)) {
			return errors;
		}
		// 如果输入了验证码，那么必须验证；如果没有输入验证码，则根据当前用户判断是否需要验证码。
		if (!StringUtils.isBlank(captcha)
				|| (errorRemaining != null && errorRemaining < 0)) {
			if (errors.ifBlank(captcha, "captcha", 100)) {
				return errors;
			}
			try {
				if (!imageCaptchaService.validateResponseForID(session
						.getSessionId(request, response), captcha)) {
					errors.addErrorCode("error.invalidCaptcha");
					return errors;
				}
			} catch (CaptchaServiceException e) {
				errors.addErrorCode("error.exceptionCaptcha");
				log.warn("", e);
				return errors;
			}
		}
		return errors;
	}
	
	//ajax验证码验证
	/**
	 * ajax获得用户数据
	 * */
	@RequestMapping(value = "/captchaval.jhtml",method=RequestMethod.GET)
	public void ajaxCaptchaValidate(HttpServletRequest request,HttpServletResponse response,String captcha){ 
		try{ 
			PrintWriter write=response.getWriter();
			try {
				if (!imageCaptchaService.validateResponseForID(session
						.getSessionId(request, response), captcha)) {
					//0 验证码错误
					write.print(0);
				}else{
					//1 验证码正确
					write.print(1);
				}
			} catch (CaptchaServiceException e) {
				//2 程序异常
				write.print(2);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


	private WebErrors validateSubmit(String username, String password,
			String captcha, HttpServletRequest request,
			HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request); 
		if (errors.ifOutOfLength(password, "password", 1, 32)) {
			return errors;
		}
		// 如果输入了验证码，那么必须验证；如果没有输入验证码，则根据当前用户判断是否需要验证码。
		captcha=captcha.toLowerCase();
		if (!StringUtils.isBlank(captcha)) {
			if (errors.ifBlank(captcha, "captcha", 100)) {
				return errors;
			}
			try {
				if (!imageCaptchaService.validateResponseForID(session
						.getSessionId(request, response), captcha)) {
					errors.addErrorCode("error.invalidCaptcha");
					return errors;
				}
			} catch (CaptchaServiceException e) {
				errors.addErrorCode("error.exceptionCaptcha");
				log.warn("", e);
				return errors;
			}
		}
		return errors;
	}
	
	
	/**
	 * 获得地址
	 * 
	 * @param processUrl
	 * @param returnUrl
	 * @param authId
	 * @param defaultUrl
	 * @return
	 */
	private String getView(String processUrl, String returnUrl, String authId) {
		if (!StringUtils.isBlank(processUrl)) {
			StringBuilder sb = new StringBuilder("redirect:");
			sb.append(processUrl).append("?").append(AUTH_KEY).append("=")
					.append(authId);
			if (!StringUtils.isBlank(returnUrl)) {
				sb.append("&").append(RETURN_URL).append("=").append(returnUrl);
			}
			return sb.toString();
		} else if (!StringUtils.isBlank(returnUrl)) {
			StringBuilder sb = new StringBuilder("redirect:");
			sb.append(returnUrl);
			return sb.toString();
		} else {
			return null;
		}
	}

	private void writeCookieErrorRemaining(Integer userErrorRemaining,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		// 所有访问的页面都需要写一个cookie，这样可以判断已经登录了几次。
		Integer errorRemaining = getCookieErrorRemaining(request, response);
		ConfigLogin configLogin = configMng.getConfigLogin();
		Integer errorInterval = configLogin.getErrorInterval();
		if (userErrorRemaining != null
				&& (errorRemaining == null || userErrorRemaining < errorRemaining)) {
			errorRemaining = userErrorRemaining;
		}
		int maxErrorTimes = configLogin.getErrorTimes();
		if (errorRemaining == null || errorRemaining > maxErrorTimes) {
			errorRemaining = maxErrorTimes;
		} else if (errorRemaining <= 0) {
			errorRemaining = 0;
		} else {
			errorRemaining--;
		}
		model.addAttribute("errorRemaining", errorRemaining);
		CookieUtils.addCookie(request, response, COOKIE_ERROR_REMAINING,
				errorRemaining.toString(), errorInterval * 60, null);
	}


	private void writeCookieErrorRemaining(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		// 所有访问的页面都需要写一个cookie，这样可以判断已经登录了几次。
		Integer errorRemaining = getCookieErrorRemaining(request, response);
		ConfigLogin configLogin = configMng.getConfigLogin();
		Integer errorInterval = configLogin.getErrorInterval();

		int maxErrorTimes = configLogin.getErrorTimes();
		if (errorRemaining == null || errorRemaining > maxErrorTimes) {
			errorRemaining = maxErrorTimes;
		} else if (errorRemaining <= 0) {
			errorRemaining = 0;
		} else {
			errorRemaining--;
		}
		model.addAttribute("errorRemaining", errorRemaining);
		CookieUtils.addCookie(request, response, COOKIE_ERROR_REMAINING,
				errorRemaining.toString(), errorInterval * 60, null);
	}

	private Integer getCookieErrorRemaining(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie cookie = CookieUtils.getCookie(request, COOKIE_ERROR_REMAINING);
		if (cookie != null) {
			String value = cookie.getValue();
			if (NumberUtils.isDigits(value)) {
				return Integer.parseInt(value);
			}
		}
		return null;
	}

	private void removeCookieErrorRemaining(HttpServletRequest request,
			HttpServletResponse response) {
		CookieUtils.cancleCookie(request, response, COOKIE_ERROR_REMAINING,
				null);
	}

	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private ConfigMng configMng;
	@Autowired
	private AuthenticationMng authMng;
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private SessionProvider session;
}
