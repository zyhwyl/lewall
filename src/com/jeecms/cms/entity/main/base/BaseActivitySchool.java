package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the activity_school table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="activity_school"
 */

public abstract class BaseActivitySchool  implements Serializable {

	public static String REF = "ActivitySchool";
	public static String PROP_SC_NAME = "ScName";
	public static String PROP_ID = "Id";
	public static String PROP_DISTRICK = "Districk";


	// constructors
	public BaseActivitySchool () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivitySchool (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String scName;

	// many to one
	private com.jeecms.cms.entity.main.ActivityDistrick districk;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="sc_id"
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
	 * Return the value associated with the column: sc_name
	 */
	public java.lang.String getScName () {
		return scName;
	}

	/**
	 * Set the value related to the column: sc_name
	 * @param scName the sc_name value
	 */
	public void setScName (java.lang.String scName) {
		this.scName = scName;
	}



	/**
	 * Return the value associated with the column: districk_id
	 */
	public com.jeecms.cms.entity.main.ActivityDistrick getDistrick () {
		return districk;
	}

	/**
	 * Set the value related to the column: districk_id
	 * @param districk the districk_id value
	 */
	public void setDistrick (com.jeecms.cms.entity.main.ActivityDistrick districk) {
		this.districk = districk;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ActivitySchool)) return false;
		else {
			com.jeecms.cms.entity.main.ActivitySchool activitySchool = (com.jeecms.cms.entity.main.ActivitySchool) obj;
			if (null == this.getId() || null == activitySchool.getId()) return false;
			else return (this.getId().equals(activitySchool.getId()));
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