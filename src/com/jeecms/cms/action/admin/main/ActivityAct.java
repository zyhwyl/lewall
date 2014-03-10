package com.jeecms.cms.action.admin.main;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.jeecms.cms.entity.main.AcWeekChampion;
import com.jeecms.cms.entity.main.AcWeekChampionExt;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivitySchool;
import com.jeecms.cms.entity.main.ActivityType;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.entity.main.ChannelTxt;
import com.jeecms.cms.entity.main.CmsGroup;
import com.jeecms.cms.entity.main.CmsModel;
import com.jeecms.cms.entity.main.CmsModelItem;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser; 
import com.jeecms.cms.manager.main.AcWeekChampionExtMng;
import com.jeecms.cms.manager.main.AcWeekChampionMng;
import com.jeecms.cms.manager.main.ActivityMng;
import com.jeecms.cms.manager.main.ActivitySchoolMng;
import com.jeecms.cms.manager.main.ActivityTypeMng;
import com.jeecms.cms.manager.main.ChannelMng;
import com.jeecms.cms.manager.main.CmsGroupMng;
import com.jeecms.cms.manager.main.CmsLogMng;
import com.jeecms.cms.manager.main.CmsModelItemMng;
import com.jeecms.cms.manager.main.CmsModelMng;
import com.jeecms.cms.manager.main.CmsUserMng;
import com.jeecms.cms.web.CmsUtils;
import com.jeecms.cms.web.WebErrors;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.core.tpl.TplManager;
import com.jeecms.core.web.CoreUtils;

@Controller
public class ActivityAct {
	private static final int PAGE_SIZE=10;
	
	/*-------------活动类型增删改查管理------------------*/
	@RequestMapping("/activity/activitytype_right.do")
	public String activitytypeList(int pageNo,HttpServletRequest request, ModelMap model) {
		Pagination pagination=typeMng.getPaginationByProperities(null,null, 
				pageNo, PAGE_SIZE);
		
		model.addAttribute("pagination", pagination);
		return "activity/activitytype_list";
	}
	@RequestMapping("/activity/activitytype_add.do")
	public String activitytypeAdd(HttpServletRequest request, ModelMap model) {
		return "activity/activitytype_add";
	}
	
	@RequestMapping("/activity/activitytype_save.do")
	public String activitytypeSave(HttpServletRequest request, ModelMap model,ActivityType activitytype) {
		CmsUser user=CmsUtils.getUser(request);
		activitytype.setAtUserid(user.getId());
		activitytype.init();
		
		typeMng.save(activitytype);
		
		return activitytypeList(1, request, model);
	}
	
	@RequestMapping("/activity/activitytype_edit.do")
	public String activitytypeEdit(HttpServletRequest request, ModelMap model,Integer id) {
		ActivityType activitytype=typeMng.findById(id);
		model.addAttribute("activitytype", activitytype);
		return "activity/activitytype_edit";
	}
	
	@RequestMapping("/activity/activitytype_update.do")
	public String activitytypeUpdate(HttpServletRequest request, ModelMap model,ActivityType activitytype) {
		Updater<ActivityType> updater=new Updater<ActivityType>(activitytype);
		typeMng.updateByUpdater(updater);
		
		return activitytypeList(1, request, model);
	}
	
	@RequestMapping("/activity/activitytype_delete.do")
	public String activitytypeUpdate(HttpServletRequest request, ModelMap model,String ids) {
		String []idsstr=ids.split(",");
		for(String id:idsstr){
			typeMng.deleteById(Integer.valueOf(id));
		}
		return activitytypeList(1, request, model);
	}
	/*-------------活动类型增删改查管理结束------------------*/
	
	/*-------------周冠军增删改查管理------------------*/
	@RequestMapping("/activity/champion_right.do")
	public String championList(int pageNo,HttpServletRequest request, ModelMap model) {
		Pagination pagination=championMng.getPaginationByProperities(null,null, 
				pageNo, PAGE_SIZE);
		
		model.addAttribute("pagination", pagination);
		return "activity/champion_list";
	}
	@RequestMapping("/activity/champion_add.do")
	public String championAdd(HttpServletRequest request, ModelMap model) {
		List schoolList=schoolMng.getPaginationByProperities(null,null,1,100).getList();
		
		//得到第一个学校的活动
		if(schoolList!=null&&schoolList.size()>0){
			List activityList=activityMng.getPaginationByProperities(new String[]{"AcSchool","AcState"}
				,new Object[]{((ActivitySchool)schoolList.get(0)).getId(),Activity.ACTIVITY_GOING},1,100).getList();
			model.addAttribute("activityList",activityList);
		}
		
		model.addAttribute("schoolList",schoolList);
		return "activity/champion_add";
	}
	
	@RequestMapping("/activity/champion_save.do")
	public String championSave(HttpServletRequest request, ModelMap model,AcWeekChampion champion,AcWeekChampionExt championExt) {
		champion.setChampionDate(new Timestamp(System.currentTimeMillis()));
		champion.setAcWeekChampionExt(championExt);
		championExt.setAcWeekChampion(champion);
		
		championMng.save(champion);
		championExtMng.save(championExt);
		return championList(1, request, model);
	}
	
	@RequestMapping("/activity/champion_edit.do")
	public String championEdit(HttpServletRequest request, ModelMap model,Integer id) {
		AcWeekChampion champion=championMng.findById(id);
		model.addAttribute("champion", champion);
		List schoolList=schoolMng.getPaginationByProperities(null,null,1,100).getList();
		
		//得到第一个学校的活动
		if(schoolList!=null&&schoolList.size()>0){
			List activityList=activityMng.getPaginationByProperities(new String[]{"AcSchool","AcState"}
				,new Object[]{((ActivitySchool)schoolList.get(0)).getId(),Activity.ACTIVITY_GOING},1,100).getList();
			model.addAttribute("activityList",activityList);
		}
		
		model.addAttribute("schoolList",schoolList);
		
		return "activity/champion_edit";
	}
	
	@RequestMapping("/activity/champion_update.do")
	public String championUpdate(HttpServletRequest request, ModelMap model,AcWeekChampion champion,AcWeekChampionExt championExt) {
		champion.setAcWeekChampionExt(championExt);
		
		Updater<AcWeekChampion> updater=new Updater<AcWeekChampion>(champion);
		championMng.updateByUpdater(updater);
		
		return championList(1, request, model);
	}
	
	@RequestMapping("/activity/champion_delete.do")
	public String championUpdate(HttpServletRequest request, ModelMap model,String ids) {
		String []idsstr=ids.split(",");
		for(String id:idsstr){
			championExtMng.deleteById(Integer.valueOf(id));
			championMng.deleteById(Integer.valueOf(id));
		}
		return championList(1, request, model);
	}
	/*-------------活动类型增删改查管理结束------------------*/
	
	@Autowired
	private ActivityTypeMng typeMng; 
	@Autowired
	private AcWeekChampionMng championMng;  
	@Autowired
	private AcWeekChampionExtMng championExtMng;  
	@Autowired
	private ActivitySchoolMng schoolMng;  
	@Autowired
	private ActivityMng activityMng;  
}