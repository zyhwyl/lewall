package com.jeecms.cms.manager.main;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivitySchool;
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
public interface ActivitySchoolMng {
	public ActivitySchool save(ActivitySchool bean);
	public ActivitySchool updateByUpdater(Updater<ActivitySchool> updater);
	public ActivitySchool deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public ActivitySchool findById(Integer id);
}