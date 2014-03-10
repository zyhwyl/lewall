package com.jeecms.cms.entity.main;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.jeecms.cms.entity.main.base.BaseActivityJoin;



public class ActivityJoin extends BaseActivityJoin {
	private static final long serialVersionUID = 1L;

	public static final Integer JOIN_REJECT=0;
	public static final Integer JOIN_IN=1;
	public static final Integer JOIN_INVITING=2;
	
	private AcUserTeam joinTeam;
	
	
	public AcUserTeam getJoinTeam() {
		return joinTeam;
	}

	public void setJoinTeam(AcUserTeam joinTeam) {
		this.joinTeam = joinTeam;
	}

	/*[CONSTRUCTOR MARKER BEGIN]*/
	public ActivityJoin () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ActivityJoin (java.lang.String id) {
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