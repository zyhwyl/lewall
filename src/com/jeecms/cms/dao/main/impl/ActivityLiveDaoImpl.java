package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.ActivityLiveDao;
import com.jeecms.cms.dao.main.ActivityCountryDao;
import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityLive;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Repository
public class ActivityLiveDaoImpl extends HibernateBaseDao<ActivityLive, Integer>
		implements ActivityLiveDao {

	@Override
	public ActivityLive save(ActivityLive bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public ActivityLive deleteById(Integer id) {
		ActivityLive entity=super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ActivityLive> getEntityClass() {
		return ActivityLive.class;
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize) {
		Finder f = Finder.create("from ActivityLive bean");
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
		getSession().setFlushMode(FlushMode.MANUAL); 
		return find(f, pageNo, pageSize);
	}

	@Override
	public ActivityLive findById(Integer id) {
		return (ActivityLive)getSession().get(ActivityLive.class, id);
	}

	@Override
	public List<ActivityLive> getListByProperities(String[] keys,
			Object[] values) {
		
		String sql = "from ActivityLive ";
		sql+=" where ";
		if (keys!=null&&keys.length>0) {
			int i=0;
			for(String key:keys){
				sql+=key+"=:"+key+" AND ";
				i++;
			}	
		}
		sql+="1=1"; 
		Query query=getSession().createQuery(sql);
		if (keys!=null&&keys.length>0) {
			int i=0;
			for(String key:keys){
				query.setParameter(key, values[i]);
				i++;
			}	
		}

		return query.list();
	}

	@Override
	public Integer getCountByProperities(String keys, Object values) {
		Query query = getSession().createSQLQuery("select count(*) from activity_join where "+keys+"=:"+keys+" and 1=1");
		query.setParameter(keys, values);
		return ((Number)query.uniqueResult()).intValue();
	}

	@Override
	public Integer getCountByProperities(String[] keys, Object[] values) {
		String sql="select count(*) from activity_join ";
		sql+=" where ";
		if (keys!=null&&keys.length>0) {
			int i=0;
			for(String key:keys){
				sql+=key+"=:"+key+" AND ";
				i++;
			}	
		}
		sql+=" 1=1";
		Query query = getSession().createSQLQuery(sql);
		if (keys!=null&&keys.length>0) {
			int i=0;
			for(String key:keys){
				query.setParameter(key, values[i]);
				i++;
			}	
		}
		return ((Number)query.uniqueResult()).intValue();
	}
}