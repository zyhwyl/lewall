package com.jeecms.cms.action.directive;

import static com.jeecms.cms.Constants.TPL_STYLE_SCHOOL;
import static com.jeecms.cms.Constants.TPL_SUFFIX;
import static com.jeecms.cms.web.FrontUtils.PARAM_STYLE_LIST;
import static com.jeecms.common.web.Constants.UTF8;
import static com.jeecms.common.web.freemarker.DirectiveUtils.OUT_LIST;
import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jeecms.cms.action.directive.abs.AbstractContentDirective;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.ActivityProvince;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.manager.main.ActivityCountryMng;
import com.jeecms.cms.manager.main.ActivityProvinceMng;
import com.jeecms.cms.manager.main.CmsSiteMng;
import com.jeecms.cms.web.FrontUtils;
import com.jeecms.common.web.freemarker.DirectiveUtils;
import com.jeecms.common.web.freemarker.ParamsRequiredException;
import com.jeecms.common.web.freemarker.DirectiveUtils.InvokeType;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 内容列表标签
 * 
 * @author liufang
 * 
 */
public class SchoolSelectDirective implements TemplateDirectiveModel{

	@SuppressWarnings("unchecked")
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		CmsSite site = FrontUtils.getSite(env);
		List activityProvince=provinceMng.getPaginationByProperities(
				null, null, 1, 100).getList();

		Map<String, TemplateModel> paramWrap = new HashMap<String, TemplateModel>(
				params);
		paramWrap.put(OUT_LIST, DEFAULT_WRAPPER.wrap(activityProvince));
		Map<String, TemplateModel> origMap = DirectiveUtils
				.addParamsToVariable(env, paramWrap);
		InvokeType type = DirectiveUtils.getInvokeType(params);
		String listStyle = DirectiveUtils.getString(PARAM_STYLE_LIST, params);

		env.include(TPL_STYLE_SCHOOL + listStyle + TPL_SUFFIX, UTF8, true);

		DirectiveUtils.removeParamsFromVariable(env, paramWrap, origMap);
	}
 
	
	@Autowired
	protected ActivityProvinceMng provinceMng;

}
