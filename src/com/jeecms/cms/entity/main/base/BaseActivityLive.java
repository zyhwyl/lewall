package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the activity_live table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="activity_live"
 */

public abstract class BaseActivityLive  implements Serializable {

	public static String REF = "ActivityLive";
	public static String PROP_LIVE_USER = "LiveUser";
	public static String PROP_LIVE_CONTENT = "LiveContent";
	public static String PROP_LIVE_ACTIVITY = "LiveActivity";
	public static String PROP_LIVE_IMGS = "LiveImgs";
	public static String PROP_ID = "Id";
	public static String PROP_LIVE_DATE = "LiveDate";


	// constructors
	public BaseActivityLive () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivityLive (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.Integer liveActivity;
	private java.lang.Integer liveUser;
	private java.lang.String liveContent;
	private java.lang.String liveImgs;
	private java.util.Date liveDate;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="live_id"
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
	 * Return the value associated with the column: live_activity
	 */
	public java.lang.Integer getLiveActivity () {
		return liveActivity;
	}

	/**
	 * Set the value related to the column: live_activity
	 * @param liveActivity the live_activity value
	 */
	public void setLiveActivity (java.lang.Integer liveActivity) {
		this.liveActivity = liveActivity;
	}



	/**
	 * Return the value associated with the column: live_user
	 */
	public java.lang.Integer getLiveUser () {
		return liveUser;
	}

	/**
	 * Set the value related to the column: live_user
	 * @param liveUser the live_user value
	 */
	public void setLiveUser (java.lang.Integer liveUser) {
		this.liveUser = liveUser;
	}



	/**
	 * Return the value associated with the column: live_content
	 */
	public java.lang.String getLiveContent () {
		return liveContent;
	}

	/**
	 * Set the value related to the column: live_content
	 * @param liveContent the live_content value
	 */
	public void setLiveContent (java.lang.String liveContent) {
		this.liveContent = liveContent;
	}



	/**
	 * Return the value associated with the column: live_imgs
	 */
	public java.lang.String getLiveImgs () {
		return liveImgs;
	}

	/**
	 * Set the value related to the column: live_imgs
	 * @param liveImgs the live_imgs value
	 */
	public void setLiveImgs (java.lang.String liveImgs) {
		this.liveImgs = liveImgs;
	}



	/**
	 * Return the value associated with the column: live_date
	 */
	public java.util.Date getLiveDate () {
		return liveDate;
	}

	/**
	 * Set the value related to the column: live_date
	 * @param liveDate the live_date value
	 */
	public void setLiveDate (java.util.Date liveDate) {
		this.liveDate = liveDate;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ActivityLive)) return false;
		else {
			com.jeecms.cms.entity.main.ActivityLive activityLive = (com.jeecms.cms.entity.main.ActivityLive) obj;
			if (null == this.getId() || null == activityLive.getId()) return false;
			else return (this.getId().equals(activityLive.getId()));
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