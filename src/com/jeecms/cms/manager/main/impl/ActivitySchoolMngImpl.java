package com.jeecms.cms.manager.main.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.ActivitySchoolDao;
import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ChannelExtDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivitySchool;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.manager.main.ActivitySchoolMng;
import com.jeecms.cms.manager.main.ActivityMng;
import com.jeecms.cms.manager.main.ChannelExtMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class ActivitySchoolMngImpl implements ActivitySchoolMng {

	private ActivitySchoolDao dao;

	@Autowired
	public void setDao(ActivitySchoolDao dao) {
		this.dao = dao;
	}

	@Override
	public ActivitySchool save(ActivitySchool bean) {
		return dao.save(bean);
	}

	@Override
	public ActivitySchool updateByUpdater(Updater<ActivitySchool> updater) {
		return dao.updateByUpdater(updater);
	}

	@Override
	public ActivitySchool deleteById(Integer id) {
		return dao.deleteById(id);
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize) {
		return dao.getPaginationByProperities(keys, values, pageNo, pageSize);
	}

	@Override
	public ActivitySchool findById(Integer id) {
		return dao.findById(id);
	}

}