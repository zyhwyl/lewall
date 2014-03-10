package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ActivityTagDao;
import com.jeecms.cms.dao.main.ChannelExtDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityTag;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.manager.main.ActivityMng;
import com.jeecms.cms.manager.main.ActivityTagMng;
import com.jeecms.cms.manager.main.ChannelExtMng;
import com.jeecms.common.hibernate3.Updater;

@Service
@Transactional
public class ActivityTagMngImpl implements ActivityTagMng {

	private ActivityTagDao dao;

	@Autowired
	public void setDao(ActivityTagDao dao) {
		this.dao = dao;
	}

	@Override
	public ActivityTag save(ActivityTag bean) {
		return dao.save(bean);
	}

	@Override
	public ActivityTag updateByUpdater(Updater<ActivityTag> updater) {
		return dao.updateByUpdater(updater);
	}

	@Override
	public ActivityTag deleteById(Integer id) {
		return dao.deleteById(id);
	}

	@Override
	public ActivityTag findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public ActivityTag findById(Integer id) {
		return dao.findById(id);
	}
}