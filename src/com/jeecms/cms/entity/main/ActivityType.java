package com.jeecms.cms.entity.main;

import java.sql.Timestamp;

import com.jeecms.cms.entity.main.base.BaseActivityType;



public class ActivityType extends BaseActivityType {
	private static final long serialVersionUID = 1L;
	/**
	 * 活动类型启用状态
	 * 1：代表启用 0：代表停用
	 * */
	public static final Integer ACTICITY_TYPE_ON=1;
	public static final Integer ACTICITY_TYPE_OFF=0;
	

/*[CONSTRUCTOR MARKER BEGIN]*/
	public ActivityType () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ActivityType (java.lang.Integer id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/

	public void init(){
		this.setAtCreatedate(new Timestamp(System.currentTimeMillis()));
		this.setAtState(ACTICITY_TYPE_ON);
	}

}