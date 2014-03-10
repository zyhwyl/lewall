package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserTeamFans;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.AcUserTeamFans;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcUserTeamFansDao {
	public AcUserTeamFans save(AcUserTeamFans bean);
	public AcUserTeamFans updateByUpdater(Updater<AcUserTeamFans> updater);
	public AcUserTeamFans deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public AcUserTeamFans findById(Integer id);
	public List getListByProperities(String []keys,Object []values);
}