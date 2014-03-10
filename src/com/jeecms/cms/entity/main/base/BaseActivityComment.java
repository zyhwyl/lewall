package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the activity_comment table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="activity_comment"
 */

public abstract class BaseActivityComment  implements Serializable {

	public static String REF = "ActivityComment";
	public static String PROP_ACM_TEXT = "AcmText";
	public static String PROP_ACM_TO_USER = "AcmToUser";
	public static String PROP_ACM_USERNAME = "AcmUsername";
	public static String PROP_ACM_ACID = "AcmAcid";
	public static String PROP_ACM_USERID = "AcmUserid";
	public static String PROP_ACM_CREATETIME = "AcmCreatetime";
	public static String PROP_ACM_USERIMG = "AcmUserimg";
	public static String PROP_ID = "Id";


	// constructors
	public BaseActivityComment () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivityComment (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.Integer acmAcid;
	private java.lang.Integer acmUserid;
	private java.lang.String acmText;
	private java.util.Date acmCreatetime;
	private java.lang.Integer acmToUser;
	private java.lang.String acmUsername;
	private java.lang.String acmUserimg;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="acm_id"
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
	 * Return the value associated with the column: acm_acid
	 */
	public java.lang.Integer getAcmAcid () {
		return acmAcid;
	}

	/**
	 * Set the value related to the column: acm_acid
	 * @param acmAcid the acm_acid value
	 */
	public void setAcmAcid (java.lang.Integer acmAcid) {
		this.acmAcid = acmAcid;
	}



	/**
	 * Return the value associated with the column: acm_userid
	 */
	public java.lang.Integer getAcmUserid () {
		return acmUserid;
	}

	/**
	 * Set the value related to the column: acm_userid
	 * @param acmUserid the acm_userid value
	 */
	public void setAcmUserid (java.lang.Integer acmUserid) {
		this.acmUserid = acmUserid;
	}



	/**
	 * Return the value associated with the column: acm_text
	 */
	public java.lang.String getAcmText () {
		return acmText;
	}

	/**
	 * Set the value related to the column: acm_text
	 * @param acmText the acm_text value
	 */
	public void setAcmText (java.lang.String acmText) {
		this.acmText = acmText;
	}



	/**
	 * Return the value associated with the column: acm_createtime
	 */
	public java.util.Date getAcmCreatetime () {
		return acmCreatetime;
	}

	/**
	 * Set the value related to the column: acm_createtime
	 * @param acmCreatetime the acm_createtime value
	 */
	public void setAcmCreatetime (java.util.Date acmCreatetime) {
		this.acmCreatetime = acmCreatetime;
	}



	/**
	 * Return the value associated with the column: acm_to_user
	 */
	public java.lang.Integer getAcmToUser () {
		return acmToUser;
	}

	/**
	 * Set the value related to the column: acm_to_user
	 * @param acmToUser the acm_to_user value
	 */
	public void setAcmToUser (java.lang.Integer acmToUser) {
		this.acmToUser = acmToUser;
	}



	/**
	 * Return the value associated with the column: acm_username
	 */
	public java.lang.String getAcmUsername () {
		return acmUsername;
	}

	/**
	 * Set the value related to the column: acm_username
	 * @param acmUsername the acm_username value
	 */
	public void setAcmUsername (java.lang.String acmUsername) {
		this.acmUsername = acmUsername;
	}



	/**
	 * Return the value associated with the column: acm_userimg
	 */
	public java.lang.String getAcmUserimg () {
		return acmUserimg;
	}

	/**
	 * Set the value related to the column: acm_userimg
	 * @param acmUserimg the acm_userimg value
	 */
	public void setAcmUserimg (java.lang.String acmUserimg) {
		this.acmUserimg = acmUserimg;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ActivityComment)) return false;
		else {
			com.jeecms.cms.entity.main.ActivityComment activityComment = (com.jeecms.cms.entity.main.ActivityComment) obj;
			if (null == this.getId() || null == activityComment.getId()) return false;
			else return (this.getId().equals(activityComment.getId()));
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