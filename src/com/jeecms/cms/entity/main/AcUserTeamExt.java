package com.jeecms.cms.entity.main;

import com.jeecms.cms.entity.main.base.BaseAcUserTeamExt;



public class AcUserTeamExt extends BaseAcUserTeamExt {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcUserTeamExt () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcUserTeamExt (java.lang.Integer id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/

	public void init(){
		this.setTeamEggs(0);
		this.setTeamFlowers(0);
		this.setTeamFans(0);
	}

}