package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.ActivityPictureDao;
import com.jeecms.cms.dao.main.ActivityCountryDao;
import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.ActivityPicture;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Repository
public class ActivityPictureDaoImpl extends HibernateBaseDao<ActivityPicture, Integer>
		implements ActivityPictureDao {

	@Override
	public ActivityPicture save(ActivityPicture bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public ActivityPicture deleteById(Integer id) {
		ActivityPicture entity=super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ActivityPicture> getEntityClass() {
		return ActivityPicture.class;
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize) {
		Finder f = Finder.create("from ActivityPicture bean");
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
	public ActivityPicture findById(Integer id) {
		return (ActivityPicture)getSession().get(ActivityPicture.class, id);
	}

	@Override
	public List getListByProperities(String[] keys,
			Object[] values) {
		
		String sql = "select ap_imgpath from activity_picture ";
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