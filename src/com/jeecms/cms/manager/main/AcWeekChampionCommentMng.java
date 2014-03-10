package com.jeecms.cms.manager.main;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.AcWeekChampionComment;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.entity.main.ChannelTxt;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcWeekChampionCommentMng {
	public AcWeekChampionComment save(AcWeekChampionComment bean);
	public AcWeekChampionComment updateByUpdater(Updater<AcWeekChampionComment> updater);
	public AcWeekChampionComment deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public AcWeekChampionComment findById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public List<AcWeekChampionComment> getListByProperities(String []keys,Object []values);
}