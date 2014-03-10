package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the activity_tag table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="activity_tag"
 */

public abstract class BaseActivityTag  implements Serializable {

	public static String REF = "ActivityTag";
	public static String PROP_TAG_USERID = "TagUserid";
	public static String PROP_TAG_CREATEDATE = "TagCreatedate";
	public static String PROP_TAG_HOT = "TagHot";
	public static String PROP_ID = "Id";
	public static String PROP_TAG_NAME = "TagName";


	// constructors
	public BaseActivityTag () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivityTag (java.lang.Integer id) {
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

	// collections
	private java.util.Set<com.jeecms.cms.entity.main.TagToActivity> tagToActivities;



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



	/**
	 * Return the value associated with the column: TagToActivities
	 */
	public java.util.Set<com.jeecms.cms.entity.main.TagToActivity> getTagToActivities () {
		return tagToActivities;
	}

	/**
	 * Set the value related to the column: TagToActivities
	 * @param tagToActivities the TagToActivities value
	 */
	public void setTagToActivities (java.util.Set<com.jeecms.cms.entity.main.TagToActivity> tagToActivities) {
		this.tagToActivities = tagToActivities;
	}

	public void addToTagToActivities (com.jeecms.cms.entity.main.TagToActivity tagToActivity) {
		if (null == getTagToActivities()) setTagToActivities(new java.util.TreeSet<com.jeecms.cms.entity.main.TagToActivity>());
		getTagToActivities().add(tagToActivity);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ActivityTag)) return false;
		else {
			com.jeecms.cms.entity.main.ActivityTag activityTag = (com.jeecms.cms.entity.main.ActivityTag) obj;
			if (null == this.getId() || null == activityTag.getId()) return false;
			else return (this.getId().equals(activityTag.getId()));
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