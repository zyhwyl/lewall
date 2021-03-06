package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserTeamExt;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.AcUserTeamExt;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcUserTeamExtDao {
	public AcUserTeamExt save(AcUserTeamExt bean);
	public AcUserTeamExt updateByUpdater(Updater<AcUserTeamExt> updater);
	public AcUserTeamExt deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public AcUserTeamExt findById(Integer id);
	public List getListByProperities(String []keys,Object []values);
}