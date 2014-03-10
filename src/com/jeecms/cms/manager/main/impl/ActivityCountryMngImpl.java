package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.ActivityCountryDao;
import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ChannelExtDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.manager.main.ActivityCountryMng;
import com.jeecms.cms.manager.main.ActivityMng;
import com.jeecms.cms.manager.main.ChannelExtMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class ActivityCountryMngImpl implements ActivityCountryMng {

	private ActivityCountryDao dao;

	@Autowired
	public void setDao(ActivityCountryDao dao) {
		this.dao = dao;
	}

	@Override
	public ActivityCountry save(ActivityCountry bean) {
		return dao.save(bean);
	}

	@Override
	public ActivityCountry updateByUpdater(Updater<ActivityCountry> updater) {
		return dao.updateByUpdater(updater);
	}

	@Override
	public ActivityCountry deleteById(Integer id) {
		return dao.deleteById(id);
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize) {
		return dao.getPaginationByProperities(keys, values, pageNo, pageSize);
	}

	@Override
	public ActivityCountry findById(Integer id) {
		return dao.findById(id);
	}

}