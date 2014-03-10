package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.AcUserFriend;
import com.jeecms.cms.entity.main.ActivityCountry;
import com.jeecms.cms.entity.main.AcUserFriend;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

public interface AcUserFriendDao {
	public AcUserFriend save(AcUserFriend bean);
	public AcUserFriend updateByUpdater(Updater<AcUserFriend> updater);
	public AcUserFriend deleteById(Integer id);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize);
	public Pagination getPaginationByProperities(String []keys,Object []values,int pageNo,int pageSize,String order);
	public AcUserFriend findById(Integer id);
	public List getListByProperities(String []keys,Object []values);
}