package com.jeecms.cms.entity.main;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.jeecms.cms.entity.main.base.BaseActivityPicture;



public class ActivityPicture extends BaseActivityPicture {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ActivityPicture () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ActivityPicture (java.lang.String id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/

	//生成主键
	public void getUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = StringUtils.remove(uuid, '-');
		this.setId(uuid);
	}
}