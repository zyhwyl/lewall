package com.jeecms.cms.entity.main;

import com.jeecms.cms.entity.main.base.BaseCmsUserSchool;



public class CmsUserSchool extends BaseCmsUserSchool {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsUserSchool () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsUserSchool (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsUserSchool (
		java.lang.Integer id,
		com.jeecms.cms.entity.main.ActivitySchool school) {

		super (
			id,
			school);
	}

/*[CONSTRUCTOR MARKER END]*/


}