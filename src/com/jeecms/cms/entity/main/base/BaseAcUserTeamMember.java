package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_user_team_member table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_user_team_member"
 */

public abstract class BaseAcUserTeamMember  implements Serializable {

	public static String REF = "AcUserTeamMember";
	public static String PROP_AUT_USERID = "AutUserid";
	public static String PROP_AUT_USERIMG = "AutUserimg";
	public static String PROP_AUT_JOINTIME = "AutJointime";
	public static String PROP_AUT_DUTY = "AutDuty";
	public static String PROP_AUT_USERNAME = "AutUsername";
	public static String PROP_AUT_SENDINGTIME = "AutSendingtime";
	public static String PROP_ID = "Id";
	public static String PROP_AUT_CANPUB = "AutCanpub";
	public static String PROP_AUT_TEAMID = "AutTeamid";
	public static String PROP_AUT_STATE = "AutState";


	// constructors
	public BaseAcUserTeamMember () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcUserTeamMember (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcUserTeamMember (
		java.lang.String id,
		java.lang.Integer autTeamid,
		java.lang.Integer autUserid) {

		this.setId(id);
		this.setAutTeamid(autTeamid);
		this.setAutUserid(autUserid);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.Integer autTeamid;
	private java.lang.Integer autUserid;
	private java.lang.String autDuty;
	private java.util.Date autJointime;
	private java.util.Date autSendingtime;
	private java.lang.Integer autState;
	private java.lang.Integer autCanpub;
	private java.lang.String autUserimg;
	private java.lang.String autUsername;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="aut_id"
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
	 * Return the value associated with the column: aut_teamid
	 */
	public java.lang.Integer getAutTeamid () {
		return autTeamid;
	}

	/**
	 * Set the value related to the column: aut_teamid
	 * @param autTeamid the aut_teamid value
	 */
	public void setAutTeamid (java.lang.Integer autTeamid) {
		this.autTeamid = autTeamid;
	}



	/**
	 * Return the value associated with the column: aut_userid
	 */
	public java.lang.Integer getAutUserid () {
		return autUserid;
	}

	/**
	 * Set the value related to the column: aut_userid
	 * @param autUserid the aut_userid value
	 */
	public void setAutUserid (java.lang.Integer autUserid) {
		this.autUserid = autUserid;
	}



	/**
	 * Return the value associated with the column: aut_duty
	 */
	public java.lang.String getAutDuty () {
		return autDuty;
	}

	/**
	 * Set the value related to the column: aut_duty
	 * @param autDuty the aut_duty value
	 */
	public void setAutDuty (java.lang.String autDuty) {
		this.autDuty = autDuty;
	}



	/**
	 * Return the value associated with the column: aut_jointime
	 */
	public java.util.Date getAutJointime () {
		return autJointime;
	}

	/**
	 * Set the value related to the column: aut_jointime
	 * @param autJointime the aut_jointime value
	 */
	public void setAutJointime (java.util.Date autJointime) {
		this.autJointime = autJointime;
	}



	/**
	 * Return the value associated with the column: aut_sendingtime
	 */
	public java.util.Date getAutSendingtime () {
		return autSendingtime;
	}

	/**
	 * Set the value related to the column: aut_sendingtime
	 * @param autSendingtime the aut_sendingtime value
	 */
	public void setAutSendingtime (java.util.Date autSendingtime) {
		this.autSendingtime = autSendingtime;
	}



	/**
	 * Return the value associated with the column: aut_state
	 */
	public java.lang.Integer getAutState () {
		return autState;
	}

	/**
	 * Set the value related to the column: aut_state
	 * @param autState the aut_state value
	 */
	public void setAutState (java.lang.Integer autState) {
		this.autState = autState;
	}



	/**
	 * Return the value associated with the column: aut_canpub
	 */
	public java.lang.Integer getAutCanpub () {
		return autCanpub;
	}

	/**
	 * Set the value related to the column: aut_canpub
	 * @param autCanpub the aut_canpub value
	 */
	public void setAutCanpub (java.lang.Integer autCanpub) {
		this.autCanpub = autCanpub;
	}



	/**
	 * Return the value associated with the column: aut_userimg
	 */
	public java.lang.String getAutUserimg () {
		return autUserimg;
	}

	/**
	 * Set the value related to the column: aut_userimg
	 * @param autUserimg the aut_userimg value
	 */
	public void setAutUserimg (java.lang.String autUserimg) {
		this.autUserimg = autUserimg;
	}



	/**
	 * Return the value associated with the column: aut_username
	 */
	public java.lang.String getAutUsername () {
		return autUsername;
	}

	/**
	 * Set the value related to the column: aut_username
	 * @param autUsername the aut_username value
	 */
	public void setAutUsername (java.lang.String autUsername) {
		this.autUsername = autUsername;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcUserTeamMember)) return false;
		else {
			com.jeecms.cms.entity.main.AcUserTeamMember acUserTeamMember = (com.jeecms.cms.entity.main.AcUserTeamMember) obj;
			if (null == this.getId() || null == acUserTeamMember.getId()) return false;
			else return (this.getId().equals(acUserTeamMember.getId()));
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