package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.ActivityLiveDao;
import com.jeecms.cms.dao.main.ActivityCountryDao;
import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ChannelExtDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityLive;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.manager.main.ActivityLiveMng;
import com.jeecms.cms.manager.main.ActivityCountryMng;
import com.jeecms.cms.manager.main.ActivityMng;
import com.jeecms.cms.manager.main.ChannelExtMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class ActivityLiveMngImpl implements ActivityLiveMng {

	private ActivityLiveDao dao;

	@Autowired
	public void setDao(ActivityLiveDao dao) {
		this.dao = dao;
	}

	@Override
	public ActivityLive save(ActivityLive bean) {
		return dao.save(bean);
	}

	@Override
	public ActivityLive updateByUpdater(Updater<ActivityLive> updater) {
		return dao.updateByUpdater(updater);
	}

	@Override
	public ActivityLive deleteById(Integer id) {
		return dao.deleteById(id);
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize) {
		return dao.getPaginationByProperities(keys, values, pageNo, pageSize);
	}

	@Override
	public ActivityLive findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<ActivityLive> getListByProperities(String[] keys,
			Object[] values) {
		return dao.getListByProperities(keys, values);
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