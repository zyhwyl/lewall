package com.jeecms.cms.entity.main;

import java.sql.Timestamp;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.jeecms.cms.entity.main.base.BaseAcUserTeamFans;



public class AcUserTeamFans extends BaseAcUserTeamFans {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcUserTeamFans () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcUserTeamFans (java.lang.String id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/
	//生成主键
	public void getUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = StringUtils.remove(uuid, '-');
		this.setId(uuid);
		this.setAcfDate(new Timestamp(System.currentTimeMillis()));
	}

}