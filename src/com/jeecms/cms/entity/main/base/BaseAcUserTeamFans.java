package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_user_team_fans table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_user_team_fans"
 */

public abstract class BaseAcUserTeamFans  implements Serializable {

	public static String REF = "AcUserTeamFans";
	public static String PROP_ACF_TEAMID = "AcfTeamid";
	public static String PROP_ACF_USERID = "AcfUserid";
	public static String PROP_ACF_DATE = "AcfDate";
	public static String PROP_ACF_USERNAME = "AcfUsername";
	public static String PROP_ID = "Id";
	public static String PROP_ACF_USERIMG = "AcfUserimg";


	// constructors
	public BaseAcUserTeamFans () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcUserTeamFans (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.Integer acfTeamid;
	private java.lang.Integer acfUserid;
	private java.lang.String acfUsername;
	private java.lang.String acfUserimg;
	private java.util.Date acfDate;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="acf_id"
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
	 * Return the value associated with the column: acf_teamid
	 */
	public java.lang.Integer getAcfTeamid () {
		return acfTeamid;
	}

	/**
	 * Set the value related to the column: acf_teamid
	 * @param acfTeamid the acf_teamid value
	 */
	public void setAcfTeamid (java.lang.Integer acfTeamid) {
		this.acfTeamid = acfTeamid;
	}



	/**
	 * Return the value associated with the column: acf_userid
	 */
	public java.lang.Integer getAcfUserid () {
		return acfUserid;
	}

	/**
	 * Set the value related to the column: acf_userid
	 * @param acfUserid the acf_userid value
	 */
	public void setAcfUserid (java.lang.Integer acfUserid) {
		this.acfUserid = acfUserid;
	}



	/**
	 * Return the value associated with the column: acf_username
	 */
	public java.lang.String getAcfUsername () {
		return acfUsername;
	}

	/**
	 * Set the value related to the column: acf_username
	 * @param acfUsername the acf_username value
	 */
	public void setAcfUsername (java.lang.String acfUsername) {
		this.acfUsername = acfUsername;
	}



	/**
	 * Return the value associated with the column: acf_userimg
	 */
	public java.lang.String getAcfUserimg () {
		return acfUserimg;
	}

	/**
	 * Set the value related to the column: acf_userimg
	 * @param acfUserimg the acf_userimg value
	 */
	public void setAcfUserimg (java.lang.String acfUserimg) {
		this.acfUserimg = acfUserimg;
	}



	/**
	 * Return the value associated with the column: acf_date
	 */
	public java.util.Date getAcfDate () {
		return acfDate;
	}

	/**
	 * Set the value related to the column: acf_date
	 * @param acfDate the acf_date value
	 */
	public void setAcfDate (java.util.Date acfDate) {
		this.acfDate = acfDate;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcUserTeamFans)) return false;
		else {
			com.jeecms.cms.entity.main.AcUserTeamFans acUserTeamFans = (com.jeecms.cms.entity.main.AcUserTeamFans) obj;
			if (null == this.getId() || null == acUserTeamFans.getId()) return false;
			else return (this.getId().equals(acUserTeamFans.getId()));
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