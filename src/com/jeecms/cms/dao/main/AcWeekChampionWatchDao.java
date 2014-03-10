package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcWeekChampionWatch;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.AcWeekChampionWatch;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcWeekChampionWatchDao {
	public AcWeekChampionWatch save(AcWeekChampionWatch bean);
	public AcWeekChampionWatch updateByUpdater(Updater<AcWeekChampionWatch> updater);
	public AcWeekChampionWatch deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public AcWeekChampionWatch findById(Integer id);
	public List getListByProperities(String []keys,Object []values);
}