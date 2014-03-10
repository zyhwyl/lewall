package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jws.WebParam.Mode;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.entity.main.CmsConfig;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsUserExt;
import com.jeecms.cms.entity.main.MemberConfig;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.manager.main.impl.CmsUserMngImpl;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.cms.web.WebErrors;
import com.jeecms.common.email.EmailSendTool;
import com.jeecms.common.email.EmailSender;
import com.jeecms.common.email.MessageTemplate;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.security.BadCredentialsException;
import com.jeecms.common.security.UsernameNotFoundException;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.Config.ConfigEmailSender;
import com.jeecms.core.entity.Authentication;
import com.jeecms.core.entity.UnifiedUser;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.core.manager.ConfigMng;
import com.jeecms.core.manager.UnifiedUserMng;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 前台会员注册Action
 * 
 * @author liufang
 * 
 */
@Controller
public class RegisterAct {
	private static final Logger log = LoggerFactory
			.getLogger(RegisterAct.class);

	public static final String REGISTER = "tpl.register";
	public static final String REGISTER_RESULT = "tpl.registerResult";
	public static final String REGISTER_ACTIVE_SUCCESS = "tpl.registerActiveSuccess";
	public static final String LOGIN_INPUT = "tpl.loginInput";
	private static final String PRE_NAME="/WEB-INF/t/cms/www/red/member/";

	@RequestMapping(value = "/register.jspx", method = RequestMethod.GET)
	public String input(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		// 没有开启会员注册
		if (!mcfg.isRegisterOn()) {
			return FrontUtils.showMessage(request, model,
					"member.registerClose");
		}
		FrontUtils.frontData(request, model, site);
		model.addAttribute("mcfg", mcfg);
		return PRE_NAME+"会员注册页.html";
	}

	public static void main(String[] args) {
		Map<String,String> map=new HashMap<String, String>();
		map.put("email_host","smtp.126.com");
		map.put("email_port","25");
		map.put("email_encoding","UTF-8");
		map.put("email_username","lewall@126.com");
		map.put("email_password","19880605203X");
		map.put("email_personal","乐喔网 www.lwall.net--创造我们的生活");
		map.put("return_url", "");
		
		EmailSender sender =ConfigEmailSender.create(map);
		
		EmailSendTool sendEmail = new EmailSendTool(sender.getHost(),sender
				.getUsername(),sender.getPassword(),"498821924@qq.com","test","test",sender.getPersonal()
				, "head_name", "head_value");
		try {
			sendEmail.send();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/register.jspx", method = RequestMethod.POST)
	public String submit(String username, String email, String password,
			CmsUserExt userExt, String captcha, String nextUrl,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		CmsConfig config=site.getConfig();
		WebErrors errors = validateSubmit(username, email, password, captcha,
				site, request, response);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		String ip = RequestUtils.getIpAddr(request);
		if(config.getEmailValidate()){
			EmailSender sender = ConfigEmailSender.createByProperties(request); 
			if (sender == null) {
				// 邮件服务器没有设置好
				model.addAttribute("status", 4);
			}else {
				try {
					CmsUser user=cmsUserMng.registerMember(username, email, password, ip, null, userExt,
							false, sender); 
					
					model.addAttribute("status", 0);
					model.addAttribute("user", user);
				} catch (UnsupportedEncodingException e) {
					// 发送邮件异常
					model.addAttribute("status", 100);
					model.addAttribute("message", e.getMessage());
					log.error("send email exception.", e);
				}catch (MessagingException e) {
					// 发送邮件异常
					model.addAttribute("status", 101);
					model.addAttribute("message", e.getMessage());
					log.error("send email exception.", e);
				}
			}
			log.info("member register success. username={}", username);
			FrontUtils.frontData(request, model, site);
			if (!StringUtils.isBlank(nextUrl)) {
				response.sendRedirect(nextUrl);
				return null;
			} else {
				return FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_MEMBER, REGISTER_RESULT);
			}
		}else{
			cmsUserMng.registerMember(username, email, password, ip, null, userExt);
			log.info("member register success. username={}", username);
			FrontUtils.frontData(request, model, site);
			FrontUtils.frontPageData(request, model);
			model.addAttribute("success",true);
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MEMBER, LOGIN_INPUT);
		}
		
	}
	
