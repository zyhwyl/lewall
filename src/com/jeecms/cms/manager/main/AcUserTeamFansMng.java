package com.jeecms.cms.manager.main;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserTeamFans;
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
public interface AcUserTeamFansMng {
	public AcUserTeamFans save(AcUserTeamFans bean);
	public AcUserTeamFans updateByUpdater(Updater<AcUserTeamFans> updater);
	public AcUserTeamFans deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public AcUserTeamFans findById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public List getListByProperities(String []keys,Object []values);
}