package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_user_friend table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_user_friend"
 */

public abstract class BaseAcUserFriend  implements Serializable {

	public static String REF = "AcUserFriend";
	public static String PROP_AUF_CREATETIME = "AufCreatetime";
	public static String PROP_ID = "Id";
	public static String PROP_AUF_STATE = "AufState";
	public static String PROP_AUF_ACCEPTTIME = "AufAccepttime";
	public static String PROP_AUF_RECEIVEUSER = "AufReceiveuser";
	public static String PROP_AUF_APPLYUSER = "AufApplyuser";


	// constructors
	public BaseAcUserFriend () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcUserFriend (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.Integer aufApplyuser;
	private java.lang.Integer aufReceiveuser;
	private java.util.Date aufCreatetime;
	private java.lang.Integer aufState;
	private java.util.Date aufAccepttime;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="auf_id"
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
	 * Return the value associated with the column: auf_applyuser
	 */
	public java.lang.Integer getAufApplyuser () {
		return aufApplyuser;
	}

	/**
	 * Set the value related to the column: auf_applyuser
	 * @param aufApplyuser the auf_applyuser value
	 */
	public void setAufApplyuser (java.lang.Integer aufApplyuser) {
		this.aufApplyuser = aufApplyuser;
	}



	/**
	 * Return the value associated with the column: auf_receiveuser
	 */
	public java.lang.Integer getAufReceiveuser () {
		return aufReceiveuser;
	}

	/**
	 * Set the value related to the column: auf_receiveuser
	 * @param aufReceiveuser the auf_receiveuser value
	 */
	public void setAufReceiveuser (java.lang.Integer aufReceiveuser) {
		this.aufReceiveuser = aufReceiveuser;
	}



	/**
	 * Return the value associated with the column: auf_createtime
	 */
	public java.util.Date getAufCreatetime () {
		return aufCreatetime;
	}

	/**
	 * Set the value related to the column: auf_createtime
	 * @param aufCreatetime the auf_createtime value
	 */
	public void setAufCreatetime (java.util.Date aufCreatetime) {
		this.aufCreatetime = aufCreatetime;
	}



	/**
	 * Return the value associated with the column: auf_state
	 */
	public java.lang.Integer getAufState () {
		return aufState;
	}

	/**
	 * Set the value related to the column: auf_state
	 * @param aufState the auf_state value
	 */
	public void setAufState (java.lang.Integer aufState) {
		this.aufState = aufState;
	}



	/**
	 * Return the value associated with the column: auf_accepttime
	 */
	public java.util.Date getAufAccepttime () {
		return aufAccepttime;
	}

	/**
	 * Set the value related to the column: auf_accepttime
	 * @param aufAccepttime the auf_accepttime value
	 */
	public void setAufAccepttime (java.util.Date aufAccepttime) {
		this.aufAccepttime = aufAccepttime;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcUserFriend)) return false;
		else {
			com.jeecms.cms.entity.main.AcUserFriend acUserFriend = (com.jeecms.cms.entity.main.AcUserFriend) obj;
			if (null == this.getId() || null == acUserFriend.getId()) return false;
			else return (this.getId().equals(acUserFriend.getId()));
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