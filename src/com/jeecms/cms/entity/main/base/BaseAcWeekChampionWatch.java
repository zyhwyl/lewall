package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_week_champion_watch table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_week_champion_watch"
 */

public abstract class BaseAcWeekChampionWatch  implements Serializable {

	public static String REF = "AcWeekChampionWatch";
	public static String PROP_WATCH_USER_NAME = "WatchUserName";
	public static String PROP_CHAMPION_ID = "ChampionId";
	public static String PROP_ID = "Id";
	public static String PROP_WATCH_USER = "WatchUser";
	public static String PROP_WATCH_USER_IMG = "WatchUserImg";
	public static String PROP_WATCH_TIME = "WatchTime";


	// constructors
	public BaseAcWeekChampionWatch () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcWeekChampionWatch (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.util.Date watchTime;
	private java.lang.Integer watchUser;
	private java.lang.Integer championId;
	private java.lang.String watchUserName;
	private java.lang.String watchUserImg;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="watch_id"
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
	 * Return the value associated with the column: watch_time
	 */
	public java.util.Date getWatchTime () {
		return watchTime;
	}

	/**
	 * Set the value related to the column: watch_time
	 * @param watchTime the watch_time value
	 */
	public void setWatchTime (java.util.Date watchTime) {
		this.watchTime = watchTime;
	}



	/**
	 * Return the value associated with the column: watch_user
	 */
	public java.lang.Integer getWatchUser () {
		return watchUser;
	}

	/**
	 * Set the value related to the column: watch_user
	 * @param watchUser the watch_user value
	 */
	public void setWatchUser (java.lang.Integer watchUser) {
		this.watchUser = watchUser;
	}



	/**
	 * Return the value associated with the column: champion_id
	 */
	public java.lang.Integer getChampionId () {
		return championId;
	}

	/**
	 * Set the value related to the column: champion_id
	 * @param championId the champion_id value
	 */
	public void setChampionId (java.lang.Integer championId) {
		this.championId = championId;
	}



	/**
	 * Return the value associated with the column: watch_user_name
	 */
	public java.lang.String getWatchUserName () {
		return watchUserName;
	}

	/**
	 * Set the value related to the column: watch_user_name
	 * @param watchUserName the watch_user_name value
	 */
	public void setWatchUserName (java.lang.String watchUserName) {
		this.watchUserName = watchUserName;
	}



	/**
	 * Return the value associated with the column: watch_user_img
	 */
	public java.lang.String getWatchUserImg () {
		return watchUserImg;
	}

	/**
	 * Set the value related to the column: watch_user_img
	 * @param watchUserImg the watch_user_img value
	 */
	public void setWatchUserImg (java.lang.String watchUserImg) {
		this.watchUserImg = watchUserImg;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcWeekChampionWatch)) return false;
		else {
			com.jeecms.cms.entity.main.AcWeekChampionWatch acWeekChampionWatch = (com.jeecms.cms.entity.main.AcWeekChampionWatch) obj;
			if (null == this.getId() || null == acWeekChampionWatch.getId()) return false;
			else return (this.getId().equals(acWeekChampionWatch.getId()));
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