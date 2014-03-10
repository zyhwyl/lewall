package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the activity_watch table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="activity_watch"
 */

public abstract class BaseActivityWatch  implements Serializable {

	public static String REF = "ActivityWatch";
	public static String PROP_AW_ACTIVITYID = "awActivityid";
	public static String PROP_AW_WATCHTIME = "AwWatchtime";
	public static String PROP_AW_USERID = "awUserid";
	public static String PROP_ID = "Id";
	public static String PROP_AW_USERIMG = "awUserimg";
	public static String PROP_AW_USERNAME = "awUsername";


	// constructors
	public BaseActivityWatch () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivityWatch (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.util.Date awWatchtime;
	private java.lang.Integer awActivityid;
	private java.lang.Integer awUserid;
	private java.lang.String awUsername;
	private java.lang.String awUserimg;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="aw_id"
     */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: aw_watchtime
	 */
	public java.util.Date getAwWatchtime () {
		return awWatchtime;
	}

	/**
	 * Set the value related to the column: aw_watchtime
	 * @param awWatchtime the aw_watchtime value
	 */
	public void setAwWatchtime (java.util.Date awWatchtime) {
		this.awWatchtime = awWatchtime;
	}



	/**
	 * Return the value associated with the column: aw_activityid
	 */
	public java.lang.Integer getAwActivityid () {
		return awActivityid;
	}

	/**
	 * Set the value related to the column: aw_activityid
	 * @param awActivityid the aw_activityid value
	 */
	public void setAwActivityid (java.lang.Integer awActivityid) {
		this.awActivityid = awActivityid;
	}



	/**
	 * Return the value associated with the column: aw_userid
	 */
	public java.lang.Integer getAwUserid () {
		return awUserid;
	}

	/**
	 * Set the value related to the column: aw_userid
	 * @param awUserid the aw_userid value
	 */
	public void setAwUserid (java.lang.Integer awUserid) {
		this.awUserid = awUserid;
	}



	/**
	 * Return the value associated with the column: aw_username
	 */
	public java.lang.String getAwUsername () {
		return awUsername;
	}

	/**
	 * Set the value related to the column: aw_username
	 * @param awUsername the aw_username value
	 */
	public void setAwUsername (java.lang.String awUsername) {
		this.awUsername = awUsername;
	}



	/**
	 * Return the value associated with the column: aw_userimg
	 */
	public java.lang.String getAwUserimg () {
		return awUserimg;
	}

	/**
	 * Set the value related to the column: aw_userimg
	 * @param awUserimg the aw_userimg value
	 */
	public void setAwUserimg (java.lang.String awUserimg) {
		this.awUserimg = awUserimg;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ActivityWatch)) return false;
		else {
			com.jeecms.cms.entity.main.ActivityWatch activityWatch = (com.jeecms.cms.entity.main.ActivityWatch) obj;
			if (null == this.getId() || null == activityWatch.getId()) return false;
			else return (this.getId().equals(activityWatch.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}