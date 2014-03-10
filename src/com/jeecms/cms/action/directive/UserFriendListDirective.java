package com.jeecms.cms.action.directive;
 
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.entity.main.AcUserFriend;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.AcUserFriendMng;
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
public class UserFriendListDirective implements TemplateDirectiveModel {
 

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSite site = FrontUtils.getSite(env);
		//用户所在学校ID
		Integer userid = DirectiveUtils.getInt("userid", params); 
		
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		List<CmsUser> userList=new ArrayList<CmsUser>(); 
		//查询该用户申请并通过的好友
		Pagination applyP=friendMng.getPaginationByProperities(new String[]{"AufApplyuser","AufState"}
			,new Object[]{userid,AcUserFriend.STATE_ACCEPTING},1,30);
		if(applyP.getList()!=null&&applyP.getList().size()>0){
			//查询好友并放入userlist中
			for(int i=0;i<applyP.getList().size();i++){
				AcUserFriend friend=(AcUserFriend)applyP.getList().get(i);
				CmsUser user=userMng.findById(friend.getAufReceiveuser());
				userList.add(user);
			}
		}
		//查询该用户接受的好友
		Pagination acceptP=friendMng.getPaginationByProperities(new String[]{"AufReceiveuser","AufState"}
			,new Object[]{userid,AcUserFriend.STATE_ACCEPTING},1,30);
		if(acceptP.getList()!=null&&acceptP.getList().size()>0){
			//查询好友并放入userlist中
			for(int i=0;i<acceptP.getList().size();i++){
				AcUserFriend friend=(AcUserFriend)acceptP.getList().get(i);
				CmsUser user=userMng.findById(friend.getAufApplyuser());
				userList.add(user);
			}
		} 
		//查询到用户时
		if(userList!=null&&userList.size()>0){
			paramWrap.put("userList", DEFAULT_WRAPPER.wrap(userList));
		}else{
			paramWrap.put("error", DEFAULT_WRAPPER.wrap("你还没有添加好友啊！"));
		}
		
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

	@Autowired
	private CmsUserMng userMng;
	@Autowired
	private AcUserFriendMng friendMng;
}
