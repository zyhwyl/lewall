package com.jeecms.cms.manager.main;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.AcUserCheckin;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.entity.main.ChannelTxt;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;


public interface AcUserCheckinMng {
	public AcUserCheckin save(AcUserCheckin bean);
	public AcUserCheckin updateByUpdater(Updater<AcUserCheckin> updater);
	public AcUserCheckin deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public AcUserCheckin findById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public List<AcUserCheckin> getListByProperities(String []keys,Object []values);
	public boolean isCheckinToday(Integer userid);
}