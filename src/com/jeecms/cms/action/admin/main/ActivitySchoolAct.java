package com.jeecms.cms.action.admin.main;

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

import com.jeecms.cms.entity.main.ActivityCity;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.ActivityDistrick;
import com.jeecms.cms.entity.main.ActivityProvince;
import com.jeecms.cms.entity.main.ActivitySchool;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.entity.main.ChannelTxt;
import com.jeecms.cms.entity.main.CmsGroup;
import com.jeecms.cms.entity.main.CmsModel;
import com.jeecms.cms.entity.main.CmsModelItem;
import com.jeecms.cms.entity.main.CmsSite;
import com.jeecms.cms.entity.main.CmsUser;
import com.jeecms.cms.manager.main.ActivityCityMng;
import com.jeecms.cms.manager.main.ActivityCountryMng;
import com.jeecms.cms.manager.main.ActivityDistrickMng;
import com.jeecms.cms.manager.main.ActivityProvinceMng;
import com.jeecms.cms.manager.main.ActivitySchoolMng;
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
public class ActivitySchoolAct {
	private static final Logger log = LoggerFactory.getLogger(ActivitySchoolAct.class);
	private static final int PAGE_SIZE=10;
	
	/*-------------国家增删改查管理------------------*/
	@RequestMapping("/activity/country_right.do")
	public String countryList(int pageNo,HttpServletRequest request, ModelMap model) {
		Pagination pagination=countryMng.getPaginationByProperities(null,null, 
				pageNo, PAGE_SIZE);
		
		model.addAttribute("pagination", pagination);
		return "activity/country_list";
	}
	@RequestMapping("/activity/country_add.do")
	public String countryAdd(HttpServletRequest request, ModelMap model) {
		return "activity/country_add";
	}
	
	@RequestMapping("/activity/country_save.do")
	public String countrySave(HttpServletRequest request, ModelMap model,ActivityCountry country) {
		countryMng.save(country);
 
		return countryList(1, request, model);
	}
	
	@RequestMapping("/activity/country_edit.do")
	public String countryEdit(HttpServletRequest request, ModelMap model,Integer id) {
		ActivityCountry country=countryMng.findById(id);
		model.addAttribute("country", country);
		return "activity/country_edit";
	}
	
	@RequestMapping("/activity/country_update.do")
	public String countryUpdate(HttpServletRequest request, ModelMap model,ActivityCountry country) {
		Updater<ActivityCountry> updater=new Updater<ActivityCountry>(country);
		countryMng.updateByUpdater(updater);
		
		return countryList(1, request, model);
	}
	
	@RequestMapping("/activity/country_delete.do")
	public String countryUpdate(HttpServletRequest request, ModelMap model,String ids) {
		String []idsstr=ids.split(",");
		for(String id:idsstr){
			countryMng.deleteById(Integer.valueOf(id));
		}
		return countryList(1, request, model);
	}
	/*-------------国家增删改查管理结束------------------*/
	/*-------------省份增删改查管理------------------*/
	@RequestMapping("/activity/province_right.do")
	public String provinceList(int pageNo,HttpServletRequest request, ModelMap model) {
		Pagination pagination=provinceMng.getPaginationByProperities(null,null, 
				pageNo, PAGE_SIZE);
		
		model.addAttribute("pagination", pagination);
		return "activity/province_list";
	}
	
	@RequestMapping("/activity/province_add.do")
	public String provinceAdd(HttpServletRequest request, ModelMap model) {
		//国家列表
		List list=countryMng.getPaginationByProperities(null, null,1, 100).getList();
		model.addAttribute("list", list);
		return "activity/province_add";
	}
	
	@RequestMapping("/activity/province_save.do")
	public String provinceSave(HttpServletRequest request, ModelMap model,ActivityProvince province,Integer countryid) {
		ActivityCountry country=countryMng.findById(countryid);
		province.setCountry(country);
		
		provinceMng.save(province);
		return provinceList(1, request, model);
	}
	
