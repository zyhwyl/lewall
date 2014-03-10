package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the acitivity_type table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="acitivity_type"
 */

public abstract class BaseAcitivityType  implements Serializable {

	public static String REF = "AcitivityType";
	public static String PROP_AT_CREATEDATE = "AtCreatedate";
	public static String PROP_AT_USERID = "AtUserid";
	public static String PROP_AT_STATE = "AtState";
	public static String PROP_ID = "Id";
	public static String PROP_AT_NAME = "AtName";


	// constructors
	public BaseAcitivityType () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcitivityType (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String atName;
	private java.util.Date atCreatedate;
	private java.lang.Integer atUserid;
	private java.lang.Integer atState;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="at_id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: at_name
	 */
	public java.lang.String getAtName () {
		return atName;
	}

	/**
	 * Set the value related to the column: at_name
	 * @param atName the at_name value
	 */
	public void setAtName (java.lang.String atName) {
		this.atName = atName;
	}



	/**
	 * Return the value associated with the column: at_createdate
	 */
	public java.util.Date getAtCreatedate () {
		return atCreatedate;
	}

	/**
	 * Set the value related to the column: at_createdate
	 * @param atCreatedate the at_createdate value
	 */
	public void setAtCreatedate (java.util.Date atCreatedate) {
		this.atCreatedate = atCreatedate;
	}



	/**
	 * Return the value associated with the column: at_userid
	 */
	public java.lang.Integer getAtUserid () {
		return atUserid;
	}

	/**
	 * Set the value related to the column: at_userid
	 * @param atUserid the at_userid value
	 */
	public void setAtUserid (java.lang.Integer atUserid) {
		this.atUserid = atUserid;
	}



	/**
	 * Return the value associated with the column: at_state
	 */
	public java.lang.Integer getAtState () {
		return atState;
	}

	/**
	 * Set the value related to the column: at_state
	 * @param atState the at_state value
	 */
	public void setAtState (java.lang.Integer atState) {
		this.atState = atState;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcitivityType)) return false;
		else {
			com.jeecms.cms.entity.main.AcitivityType acitivityType = (com.jeecms.cms.entity.main.AcitivityType) obj;
			if (null == this.getId() || null == acitivityType.getId()) return false;
			else return (this.getId().equals(acitivityType.getId()));
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