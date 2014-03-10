package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the tag_to_activity table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="tag_to_activity"
 */

public abstract class BaseTagToActivity  implements Serializable {

	public static String REF = "TagToActivity";
	public static String PROP_TAG = "Tag";
	public static String PROP_AC = "Ac";
	public static String PROP_ID = "Id";


	// constructors
	public BaseTagToActivity () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTagToActivity (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// many to one
	private com.jeecms.cms.entity.main.Activity ac;
	private com.jeecms.cms.entity.main.ActivityTag tag;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="ta_id"
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
	 * Return the value associated with the column: ac_id
	 */
	public com.jeecms.cms.entity.main.Activity getAc () {
		return ac;
	}

	/**
	 * Set the value related to the column: ac_id
	 * @param ac the ac_id value
	 */
	public void setAc (com.jeecms.cms.entity.main.Activity ac) {
		this.ac = ac;
	}



	/**
	 * Return the value associated with the column: tag_id
	 */
	public com.jeecms.cms.entity.main.ActivityTag getTag () {
		return tag;
	}

	/**
	 * Set the value related to the column: tag_id
	 * @param tag the tag_id value
	 */
	public void setTag (com.jeecms.cms.entity.main.ActivityTag tag) {
		this.tag = tag;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.TagToActivity)) return false;
		else {
			com.jeecms.cms.entity.main.TagToActivity tagToActivity = (com.jeecms.cms.entity.main.TagToActivity) obj;
			if (null == this.getId() || null == tagToActivity.getId()) return false;
			else return (this.getId().equals(tagToActivity.getId()));
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