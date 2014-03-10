package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ActivityTagDao;
import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityTag;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Repository
public class ActivityTagDaoImpl extends HibernateBaseDao<ActivityTag, Integer>
		implements ActivityTagDao {

	@Override
	public ActivityTag save(ActivityTag bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public ActivityTag deleteById(Integer id) {
		ActivityTag entity=super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ActivityTag> getEntityClass() {
		return ActivityTag.class;
	}

	@Override
	public ActivityTag findByName(String name) {
		String sql="From ActivityTag where TagName=:TagName";
		Query query=getSession().createQuery(sql);
		query.setParameter("TagName", name);
		if(query.list().size()>0){
			return (ActivityTag)query.list().get(0);
		}
		return null;
	}

	@Override
	public ActivityTag findById(Integer id) {
		return (ActivityTag)getSession().get(ActivityTag.class, id);
	}

}