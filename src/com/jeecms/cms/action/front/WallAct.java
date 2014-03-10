package com.jeecms.cms.action.front;

import static com.jeecms.cms.Constants.TPLDIR_MEMBER;
import static com.jeecms.cms.Constants.TPLDIR_SPECIAL;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.entity.main.AcUserCheckin;
import com.jeecms.cms.entity.main.AcUserTeam;
import com.jeecms.cms.entity.main.AcUserTeamFans;
import com.jeecms.cms.entity.main.AcUserTeamMember;
import com.jeecms.cms.entity.main.AcUserTeamTag;
import com.jeecms.cms.entity.main.AcWeekChampion;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityComment;
import com.jeecms.cms.entity.main.ActivityJoin;
import com.jeecms.cms.entity.main.ActivityLive;
import com.jeecms.cms.entity.main.ActivityTag;
import com.jeecms.cms.entity.main.ActivityWatch;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.entity.main.MemberConfig;
import com.jeecms.cms.manager.main.AcUserFriendMng;
import com.jeecms.cms.manager.main.AcUserTeamExtMng;
import com.jeecms.cms.manager.main.AcUserTeamFansMng;
import com.jeecms.cms.manager.main.AcUserTeamMemberMng;
import com.jeecms.cms.manager.main.AcUserTeamMng;
import com.jeecms.cms.manager.main.AcUserTeamTagMng;
import com.jeecms.cms.manager.main.AcWeekChampionMng;
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
import com.jeecms.cms.manager.main.CmsUserExtMng;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.manager.main.TagToActivityMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.util.DateUtils;

