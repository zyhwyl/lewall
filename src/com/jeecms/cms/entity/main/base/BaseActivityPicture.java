package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the activity_picture table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="activity_picture"
 */

public abstract class BaseActivityPicture  implements Serializable {

	public static String REF = "ActivityPicture";
	public static String PROP_AP_IMGPATH = "ApImgpath";
	public static String PROP_AC = "Ac";
	public static String PROP_ID = "Id";


	// constructors
	public BaseActivityPicture () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivityPicture (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;
	private Integer acId;

	// fields
	private java.lang.String apImgpath;

	// many to one
	private com.jeecms.cms.entity.main.Activity ac;



	public Integer getAcId() {
		return acId;
	}

	public void setAcId(Integer acId) {
		this.acId = acId;
	}

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="ap_id"
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
	 * Return the value associated with the column: ap_imgpath
	 */
	public java.lang.String getApImgpath () {
		return apImgpath;
	}

	/**
	 * Set the value related to the column: ap_imgpath
	 * @param apImgpath the ap_imgpath value
	 */
	public void setApImgpath (java.lang.String apImgpath) {
		this.apImgpath = apImgpath;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ActivityPicture)) return false;
		else {
			com.jeecms.cms.entity.main.ActivityPicture activityPicture = (com.jeecms.cms.entity.main.ActivityPicture) obj;
			if (null == this.getId() || null == activityPicture.getId()) return false;
			else return (this.getId().equals(activityPicture.getId()));
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