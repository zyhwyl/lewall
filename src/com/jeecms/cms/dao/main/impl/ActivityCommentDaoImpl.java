package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.ActivityCommentDao;
import com.jeecms.cms.dao.main.ActivityCountryDao;
import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityComment;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Repository
public class ActivityCommentDaoImpl extends HibernateBaseDao<ActivityComment, Integer>
		implements ActivityCommentDao {

	@Override
	public ActivityComment save(ActivityComment bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public ActivityComment deleteById(Integer id) {
		ActivityComment entity=super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ActivityComment> getEntityClass() {
		return ActivityComment.class;
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize) {
		Finder f = Finder.create("from ActivityComment bean");
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
	public ActivityComment findById(Integer id) {
		return (ActivityComment)getSession().get(ActivityComment.class, id);
	}

	@Override
	public List getListByProperities(String[] keys,
			Object[] values) {
		return getListByProperities(keys,values,null);
	} 
	@Override
	public List getListByProperities(String[] keys, Object[] values,
			String order) {
		String sql = "from ActivityComment ";
		sql+=" where ";
		if (keys!=null&&keys.length>0) {
			int i=0;
			for(String key:keys){
				sql+=key+"=:"+key+" AND ";
				i++;
			}	
		}
		sql+="1=1"; 
		if(order!=null&&!order.equals("")){
			sql+=" ORDER BY "+order;
		} 
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
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize, String order) {
		Finder f = Finder.create("from ActivityComment bean");
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
		if(order!=null&&!order.equals("")){
			f.append(" order by "+order);
		}
		return find(f, pageNo, pageSize);
	}

}