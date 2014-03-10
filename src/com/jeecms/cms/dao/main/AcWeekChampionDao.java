package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcWeekChampion;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.AcWeekChampion;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcWeekChampionDao {
	public AcWeekChampion save(AcWeekChampion bean);
	public AcWeekChampion updateByUpdater(Updater<AcWeekChampion> updater);
	public AcWeekChampion deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public AcWeekChampion findById(Integer id);
	public List getListByProperities(String []keys,Object []values);
}