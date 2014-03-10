package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserSay;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.AcUserSay;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcUserSayDao {
	public AcUserSay save(AcUserSay bean);
	public AcUserSay updateByUpdater(Updater<AcUserSay> updater);
	public AcUserSay deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public AcUserSay findById(Integer id);
	public List<AcUserSay> getListByProperities(String []keys,Object []values);
	public Integer getCountByProperities(String keys,Object values);
	public Integer getCountByProperities(String []keys,Object []values);
}