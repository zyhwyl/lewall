package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserTeam;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.AcUserTeam;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcUserTeamDao {
	public AcUserTeam save(AcUserTeam bean);
	public AcUserTeam updateByUpdater(Updater<AcUserTeam> updater);
	public AcUserTeam deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public AcUserTeam findById(Integer id);
	public List getListByProperities(String []keys,Object []values);
	public Integer countUserJoinTeamByUserid(Integer userid);
	public Integer countUserCreateTeamByUserid(Integer userid);
}