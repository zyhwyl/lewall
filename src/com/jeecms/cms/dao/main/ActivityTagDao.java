package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityTag;
import com.jeecms.common.hibernate3.Updater;

public interface ActivityTagDao {
	public ActivityTag save(ActivityTag bean);
	public ActivityTag updateByUpdater(Updater<ActivityTag> updater);
	public ActivityTag deleteById(Integer id);
	public ActivityTag findById(Integer id);
	public ActivityTag findByName(String name);
}