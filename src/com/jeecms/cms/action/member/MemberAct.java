package com.jeecms.cms.action.member;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.lang.math.NumberUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.entity.main.AcUserFriend;
import com.jeecms.cms.entity.main.AcUserSay;
import com.jeecms.cms.entity.main.AcUserTeam;
import com.jeecms.cms.entity.main.AcUserTeamExt;
import com.jeecms.cms.entity.main.AcUserTeamFans;
import com.jeecms.cms.entity.main.AcUserTeamFlag;
import com.jeecms.cms.entity.main.AcUserTeamMember;
import com.jeecms.cms.entity.main.AcUserTeamTag;
import com.jeecms.cms.entity.main.ActivityTag;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.CmsUserExt;
import com.jeecms.cms.entity.main.MemberConfig;
import com.jeecms.cms.entity.main.TagToActivity;
import com.jeecms.cms.manager.main.AcUserFriendMng;
import com.jeecms.cms.manager.main.AcUserSayMng;
import com.jeecms.cms.manager.main.AcUserTeamExtMng;
import com.jeecms.cms.manager.main.AcUserTeamFansMng;
import com.jeecms.cms.manager.main.AcUserTeamMemberMng;
import com.jeecms.cms.manager.main.AcUserTeamMng;
import com.jeecms.cms.manager.main.AcUserTeamTagMng;
import com.jeecms.cms.manager.main.CmsUserExtMng;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.cms.web.WebErrors;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.image.ImageUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * 会员中心Action
 * 
 * @author liufang
 * 
 */
@Controller
public class MemberAct {
	private static final Logger log = LoggerFactory.getLogger(MemberAct.class);

	public static final String MEMBER_CENTER = "tpl.memberCenter";
	public static final String MEMBER_PROFILE = "tpl.memberProfile";
	public static final String MEMBER_PORTRAIT = "tpl.memberPortrait";
	public static final String MEMBER_PASSWORD = "tpl.memberPassword";
	private static String PRE_PATH="/WEB-INF/t/cms/www/red/member/";

