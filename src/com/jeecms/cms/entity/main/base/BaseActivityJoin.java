package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the activity_join table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="activity_join"
 */

public abstract class BaseActivityJoin  implements Serializable {

	public static String REF = "ActivityJoin";
	public static String PROP_AJ_ISANONYMOUS = "AjIsanonymous";
	public static String PROP_AJ_TEAM = "AjTeam";
	public static String PROP_AJ_USERID = "AjUserid";
	public static String PROP_AJ_JOINTIME = "AjJointime";
	public static String PROP_AJ_USERNAME = "AjUsername";
	public static String PROP_AJ_ACTIVITYID = "AjActivityid";
	public static String PROP_AJ_WORDS = "AjWords";
	public static String PROP_ID = "Id";
	public static String PROP_AJ_USERIMG = "AjUserimg";
	public static String PROP_AJ_STATE = "AjState";


	// constructors
	public BaseActivityJoin () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivityJoin (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.util.Date ajJointime;
	private java.lang.String ajUsername;
	private java.lang.String ajUserimg;
	private java.lang.Integer ajState;
	private java.lang.Integer ajTeam;
	private java.lang.Integer ajIsanonymous;
	private java.lang.String ajWords;
	private java.lang.Integer ajActivityid;
	private java.lang.Integer ajUserid;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="aj_id"
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
	 * Return the value associated with the column: aj_jointime
	 */
	public java.util.Date getAjJointime () {
		return ajJointime;
	}

	/**
	 * Set the value related to the column: aj_jointime
	 * @param ajJointime the aj_jointime value
	 */
	public void setAjJointime (java.util.Date ajJointime) {
		this.ajJointime = ajJointime;
	}



	/**
	 * Return the value associated with the column: aj_username
	 */
	public java.lang.String getAjUsername () {
		return ajUsername;
	}

	/**
	 * Set the value related to the column: aj_username
	 * @param ajUsername the aj_username value
	 */
	public void setAjUsername (java.lang.String ajUsername) {
		this.ajUsername = ajUsername;
	}



	/**
	 * Return the value associated with the column: aj_userimg
	 */
	public java.lang.String getAjUserimg () {
		return ajUserimg;
	}

	/**
	 * Set the value related to the column: aj_userimg
	 * @param ajUserimg the aj_userimg value
	 */
	public void setAjUserimg (java.lang.String ajUserimg) {
		this.ajUserimg = ajUserimg;
	}



	/**
	 * Return the value associated with the column: aj_state
	 */
	public java.lang.Integer getAjState () {
		return ajState;
	}

	/**
	 * Set the value related to the column: aj_state
	 * @param ajState the aj_state value
	 */
	public void setAjState (java.lang.Integer ajState) {
		this.ajState = ajState;
	}



	/**
	 * Return the value associated with the column: aj_team
	 */
	public java.lang.Integer getAjTeam () {
		return ajTeam;
	}

	/**
	 * Set the value related to the column: aj_team
	 * @param ajTeam the aj_team value
	 */
	public void setAjTeam (java.lang.Integer ajTeam) {
		this.ajTeam = ajTeam;
	}



	/**
	 * Return the value associated with the column: aj_isanonymous
	 */
	public java.lang.Integer getAjIsanonymous () {
		return ajIsanonymous;
	}

	/**
	 * Set the value related to the column: aj_isanonymous
	 * @param ajIsanonymous the aj_isanonymous value
	 */
	public void setAjIsanonymous (java.lang.Integer ajIsanonymous) {
		this.ajIsanonymous = ajIsanonymous;
	}



	/**
	 * Return the value associated with the column: aj_words
	 */
	public java.lang.String getAjWords () {
		return ajWords;
	}

	/**
	 * Set the value related to the column: aj_words
	 * @param ajWords the aj_words value
	 */
	public void setAjWords (java.lang.String ajWords) {
		this.ajWords = ajWords;
	}



	/**
	 * Return the value associated with the column: aj_activityid
	 */
	public java.lang.Integer getAjActivityid () {
		return ajActivityid;
	}

	/**
	 * Set the value related to the column: aj_activityid
	 * @param ajActivityid the aj_activityid value
	 */
	public void setAjActivityid (java.lang.Integer ajActivityid) {
		this.ajActivityid = ajActivityid;
	}



	/**
	 * Return the value associated with the column: aj_userid
	 */
	public java.lang.Integer getAjUserid () {
		return ajUserid;
	}

	/**
	 * Set the value related to the column: aj_userid
	 * @param ajUserid the aj_userid value
	 */
	public void setAjUserid (java.lang.Integer ajUserid) {
		this.ajUserid = ajUserid;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ActivityJoin)) return false;
		else {
			com.jeecms.cms.entity.main.ActivityJoin activityJoin = (com.jeecms.cms.entity.main.ActivityJoin) obj;
			if (null == this.getId() || null == activityJoin.getId()) return false;
			else return (this.getId().equals(activityJoin.getId()));
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