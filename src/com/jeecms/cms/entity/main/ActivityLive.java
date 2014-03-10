package com.jeecms.cms.entity.main;

import java.sql.Timestamp;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.jeecms.cms.entity.main.base.BaseActivityLive;



public class ActivityLive extends BaseActivityLive {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ActivityLive () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ActivityLive (java.lang.String id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/

	//生成主键
	public void getUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = StringUtils.remove(uuid, '-');
		this.setId(uuid);
		this.setLiveDate(new Timestamp(System.currentTimeMillis()));
	}
}