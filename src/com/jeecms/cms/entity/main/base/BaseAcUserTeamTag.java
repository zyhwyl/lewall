package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_user_team_tag table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_user_team_tag"
 */

public abstract class BaseAcUserTeamTag  implements Serializable {

	public static String REF = "AcUserTeamTag";
	public static String PROP_TAG_USERID = "TagUserid";
	public static String PROP_TAG_CREATEDATE = "TagCreatedate";
	public static String PROP_TAG_HOT = "TagHot";
	public static String PROP_ID = "Id";
	public static String PROP_TAG_NAME = "TagName";


	// constructors
	public BaseAcUserTeamTag () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcUserTeamTag (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String tagName;
	private java.lang.Integer tagUserid;
	private java.util.Date tagCreatedate;
	private java.lang.Integer tagHot;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="tag_id"
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
	 * Return the value associated with the column: tag_name
	 */
	public java.lang.String getTagName () {
		return tagName;
	}

	/**
	 * Set the value related to the column: tag_name
	 * @param tagName the tag_name value
	 */
	public void setTagName (java.lang.String tagName) {
		this.tagName = tagName;
	}



	/**
	 * Return the value associated with the column: tag_userid
	 */
	public java.lang.Integer getTagUserid () {
		return tagUserid;
	}

	/**
	 * Set the value related to the column: tag_userid
	 * @param tagUserid the tag_userid value
	 */
	public void setTagUserid (java.lang.Integer tagUserid) {
		this.tagUserid = tagUserid;
	}



	/**
	 * Return the value associated with the column: tag_createdate
	 */
	public java.util.Date getTagCreatedate () {
		return tagCreatedate;
	}

	/**
	 * Set the value related to the column: tag_createdate
	 * @param tagCreatedate the tag_createdate value
	 */
	public void setTagCreatedate (java.util.Date tagCreatedate) {
		this.tagCreatedate = tagCreatedate;
	}



	/**
	 * Return the value associated with the column: tag_hot
	 */
	public java.lang.Integer getTagHot () {
		return tagHot;
	}

	/**
	 * Set the value related to the column: tag_hot
	 * @param tagHot the tag_hot value
	 */
	public void setTagHot (java.lang.Integer tagHot) {
		this.tagHot = tagHot;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcUserTeamTag)) return false;
		else {
			com.jeecms.cms.entity.main.AcUserTeamTag acUserTeamTag = (com.jeecms.cms.entity.main.AcUserTeamTag) obj;
			if (null == this.getId() || null == acUserTeamTag.getId()) return false;
			else return (this.getId().equals(acUserTeamTag.getId()));
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