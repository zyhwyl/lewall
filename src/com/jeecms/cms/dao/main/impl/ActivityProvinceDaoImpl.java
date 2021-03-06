package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.ActivityCountryDao;
import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ActivityProvinceDao;
import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.ActivityProvince;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Repository
public class ActivityProvinceDaoImpl extends HibernateBaseDao<ActivityProvince, Integer>
		implements ActivityProvinceDao {

	@Override
	public ActivityProvince save(ActivityProvince bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public ActivityProvince deleteById(Integer id) {
		ActivityProvince entity=super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ActivityProvince> getEntityClass() {
		return ActivityProvince.class;
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize) {
		Finder f = Finder.create("from ActivityProvince bean");
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
	public ActivityProvince findById(Integer id) {
		return (ActivityProvince)getSession().get(ActivityProvince.class, id);
	}
}