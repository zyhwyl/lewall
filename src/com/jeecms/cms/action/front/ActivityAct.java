package com.jeecms.cms.action.front;
 

import java.io.PrintWriter;
import java.io.Writer;
import java.net.URLEncoder; 
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList; 
import java.util.List; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.json.JSONObject; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
  
import com.hg.doc.at;
import com.jeecms.cms.entity.main.AcUserCheckin;
import com.jeecms.cms.entity.main.AcUserFriend;
import com.jeecms.cms.entity.main.AcUserTeam;
import com.jeecms.cms.entity.main.AcUserTeamMember;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityComment; 
import com.jeecms.cms.entity.main.ActivityJoin;
import com.jeecms.cms.entity.main.ActivityLive;
import com.jeecms.cms.entity.main.ActivityPicture; 
import com.jeecms.cms.entity.main.ActivitySchool;
import com.jeecms.cms.entity.main.ActivityTag;
import com.jeecms.cms.entity.main.ActivityWatch; 
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser; 
import com.jeecms.cms.entity.main.TagToActivity; 
import com.jeecms.cms.manager.main.AcUserCheckinMng;
import com.jeecms.cms.manager.main.AcUserFriendMng;
import com.jeecms.cms.manager.main.AcUserTeamMemberMng;
import com.jeecms.cms.manager.main.AcUserTeamMng;
import com.jeecms.cms.manager.main.ActivityCommentMng;
import com.jeecms.cms.manager.main.ActivityDistrickMng;
import com.jeecms.cms.manager.main.ActivityJoinMng;
import com.jeecms.cms.manager.main.ActivityLiveMng;
import com.jeecms.cms.manager.main.ActivityMng;
import com.jeecms.cms.manager.main.ActivityPictureMng;
import com.jeecms.cms.manager.main.ActivitySchoolMng;
import com.jeecms.cms.manager.main.ActivityTagMng;
import com.jeecms.cms.manager.main.ActivityTypeMng;
import com.jeecms.cms.manager.main.ActivityWatchMng; 
import com.jeecms.cms.manager.main.CmsUserMng; 
import com.jeecms.cms.manager.main.TagToActivityMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;
import com.jeecms.common.util.StrUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.web.front.URLHelper;
import com.jeecms.core.web.front.URLHelper.PageInfo;
import com.octo.captcha.service.image.ImageCaptchaService;

@Controller
public class ActivityAct {
	private static String PRE_PATH="/WEB-INF/t/cms/www/red/activity/";
	
	
	/**
	 * 发布活动入口
	 * */
	@RequestMapping(value = "/activity/save.jspx",method=RequestMethod.GET)
	public String pubActivityAccess(HttpServletRequest request,HttpServletResponse response, ModelMap model,Integer cityid){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		CmsUser user=CmsUtils.getUser(request);
		//当前用户是否登录
		if(user==null){
			model.addAttribute("fromurl","/activity/save.jspx?cityid="+cityid);
			return "/WEB-INF/t/cms/www/red/member/会员登录页.html";
		}
		
		//活动类型
		List activityType=typeMng.getPaginationByProperities(null, null,1, 100).getList();
		
		List districkList=districkMng.getPaginationByProperities(new String[]{"city_id"}, new Object[]{cityid},1, 1000).getList();
		if(districkList!=null&&districkList.size()>0){
			model.addAttribute("districkList", districkList);
			model.addAttribute("activityType", activityType);
		}else{
			model.addAttribute("error","参数异常!!!");
		}
		return PRE_PATH+"发布提交页.html";
	}
	
	/**
	 * 发布活动保存
	 * */
	@RequestMapping(value = "/activity/save.jspx",method=RequestMethod.POST)
	public String saveActivity(String imgpaths,String tags,String txt,Integer acCategory
			,Timestamp beginDate,Integer signMax,Integer signMin,Integer scid,Integer isshow,String acContactmethod,
			Integer acTeamid,Integer duration,String captcha,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user=CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);

		//标签集合
		List<TagToActivity> ttas=new ArrayList<TagToActivity>();
		Activity activity=new Activity();
		//初始化固定值字段
		activity.init();
		
		activity.setAcTxt(txt);
		activity.setAcBegindate(beginDate);
		if(signMax!=null){
			activity.setAcSignmax(signMax);
		}
		if(signMin!=null){
			activity.setAcSignmin(signMin);
		}
		activity.setAcUserid(user.getId());
		activity.setAcCategory(acCategory);
		activity.setAcSchool(scid);
		activity.setAcContactmethod(acContactmethod);
		activity.setAcIsshow(isshow);
		activity.setAcTeamid(acTeamid);
		activity.setAcDuration(duration);
		if(acTeamid!=null&&acTeamid.equals("")){
			activity.setAcTeamid(acTeamid);
		}
		
