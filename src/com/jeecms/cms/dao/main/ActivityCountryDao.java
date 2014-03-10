package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface ActivityCountryDao {
	public ActivityCountry save(ActivityCountry bean);
	public ActivityCountry updateByUpdater(Updater<ActivityCountry> updater);
	public ActivityCountry deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public ActivityCountry findById(Integer id);
}