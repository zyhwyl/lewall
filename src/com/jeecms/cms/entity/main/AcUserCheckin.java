package com.jeecms.cms.entity.main;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.jeecms.cms.entity.main.base.BaseAcUserCheckin;



public class AcUserCheckin extends BaseAcUserCheckin {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcUserCheckin () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcUserCheckin (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcUserCheckin (
		java.lang.String id,
		java.lang.Integer checkUser,
		java.util.Date checkDate) {

		super (
			id,
			checkUser,
			checkDate);
	}

/*[CONSTRUCTOR MARKER END]*/
	//生成主键
	public void getUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = StringUtils.remove(uuid, '-');
		this.setId(uuid);
	}

}