	@RequestMapping("/activity/province_edit.do")
	public String provinceEdit(HttpServletRequest request, ModelMap model,Integer id) {
		ActivityProvince province=provinceMng.findById(id);
		//国家列表
		List list=countryMng.getPaginationByProperities(null, null,1, 100).getList();
		model.addAttribute("list", list);
		model.addAttribute("province", province);
		return "activity/province_edit";
	}
	
	@RequestMapping("/activity/province_update.do")
	public String provinceUpdate(HttpServletRequest request, ModelMap model,ActivityProvince province,Integer countryid) {
		ActivityCountry country=countryMng.findById(countryid);
		province.setCountry(country);
		
		Updater<ActivityProvince> updater=new Updater<ActivityProvince>(province);
		provinceMng.updateByUpdater(updater);
		
		return provinceList(1, request, model);
	}
	
	@RequestMapping("/activity/province_delete.do")
	public String provinceDelete(HttpServletRequest request, ModelMap model,String ids) {
		String []idsstr=ids.split(",");
		for(String id:idsstr){
			provinceMng.deleteById(Integer.valueOf(id));
		}
		return provinceList(1, request, model);
	}
	/*-------------省份增删改查管理结束------------------*/
	/*-------------市增删改查管理------------------*/
	@RequestMapping("/activity/city_right.do")
	public String cityList(int pageNo,HttpServletRequest request, ModelMap model) {
		Pagination pagination=cityMng.getPaginationByProperities(null,null, 
				pageNo, PAGE_SIZE);
		
		model.addAttribute("pagination", pagination);
		return "activity/city_list";
	}
	
	@RequestMapping("/activity/city_add.do")
	public String cityAdd(HttpServletRequest request, ModelMap model) {
		//省份列表
		List list=provinceMng.getPaginationByProperities(null, null,1, 100).getList();
		model.addAttribute("list", list);
		return "activity/city_add";
	}
	
	@RequestMapping("/activity/city_save.do")
	public String citySave(HttpServletRequest request, ModelMap model,ActivityCity city,Integer provinceid) {
		ActivityProvince province=provinceMng.findById(provinceid);
		city.setProvince(province);
		
		cityMng.save(city);
		return cityList(1, request, model);
	}
	
	@RequestMapping("/activity/city_edit.do")
	public String cityEdit(HttpServletRequest request, ModelMap model,Integer id) {
		ActivityCity city=cityMng.findById(id);
		//省份列表
		List list=provinceMng.getPaginationByProperities(null, null,1, 100).getList();
		model.addAttribute("list", list);
		model.addAttribute("city", city);
		return "activity/city_edit";
	}
	
	@RequestMapping("/activity/city_update.do")
	public String cityUpdate(HttpServletRequest request, ModelMap model,ActivityCity city,Integer provinceid) {
		ActivityProvince province=provinceMng.findById(provinceid);
		city.setProvince(province);
		
		Updater<ActivityCity> updater=new Updater<ActivityCity>(city);
		cityMng.updateByUpdater(updater);
		
		return cityList(1, request, model);
	}
	
	@RequestMapping("/activity/city_delete.do")
	public String cityDelete(HttpServletRequest request, ModelMap model,String ids) {
		String []idsstr=ids.split(",");
		for(String id:idsstr){
			cityMng.deleteById(Integer.valueOf(id));
		}
		return cityList(1, request, model);
	}
	/*-------------市增删改查管理结束------------------*/
	/*-------------区增删改查管理------------------*/
	@RequestMapping("/activity/districk_right.do")
	public String districkList(int pageNo,HttpServletRequest request, ModelMap model) {
		Pagination pagination=districkMng.getPaginationByProperities(null,null, 
				pageNo, PAGE_SIZE);
		
		model.addAttribute("pagination", pagination);
		return "activity/districk_list";
	}
	
	@RequestMapping("/activity/districk_add.do")
	public String districkAdd(HttpServletRequest request, ModelMap model) {
		//市列表
		List list=cityMng.getPaginationByProperities(null, null,1, 100).getList();
		model.addAttribute("list", list);
		return "activity/districk_add";
	}
	
