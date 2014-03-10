package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the activity_districk table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="activity_districk"
 */

public abstract class BaseActivityDistrick  implements Serializable {

	public static String REF = "ActivityDistrick";
	public static String PROP_DISTRICK_NAME = "DistrickName";
	public static String PROP_ID = "Id";
	public static String PROP_CITY = "City";


	// constructors
	public BaseActivityDistrick () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivityDistrick (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String districkName;

	// many to one
	private com.jeecms.cms.entity.main.ActivityCity city;

	// collections
	private java.util.List<com.jeecms.cms.entity.main.ActivitySchool> activitySchools;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="districk_id"
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
	 * Return the value associated with the column: districk_name
	 */
	public java.lang.String getDistrickName () {
		return districkName;
	}

	/**
	 * Set the value related to the column: districk_name
	 * @param districkName the districk_name value
	 */
	public void setDistrickName (java.lang.String districkName) {
		this.districkName = districkName;
	}



	/**
	 * Return the value associated with the column: city_id
	 */
	public com.jeecms.cms.entity.main.ActivityCity getCity () {
		return city;
	}

	/**
	 * Set the value related to the column: city_id
	 * @param city the city_id value
	 */
	public void setCity (com.jeecms.cms.entity.main.ActivityCity city) {
		this.city = city;
	}







	public java.util.List<com.jeecms.cms.entity.main.ActivitySchool> getActivitySchools() {
		return activitySchools;
	}

	public void setActivitySchools(
			java.util.List<com.jeecms.cms.entity.main.ActivitySchool> activitySchools) {
		this.activitySchools = activitySchools;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ActivityDistrick)) return false;
		else {
			com.jeecms.cms.entity.main.ActivityDistrick activityDistrick = (com.jeecms.cms.entity.main.ActivityDistrick) obj;
			if (null == this.getId() || null == activityDistrick.getId()) return false;
			else return (this.getId().equals(activityDistrick.getId()));
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