package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserTeamTag;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.AcUserTeamTag;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcUserTeamTagDao {
	public AcUserTeamTag save(AcUserTeamTag bean);
	public AcUserTeamTag updateByUpdater(Updater<AcUserTeamTag> updater);
	public AcUserTeamTag deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public AcUserTeamTag findById(Integer id);
	public AcUserTeamTag findByName(String tagName);
	public List getListByProperities(String []keys,Object []values);
}