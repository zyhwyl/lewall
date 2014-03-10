package com.jeecms.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ChannelExtDao;
import com.jeecms.cms.dao.main.TagToActivityDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.entity.main.TagToActivity;
import com.jeecms.cms.manager.main.ActivityMng;
import com.jeecms.cms.manager.main.ChannelExtMng;
import com.jeecms.cms.manager.main.TagToActivityMng;
import com.jeecms.common.hibernate3.Updater;

@Service
@Transactional
public class TagToActivityMngImpl implements TagToActivityMng {

	private TagToActivityDao dao;

	@Autowired
	public void setDao(TagToActivityDao dao) {
		this.dao = dao;
	}

	@Override
	public TagToActivity save(TagToActivity bean) {
		return dao.save(bean);
	}

	@Override
	public TagToActivity updateByUpdater(Updater<TagToActivity> updater) {
		return dao.updateByUpdater(updater);
	}

	@Override
	public TagToActivity deleteById(Integer id) {
		return dao.deleteById(id);
	}

	@Override
	public List getListByProperities(String[] keys, Object[] values) {
		return dao.getListByProperities(keys, values);
	}

}