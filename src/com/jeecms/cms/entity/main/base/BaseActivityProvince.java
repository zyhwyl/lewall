package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the activity_province table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="activity_province"
 */

public abstract class BaseActivityProvince  implements Serializable {

	public static String REF = "ActivityProvince";
	public static String PROP_ID = "Id";
	public static String PROP_COUNTRY = "Country";
	public static String PROP_PROVINCE_NAME = "ProvinceName";


	// constructors
	public BaseActivityProvince () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivityProvince (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String provinceName;

	// many to one
	private com.jeecms.cms.entity.main.ActivityCountry country;

	// collections
	private java.util.Set<com.jeecms.cms.entity.main.ActivityCity> activityCities;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="province_id"
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
	 * Return the value associated with the column: province_name
	 */
	public java.lang.String getProvinceName () {
		return provinceName;
	}

	/**
	 * Set the value related to the column: province_name
	 * @param provinceName the province_name value
	 */
	public void setProvinceName (java.lang.String provinceName) {
		this.provinceName = provinceName;
	}



	/**
	 * Return the value associated with the column: country_id
	 */
	public com.jeecms.cms.entity.main.ActivityCountry getCountry () {
		return country;
	}

	/**
	 * Set the value related to the column: country_id
	 * @param country the country_id value
	 */
	public void setCountry (com.jeecms.cms.entity.main.ActivityCountry country) {
		this.country = country;
	}



	/**
	 * Return the value associated with the column: ActivityCities
	 */
	public java.util.Set<com.jeecms.cms.entity.main.ActivityCity> getActivityCities () {
		return activityCities;
	}

	/**
	 * Set the value related to the column: ActivityCities
	 * @param activityCities the ActivityCities value
	 */
	public void setActivityCities (java.util.Set<com.jeecms.cms.entity.main.ActivityCity> activityCities) {
		this.activityCities = activityCities;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ActivityProvince)) return false;
		else {
			com.jeecms.cms.entity.main.ActivityProvince activityProvince = (com.jeecms.cms.entity.main.ActivityProvince) obj;
			if (null == this.getId() || null == activityProvince.getId()) return false;
			else return (this.getId().equals(activityProvince.getId()));
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