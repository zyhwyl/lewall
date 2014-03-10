package com.jeecms.cms.manager.main;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.AcWeekChampionExt;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.entity.main.ChannelTxt;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcWeekChampionExtMng {
	public AcWeekChampionExt save(AcWeekChampionExt bean);
	public AcWeekChampionExt updateByUpdater(Updater<AcWeekChampionExt> updater);
	public AcWeekChampionExt deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public AcWeekChampionExt findById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public List<AcWeekChampionExt> getListByProperities(String []keys,Object []values);
}