package com.jeecms.cms.entity.main;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.jeecms.cms.entity.main.base.BaseTagToActivity;



public class TagToActivity extends BaseTagToActivity {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TagToActivity () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TagToActivity (java.lang.String id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/

	//得到主键
	public void getUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = StringUtils.remove(uuid, '-');
		this.setId(uuid);
	}

}