	@RequestMapping(value = "/qq_register.jspx", method = RequestMethod.POST)
	public String qqsubmit(String username, String email,String openid,Integer gender,
			CmsUserExt userExt,String nextUrl,HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		CmsConfig config=site.getConfig();
		WebErrors errors = validateSubmit(username, email,"","",
				site, request, response);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		String ip = RequestUtils.getIpAddr(request);
		if(config.getEmailValidate()){
			EmailSender sender = ConfigEmailSender.createByProperties(request); 
			if (sender == null) {
				// 邮件服务器没有设置好
				model.addAttribute("status", 4);
			}else {
				try {
					userExt=new CmsUserExt();
					if(gender.equals(1)){
						userExt.setGender(true);
					}else{
						userExt.setGender(false);
					}
					CmsUser user=cmsUserMng.registerQQMember(username, email, openid, ip, null, userExt,
							false, sender); 
					model.addAttribute("status", 0);
					model.addAttribute("user", user);
				} catch (UnsupportedEncodingException e) {
					// 发送邮件异常
					model.addAttribute("status", 100);
					model.addAttribute("message", e.getMessage());
					log.error("send email exception.", e);
				}catch (MessagingException e) {
					// 发送邮件异常
					model.addAttribute("status", 101);
					model.addAttribute("message", e.getMessage());
					log.error("send email exception.", e);
				}
			}
			log.info("member register success. username={}", username);
			FrontUtils.frontData(request, model, site);
			if (!StringUtils.isBlank(nextUrl)) {
				response.sendRedirect(nextUrl);
				return null;
			} else {
				return FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_MEMBER, REGISTER_RESULT);
			}
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_MEMBER, LOGIN_INPUT);
	}
	
	/**
	 * 生成邮箱激活验证
	 * */
	@RequestMapping(value = "/acitivity/generatemail.jspx")
	public void generateValidate(String userid, String email,
			HttpServletRequest request,ModelMap model,HttpServletResponse response){
		if(userid!=null&&!userid.equals("")){ 
			//获得用户 将其邮箱验证码刷新
			UnifiedUser user=unifiedUserMng.findById(Integer.valueOf(userid));
			//生成验证码
			String valicode = StringUtils.remove(UUID.randomUUID().toString(), '-');
			user.setActivationCode(valicode);  
			
			//更新当前用户验证码
			Updater<UnifiedUser> updater=new Updater<UnifiedUser>(user);
			unifiedUserMng.updateByUpdater(updater);
			
			EmailSender sender = ConfigEmailSender.createByProperties(request); 
			if (sender == null) {
				// 邮件服务器没有设置好
				Writer writer;
				try {
					writer = response.getWriter();
					writer.write("1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				String text=sender.getContent();
				String url=sender.getReturnUrl();
				url=StringUtils.replace(url, "${userid}",userid.toString());
				url=StringUtils.replace(url, "${email}",email);
				//生成valicode
				url=StringUtils.replace(url, "${valicode}",valicode);
				text = text + url;
 				//发送邮件
				EmailSendTool sendEmail = new EmailSendTool(sender.getHost(), sender
						.getUsername(), sender.getPassword(),email,sender.getTitle(), 
						 text, sender.getPersonal(), "head_name", "head_value");
				try {
					sendEmail.send();
					Writer writer=response.getWriter();
					writer.write("0");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//邮箱验证
	@RequestMapping(value = "/acitivity/mailvalidate.jspx", method = RequestMethod.GET)
	public String mailValidate(String userid, String email, String valicode,String jumpUrl,
			HttpServletRequest request,ModelMap model,HttpServletResponse response){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		UnifiedUser user=unifiedUserMng.findById(Integer.valueOf(userid));
		if(user!=null){
			String userValicode=user.getActivationCode();
			if(valicode!=null&&userValicode!=null){
				//验证成功 将该会员验证状态激活并登陆
				if(userValicode.equals(valicode)){
					String ip = RequestUtils.getIpAddr(request); 
					user.setActivation(true);
					Updater<UnifiedUser> userUpdater=new Updater<UnifiedUser>(user);
					unifiedUserMng.updateByUpdater(userUpdater);
					try {
						Authentication auth = authMng.login(user.getEmail(),ip,
								request, response, session);
						// 是否需要在这里加上登录次数的更新？按正常的方式，应该在process里面处理的，不过这里处理也没大问题。
						cmsUserMng.updateLoginInfo(auth.getUid(), ip);
 
						
						//登录后跳转到完善学校资料部分
						return "redirect:/member/portrait.jspx";
					} catch (UsernameNotFoundException e) {
						e.printStackTrace();
					} catch (BadCredentialsException e) {
						e.printStackTrace();
					}
				}else{
					model.addAttribute("error", "验证码错误或者已过期！");
				}
			}else{
				model.addAttribute("error", "验证码为空！");
			}
			return PRE_NAME+"激活成功页.html";
		}else{
			model.addAttribute("error", "传入用户有误！");
			return PRE_NAME+"激活成功页.html";
		}
		
	}

	@RequestMapping(value = "/active.jspx", method = RequestMethod.GET)
	public String active(String username, String key,HttpServletRequest request, 
			HttpServletResponse response,ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateActive(username, key, request, response);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		UnifiedUser user = unifiedUserMng.active(username, key);
		String ip = RequestUtils.getIpAddr(request);
		authMng.activeLogin(user, ip, request, response, session);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, REGISTER_ACTIVE_SUCCESS);
	}

	@RequestMapping(value = "/username_unique.jspx")
	public void usernameUnique(HttpServletRequest request,
			HttpServletResponse response) {
		String username = RequestUtils.getQueryParam(request, "username");
		// 用户名为空，返回false。
		if (StringUtils.isBlank(username)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		CmsSite site = CmsUtils.getSite(request);
		CmsConfig config = site.getConfig();
		// 保留字检查不通过，返回false。
		if (!config.getMemberConfig().checkUsernameReserved(username)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		// 用户名存在，返回false。
		if (unifiedUserMng.usernameExist(username)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		ResponseUtils.renderJson(response, "true");
	}

	@RequestMapping(value = "/email_unique.jspx")
	public void emailUnique(HttpServletRequest request,
			HttpServletResponse response) {
		String email = RequestUtils.getQueryParam(request, "email");
		// email为空，返回false。
		if (StringUtils.isBlank(email)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		// email存在，返回false。
		if (unifiedUserMng.emailExist(email)) {
			ResponseUtils.renderJson(response, "false");
			return;
		}
		ResponseUtils.renderJson(response, "true");
	}

	private WebErrors validateSubmit(String username, String email,
			String password, String captcha, CmsSite site,
			HttpServletRequest request, HttpServletResponse response) {
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		WebErrors errors = WebErrors.create(request);
		if(!captcha.equals("")){
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
		
		List<UnifiedUser> user=unifiedUserMng.getByEmail(email);
		//邮箱被使用
		if(user!=null&&user.size()>0){
			errors.addErrorString("邮箱已经被使用！");
			return errors;
		}
		
		if (errors.ifOutOfLength(username, "用户名",
				mcfg.getUsernameMinLen(), 100)) {
			return errors;
		}
		if(!password.equals("")){
			if (errors.ifOutOfLength(password, "密码",
					mcfg.getPasswordMinLen(), 100)) {
				return errors;
			}
		}
		if(errors.ifNotEmail(email,"邮箱", 100)){
			return errors;
		}
		if (errors.ifMaxLength(email, "邮箱", 100)) {
			return errors;
		}
		// 保留字检查不通过，返回false。
		if (!mcfg.checkUsernameReserved(username)) {
			errors.addErrorCode("error.usernameReserved");
			return errors;
		}
		return errors;
	}

	private WebErrors validateActive(String username, String activationCode,
			HttpServletRequest request, HttpServletResponse response) {
		WebErrors errors = WebErrors.create(request);
		if (StringUtils.isBlank(username)
				|| StringUtils.isBlank(activationCode)) {
			errors.addErrorCode("error.exceptionParams");
			return errors;
		}
		UnifiedUser user = unifiedUserMng.getByUsername(username);
		if (user == null) {
			errors.addErrorCode("error.usernameNotExist");
			return errors;
		}
		if (user.getActivation()
				|| StringUtils.isBlank(user.getActivationCode())) {
			errors.addErrorCode("error.usernameActivated");
			return errors;
		}
		if (!user.getActivationCode().equals(activationCode)) {
			errors.addErrorCode("error.exceptionActivationCode");
			return errors;
		}
		return errors;
	}

	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private ConfigMng configMng;
	@Autowired
	private AuthenticationMng authMng;
}

