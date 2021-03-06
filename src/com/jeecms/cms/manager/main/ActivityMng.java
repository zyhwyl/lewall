package com.jeecms.cms.manager.main;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.Activity;
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
public interface ActivityMng {
	/**
	 * 发布一个活动
	 * 
	 * */
	public Activity save(Activity bean);
	public Activity updateByUpdater(Updater<Activity> updater);
	public Activity deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public Activity findById(Integer id);
	public Integer getCountByProperities(String keys,Object values);
	public Integer getCountByProperities(String []keys,Object []values);
	
}