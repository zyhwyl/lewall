package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.AcUserTeamTagDao;
import com.jeecms.cms.dao.main.ActivityCountryDao;
import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ChannelExtDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserTeamTag;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.manager.main.AcUserTeamTagMng;
import com.jeecms.cms.manager.main.ActivityCountryMng;
import com.jeecms.cms.manager.main.ActivityMng;
import com.jeecms.cms.manager.main.ChannelExtMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class AcUserTeamTagMngImpl implements AcUserTeamTagMng {

	private AcUserTeamTagDao dao;

	@Autowired
	public void setDao(AcUserTeamTagDao dao) {
		this.dao = dao;
	}

	@Override
	public AcUserTeamTag save(AcUserTeamTag bean) {
		return dao.save(bean);
	}

	@Override
	public AcUserTeamTag updateByUpdater(Updater<AcUserTeamTag> updater) {
		return dao.updateByUpdater(updater);
	}

	@Override
	public AcUserTeamTag deleteById(Integer id) {
		return dao.deleteById(id);
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize) {
		return dao.getPaginationByProperities(keys, values, pageNo, pageSize);
	}

	@Override
	public AcUserTeamTag findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public List getListByProperities(String[] keys,
			Object[] values) {
		return dao.getListByProperities(keys, values);
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize, String order) {
		return dao.getPaginationByProperities(keys, values, pageNo, pageSize, order);
	}

	@Override
	public AcUserTeamTag findByName(String tagName) {
		return dao.findByName(tagName);
	}

}