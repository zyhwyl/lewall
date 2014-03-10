package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.ActivityCountryDao;
import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Repository
public class ActivityCountryDaoImpl extends HibernateBaseDao<ActivityCountry, Integer>
		implements ActivityCountryDao {

	@Override
	public ActivityCountry save(ActivityCountry bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public ActivityCountry deleteById(Integer id) {
		ActivityCountry entity=super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ActivityCountry> getEntityClass() {
		return ActivityCountry.class;
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize) {
		Finder f = Finder.create("from ActivityCountry bean");
		f.append(" where ");
		if (keys!=null&&keys.length>0) {
			int i=0;
			for(String key:keys){
				f.append(key+"=:"+key+" AND ");
				f.setParam(key,values[i]);
				i++;
			}	
		}
		f.append("1=1"); 
		return find(f, pageNo, pageSize);
	}

	@Override
	public ActivityCountry findById(Integer id) {
		return (ActivityCountry)getSession().get(ActivityCountry.class, id);
	}
}