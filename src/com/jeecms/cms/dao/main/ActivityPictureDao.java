package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityPicture;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.ActivityPicture;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface ActivityPictureDao {
	public ActivityPicture save(ActivityPicture bean);
	public ActivityPicture updateByUpdater(Updater<ActivityPicture> updater);
	public ActivityPicture deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public ActivityPicture findById(Integer id);
	public List getListByProperities(String []keys,Object []values);
}