		activityMng.save(activity);
		
		//如果有图片则将图片存入
		if(imgpaths!=null&&!imgpaths.equals("")){
			imgpaths=imgpaths.substring(0,imgpaths.length()-1);
			String []imgpath=imgpaths.split(",");
			for(String img:imgpath){
				ActivityPicture ap=new ActivityPicture();
				ap.setApImgpath(img);
				ap.getUUID(); 
				ap.setAc(activity);
				ap.setAcId(activity.getId());
				activityPictureMng.save(ap);
			} 
		}
		
		//如果上传有标签 1:先判断标签库中是否存在 如存在则直接引用 且让这标签热度+1
		//2：如标签不存在 则先将标签插入标签库 再引用该标签
		if(tags!=null&&!tags.equals("")){
			String []tagtmp=tags.split(",");
			for(String tag:tagtmp){		
				//从标签库中查询相同的标签
				ActivityTag at=activityTagMng.findByName(tag);
				//标签存在
				if(at!=null){
					at.setTagHot(at.getTagHot()+1);
					TagToActivity tta=new TagToActivity();
					tta.setAc(activity);
					tta.setTag(at);
					tta.getUUID();
					ttas.add(tta);
					//存入标签对应关系
					tagMng.save(tta);
				}else{
					//插入标签库
					at=new ActivityTag();
					at.setTagCreatedate(new Timestamp(System.currentTimeMillis()));
					at.setTagName(tag);
					at.setTagHot(0);
					at.setTagUserid(user.getId());
					activityTagMng.save(at);
					//插入标签对应关系
					TagToActivity tta=new TagToActivity();
					tta.setAc(activity);
					tta.setTag(at);
					tta.getUUID();
					ttas.add(tta); 
					tagMng.save(tta);
				}
			}
		}
		activity.setTagToActivities(ttas); 
		return PRE_PATH+"发布结果页.html";
	}

	/**
	 * 活动墙入口
	 * */
	@RequestMapping(value = "/activity/index/*.jhtml",method=RequestMethod.GET)
	public String activityAccess(HttpServletRequest request,HttpServletResponse response,
			ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		CmsUser user=CmsUtils.getUser(request);
		String[] paths = URLHelper.getPaths(request);

		Integer schoolId=Integer.valueOf(paths[2]);
		
		List list=activityMng.getPaginationByProperities(new String[]{"AcSchool"}, new Object[]{schoolId}
				,1,15,"AcCreatedate DESC").getList(); 
		
		//得到活动图片、发布活动的用户、活动标签
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Integer acId=((Activity)list.get(i)).getId();
				Integer userId=((Activity)list.get(i)).getAcUserid();
				//图片
				List<String> pictureList=activityPictureMng.getListByProperities(new String[]{"ac_id"},new Object[]{acId});
				if(pictureList!=null&&pictureList.size()>0){
					((Activity)list.get(i)).setImgpaths(pictureList);
				}
				//用户
				CmsUser	acUser=userMng.findById(userId);
				((Activity)list.get(i)).setUser(acUser);
				//如果该活动团队ID不为空则是团队发布 查询出该团队
				if(((Activity)list.get(i)).getAcTeamid()!=null&&!((Activity)list.get(i)).getAcTeamid().equals("")){
					AcUserTeam team=teamMng.findById(((Activity)list.get(i)).getAcTeamid());
					((Activity)list.get(i)).setTeam(team);
				}
				
				//标签
				List<Integer> tagids=tagMng.getListByProperities(new String[]{"ac_id"},new Object[]{acId});
				List<ActivityTag> tags=new ArrayList<ActivityTag>();
				if(tagids!=null&&tagids.size()>0){
					for(Integer tagid:tagids){
						ActivityTag tag=activityTagMng.findById(tagid);
						if(tag!=null){
							tags.add(tag);
						}
					}
				}
				//获得该活动的评论
				Pagination comments=commentMng.getPaginationByProperities(new String[]{"AcmAcid"}, new Object[]{acId}
					, 1, 3, "AcmCreatetime desc");
				List commentlist=comments.getList();
				((Activity)list.get(i)).setCommentList(commentlist);
				
				//如果用户登录 判断当前用户是否参加以及围观此活动
				if(user!=null){
					//判断当前用户是否已经围观过
					Pagination awlist=watchMng.getPaginationByProperities(new String[]{"awActivityid","awUserid"}
								,new Object[]{acId,user.getId()},1,100);

					if(awlist.getList()!=null&&awlist.getList().size()>0){
						//1用户已经围观过
						((Activity)list.get(i)).setIsWatch(1);
					}
					//判断当前用户是否已经报过名
					Pagination ajlist=joinMng.getPaginationByProperities(new String[]{"AjActivityid","AjUserid"}
								,new Object[]{acId,user.getId()},1,100);

					if(ajlist.getList()!=null&&ajlist.getList().size()>0){
						((Activity)list.get(i)).setIsSign(1);
					}
				}
				
				//获得该活动的参与人数和围观人数
				Integer joinCount=joinMng.getCountByProperities("aj_activityid",((Activity)list.get(i)).getId());
				Integer watchCount=watchMng.getCountByProperities("aw_activityid", ((Activity)list.get(i)).getId());
				
				((Activity)list.get(i)).setWatchNum(watchCount);
				((Activity)list.get(i)).setJoinNum(joinCount);
				((Activity)list.get(i)).setTags(tags);
				//设置当前活动的所属列 共五列
				Integer nowPanel=i%5;
				((Activity)list.get(i)).setNowPanel(nowPanel);
			} 
		}
		
		model.addAttribute("list",list);
		model.addAttribute("schoolId",schoolId);
		
		return PRE_PATH+"活动墙.html";
	} 
	
	/**
	 * ajax滚屏加载获得数据
	 * */
	@RequestMapping(value = "/activity/ajaxactivity.jhtml",method=RequestMethod.GET)
	public void ajaxGetActivity(HttpServletRequest request,HttpServletResponse response,
					Integer page,Integer schoolId){ 
		try{
			Pagination p=activityMng.getPaginationByProperities(new String[]{"AcSchool"}, new Object[]{schoolId}
			,page,15);
			PrintWriter write=response.getWriter();
			if(page>p.getTotalPage()){
				JSONObject json=new JSONObject();
				json.put("nodate_error", 1);
				write.print(json.toString());
			}else{
				List list=p.getList(); 
				JSONObject activity=new JSONObject();
				List<JSONObject> activityList=new ArrayList<JSONObject>();
				//得到活动图片、发布活动的用户、活动标签
				if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
						Integer acId=((Activity)list.get(i)).getId();
						Integer userId=((Activity)list.get(i)).getAcUserid();
						//图片
						List<String> pictureList=activityPictureMng.getListByProperities(new String[]{"ac_id"},new Object[]{acId});
						if(pictureList!=null&&pictureList.size()>0){
							((Activity)list.get(i)).setImgpaths(pictureList);
						}
						//用户
						CmsUser	acUser=userMng.findById(userId);
						((Activity)list.get(i)).setUser(acUser);
						//标签
						List<Integer> tagids=tagMng.getListByProperities(new String[]{"ac_id"},new Object[]{acId});
						List<ActivityTag> tags=new ArrayList<ActivityTag>();
						if(tagids!=null&&tagids.size()>0){
							for(Integer tagid:tagids){
								ActivityTag tag=activityTagMng.findById(tagid);
								if(tag!=null){
									tags.add(tag);
								}
							}
						} 
						//设置当前活动的所属列 共五列
						Integer nowPanel=i%5;
						
						Activity tmp=(Activity)list.get(i);
						activity=new JSONObject();
						activity.put("pictureList",pictureList);
						activity.put("tags",tags);
						activity.put("id",tmp.getId());
						activity.put("nowPanel",nowPanel);
						activity.put("userImg",acUser.getUserExt().getUserImg());
						activity.put("userName",acUser.getUsername());
						activity.put("actxt",URLEncoder.encode(((Activity)list.get(i)).getAcTxt(),"UTF-8"));
						
						activityList.add(activity);
					} 
					 
					write.print(activityList);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * 活动报名入口
	 * @throws ParseException 
	 * */
	@RequestMapping(value = "/activity/sign.jspx",method=RequestMethod.GET)
	public String signActivityAccess(HttpServletRequest request,HttpServletResponse response, ModelMap model,Integer acId) throws ParseException{
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		CmsUser user=CmsUtils.getUser(request);
		//当前用户是否登录
		if(user==null){
			model.addAttribute("fromurl","/activity/sign.jspx?acId="+acId);
			return "/WEB-INF/t/cms/www/red/member/会员登录页.html";
		} 
		Activity activity=activityMng.findById(acId);
		if(activity!=null){
			//判断当前活动是否结束
			if(!activity.getAcState().equals(Activity.ACTIVITY_FINISHED)){
				Integer tanceToday=DateUtils.daysBetween(new java.util.Date(),activity.getAcBegindate());
				if(tanceToday>0){
					//活动还没开始
					activity.setAcState(Activity.ACTIVITY_PREPARE);
					activity.setBeginChinese(DateUtils.getDateTranslate(tanceToday));
				}else if(tanceToday.equals(0)){
					activity.setAcState(Activity.ACTIVITY_GOING);
					activity.setBeginChinese("还有"+activity.getAcDuration()+"天结束");
				}else{
					//如果开始时间加上持续时间大于等于0  则活动正在进行中 否则活动结束
					if(tanceToday+activity.getAcDuration()>=0){
						activity.setAcState(Activity.ACTIVITY_GOING);
						activity.setBeginChinese("还有"+tanceToday+activity.getAcDuration()+"天结束");
					}else{
						activity.setAcState(Activity.ACTIVITY_FINISHED);
					}
				}
				//判断当前活动是否超过人数限制
				Integer joinNum=joinMng.getCountByProperities(new String[]{"aj_activityid","aj_state"},new Object[]{activity.getId(),ActivityJoin.JOIN_IN});
				if(joinNum>=activity.getAcSignmax()){
					model.addAttribute("error", "对不起，该活动报名人数已满，如果发起人踢出某个用户，你还有机会哦！");
				}
			}
			model.addAttribute("activity", activity);
		}else{
			model.addAttribute("error", "对不起，该活动不存在或者已被删除！");
		} 
		return PRE_PATH+"活动报名提交页.html";
	}
	/**
	 * 活动详情入口
	 * @throws ParseException 
	 * */
	@RequestMapping(value = "/activity/detail_*.jhtml",method=RequestMethod.GET)
	public String detailActivityAccess(HttpServletRequest request,HttpServletResponse response, ModelMap model,Integer signsuccess) throws ParseException{
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user=CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		PageInfo info = URLHelper.getPageInfo(request);
		if(signsuccess!=null){
			model.addAttribute("signsuccess",signsuccess);
		}
		String activityHref=info.getHref();
		//获得活动ID
		activityHref=activityHref.replace(info.getHrefFormer(), "");
		activityHref=activityHref.replace(info.getHrefLatter(), "");
		Integer acId=Integer.valueOf(activityHref.substring(1));
		//取得活动直播
		List<ActivityLive> liveList=liveMng.getListByProperities(new String[]{"LiveActivity"},new Object[]{acId});
		
		Activity activity=activityMng.findById(acId);
		if(activity!=null){
			//图片
			List<String> pictureList=activityPictureMng.getListByProperities(new String[]{"ac_id"},new Object[]{acId});
			
			//用户
			CmsUser	acUser=userMng.findById(activity.getAcUserid());
			activity.setUser(acUser);
			//如果该活动团队ID不为空则是团队发布 查询出该团队
			if(activity.getAcTeamid()!=null&&!activity.getAcTeamid().equals("")){
				AcUserTeam team=teamMng.findById(activity.getAcTeamid());
				activity.setTeam(team);
			}
			//标签
			List<Integer> tagids=tagMng.getListByProperities(new String[]{"ac_id"},new Object[]{acId});
			List<ActivityTag> tags=new ArrayList<ActivityTag>();
			if(tagids!=null&&tagids.size()>0){
				for(Integer tagid:tagids){
					ActivityTag tag=activityTagMng.findById(tagid);
					if(tag!=null){
						tags.add(tag);
					}
				}
			}
			//该活动参与的用户
			List<ActivityJoin> joinList=joinMng.getListByProperities(new String[]{"AjActivityid","AjState"}, new Object[]{activity.getId(),ActivityJoin.JOIN_IN});
			//判断是否有团队参与
			for(ActivityJoin join:joinList){
				if(join.getAjTeam()!=null){
					//查找出此团队
					AcUserTeam team=teamMng.findById(join.getAjTeam());
					join.setJoinTeam(team);
				}
			}
			
			//判断该活动是否已经结束
			if(!activity.getAcState().equals(Activity.ACTIVITY_FINISHED)){
				Integer tanceToday=DateUtils.daysBetween(new java.util.Date(),activity.getAcBegindate());
				if(tanceToday>0){
					//活动还没开始
					activity.setAcState(Activity.ACTIVITY_PREPARE);
					activity.setBeginChinese(DateUtils.getDateTranslate(tanceToday));
				}else if(tanceToday.equals(0)){
					activity.setAcState(Activity.ACTIVITY_GOING);
					activity.setBeginChinese("还有"+activity.getAcDuration()+"天结束");
				}else{
					//如果开始时间加上持续时间大于等于0  则活动正在进行中 否则活动结束
					if(tanceToday+activity.getAcDuration()>=0){
						activity.setAcState(Activity.ACTIVITY_GOING);
						Integer left=tanceToday+activity.getAcDuration();
						activity.setBeginChinese("还有"+left+"天结束");
					}else{
						activity.setAcState(Activity.ACTIVITY_FINISHED);
					}
				}
			}
			
			//获得该活动的参与人数和围观人数
			Integer joinCount=joinMng.getCountByProperities("aj_activityid",activity.getId());
			Integer watchCount=watchMng.getCountByProperities("aw_activityid",activity.getId());
			
			//活动的评论用户
			List<ActivityComment> commentList=commentMng.getListByProperities(new String[]{"AcmAcid"}, new Object[]{activity.getId()},"AcmCreatetime");
			//整理出该活动属于回复的评论
			List<ActivityComment> responseList=new ArrayList<ActivityComment>();
			for(ActivityComment comment:commentList){
				if(comment.getAcmToUser()!=null&&!comment.getAcmToUser().equals("")){
					responseList.add(comment);
				}
			}
			
			//将一个用户的几条评论整理为一条记录
			List<ActivityComment> newCommentList=new ArrayList<ActivityComment>();
			boolean insertFlag=false;
			for(int j=0;j<commentList.size();j++){
				ActivityComment comment=commentList.get(j);
				for(int i=0;i<newCommentList.size();i++){
					//重复则将此评论的内容添加的新的评论中
					if(newCommentList.get(i).getAcmUserid().equals(comment.getAcmUserid())){
						newCommentList.get(i).setAcmText(newCommentList.get(i).getAcmText()+"|"+comment.getAcmText());
						insertFlag=true;
					}
				}
				if(insertFlag==false){
					newCommentList.add(comment);
				}
				insertFlag=false;
			}
			
			//活动的围观用户
			List<ActivityWatch> watchList=watchMng.getListByProperities(new String[]{"awActivityid"}, new Object[]{activity.getId()});
			for(int i=0;i<watchList.size();i++){
				watchList.get(i).setNowPanel(i%2);
				//如果该用户也有评论 则将评论插入到围观用户中并删除这条评论
				for(int j=0;j<newCommentList.size();j++){
					if(watchList.get(i).getAwUserid().equals(newCommentList.get(j).getAcmUserid())){
						watchList.get(i).setComment(newCommentList.get(j));
						newCommentList.remove(j);
					}
				}
			}
			
			//如果用户登录 判断当前用户是否参加以及围观此活动
			if(user!=null){
				//判断当前用户是否已经围观过
				Pagination awlist=watchMng.getPaginationByProperities(new String[]{"awActivityid","awUserid"}
							,new Object[]{acId,user.getId()},1,100);

				if(awlist.getList()!=null&&awlist.getList().size()>0){
					//1用户已经围观过
					activity.setIsWatch(1);
				}
				//判断当前用户是否已经报过名
				Pagination ajlist=joinMng.getPaginationByProperities(new String[]{"AjActivityid","AjUserid"}
							,new Object[]{acId,user.getId()},1,100);

				if(ajlist.getList()!=null&&ajlist.getList().size()>0){
					activity.setIsSign(1);
				}
			} 
			activity.setJoinNum(joinCount);
			activity.setWatchNum(watchCount);
			activity.setCommentList(newCommentList);
			activity.setJoinList(joinList);
			activity.setImgpaths(pictureList);
			activity.setWatchList(watchList);
			activity.setTags(tags);
			activity.setLiveList(liveList);
			model.addAttribute("activity", activity);
		}else{
			model.addAttribute("error", "对不起，该活动不存在或者已被删除！");
		}
		
		return PRE_PATH+"活动详细页.html";
	}
	
	
	/**
	 * 活动报名保存
	 * */
	@RequestMapping(value = "/activity/signsave.jspx",method=RequestMethod.GET)
	public String signActivitySave(HttpServletRequest request,HttpServletResponse response, ModelMap model
			,Integer acId,Integer teamId,Integer isanonymous,String words){
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user=CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		//当前用户是否登录
		if(user==null){
			model.addAttribute("fromurl","/activity/sign.jspx?acid="+acId);
			return "/WEB-INF/t/cms/www/red/member/会员登录页.html";
		}
		//判断当前用户是否已经报过名
		Pagination ajlist=joinMng.getPaginationByProperities(new String[]{"AjActivityid","AjUserid"}
		,new Object[]{acId,user.getId()},1,100);
		
		if(ajlist.getList()!=null&&ajlist.getList().size()>0){
			model.addAttribute("error", "对不起，该活动你已经报过名了，你可以到个人中心去取消你的报名！");
			return PRE_PATH+"活动报名提交页.html";
		}
		
		ActivityJoin aj=new ActivityJoin();
		//如果是团队报名 则判断下该团队是否已经报过名了
		if(teamId!=null){
			Pagination teamlist=joinMng.getPaginationByProperities(new String[]{"AjActivityid","AjTeam"}
			,new Object[]{acId,teamId},1,100);
			if(teamlist.getList()!=null&&teamlist.getList().size()>0){
				model.addAttribute("error", "对不起，此团队已经报过名了，报名的用户可以取消报名！");
				return PRE_PATH+"活动报名提交页.html";
			}else{
				aj.setAjTeam(teamId);
			}
		}
		Activity activity=activityMng.findById(acId);
		if(activity!=null){
			aj.getUUID();
			aj.setAjActivityid(acId);
			aj.setAjUserid(user.getId());
			aj.setAjJointime(new Timestamp(System.currentTimeMillis()));
			aj.setAjUsername(user.getUsername());
			aj.setAjUserimg(user.getUserImg());
			aj.setAjState(ActivityJoin.JOIN_IN);
			if(isanonymous!=null){
				aj.setAjIsanonymous(isanonymous);
			}else{
				aj.setAjIsanonymous(0);
			}
			if(words!=null){
				aj.setAjWords(words);
			}
			joinMng.save(aj); 
			model.addAttribute("activity", activity);
		}else{
			model.addAttribute("error", "对不起，该活动不存在或者已被删除！");
			return PRE_PATH+"活动报名提交页.html";
		}
		return "redirect:detail_"+acId+".jhtml?signsuccess=1";  
	}
	
	
	/**
	 * 活动直播保存
	 * */
	@RequestMapping(value = "/activity/livesave.jspx",method=RequestMethod.POST)
	public String liveActivitySave(HttpServletRequest request,HttpServletResponse response, ModelMap model
			,Integer acId,String imgpaths,String livetxt){
		CmsSite site = CmsUtils.getSite(request);
		CmsUser user=CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		if(!imgpaths.equals("")){
			imgpaths=imgpaths.substring(0,imgpaths.length()-1);
		}
		//当前用户是否登录
		if(user==null){
			model.addAttribute("fromurl","/activity/detail_"+acId+".jhtml");
			return "/WEB-INF/t/cms/www/red/member/会员登录页.html";
		}
		ActivityLive al=new ActivityLive();
		al.getUUID();
		al.setLiveActivity(acId);
		al.setLiveContent(livetxt);
		al.setLiveImgs(imgpaths);
		al.setLiveUser(user.getId());
		
		liveMng.save(al);
		return "redirect:../activity/detail_"+acId+".jhtml";
	}
	
	/**
	 * 活动围观保存
	 * */
	@RequestMapping(value = "/activity/watchsave.jspx")
	public void watchActivitySave(HttpServletRequest request,HttpServletResponse response, ModelMap model,
				Integer acId){
 
		try{
			CmsUser user=CmsUtils.getUser(request);
			Writer writer=response.getWriter();
			//当前用户是否登录
			if(user==null){
				//0  用户尚未登录
				writer.write("0");
			}else{
				//判断当前用户是否已经围观过
				Pagination awlist=watchMng.getPaginationByProperities(new String[]{"awActivityid","awUserid"}
							,new Object[]{acId,user.getId()},1,100);

				if(awlist.getList()!=null&&awlist.getList().size()>0){
					//1 用户已经围观过
					writer.write("1");
				}else{
					ActivityWatch aj=new ActivityWatch();

					Activity activity=activityMng.findById(acId);
					if(activity!=null){
						aj.getUUID();
						aj.setAwActivityid(acId);
						aj.setAwUserid(user.getId());
						aj.setAwWatchtime(new Timestamp(System.currentTimeMillis()));
						aj.setAwUserimg(user.getUserImg());
						aj.setAwUsername(user.getUsername());
						watchMng.save(aj);
						
						//3 围观成功
						writer.write("3");
					}else{
						//2 该活动不存在
						writer.write("2");
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 活动评论保存
	 * */
	@RequestMapping(value = "/activity/commentsave.jspx")
	public void commentActivitySave(HttpServletRequest request,HttpServletResponse response, ModelMap model,
				Integer acId,String text,Integer toUser){
 
		try{
			CmsSite site = CmsUtils.getSite(request);
			CmsUser user=CmsUtils.getUser(request);
			Writer writer=response.getWriter();
			//当前用户是否登录
			if(user==null){
				//0  用户尚未登录
				writer.write("0");
			}else{
				if(!text.equals("")){
					ActivityComment acm=new ActivityComment();
					
					Activity activity=activityMng.findById(acId);
					if(activity!=null){
						//转化表情代码
						text=StrUtils.convertExpresiion(text,site.getContextPath());
						
						acm.getUUID();
						acm.setAcmAcid(acId);
						acm.setAcmUserid(user.getId());
						acm.setAcmText(text);
						acm.setAcmCreatetime(new Timestamp(System.currentTimeMillis()));
						acm.setAcmUsername(user.getUsername());
						acm.setAcmUserimg(user.getUserImg());
						if(toUser!=null){
							acm.setAcmToUser(toUser);
						}
						commentMng.save(acm);
						//2 评论成功
						writer.write("2");
					}else{
						//1 该活动不存在
						writer.write("1");
					}
				}else{
					//3 评论内容为空
					writer.write("3");
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ajax获得用户数据
	 * */
	@RequestMapping(value = "/activity/getuserdate.jhtml",method=RequestMethod.GET)
	public void ajaxGetUserDate(HttpServletRequest request,HttpServletResponse response,
					Integer userId){ 
		try{ 
			CmsUser user=CmsUtils.getUser(request);
			CmsUser searchUser=userMng.findById(userId);
			
			JSONObject json=new JSONObject();
			//json的类型 0属于用户信息 1属于活动信息
			json.put("typeflag",0);
			List<JSONObject> jsonlist=new ArrayList<JSONObject>();
			PrintWriter write=response.getWriter();
			json.put("userid",searchUser.getId());
			json.put("userimg",searchUser.getUserImg());
			json.put("userlv",URLEncoder.encode(searchUser.getGroup().getName(),"UTF-8"));
			json.put("username",URLEncoder.encode(searchUser.getUsername(),"UTF-8"));
			//用户所在学校
			ActivitySchool as=schoolMng.findById(searchUser.getSchoolId());
			json.put("userschool",URLEncoder.encode(as.getScName(),"UTF-8"));
			//团队数目
			Integer joinCount=teamMng.countUserJoinTeamByUserid(userId);
			json.put("joinCount",joinCount);
			Integer createCount=teamMng.countUserCreateTeamByUserid(userId);
			json.put("createCount",createCount);
			//发布活动数目
			Integer pubCount=activityMng.getCountByProperities("ac_userid",userId);
			json.put("pubCount",pubCount);
			
			//查找该用户和登录用户的关系
			if(user!=null){
				//查找当前用户是否邀请登录用户为好友
				Pagination friendp=friendMng.getPaginationByProperities(new String[]{"AufApplyuser","AufReceiveuser"}
						,new Object[]{userId,user.getId()},1,1);
				if(friendp.getList()!=null&&friendp.getList().size()>0){
					AcUserFriend friend=(AcUserFriend)friendp.getList().get(0);
					json.put("friendapply",friend.getAufState());
				}else{
					//查找登录用户是否向当前用户发送好友邀请
					friendp=friendMng.getPaginationByProperities(new String[]{"AufApplyuser","AufReceiveuser"}
					,new Object[]{user.getId(),userId},1,1);
					if(friendp.getList()!=null&&friendp.getList().size()>0){
						AcUserFriend friend=(AcUserFriend)friendp.getList().get(0);
						json.put("friendreceive",friend.getAufState());
					}else{
						//该用户没有和登录用户有朋友关系
						json.put("nofriend",1);
					}
				}	
			}
			
			//得到该用户所发布的活动
			Pagination p=activityMng.getPaginationByProperities(new String[]{"AcUserid"},new Object[]{userId},1,3,"AcCreatedate DESC");
			if(p.getList()!=null&&p.getList().size()>0){
				//遍历活动 如果活动已经结束 则不返回
				List list=p.getList();
				JSONObject acJson=new JSONObject();
				for(int i=0;i<list.size();i++){
					Activity ac=(Activity)list.get(i);
					java.util.Date beginDate=ac.getAcBegindate();
					int days=DateUtils.daysBetween(new java.util.Date(), beginDate);
					if(days<0){
						
					}else{
						acJson=new JSONObject();
						String daystring=DateUtils.getDateTranslate(days);
						acJson.put("daystring", daystring);
						acJson.put("typeflag", 1);
						jsonlist.add(acJson);
					}
				}
				if(jsonlist.size()>0){
					jsonlist.add(json); 
				}else{
					//1 未找到该用户发布的正在进行的活动
					json.put("error",2);
					jsonlist.add(json);
				}
			}else{
				//1 未找到该用户发布的活动
				json.put("error",1);
				jsonlist.add(json);
			}
			write.write(jsonlist.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}
	} 

	/**
	 * ajax获得团队成员
	 * */
	@RequestMapping(value = "/activity/getteammember.jhtml",method=RequestMethod.GET)
	public void ajaxTeamMemberInfo(HttpServletRequest request,HttpServletResponse response,Integer teamid){ 
		try{ 
			CmsUser user=CmsUtils.getUser(request);
			PrintWriter write=response.getWriter();
			JSONObject json=new JSONObject(); 
			List<JSONObject> jsonlist=new ArrayList<JSONObject>();
			List<AcUserTeamMember> atmList=teamMemberMng.getListByProperities(new String[]{"AutTeamid","AutState"}
				,new Object[]{teamid,AcUserTeamMember.MEMBER_STATE_NORMAL});

			if(atmList!=null&&atmList.size()>0){
				for (AcUserTeamMember member:atmList) {
					CmsUser usertmp=userMng.findById(member.getAutUserid());
					json.put("username",URLEncoder.encode(usertmp.getUsername(),"UTF-8"));
					json.put("userimg",usertmp.getUserImg());
					json.put("userlv",URLEncoder.encode(usertmp.getGroup().getName(),"UTF-8"));
					json.put("userid",usertmp.getId());
					json.put("userduty",URLEncoder.encode(member.getAutDuty(),"UTF-8"));
					jsonlist.add(json);
				}
				write.write(jsonlist.toString());
			}else{
				//未找到相关成员
				json.put("error",1);
				jsonlist.add(json);
				write.write(jsonlist.toString());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ajax签到
	 * */
	@RequestMapping(value = "/activity/usercheckin.jspx",method=RequestMethod.GET)
	public void ajaxUserCheckin(HttpServletRequest request,HttpServletResponse response){ 
		try{ 
			CmsUser user=CmsUtils.getUser(request);
			PrintWriter write=response.getWriter();
			if(user!=null){
				//判断用户是否已经签到过了
				if(checkinMng.isCheckinToday(user.getId())){
					//2 该用户已经签到过了
					write.print(2);
				}else{
					AcUserCheckin check=new AcUserCheckin();
					check.getUUID();
					check.setCheckDate(new Timestamp(System.currentTimeMillis()));
					check.setCheckUser(user.getId());
					checkinMng.save(check);
					//1 签到成功
					write.print(1);
				}
			}else{
				//0 用户没登录
				write.print(0);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	private ActivityMng activityMng;
	@Autowired
	private ActivityTagMng activityTagMng;
	@Autowired
	private ActivityPictureMng activityPictureMng;
	@Autowired
	private TagToActivityMng tagMng;
	@Autowired
	private ActivitySchoolMng schoolMng; 
	@Autowired
	private ActivityDistrickMng districkMng;
	@Autowired
	private AcUserFriendMng friendMng;
	@Autowired
	private ActivityTypeMng typeMng;
	@Autowired
	private ActivityJoinMng joinMng;
	@Autowired
	private ActivityWatchMng watchMng;
	@Autowired
	private ActivityCommentMng commentMng;
	@Autowired
	private ActivityLiveMng liveMng;
	@Autowired
	private CmsUserMng userMng;
	@Autowired
	private AcUserTeamMng teamMng;
	@Autowired
	private AcUserTeamMemberMng teamMemberMng;
	@Autowired
	private AcUserCheckinMng checkinMng;
	@Autowired
	private ImageCaptchaService imageCaptchaService;
	@Autowired
	private SessionProvider session;
}