@Controller
public class WallAct {
	private static String PRE_PATH="/WEB-INF/t/cms/www/red/index/";
	/**
	 * 团队墙入口
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teamwall.jhtml", method = RequestMethod.GET)
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) { 
		CmsSite site = CmsUtils.getSite(request); 
		FrontUtils.frontData(request, model, site);
		CmsUser user=CmsUtils.getUser(request);
		Integer school=4;
		Integer pageNo=1;
		Pagination p=teamMng.getPaginationByProperities(new String[]{"TeamSchoolid"},new Object[]{school},
				1,150," TeamCreatetime DESC");
		if(p.getList()!=null&&p.getList().size()>0){
			List teamList=p.getList();
			//将团队成员、标签插入到团队中
			for(int i=0;i<teamList.size();i++){
				//查询标签
				List<AcUserTeamTag> tags=new ArrayList<AcUserTeamTag>();
				String []tmpTags=((AcUserTeam)teamList.get(i)).getAcUserTeamExt().getTeamTag().split(",");
				for(String tagid:tmpTags){
					AcUserTeamTag tag=teamTagMng.findById(Integer.valueOf(tagid));
					tags.add(tag);
				}
				if(tags.size()>0){
					((AcUserTeam)teamList.get(i)).setTags(tags);
				}
				//查询成员
				List<AcUserTeamMember> cMemberList=teamMemberMng.getListByProperities(new String[]{"AutTeamid"}
					,new Object[]{((AcUserTeam)teamList.get(i)).getId()});
				if(cMemberList!=null&&cMemberList.size()>0){
					((AcUserTeam)teamList.get(i)).setMembers(cMemberList);  
				} 
				if(user!=null){
					//当前用户是否关注过该团队
					List<AcUserTeamFans> fans=teamFansMng.getListByProperities(new String[]{"AcfTeamid","AcfUserid"},
							new Object[]{((AcUserTeam)teamList.get(i)).getId(),user.getId()});
					if(fans!=null&&fans.size()>0){
						((AcUserTeam)teamList.get(i)).setCanAttention(0);
					} 
					//当前用户是否加入该团队
					for(AcUserTeamMember member:cMemberList){
						if(member.getAutUserid().equals(user.getId())){
							if(member.getAutState().equals(AcUserTeamMember.MEMBER_STATE_NORMAL)){
								//已加入
								((AcUserTeam)teamList.get(i)).setCanJoin(0);
							}else if(member.getAutState().equals(AcUserTeamMember.MEMBER_STATE_SENDING)){
								//被该团队邀请中
								((AcUserTeam)teamList.get(i)).setCanJoin(2);
							}else if(member.getAutState().equals(AcUserTeamMember.MEMBER_STATE_APPLYING)){
								//已发送加入申请
								((AcUserTeam)teamList.get(i)).setCanJoin(3);
							}else if(member.getAutState().equals(AcUserTeamMember.MEMBER_STATE_DISABLE)){
								//被禁用
								((AcUserTeam)teamList.get(i)).setCanJoin(4);
							}else if(member.getAutState().equals(AcUserTeamMember.MEMBER_STATE_DISCARD)){
								//申请加入请求被忽略
								((AcUserTeam)teamList.get(i)).setCanJoin(5);
							}else{
								((AcUserTeam)teamList.get(i)).setCanJoin(1);
							}
						}
					}
				}
			}
			model.addAttribute("teamList",teamList);
		}else{
			model.addAttribute("error","该学校还没人创建团队额！");
		}
		
		return PRE_PATH+"团队墙.html";
	}
	/**
	 * 梦想墙入口
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dreamwall.jhtml", method = RequestMethod.GET)
	public String dreamIndex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) { 
		CmsSite site = CmsUtils.getSite(request); 
		FrontUtils.frontData(request, model, site);
		Integer school=1;
		Integer pageNo=1;
		Pagination p=teamMng.getPaginationByProperities(new String[]{"TeamSchoolid"},new Object[]{school},
				1,150," TeamCreatetime DESC");
		if(p.getList()!=null&&p.getList().size()>0){
			List teamList=p.getList();
			//将团队成员、标签插入到团队中
			for(int i=0;i<teamList.size();i++){
				//查询标签
				List<AcUserTeamTag> tags=new ArrayList<AcUserTeamTag>();
				String []tmpTags=((AcUserTeam)teamList.get(i)).getAcUserTeamExt().getTeamTag().split(",");
				for(String tagid:tmpTags){
					AcUserTeamTag tag=teamTagMng.findById(Integer.valueOf(tagid));
					tags.add(tag);
				}
				if(tags.size()>0){
					((AcUserTeam)teamList.get(i)).setTags(tags);
				}
				//查询成员
				List<AcUserTeamMember> cMemberList=teamMemberMng.getListByProperities(new String[]{"AutTeamid"}
				,new Object[]{((AcUserTeam)teamList.get(i)).getId()});
				if(cMemberList!=null&&cMemberList.size()>0){
					((AcUserTeam)teamList.get(i)).setMembers(cMemberList);
				}
			}
			model.addAttribute("teamList",teamList);
		}else{
			model.addAttribute("error","该学校还没人创建团队额！");
		}
		
		return PRE_PATH+"梦想墙.html";
	}
	
	/**
	 * 周冠军入口
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/championwall.jhtml", method = RequestMethod.GET)
	public String championIndex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws ParseException { 
		CmsSite site = CmsUtils.getSite(request); 
		FrontUtils.frontData(request, model, site);
		CmsUser user=CmsUtils.getUser(request);
		Integer school=4;
		Integer pageNo=1;
		Pagination p=championMng.getPaginationByProperities(new String[]{"SchoolId","ChampionState"}
			,new Object[]{school,1},1,1," ChampionDate DESC");
		if(p.getList()!=null&&p.getList().size()>0){
			AcWeekChampion champion=(AcWeekChampion)p.getList().get(0);
			//查找该活动
			Activity activity=activityMng.findById(champion.getChampionActivity());
			model.addAttribute("champion",champion);
			model.addAttribute("activity",activity);
			if(activity!=null){
				//取得活动直播
				List<ActivityLive> liveList=liveMng.getListByProperities(new String[]{"LiveActivity"},new Object[]{activity.getId()});
				
				//图片
				List<String> pictureList=activityPictureMng.getListByProperities(new String[]{"ac_id"},new Object[]{activity.getId()});
				
				//用户
				CmsUser	acUser=userMng.findById(activity.getAcUserid());
				activity.setUser(acUser);
				//如果该活动团队ID不为空则是团队发布 查询出该团队
				if(activity.getAcTeamid()!=null&&!activity.getAcTeamid().equals("")){
					AcUserTeam team=teamMng.findById(activity.getAcTeamid());
					activity.setTeam(team);
				}
				//标签
				List<Integer> tagids=tagMng.getListByProperities(new String[]{"ac_id"},new Object[]{activity.getId()});
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
				
				//获得该活动的参与人数和围观人数
				Integer joinCount=joinMng.getCountByProperities("aj_activityid",activity.getId());
				Integer watchCount=watchMng.getCountByProperities("aw_activityid",activity.getId());
				
				//活动的评论用户
				List<ActivityComment> commentList=commentMng.getListByProperities(new String[]{"AcmAcid"}, new Object[]{activity.getId()});
				//将评论一个用户的几条评论整理为一条记录
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
								,new Object[]{activity.getId(),user.getId()},1,100);

					if(awlist.getList()!=null&&awlist.getList().size()>0){
						//1用户已经围观过
						activity.setIsWatch(1);
					}
					//判断当前用户是否已经报过名
					Pagination ajlist=joinMng.getPaginationByProperities(new String[]{"AjActivityid","AjUserid"}
								,new Object[]{activity.getId(),user.getId()},1,100);

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
		}else{
			model.addAttribute("error","对不起,该学校还没周活动！");
		}
		
		return PRE_PATH+"周冠军.html";
	}

	/**
	 * ajax获得用户数据
	 * */
	@RequestMapping(value = "/activity/getexpression.jhtml",method=RequestMethod.GET)
	public void ajaxGetExpression(HttpServletRequest request,HttpServletResponse response){ 
		try{ 
			PrintWriter write=response.getWriter();
			//查找表情包
			
			//文件根路径
			String savePath = request.getSession().getServletContext().getRealPath("/"); 
			String exprePath="/r/cms/www/red/images/expression/";
			File file=new File(savePath+exprePath);
			File list[] = file.listFiles();
			String fileName="";
			for(int i=0;i<list.length;i++){
				if(list[i].getName().indexOf(".gif")>=0){
					fileName+=list[i].getName().replace(".gif","")+",";
				} 
			}
			if(!fileName.equals("")){
				fileName=fileName.substring(0,fileName.length()-1);
			}
			write.print(fileName);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	private CmsUserMng cmsUserMng;
	@Autowired
	private CmsUserExtMng cmsUserExtMng;
	@Autowired
	private AcUserFriendMng friendMng;
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
	private AcWeekChampionMng championMng;
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
}
