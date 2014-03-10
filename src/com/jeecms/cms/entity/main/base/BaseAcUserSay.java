package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_user_say table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_user_say"
 */

public abstract class BaseAcUserSay  implements Serializable {

	public static String REF = "AcUserSay";
	public static String PROP_SAY_USER = "SayUser";
	public static String PROP_SAY_CONTENT = "SayContent";
	public static String PROP_ID = "Id";
	public static String PROP_SAY_DATE = "SayDate";


	// constructors
	public BaseAcUserSay () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcUserSay (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcUserSay (
		java.lang.String id,
		java.lang.Integer sayUser,
		java.lang.String sayContent,
		java.util.Date sayDate) {

		this.setId(id);
		this.setSayUser(sayUser);
		this.setSayContent(sayContent);
		this.setSayDate(sayDate);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.Integer sayUser;
	private java.lang.String sayContent;
	private java.util.Date sayDate;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="say_id"
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
	 * Return the value associated with the column: say_user
	 */
	public java.lang.Integer getSayUser () {
		return sayUser;
	}

	/**
	 * Set the value related to the column: say_user
	 * @param sayUser the say_user value
	 */
	public void setSayUser (java.lang.Integer sayUser) {
		this.sayUser = sayUser;
	}



	/**
	 * Return the value associated with the column: say_content
	 */
	public java.lang.String getSayContent () {
		return sayContent;
	}

	/**
	 * Set the value related to the column: say_content
	 * @param sayContent the say_content value
	 */
	public void setSayContent (java.lang.String sayContent) {
		this.sayContent = sayContent;
	}



	/**
	 * Return the value associated with the column: say_date
	 */
	public java.util.Date getSayDate () {
		return sayDate;
	}

	/**
	 * Set the value related to the column: say_date
	 * @param sayDate the say_date value
	 */
	public void setSayDate (java.util.Date sayDate) {
		this.sayDate = sayDate;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcUserSay)) return false;
		else {
			com.jeecms.cms.entity.main.AcUserSay acUserSay = (com.jeecms.cms.entity.main.AcUserSay) obj;
			if (null == this.getId() || null == acUserSay.getId()) return false;
			else return (this.getId().equals(acUserSay.getId()));
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