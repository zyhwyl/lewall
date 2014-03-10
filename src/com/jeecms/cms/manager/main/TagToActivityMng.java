package com.jeecms.cms.manager.main;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.jeecms.cms.entity.main.Activity;
import com.jeecms.cms.entity.main.Channel;
import com.jeecms.cms.entity.main.ChannelExt;
import com.jeecms.cms.entity.main.ChannelTxt;
import com.jeecms.cms.entity.main.TagToActivity;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 栏目管理接口
 * 
 * @author liufang
 * 
 */
public interface TagToActivityMng {
	public TagToActivity save(TagToActivity bean);
	public TagToActivity updateByUpdater(Updater<TagToActivity> updater);
	public TagToActivity deleteById(Integer id);
	public List getListByProperities(String []keys,Object []values);
}