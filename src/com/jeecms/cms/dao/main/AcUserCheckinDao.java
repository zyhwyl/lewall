package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserCheckin;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.AcUserCheckin;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcUserCheckinDao {
	public AcUserCheckin save(AcUserCheckin bean);
	public AcUserCheckin updateByUpdater(Updater<AcUserCheckin> updater);
	public AcUserCheckin deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public AcUserCheckin findById(Integer id);
	public List getListByProperities(String []keys,Object []values);
	public boolean isCheckinToday(Integer userid);
}