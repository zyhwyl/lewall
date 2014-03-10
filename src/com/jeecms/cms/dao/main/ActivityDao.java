package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface ActivityDao {
	public Activity save(Activity bean);
	public Activity updateByUpdater(Updater<Activity> updater);
	public Activity deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public Activity findById(Integer id);
	public Integer getCountByProperities(String keys,Object values);
	public Integer getCountByProperities(String []keys,Object []values);
}