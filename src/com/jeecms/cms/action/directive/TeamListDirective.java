package com.jeecms.cms.action.directive;
 
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.entity.main.AcUserTeam;
import com.jeecms.cms.entity.main.AcUserTeamMember;
import com.jeecms.cms.entity.main.AcUserTeamTag;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.AcUserTeamMemberMng;
import com.jeecms.cms.manager.main.AcUserTeamMng;
import com.jeecms.cms.manager.main.AcUserTeamTagMng;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.freemarker.DirectiveUtils;
import com.jeecms.common.web.freemarker.ParamsRequiredException;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 栏目对象标签
 * 
 * @author liufang
 * 
 */
public class TeamListDirective implements TemplateDirectiveModel {
	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSite site = FrontUtils.getSite(env);
		//用户 ID
		Integer userId = DirectiveUtils.getInt("userId", params);
		
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		
		//获取用户创建的团队
		Pagination createP=teamMng.getPaginationByProperities(new String[]{"TeamCreateuser"},new Object[]{userId}
			,1,1000," TeamCreatetime DESC");
		
		List teamList=null;
		List<AcUserTeam> joinList=new ArrayList<AcUserTeam>();

		//查找用户创建的团队
		if(createP.getList()!=null&&createP.getList().size()>0){
			teamList=createP.getList();
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
		}
		
		//查找用户加入的团队
		Pagination joinP=teamMemberMng.getPaginationByProperities(new String[]{"AutUserid"},new Object[]{userId}
			,1,1000," AutJointime DESC");
		if(joinP.getList()!=null&&joinP.getList().size()>0){
			//如果有加入的团队 查出该团队 再将团队成员、标签插入
			for(int i=0;i<joinP.getList().size();i++){
				AcUserTeam act=teamMng.findById(((AcUserTeamMember)joinP.getList().get(i)).getAutTeamid());
				//查出此团队的成员
				List<AcUserTeamMember> cMemberList=teamMemberMng.getListByProperities(new String[]{"AutTeamid"}
					,new Object[]{act.getId()});

				//查找该用户是不是具有团发布的权限
				if(((AcUserTeamMember)joinP.getList().get(i)).getAutCanpub().equals(0)){
					act.setCanpub(0);
				}else{
					act.setCanpub(1);
				}
				if(cMemberList!=null&&cMemberList.size()>0){
					act.setMembers(cMemberList);
				}
				//查询标签
				List<AcUserTeamTag> tags=new ArrayList<AcUserTeamTag>();
				String []tmpTags=act.getAcUserTeamExt().getTeamTag().split(",");
				for(String tagid:tmpTags){
					AcUserTeamTag tag=teamTagMng.findById(Integer.valueOf(tagid));
					tags.add(tag);
				}
				if(tags.size()>0){
					act.setTags(tags);
				}
				joinList.add(act);
			}
		}
		
		if(teamList==null&&!(joinList.size()>0)){
			paramWrap.put("error", DEFAULT_WRAPPER.wrap("你还没有创立团队额！"));
		}else{
			if(teamList!=null){
				paramWrap.put("teamList", DEFAULT_WRAPPER.wrap(teamList));
			}
			if(joinList!=null){
				paramWrap.put("joinList", DEFAULT_WRAPPER.wrap(joinList));
			}
		}
		
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

	@Autowired
	private AcUserTeamMng teamMng;
	@Autowired
	private AcUserTeamMemberMng teamMemberMng;
	@Autowired
	private AcUserTeamTagMng teamTagMng;
}
