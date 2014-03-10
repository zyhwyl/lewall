package com.jeecms.cms.manager.main;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserSay;
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
public interface AcUserSayMng {
	public AcUserSay save(AcUserSay bean);
	public AcUserSay updateByUpdater(Updater<AcUserSay> updater);
	public AcUserSay deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public AcUserSay findById(Integer id);
	public List<AcUserSay> getListByProperities(String []keys,Object []values);
	public Integer getCountByProperities(String keys,Object values);
	public Integer getCountByProperities(String []keys,Object []values);
}