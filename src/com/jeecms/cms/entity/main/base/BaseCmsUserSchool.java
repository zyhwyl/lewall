package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_user_school table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_user_school"
 */

public abstract class BaseCmsUserSchool  implements Serializable {

	public static String REF = "CmsUserSchool";
	public static String PROP_USER = "user";
	public static String PROP_ID = "id";
	public static String PROP_SCHOOL = "school";


	// constructors
	public BaseCmsUserSchool () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsUserSchool (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsUserSchool (
		java.lang.Integer id,
		com.jeecms.cms.entity.main.ActivitySchool school) {

		this.setId(id);
		this.setSchool(school);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// one to one
	private com.jeecms.cms.entity.main.CmsUser user;

	// many to one
	private com.jeecms.cms.entity.main.ActivitySchool school;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="user_id"
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
	 * Return the value associated with the column: user
	 */
	public com.jeecms.cms.entity.main.CmsUser getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: user
	 * @param user the user value
	 */
	public void setUser (com.jeecms.cms.entity.main.CmsUser user) {
		this.user = user;
	}



	/**
	 * Return the value associated with the column: school_id
	 */
	public com.jeecms.cms.entity.main.ActivitySchool getSchool () {
		return school;
	}

	/**
	 * Set the value related to the column: school_id
	 * @param school the school_id value
	 */
	public void setSchool (com.jeecms.cms.entity.main.ActivitySchool school) {
		this.school = school;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.CmsUserSchool)) return false;
		else {
			com.jeecms.cms.entity.main.CmsUserSchool cmsUserSchool = (com.jeecms.cms.entity.main.CmsUserSchool) obj;
			if (null == this.getId() || null == cmsUserSchool.getId()) return false;
			else return (this.getId().equals(cmsUserSchool.getId()));
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