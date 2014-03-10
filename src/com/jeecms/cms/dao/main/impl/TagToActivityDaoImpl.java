package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ActivityTagDao;
import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.cms.dao.main.TagToActivityDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityTag;
import com.jeecms.cms.entity.main.TagToActivity;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Repository
public class TagToActivityDaoImpl extends HibernateBaseDao<TagToActivity, Integer>
		implements TagToActivityDao {

	@Override
	public TagToActivity save(TagToActivity bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public TagToActivity deleteById(Integer id) {
		TagToActivity entity=super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<TagToActivity> getEntityClass() {
		return TagToActivity.class;
	}

	@Override
	public List getListByProperities(String[] keys, Object[] values) {
		String sql = "select tag_id from tag_to_activity ";
		sql+=" where ";
		if (keys!=null&&keys.length>0) {
			int i=0;
			for(String key:keys){
				sql+=key+"=:"+key+" AND ";
				i++;
			}	
		}
		sql+="1=1"; 
		Query query=getSession().createSQLQuery(sql);
		if (keys!=null&&keys.length>0) {
			int i=0;
			for(String key:keys){
				query.setParameter(key, values[i]);
				i++;
			}	
		}
		
		
		return query.list();
	}
}