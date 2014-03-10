package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the activity_city table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="activity_city"
 */

public abstract class BaseActivityCity  implements Serializable {

	public static String REF = "ActivityCity";
	public static String PROP_CITY_NAME = "CityName";
	public static String PROP_ID = "Id";
	public static String PROP_PROVINCE = "Province";


	// constructors
	public BaseActivityCity () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivityCity (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String cityName;

	// many to one
	private com.jeecms.cms.entity.main.ActivityProvince province;

	// collections
	private java.util.List<com.jeecms.cms.entity.main.ActivityDistrick> activityDistricks;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="city_id"
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
	 * Return the value associated with the column: city_name
	 */
	public java.lang.String getCityName () {
		return cityName;
	}

	/**
	 * Set the value related to the column: city_name
	 * @param cityName the city_name value
	 */
	public void setCityName (java.lang.String cityName) {
		this.cityName = cityName;
	}



	public java.util.List<com.jeecms.cms.entity.main.ActivityDistrick> getActivityDistricks() {
		return activityDistricks;
	}

	public void setActivityDistricks(
			java.util.List<com.jeecms.cms.entity.main.ActivityDistrick> activityDistricks) {
		this.activityDistricks = activityDistricks;
	}

	/**
	 * Return the value associated with the column: province_id
	 */
	public com.jeecms.cms.entity.main.ActivityProvince getProvince () {
		return province;
	}

	/**
	 * Set the value related to the column: province_id
	 * @param province the province_id value
	 */
	public void setProvince (com.jeecms.cms.entity.main.ActivityProvince province) {
		this.province = province;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.ActivityCity)) return false;
		else {
			com.jeecms.cms.entity.main.ActivityCity activityCity = (com.jeecms.cms.entity.main.ActivityCity) obj;
			if (null == this.getId() || null == activityCity.getId()) return false;
			else return (this.getId().equals(activityCity.getId()));
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