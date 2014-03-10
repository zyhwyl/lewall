package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.ActivityProvince;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface ActivityProvinceDao {
	public ActivityProvince save(ActivityProvince bean);
	public ActivityProvince updateByUpdater(Updater<ActivityProvince> updater);
	public ActivityProvince deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public ActivityProvince findById(Integer id);
}