package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_user_checkin table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_user_checkin"
 */

public abstract class BaseAcUserCheckin  implements Serializable {

	public static String REF = "AcUserCheckin";
	public static String PROP_ID = "Id";
	public static String PROP_CHECK_USER = "CheckUser";
	public static String PROP_CHECK_DATE = "CheckDate";


	// constructors
	public BaseAcUserCheckin () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcUserCheckin (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcUserCheckin (
		java.lang.String id,
		java.lang.Integer checkUser,
		java.util.Date checkDate) {

		this.setId(id);
		this.setCheckUser(checkUser);
		this.setCheckDate(checkDate);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.Integer checkUser;
	private java.util.Date checkDate;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="check_id"
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
	 * Return the value associated with the column: check_user
	 */
	public java.lang.Integer getCheckUser () {
		return checkUser;
	}

	/**
	 * Set the value related to the column: check_user
	 * @param checkUser the check_user value
	 */
	public void setCheckUser (java.lang.Integer checkUser) {
		this.checkUser = checkUser;
	}



	/**
	 * Return the value associated with the column: check_date
	 */
	public java.util.Date getCheckDate () {
		return checkDate;
	}

	/**
	 * Set the value related to the column: check_date
	 * @param checkDate the check_date value
	 */
	public void setCheckDate (java.util.Date checkDate) {
		this.checkDate = checkDate;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcUserCheckin)) return false;
		else {
			com.jeecms.cms.entity.main.AcUserCheckin acUserCheckin = (com.jeecms.cms.entity.main.AcUserCheckin) obj;
			if (null == this.getId() || null == acUserCheckin.getId()) return false;
			else return (this.getId().equals(acUserCheckin.getId()));
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