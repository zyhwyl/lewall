package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcWeekChampionComment;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.AcWeekChampionComment;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcWeekChampionCommentDao {
	public AcWeekChampionComment save(AcWeekChampionComment bean);
	public AcWeekChampionComment updateByUpdater(Updater<AcWeekChampionComment> updater);
	public AcWeekChampionComment deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public AcWeekChampionComment findById(Integer id);
	public List getListByProperities(String []keys,Object []values);
}