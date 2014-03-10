package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityWatch;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.ActivityWatch;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface ActivityWatchDao {
	public ActivityWatch save(ActivityWatch bean);
	public ActivityWatch updateByUpdater(Updater<ActivityWatch> updater);
	public ActivityWatch deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public ActivityWatch findById(Integer id);
	public List getListByProperities(String []keys,Object []values);
	public Integer getCountByProperities(String keys,Object values);
}