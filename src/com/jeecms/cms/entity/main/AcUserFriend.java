package com.jeecms.cms.entity.main;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.jeecms.cms.entity.main.base.BaseAcUserFriend;



public class AcUserFriend extends BaseAcUserFriend {
	private static final long serialVersionUID = 1L;

	public final static Integer STATE_APPLYING=0;
	public final static Integer STATE_ACCEPTING=1;
	public final static Integer STATE_REJECTING=2;
	
/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcUserFriend () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcUserFriend (java.lang.String id) {
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