package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityComment;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.ActivityComment;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface ActivityCommentDao {
	public ActivityComment save(ActivityComment bean);
	public ActivityComment updateByUpdater(Updater<ActivityComment> updater);
	public ActivityComment deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public ActivityComment findById(Integer id);
	public List getListByProperities(String []keys,Object []values);
	public List getListByProperities(String []keys,Object []values,String order);
}