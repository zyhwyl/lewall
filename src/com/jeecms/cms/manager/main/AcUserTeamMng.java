package com.jeecms.cms.manager.main;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.AcUserTeamMember;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserTeam;
import com.jeecms.cms.entity.main.ActivityCountry;
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
public interface AcUserTeamMng {
	public AcUserTeam save(AcUserTeam bean);
	public AcUserTeam updateByUpdater(Updater<AcUserTeam> updater);
	public AcUserTeam deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public AcUserTeam findById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public List<AcUserTeamMember> getListByProperities(String []keys,Object []values);
	public Integer countUserJoinTeamByUserid(Integer userid);
	public Integer countUserCreateTeamByUserid(Integer userid);
}