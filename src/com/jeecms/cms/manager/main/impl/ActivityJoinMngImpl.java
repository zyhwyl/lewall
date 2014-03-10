package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.ActivityJoinDao;
import com.jeecms.cms.dao.main.ActivityCountryDao;
import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ChannelExtDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityJoin;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.manager.main.ActivityJoinMng;
import com.jeecms.cms.manager.main.ActivityCountryMng;
import com.jeecms.cms.manager.main.ActivityMng;
import com.jeecms.cms.manager.main.ChannelExtMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class ActivityJoinMngImpl implements ActivityJoinMng {

	private ActivityJoinDao dao;

	@Autowired
	public void setDao(ActivityJoinDao dao) {
		this.dao = dao;
	}

	@Override
	public ActivityJoin save(ActivityJoin bean) {
		return dao.save(bean);
	}

	@Override
	public ActivityJoin updateByUpdater(Updater<ActivityJoin> updater) {
		return dao.updateByUpdater(updater);
	}

	@Override
	public ActivityJoin deleteById(Integer id) {
		return dao.deleteById(id);
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize) {
		return dao.getPaginationByProperities(keys, values, pageNo, pageSize);
	}

	@Override
	public ActivityJoin findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List<ActivityJoin> getListByProperities(String[] keys,
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