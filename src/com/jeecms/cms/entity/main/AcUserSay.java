package com.jeecms.cms.entity.main;

import java.sql.Timestamp;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.jeecms.cms.entity.main.base.BaseAcUserSay;



public class AcUserSay extends BaseAcUserSay {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcUserSay () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcUserSay (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcUserSay (
		java.lang.String id,
		java.lang.Integer sayUser,
		java.lang.String sayContent,
		java.util.Date sayDate) {

		super (
			id,
			sayUser,
			sayContent,
			sayDate);
	}

/*[CONSTRUCTOR MARKER END]*/
	//生成主键
	public void getUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = StringUtils.remove(uuid, '-');
		this.setId(uuid);
		this.setSayDate(new Timestamp(System.currentTimeMillis()));
	}


}