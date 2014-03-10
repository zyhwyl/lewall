package com.jeecms.cms.manager.main;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityWatch;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.entity.main.ChannelTxt;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 栏目管理接口
 * 
 * @author liufang
 * 
 */
public interface ActivityWatchMng {
	public ActivityWatch save(ActivityWatch bean);
	public ActivityWatch updateByUpdater(Updater<ActivityWatch> updater);
	public ActivityWatch deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public ActivityWatch findById(Integer id);
	public List<ActivityWatch> getListByProperities(String []keys,Object []values);
	public Integer getCountByProperities(String keys,Object values);
}