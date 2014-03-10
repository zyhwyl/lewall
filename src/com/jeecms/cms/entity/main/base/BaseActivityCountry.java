package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the activity_country table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="activity_country"
 */

public abstract class BaseActivityCountry  implements Serializable {

	public static String REF = "ActivityCountry";
	public static String PROP_COUNTRY_NAME = "CountryName";
	public static String PROP_ID = "Id";


	// constructors
	public BaseActivityCountry () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivityCountry (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String countryName;

	// collections
	private java.util.Set<com.jeecms.cms.entity.main.ActivityProvince> activityProvinces;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="country_id"
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
	 * Return the value associated with the column: country_name
	 */
	public java.lang.String getCountryName () {
		return countryName;
	}

	/**
	 * Set the value related to the column: country_name
	 * @param countryName the country_name value
	 */
	public void setCountryName (java.lang.String countryName) {
		this.countryName = countryName;
	}



	/**
	 * Return the value associated with the column: ActivityProvinces
	 */
	public java.util.Set<com.jeecms.cms.entity.main.ActivityProvince> getActivityProvinces () {
		return activityProvinces;
	}

	/**
	 * Set the value related to the column: ActivityProvinces
	 * @param activityProvinces the ActivityProvinces value
	 */
	public void setActivityProvinces (java.util.Set<com.jeecms.cms.entity.main.ActivityProvince> activityProvinces) {
		this.activityProvinces = activityProvinces;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ActivityCountry)) return false;
		else {
			com.jeecms.cms.entity.main.ActivityCountry activityCountry = (com.jeecms.cms.entity.main.ActivityCountry) obj;
			if (null == this.getId() || null == activityCountry.getId()) return false;
			else return (this.getId().equals(activityCountry.getId()));
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