	/**
	 * 会员中心页
	 * 
	 * 如果没有登录则跳转到登陆页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/index.jspx", method = RequestMethod.GET)
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_CENTER);
	}

	/**
	 * 个人资料输入页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/profile.jspx", method = RequestMethod.GET)
	public String profileInput(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_PROFILE);
	}
	
	/**
	 * 更换头像
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/changephoto.jspx", method = RequestMethod.GET)
	public String changePhoto(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		return PRE_PATH+"会员头像修改页.html";
	}
	
	/**
	 * 个人信息完善页入口
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/portrait.jspx", method = RequestMethod.GET)
	public String portrait(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		return PRE_PATH+"会员信息完善修改页.html";
	}
	/**
	 * 个人信息完善页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/member/infoimprove.jspx", method = RequestMethod.POST)
	public String improveSubmit(String userimg,String nextUrl,
			Integer x1,Integer y1,Integer w,Integer h,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,Integer schoolId) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		CmsUserExt ext=user.getUserExt();
		FrontUtils.frontData(request, model, site);
		if(user == null){
			return FrontUtils.showLogin(request, model, site);
		}
		
		//文件保存目录路径
		String savePath = request.getSession().getServletContext().getRealPath("/");
		String orgfilepath=savePath+userimg;
		File file=new File(orgfilepath);
		String newFilePath=orgfilepath.substring(0,orgfilepath.indexOf("origin/"))+file.getName();
		String thumbFilePath=orgfilepath.substring(0,orgfilepath.indexOf("origin/"))+"/thumb/"+file.getName();
		String circleFilePath=orgfilepath.substring(0,orgfilepath.indexOf("origin/"))+"/circle/"+file.getName();
		String cornerFilePath=orgfilepath.substring(0,orgfilepath.indexOf("origin/"))+"/corner/"+file.getName();
		//判断当前路径是否存在 不存在则创建
		File thumbFile=new File(thumbFilePath);
		File circleFile=new File(circleFilePath);
		File cornerFile=new File(cornerFilePath);
		if (!thumbFile.getParentFile().exists()) {
			thumbFile.getParentFile().mkdirs();
		}
		if (!circleFile.getParentFile().exists()) {
			circleFile.getParentFile().mkdirs();
		}
		if (!cornerFile.getParentFile().exists()) {
			cornerFile.getParentFile().mkdirs();
		}
		
		//裁剪图片
		BufferedImage cutImage=ImageUtils.imgCut(orgfilepath,newFilePath,x1,y1,w,h);
		//如果图片宽高大于100则将图片缩小
		if(cutImage.getWidth()>100){
			Thumbnails.of(newFilePath).size(100,100).toFile(thumbFilePath);
		}else{
			//不缩小写入缩略图文件夹里
			ImageIO.write(cutImage,"jpg",new File(thumbFilePath));
		}
		//制作图片圆角以及圆形图片
		BufferedImage icon = ImageIO.read(new File(thumbFilePath));
		BufferedImage rounded = ImageUtils.makeRoundedCorner(icon, 20);
        BufferedImage roundedA = ImageUtils.makeRoundedCorner(icon, 1000);
        ImageIO.write(rounded, "png", new File(cornerFilePath));
        ImageIO.write(roundedA, "png", new File(circleFilePath));
		
        if(schoolId!=null){
        	user.setSchoolId(schoolId);
        } 
		ext.setUserImg(userimg);
		cmsUserExtMng.update(ext, user);
		log.info("update CmsUserExt success. id={}", user.getId());
		return "redirect:/index.jhtml";
	}

	/**
	 * 个人资料提交页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/member/profile.jspx", method = RequestMethod.POST)
	public String profileSubmit(CmsUserExt ext, String nextUrl,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,Integer schoolId) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		user.setSchoolId(schoolId);
		ext.setId(user.getId());
		cmsUserExtMng.update(ext, user);
		log.info("update CmsUserExt success. id={}", user.getId());
		return FrontUtils.showSuccess(request, model, nextUrl);
	}

	/**
	 * 密码修改输入页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/pwd.jspx", method = RequestMethod.GET)
	public String passwordInput(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		return FrontUtils.getTplPath(request, site.getSolutionPath(),
				TPLDIR_MEMBER, MEMBER_PASSWORD);
	}

	/**
	 * 密码修改提交页
	 * 
	 * @param origPwd
	 *            原始密码
	 * @param newPwd
	 *            新密码
	 * @param email
	 *            邮箱
	 * @param nextUrl
	 *            下一个页面地址
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/member/pwd.jspx", method = RequestMethod.POST)
	public String passwordSubmit(String origPwd, String newPwd, String email,
			String nextUrl, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		MemberConfig mcfg = site.getConfig().getMemberConfig();
		// 没有开启会员功能
		if (!mcfg.isMemberOn()) {
			return FrontUtils.showMessage(request, model, "member.memberClose");
		}
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		WebErrors errors = validatePasswordSubmit(user.getId(), origPwd,
				newPwd, email, request);
		if (errors.hasErrors()) {
			return FrontUtils.showError(request, response, model, errors);
		}
		cmsUserMng.updatePwdEmail(user.getId(), newPwd, email);
		return FrontUtils.showSuccess(request, model, nextUrl);
	}

	/**
	 * 团队创建输入页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/createteam.jspx", method = RequestMethod.GET)
	public String teamCreateInput(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);

		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		
		return PRE_PATH+"会员创建团队页.html";
	}
	/**
	 * 团队创建保存页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/createteam.jspx", method = RequestMethod.POST)
	public String teamCreateSave(HttpServletRequest request,Integer applyAllow,
			HttpServletResponse response, ModelMap model,String tags,String userids,String canpubs,
			String dutys,String teamName,String teamlogo,String teamdesc,String createuserduty,String captcha) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);

		if (!imageCaptchaService.validateResponseForID(session
				.getSessionId(request, response), captcha)) {
			model.addAttribute("error","验证码错误！");
			return teamCreateInput(request,response,model);
		}
		if(isTeamNameExist(teamName)){
			model.addAttribute("error","当前团队名已经存在！");
			return teamCreateInput(request,response,model);
		}
		
		if (user == null) {
			return FrontUtils.showLogin(request, model, site);
		}
		
		AcUserTeam team=new AcUserTeam();
		AcUserTeamExt teamExt=new AcUserTeamExt();
		AcUserTeamFlag teamFlag=new AcUserTeamFlag();
		
		//初始化团队
		team.init();
		teamExt.init();
		team.setTeamName(teamName);
		team.setTeamCreateuser(user.getId());
		team.setTeamCreateuserimg(user.getUserImg());
		team.setTeamCreateusername(user.getUsername());
		team.setTeamSchoolid(user.getSchoolId());
		team.setTeamCreateuserduty(createuserduty);
		team.setTeamApplyallow(applyAllow);
		teamExt.setTeamDesc(teamdesc);
		teamExt.setTeamLogo(teamlogo);
		
		teamExt.setAcUserTeam(team);
		team.setAcUserTeamExt(teamExt);
		teamFlag.setAcUserTeam(team);
		team.setAcUserTeamFlag(teamFlag);
		
		String teamTags="";
		//存入标签
		//如果上传有标签 1:先判断标签库中是否存在 如存在则直接引用 且让这标签热度+1
		//2：如标签不存在 则先将标签插入标签库 再引用该标签
		if(tags!=null&&!tags.equals("")){
			String []tagtmp=tags.split(",");
			for(String tag:tagtmp){		
				//从标签库中查询相同的标签
				AcUserTeamTag at=teamTagMng.findByName(tag);
				//标签存在
				if(at!=null){
					at.setTagHot(at.getTagHot()+1);

					//存入到临时标签中
					teamTags+=at.getId()+",";
				}else{
					//插入标签库
					at=new AcUserTeamTag();
					at.setTagCreatedate(new Timestamp(System.currentTimeMillis()));
					at.setTagName(tag);
					at.setTagHot(0);
					at.setTagUserid(user.getId());
					teamTagMng.save(at);
					//存入到临时标签中
					teamTags+=at.getId()+",";
				}
			}
		}
		if(!teamTags.equals("")){
			teamTags=teamTags.substring(0,teamTags.length()-1);
			teamExt.setTeamTag(teamTags);
		}
		
		//保存
		teamMng.save(team);
		teamExtMng.save(teamExt);
		
		//存入当前团队的成员
		String []ids=userids.split(",");
		String []ispubs=canpubs.split(",");
		String []userdutys=dutys.split(",");
		for(int i=0;i<ids.length;i++){
			AcUserTeamMember am=new AcUserTeamMember();
			am.getUUID();
			am.setAutState(AcUserTeamMember.MEMBER_STATE_SENDING);
			am.setAutJointime(new Timestamp(System.currentTimeMillis()));
			am.setAutCanpub(Integer.valueOf(ispubs[i]));
			am.setAutDuty(userdutys[i]);
			am.setAutUserid(Integer.valueOf(ids[i]));
			am.setAutSendingtime(new Timestamp(System.currentTimeMillis()));
			//查询当前用户设置其姓名和头像到成员中
			CmsUser tmpuser=cmsUserMng.findById(Integer.valueOf(ids[i]));
			am.setAutUsername(tmpuser.getUsername());
			am.setAutUserimg(tmpuser.getUserImg());
			am.setAutTeamid(team.getId());
			
			//保存当前用户
			teamMemberMng.save(am);
			
			//将邀请入团的消息发送给受邀用户
			Integer sendingFlag=tmpuser.getTeamsendFlag();
			if(sendingFlag==null){
				sendingFlag=1;
			}else{
				sendingFlag++;
			}
			tmpuser.setTeamsendFlag(sendingFlag);
			Updater<CmsUser> updater=new Updater<CmsUser>(tmpuser);
			cmsUserMng.updateByUpdater(updater);
		}
		
		return PRE_PATH+"会员创建团队页.html";
	}

	/**
	 * ajax更新当前团队
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/teamupdate.jspx")
	public void ajaxTeamUpdate(HttpServletRequest request,HttpServletResponse response, ModelMap model,Integer id,
			String createDuty,String memberids,String memberdutys,String teaamdesc,String teamlogo) {
		try{
			CmsUser user = CmsUtils.getUser(request);
			PrintWriter write=response.getWriter(); 
			if(user==null){
				//0 用户未登录
				write.print(0);
			}else{ 
				//查找该团队
				AcUserTeam team = teamMng.findById(id);
				if(user.getId().equals(team.getTeamCreateuser())){
					team.setTeamCreateuserduty(createDuty);
					AcUserTeamExt teamExt=team.getAcUserTeamExt();
					teamExt.setTeamDesc(teaamdesc);
					teamExt.setTeamLogo(teamlogo);
					team.setAcUserTeamExt(teamExt);
					team.setTeamUpdatetime(new Timestamp(System.currentTimeMillis()));
					
					String []memberidsarr=memberids.split(",");
					String []memberdutysarr=memberdutys.split(",");
					for(int i=0;i<memberidsarr.length;i++){
						String memberid=memberidsarr[i];
						String memberduty=memberdutysarr[i];
						AcUserTeamMember member=teamMemberMng.findById(memberid);
						member.setAutDuty(memberduty);
						Updater<AcUserTeamMember> updaterMember=new Updater<AcUserTeamMember>(member);
						teamMemberMng.updateByUpdater(updaterMember);
					}
					//1 更新成功
					write.print(1);
				}else{
					//2 你没用权限修改该团队
					write.print(2);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ajax当前用户受邀请信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/acceptconfirm.jspx")
	public void ajaxAcceptConfirm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try{
			CmsUser user = CmsUtils.getUser(request);
			PrintWriter write=response.getWriter(); 
			JSONObject json=new JSONObject();
			if(user==null){
				//0 用户未登录
				json.put("error", 0);
				write.print(json);
			}else{ 
				Updater<CmsUser> updater=new Updater<CmsUser>(user);
				cmsUserMng.updateByUpdater(updater);
				//查找当前用户的受邀信息
				Pagination p=teamMemberMng.getPaginationByProperities(new String[]{"AutUserid","AutState"},new Object[]{user.getId(),AcUserTeamMember.MEMBER_STATE_SENDING},1,100);
				if(p.getList()!=null&&p.getList().size()>0){
					List list=p.getList();
					List<JSONObject> jsonlist=new ArrayList<JSONObject>();
					for(int i=0;i<list.size();i++){
						json=new JSONObject();
						AcUserTeamMember atm=(AcUserTeamMember)list.get(i);
						//查找邀请团队
						AcUserTeam at=teamMng.findById(atm.getAutTeamid());
						if(at!=null){
							json.put("teamname",URLEncoder.encode(at.getTeamName(),"UTF-8"));
							json.put("createusername",URLEncoder.encode(at.getTeamCreateusername(),"UTF-8"));
							json.put("createuser",at.getTeamCreateuser());
							json.put("sendingtime", atm.getAutSendingtime());
							json.put("userduty",URLEncoder.encode(atm.getAutDuty(),"UTF-8"));
							json.put("teamlogo", at.getAcUserTeamExt().getTeamLogo());
							json.put("teamid", at.getId());
							jsonlist.add(json);
						}else{
							//2 当前团队异常或者已被删除
							json.put("error",2);
							write.print(json);
							break;
						}
					}
					write.print(jsonlist);
				}else{
					//1 未找到该用户相关邀请信息
					json.put("error",1);
					write.print(json);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ajax当前用户接受邀请
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/acceptinvite.jspx")
	public void ajaxAcceptInvite(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Integer teamid) {
		try{
			CmsUser user = CmsUtils.getUser(request);
			PrintWriter write=response.getWriter(); 
			if(user==null){
				//0 用户未登录
				write.print(0);
			}else{ 
				//查找当前用户的受邀信息
				Pagination p=teamMemberMng.getPaginationByProperities(new String[]{"AutUserid","AutState","AutTeamid"},new Object[]{user.getId(),AcUserTeamMember.MEMBER_STATE_SENDING,teamid},1,100);
				if(p.getList()!=null&&p.getList().size()>0){
					AcUserTeamMember atm=(AcUserTeamMember)p.getList().get(0);
					//将其设置为正常状态
					atm.setAutState(AcUserTeamMember.MEMBER_STATE_NORMAL);
					atm.setAutJointime(new Timestamp(System.currentTimeMillis()));
					atm.setAutUserimg(user.getUserImg());
					
					//更新该成员
					Updater<AcUserTeamMember> updaterAtm=new Updater<AcUserTeamMember>(atm);
					teamMemberMng.updateByUpdater(updaterAtm);
					
					//更新用户消息标识
					if(user.getTeamsendFlag()>0){
						Integer nowflag=user.getTeamsendFlag()-1;
						user.setTeamsendFlag(nowflag);
						Updater<CmsUser> updater=new Updater<CmsUser>(user);
						cmsUserMng.updateByUpdater(updater);
					}
					//2 更新成功
					write.print(2);
				}else{
					//1 对不起 未找到该团队 向你发送的邀请
					write.print(1);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ajax判断当前团队名是否存在
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/valiteamname.jspx")
	public void ajaxValiTeamName(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String teamName) {
		try{
			CmsUser user = CmsUtils.getUser(request);
			PrintWriter write=response.getWriter(); 
			if(user==null){
				//0 用户未登录
				write.print(0);
			}else{ 
				if(!teamName.equals("")){
					teamName=URLDecoder.decode(teamName, "UTF-8");
					if(isTeamNameExist(teamName)){
						//1 存在
						write.print(1);
					}else{
						//3 OK
						write.print(3);
					}
				}else{
					//2 团队名为空
					write.print(2);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//当前团队名是否存在
	private boolean isTeamNameExist(String teamname){
		Pagination p=teamMng.getPaginationByProperities(new String[]{"TeamName"},new Object[]{teamname},1,100);
		if(p.getList()!=null&&p.getList().size()>0){
			return true;
		}
		return false;
	}

	/**
	 * ajax用户说说
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/usersay.jspx", method = RequestMethod.POST)
	public void ajaxUserSay(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,String content) {
		try{
			PrintWriter write=response.getWriter(); 
			content=URLDecoder.decode(content, "UTF-8");
			CmsUser user = CmsUtils.getUser(request);
			if(user==null){
				//0 用户未登录 
				write.print(0);
			}else{
				//更新当前用户个性签名
				user.getUserExt().setUserSignature(content);
				//插入一条说说
				AcUserSay say=new AcUserSay();
				say.getUUID();
				say.setSayUser(user.getId());
				say.setSayContent(content); 
				sayMng.save(say);
				//1 发布成功
				write.print(1);
			}
		}catch(Exception e) { 
			e.printStackTrace();
		}
	}
	 
	/**
	 * ajax用户查询
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/usersearch.jspx", method = RequestMethod.GET)
	public void ajaxUserSearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Integer schoolid,String username,Integer gender,Integer pageNo) {
		try{
			PrintWriter write=response.getWriter();
			JSONObject json=new JSONObject(); 
			username=URLDecoder.decode(username, "UTF-8");
			CmsUser user = CmsUtils.getUser(request);
			if(user==null){
				//0 用户未登录
				json.put("error","0");
				write.print(json.toString());
			}else{
				List<JSONObject> jsonList=new ArrayList<JSONObject>();
				List userList=null;
				if(pageNo==null){
					pageNo=1;
				}
				if(schoolid!=null){
					userList=cmsUserMng.getPaginationByProperities(new String[]{"username","schoolId"},new Object[]{username,schoolid},pageNo,100).getList();
				}else{	
					userList=cmsUserMng.getPaginationByProperities(new String[]{"username"},new Object[]{username},pageNo,100).getList();
				}
				//如果用户列表不为空则返回
				if(userList!=null&&userList.size()>0){
					for(int i=0;i<userList.size();i++){
						CmsUser tmpuser=(CmsUser)userList.get(i);
						if(!user.getId().equals(tmpuser.getId())){
							json=new JSONObject();
							json.put("userimg", tmpuser.getUserImg());
							json.put("username",URLEncoder.encode(tmpuser.getUsername(),"UTF-8"));
							json.put("userid", tmpuser.getId());
							jsonList.add(json);
						}
					}
					if(jsonList.size()>0){
						write.print(jsonList);
					}else{
						//1 未找到相关用户
						json.put("error","1");
						write.print(json.toString());
					}
				}else{
					//1 未找到相关用户
					json.put("error","1");
					write.print(json.toString());
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ajax用户好友申请查询
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/friendapplyinfo.jspx")
	public void ajaxUserFriendApplyInfo(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try{
			PrintWriter write=response.getWriter();
			JSONObject json=new JSONObject();  
			CmsUser user = CmsUtils.getUser(request);
			if(user==null){
				//0 用户未登录
				json.put("error","0");
				write.print(json.toString());
			}else{
				List<JSONObject> jsonList=new ArrayList<JSONObject>();
				//查找是否有用户申请
				Pagination receiveP=friendMng.getPaginationByProperities(new String[]{"AufReceiveuser","AufState"}
						,new Object[]{user.getId(),AcUserFriend.STATE_APPLYING},1,5);
				if(receiveP.getList()!=null&&receiveP.getList().size()>0){
					//查找申请用户相关信息
					for(int i=0;i<receiveP.getList().size();i++){
						AcUserFriend friend=(AcUserFriend)receiveP.getList().get(i);
						CmsUser applyUser=cmsUserMng.findById(friend.getAufApplyuser());
						json=new JSONObject();
						json.put("friendid",friend.getId());
						json.put("username",URLEncoder.encode(applyUser.getUsername(),"UTF-8"));
						json.put("userid", applyUser.getId());
						json.put("userimg", applyUser.getUserImg());
						jsonList.add(json);
					} 
					if(jsonList.size()>0){
						write.print(jsonList);
					}
				}else{
					//1 未找到好友申请
					json.put("error","1");
					write.print(json.toString());
				} 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ajax好友申请
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/ajaxfriendapply.jspx", method = RequestMethod.POST)
	public void ajaxFriendApply(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Integer applyUserId) {
		try{
			PrintWriter write=response.getWriter();  
			CmsUser user = CmsUtils.getUser(request);
			if(user==null){
				//0 用户未登录 
				write.print(0);
			}else{
				 if(applyUserId!=null){
					 //判断是否已经是邀请过好友
					 Pagination friendp=friendMng.getPaginationByProperities(new String[]{"AufReceiveuser","AufApplyuser"}
						,new Object[]{applyUserId,user.getId()},1,1);
					 if(friendp.getList()!=null&&friendp.getList().size()>0){
						//4 好友申请已经发送了，请等待确认
						 write.print(4);
					 }else{
						 if(applyUserId.equals(user.getId())){
							 //3 不能添加自己为好友
							 write.print(3);
						 }else{
							 AcUserFriend friend=new AcUserFriend();
							 friend.getUUID();
							 friend.setAufApplyuser(user.getId());
							 friend.setAufReceiveuser(applyUserId);
							 friend.setAufState(AcUserFriend.STATE_APPLYING);
							 friend.setAufCreatetime(new Timestamp(System.currentTimeMillis()));
							 //将好友申请插入
							 friendMng.save(friend);
							 //更新当前用户添加好友标识
							 CmsUser friendUser=cmsUserMng.findById(applyUserId);
							 Integer friendflag=friendUser.getAddfriendFlag();
							 if(friendflag!=null){
								 friendflag++;
								 friendUser.setAddfriendFlag(friendflag);
							 }else{
								 friendUser.setAddfriendFlag(1);
							 }
							 Updater<CmsUser> updater=new Updater<CmsUser>(friendUser);
							 cmsUserMng.updateByUpdater(updater);
							 
							 //1 申请成功 等待验证
							 write.print(1);
						 }
					 }
				 }else{
					//2 传入要申请好友的用户ID
					write.print(2);
				 }
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * ajax好友申请接受
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/ajaxfriendaccept.jspx", method = RequestMethod.POST)
	public void ajaxFriendAccept(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Integer applyUserId) {
		try{
			PrintWriter write=response.getWriter();  
			CmsUser user = CmsUtils.getUser(request);
			if(user==null){
				//0 用户未登录 
				write.print(0);
			}else{
				 if(applyUserId!=null){
					 Pagination p=friendMng.getPaginationByProperities(new String[]{"AufReceiveuser","AufApplyuser","AufState"}
					 	,new Object[]{user.getId(),applyUserId,AcUserFriend.STATE_APPLYING},1,1);
					 
					 if(p.getList()!=null&&p.getList().size()>0){
						 AcUserFriend friend=(AcUserFriend)p.getList().get(0);
						 friend.setAufState(AcUserFriend.STATE_ACCEPTING);
						 friend.setAufAccepttime(new Timestamp(System.currentTimeMillis()));
						 Updater<AcUserFriend> updater=new Updater<AcUserFriend>(friend);
						 //更新当前用户状态
						 if(user.getAddfriendFlag()!=null&&user.getAddfriendFlag()>0){
							 user.setAddfriendFlag(user.getAddfriendFlag()-1);
						 }else{
							 user.setAddfriendFlag(0);
						 }
						 
						 friendMng.updateByUpdater(updater);
						 
						//1 接受成功
						write.print(1);
					 }else{
						//2 好友申请不存在
						write.print(2);
					 }
					 
				 }else{
					//2 传入要申请好友的用户ID
					write.print(2);
				 }
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ajax关注团队
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/ajaxattendteam.jspx", method = RequestMethod.POST)
	public void ajaxAttendTeam(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Integer teamId) {
		try{
			PrintWriter write=response.getWriter();  
			CmsUser user = CmsUtils.getUser(request);
			if(user==null){
				//0 用户未登录 
				write.print(0);
			}else{ 
				AcUserTeam team=teamMng.findById(teamId);
				if(team!=null){
					if(user.getId().equals(team.getTeamCreateuser())){
						//3 不能关注自己创建的团队
						write.print(3);
					}else{
						List<AcUserTeamFans> fansList=teamFansMng.getListByProperities(new String[]{"acf_teamid","acf_userid"},
									new Object[]{teamId,user.getId()});
						if(fansList!=null&&fansList.size()>0){
							//4 已经关注过了
							write.print(4);
						}else{
							AcUserTeamFans fans=new AcUserTeamFans();
							fans.getUUID();
							fans.setAcfTeamid(teamId);
							fans.setAcfUserid(user.getId());
							fans.setAcfUserimg(user.getUserImg());
							fans.setAcfUsername(user.getUsername());
							//更新当前团队粉丝数
							if(team.getAcUserTeamExt().getTeamFans()!=null){
								Integer teamFans=team.getAcUserTeamExt().getTeamFans();
								teamFans++;
								team.getAcUserTeamExt().setTeamFans(teamFans);
							}else{
								  team.getAcUserTeamExt().setTeamFans(0);
							} 
							teamFansMng.save(fans);
							//1 成功
							write.print(1);
						} 
					} 
				}else{
					//2 团队不存在
					write.print(2);
				} 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ajax给团队献花 或者砸蛋
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/ajaxflowerteam.jspx", method = RequestMethod.POST)
	public void ajaxFlowerTeam(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Integer teamId,Integer action) {
		try{
			PrintWriter write=response.getWriter();  
			CmsUser user = CmsUtils.getUser(request);
			if(user==null){
				//0 用户未登录 
				write.print(0);
			}else{ 
				AcUserTeam team=teamMng.findById(teamId);
				if(team!=null){
					if(user.getId().equals(team.getTeamCreateuser())){
						//3 不能自己创建的团队
						write.print(3);
					}else{ 
						Cookie cookie = CookieUtils.getCookie(request,"action");
						Integer error=0;
						if (cookie != null) {
							String value = cookie.getValue(); 
							//如果已经执行过一次这样的操作 则禁止
							if(value.equals("1")){
								error=1;
							}
						}
						if(error.equals(1)){
							//4 团队不存在
							write.print(4);
						}else{
							//献花
							if(action.equals(1)){
								if(team.getAcUserTeamExt().getTeamFlowers()!=null){
									Integer num=team.getAcUserTeamExt().getTeamFlowers();
									num++;
									team.getAcUserTeamExt().setTeamFlowers(num);
								}else{
									team.getAcUserTeamExt().setTeamFlowers(0);
								}  
							}else{
								//砸蛋
								if(team.getAcUserTeamExt().getTeamEggs()!=null){
									Integer num=team.getAcUserTeamExt().getTeamEggs();
									num++;
									team.getAcUserTeamExt().setTeamEggs(num);
								}else{
									team.getAcUserTeamExt().setTeamEggs(0);
								}  
							}
							//将操作写入cookie中
							CookieUtils.addCookie(request,response,"action",
									"1", 60 * 60, null); 
							Updater<AcUserTeam> updater=new Updater<AcUserTeam>(team);
							teamMng.updateByUpdater(updater);
							//1 成功
							write.print(1);
						} 
					} 
				}else{
					//2 团队不存在
					write.print(2);
				} 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ajax申请加入团队
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/member/ajaxattendteam.jspx", method = RequestMethod.POST)
	public void ajaxJoinTeam(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Integer teamId) {
		try{
			PrintWriter write=response.getWriter();  
			CmsUser user = CmsUtils.getUser(request);
			if(user==null){
				//0 用户未登录 
				write.print(0);
			}else{ 
				AcUserTeam team=teamMng.findById(teamId);
				if(team!=null){
					if(user.getId().equals(team.getTeamCreateuser())){
						//3 不能加入自己创建的团队
						write.print(3);
					}else{
						List<AcUserTeamMember> memberList=teamMemberMng.getListByProperities(new String[]{"AutTeamid","AutUserid"},
								new Object[]{teamId,user.getId()});
						if(memberList!=null&&memberList.size()>0){
							//4 已经加入了
							write.print(4);
						}else{
							AcUserTeamMember member=new AcUserTeamMember();
							member.getUUID();
							member.setAutSendingtime(new Timestamp(System.currentTimeMillis()));
							member.setAutCanpub(0);
							member.setAutDuty(""); 
							
							//1 成功
							write.print(1);
						} 
					} 
				}else{
					//2 团队不存在
					write.print(2);
				} 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 验证密码是否正确
	 * 
	 * @param origPwd
	 *            原密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/member/checkPwd.jspx")
	public void checkPwd(String origPwd, HttpServletRequest request,
			HttpServletResponse response) {
		CmsUser user = CmsUtils.getUser(request);
		boolean pass = cmsUserMng.isPasswordValid(user.getId(), origPwd);
		ResponseUtils.renderJson(response, pass ? "true" : "false");
	}

	private WebErrors validatePasswordSubmit(Integer id, String origPwd,
			String newPwd, String email, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifBlank(origPwd, "origPwd", 100)) {
			return errors;
		}
		if (errors.ifMaxLength(newPwd, "newPwd", 100)) {
			return errors;
		}
		if (errors.ifMaxLength(email, "email", 100)) {
			return errors;
		}
		if (!cmsUserMng.isPasswordValid(id, origPwd)) {
			errors.addErrorCode("member.origPwdInvalid");
			return errors;
		}
		return errors;
	}

	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private CmsUserExtMng cmsUserExtMng;
	@Autowired
	private AcUserFriendMng friendMng;
	@Autowired
	private AcUserSayMng sayMng;
	@Autowired
	private AcUserTeamMng teamMng;
	@Autowired
	private AcUserTeamExtMng teamExtMng;
	@Autowired
	private AcUserTeamFansMng teamFansMng;
	@Autowired
	private AcUserTeamMemberMng teamMemberMng;
	@Autowired
	private AcUserTeamTagMng teamTagMng;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private SessionProvider session;
}