	@RequestMapping("/activity/districk_save.do")
	public String districkSave(HttpServletRequest request, ModelMap model,ActivityDistrick districk,Integer cityid) {
		ActivityCity city=cityMng.findById(cityid);
		districk.setCity(city);
		
		districkMng.save(districk);
		return districkList(1, request, model);
	}
	
	@RequestMapping("/activity/districk_edit.do")
	public String districkEdit(HttpServletRequest request, ModelMap model,Integer id) {
		ActivityDistrick districk=districkMng.findById(id);
		//市列表
		List list=cityMng.getPaginationByProperities(null, null,1, 100).getList();
		model.addAttribute("list", list);
		model.addAttribute("districk", districk);
		return "activity/districk_edit";
	}
	
	@RequestMapping("/activity/districk_update.do")
	public String districkUpdate(HttpServletRequest request, ModelMap model,ActivityDistrick districk,Integer cityid) {
		ActivityCity city=cityMng.findById(cityid);
		districk.setCity(city);
		
		Updater<ActivityDistrick> updater=new Updater<ActivityDistrick>(districk);
		districkMng.updateByUpdater(updater);
		
		return districkList(1, request, model);
	}
	
	@RequestMapping("/activity/districk_delete.do")
	public String districkDelete(HttpServletRequest request, ModelMap model,String ids) {
		String []idsstr=ids.split(",");
		for(String id:idsstr){
			districkMng.deleteById(Integer.valueOf(id));
		}
		return districkList(1, request, model);
	}
	/*-------------区增删改查管理结束------------------*/
	/*-------------学校增删改查管理------------------*/
	@RequestMapping("/activity/school_right.do")
	public String schoolList(int pageNo,HttpServletRequest request, ModelMap model) {
		Pagination pagination=schoolMng.getPaginationByProperities(null,null, 
				pageNo, PAGE_SIZE);
		
		model.addAttribute("pagination", pagination);
		return "activity/school_list";
	}
	
	@RequestMapping("/activity/school_add.do")
	public String schoolAdd(HttpServletRequest request, ModelMap model) {
		//区列表
		List list=districkMng.getPaginationByProperities(null, null,1, 100).getList();
		model.addAttribute("list", list);
		return "activity/school_add";
	}
	
	@RequestMapping("/activity/school_save.do")
	public String schoolSave(HttpServletRequest request, ModelMap model,ActivitySchool school,Integer districkid) {
		ActivityDistrick districk=districkMng.findById(districkid);
		school.setDistrick(districk);
		
		schoolMng.save(school);
		return schoolList(1, request, model);
	}
	
	@RequestMapping("/activity/school_edit.do")
	public String schoolEdit(HttpServletRequest request, ModelMap model,Integer id) {
		ActivitySchool school=schoolMng.findById(id);
		//区列表
		List list=districkMng.getPaginationByProperities(null, null,1, 100).getList();
		model.addAttribute("list", list);
		model.addAttribute("school", school);
		return "activity/school_edit";
	}
	
	@RequestMapping("/activity/school_update.do")
	public String schoolUpdate(HttpServletRequest request, ModelMap model,ActivitySchool school,Integer districkid) {
		ActivityDistrick districk=districkMng.findById(districkid);
		school.setDistrick(districk);
		
		Updater<ActivitySchool> updater=new Updater<ActivitySchool>(school);
		schoolMng.updateByUpdater(updater);
		
		return schoolList(1, request, model);
	}
	
	@RequestMapping("/activity/school_delete.do")
	public String schoolDelete(HttpServletRequest request, ModelMap model,String ids) {
		String []idsstr=ids.split(",");
		for(String id:idsstr){
			schoolMng.deleteById(Integer.valueOf(id));
		}
		return schoolList(1, request, model);
	}
	/*-------------学校增删改查管理结束------------------*/
	
	
	@Autowired
	private ActivityCountryMng countryMng; 
	@Autowired
	private ActivityProvinceMng provinceMng; 
	@Autowired
	private ActivityCityMng cityMng; 
	@Autowired
	private ActivitySchoolMng schoolMng; 
	@Autowired
	private ActivityDistrickMng districkMng; 
}