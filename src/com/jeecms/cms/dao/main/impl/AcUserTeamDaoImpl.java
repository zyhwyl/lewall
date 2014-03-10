package com.jeecms.cms.dao.main.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.main.AcUserTeamDao;
import com.jeecms.cms.dao.main.ActivityCountryDao;
import com.jeecms.cms.dao.main.ActivityDao;
import com.jeecms.cms.dao.main.ChannelDao;
import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserTeam;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Repository
public class AcUserTeamDaoImpl extends HibernateBaseDao<AcUserTeam, Integer>
		implements AcUserTeamDao {

	@Override
	public AcUserTeam save(AcUserTeam bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	public AcUserTeam deleteById(Integer id) {
		AcUserTeam entity=super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<AcUserTeam> getEntityClass() {
		return AcUserTeam.class;
	}

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize) {
		Finder f = Finder.create("from AcUserTeam bean");
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
	public AcUserTeam findById(Integer id) {
		return (AcUserTeam)getSession().get(AcUserTeam.class, id);
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

	@Override
	public Pagination getPaginationByProperities(String[] keys,
			Object[] values, int pageNo, int pageSize, String order) {
		Finder f = Finder.create("from AcUserTeam bean");
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

	@Override
	public Integer countUserJoinTeamByUserid(Integer userid) {
		Query query = getSession().createSQLQuery("select count(*) from ac_user_team_member where aut_userid=:userid and 1=1");
		query.setParameter("userid",userid);
		Integer joinTeamNum=((Number)query.uniqueResult()).intValue();
		return joinTeamNum;
	}

	@Override
	public Integer countUserCreateTeamByUserid(Integer userid) {
		Query query = getSession().createSQLQuery("select count(*) from ac_user_team where team_createuser=:userid and 1=1");
		query.setParameter("userid",userid);
		Integer createTeamNum=((Number)query.uniqueResult()).intValue();
		return createTeamNum;
	}
}