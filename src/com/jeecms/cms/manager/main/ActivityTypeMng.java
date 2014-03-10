package com.jeecms.cms.manager.main;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityType;
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
public interface ActivityTypeMng {
	public ActivityType save(ActivityType bean);
	public ActivityType updateByUpdater(Updater<ActivityType> updater);
	public ActivityType deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public ActivityType findById(Integer id);
}