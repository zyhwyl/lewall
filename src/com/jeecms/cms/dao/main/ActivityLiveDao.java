package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityLive;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.ActivityLive;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface ActivityLiveDao {
	public ActivityLive save(ActivityLive bean);
	public ActivityLive updateByUpdater(Updater<ActivityLive> updater);
	public ActivityLive deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public ActivityLive findById(Integer id);
	public List<ActivityLive> getListByProperities(String []keys,Object []values);
	public Integer getCountByProperities(String keys,Object values);
	public Integer getCountByProperities(String []keys,Object []values);
}