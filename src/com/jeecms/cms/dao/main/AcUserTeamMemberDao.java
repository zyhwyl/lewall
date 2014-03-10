package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserTeamMember;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.AcUserTeamMember;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcUserTeamMemberDao {
	public AcUserTeamMember save(AcUserTeamMember bean);
	public AcUserTeamMember updateByUpdater(Updater<AcUserTeamMember> updater);
	public AcUserTeamMember deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public AcUserTeamMember findById(String id);
	public List<AcUserTeamMember> getListByProperities(String []keys,Object []values);
}