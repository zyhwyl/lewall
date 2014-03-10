package com.jeecms.cms.dao.main;

import java.util.List;

import com.jeecms.cms.entity.main.TagToActivity;
import com.jeecms.common.hibernate3.Updater;

public interface TagToActivityDao {
	public TagToActivity save(TagToActivity bean);
	public TagToActivity updateByUpdater(Updater<TagToActivity> updater);
	public TagToActivity deleteById(Integer id);
	public List getListByProperities(String []keys,Object []values);
}