package com.jeecms.cms.action.directive;
 
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.web.FrontUtils;
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
public class UserListDirective implements TemplateDirectiveModel {
 

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSite site = FrontUtils.getSite(env);
		//用户所在学校ID
		Integer schoolId = DirectiveUtils.getInt("schoolId", params);
		//用户所在的城市ID
		Integer cityId = DirectiveUtils.getInt("cityId", params);
		//用户组ID  为此ID以上等级的用户
		Integer groupId = DirectiveUtils.getInt("groupId", params);
		//用户状态是否更新 0 所有用户 1 未更新用户 2状态有更新用户
		Integer isUpdate = DirectiveUtils.getInt("groupId", params);
		//用户是否是管理员 0 非管理员 1 管理员
		Boolean isAdmin = DirectiveUtils.getBool("isAdmin", params);
		
		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		List<CmsUser> userList=null;
		//如果有城市ID  则查询这座城市的用户
		if(cityId!=null){
			
		}
		//如果城市ID为空 但有学校的ID则查询这所学校的用户
		else if(schoolId!=null){
			userList=userMng.findUserBySchool(new String[]{"schoolId","admin"}, new Object[]{schoolId,isAdmin}, 100);
		}else{
			paramWrap.put("error", DEFAULT_WRAPPER.wrap("参数错误"));
		}

		//查询到用户时
		if(userList!=null&&userList.size()>0){
			paramWrap.put("userList", DEFAULT_WRAPPER.wrap(userList));
		}else{
			paramWrap.put("error", DEFAULT_WRAPPER.wrap("所查询的用户不存在"));
		}
		
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		body.render(env.getOut());
		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}

	@Autowired
	private CmsUserMng userMng;
}
