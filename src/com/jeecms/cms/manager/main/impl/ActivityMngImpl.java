package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ChannelExtDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.manager.main.ActivityMng;
import com.jeecms.cms.manager.main.ChannelExtMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class ActivityMngImpl implements ActivityMng {

	private ActivityDao dao;

	@Autowired
	public void setDao(ActivityDao dao) {
		this.dao = dao;
	}

	@Override
	public Activity save(Activity bean) {
		return dao.save(bean);
	}

	@Override
	public Activity updateByUpdater(Updater<Activity> updater) {
		return dao.updateByUpdater(updater);
	}

	@Override
	public Activity deleteById(Integer id) {
		return dao.deleteById(id);
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize) {
		return dao.getPaginationByProperities(keys, values, pageNo, pageSize);
	}

	@Override
	public Activity findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize, String order) {
		return dao.getPaginationByProperities(keys, values, pageNo, pageSize, order);
	}

	@Override
	public Integer getCountByProperities(String keys, Object values) {
		return dao.getCountByProperities(keys, values);
	}

	@Override
	public Integer getCountByProperities(String[] keys, Object[] values) {
		return dao.getCountByProperities(keys, values);
	}
}