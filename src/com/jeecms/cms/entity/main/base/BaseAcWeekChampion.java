package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_week_champion table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_week_champion"
 */

public abstract class BaseAcWeekChampion  implements Serializable {

	public static String REF = "AcWeekChampion";
	public static String PROP_CHAMPION_DATE = "ChampionDate";
	public static String PROP_CHAMPION_USER = "ChampionUser";
	public static String PROP_CHAMPION_ACTIVITY = "ChampionActivity";
	public static String PROP_SECEND_USER = "SecendUser";
	public static String PROP_ID = "Id";
	public static String PROP_SCHOOL_ID = "SchoolId";
	public static String PROP_THIRD_USER = "ThirdUser";
	public static String PROP_CHAMPION_STATE = "ChampionState";
	public static String PROP_AC_WEEK_CHAMPION_EXT = "AcWeekChampionExt";


	// constructors
	public BaseAcWeekChampion () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcWeekChampion (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer championActivity;
	private java.util.Date championDate;
	private java.lang.Integer championState;
	private java.lang.String championUser;
	private java.lang.Integer schoolId;
	private java.lang.String secendUser;
	private java.lang.String thirdUser;

	// one to one
	private com.jeecms.cms.entity.main.AcWeekChampionExt acWeekChampionExt;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="champion_id"
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
	 * Return the value associated with the column: champion_activity
	 */
	public java.lang.Integer getChampionActivity () {
		return championActivity;
	}

	/**
	 * Set the value related to the column: champion_activity
	 * @param championActivity the champion_activity value
	 */
	public void setChampionActivity (java.lang.Integer championActivity) {
		this.championActivity = championActivity;
	}



	/**
	 * Return the value associated with the column: champion_date
	 */
	public java.util.Date getChampionDate () {
		return championDate;
	}

	/**
	 * Set the value related to the column: champion_date
	 * @param championDate the champion_date value
	 */
	public void setChampionDate (java.util.Date championDate) {
		this.championDate = championDate;
	}



	/**
	 * Return the value associated with the column: champion_state
	 */
	public java.lang.Integer getChampionState () {
		return championState;
	}

	/**
	 * Set the value related to the column: champion_state
	 * @param championState the champion_state value
	 */
	public void setChampionState (java.lang.Integer championState) {
		this.championState = championState;
	}



	/**
	 * Return the value associated with the column: champion_user
	 */
	public java.lang.String getChampionUser () {
		return championUser;
	}

	/**
	 * Set the value related to the column: champion_user
	 * @param championUser the champion_user value
	 */
	public void setChampionUser (java.lang.String championUser) {
		this.championUser = championUser;
	}



	/**
	 * Return the value associated with the column: school_id
	 */
	public java.lang.Integer getSchoolId () {
		return schoolId;
	}

	/**
	 * Set the value related to the column: school_id
	 * @param schoolId the school_id value
	 */
	public void setSchoolId (java.lang.Integer schoolId) {
		this.schoolId = schoolId;
	}



	/**
	 * Return the value associated with the column: secend_user
	 */
	public java.lang.String getSecendUser () {
		return secendUser;
	}

	/**
	 * Set the value related to the column: secend_user
	 * @param secendUser the secend_user value
	 */
	public void setSecendUser (java.lang.String secendUser) {
		this.secendUser = secendUser;
	}



	/**
	 * Return the value associated with the column: third_user
	 */
	public java.lang.String getThirdUser () {
		return thirdUser;
	}

	/**
	 * Set the value related to the column: third_user
	 * @param thirdUser the third_user value
	 */
	public void setThirdUser (java.lang.String thirdUser) {
		this.thirdUser = thirdUser;
	}



	/**
	 * Return the value associated with the column: AcWeekChampionExt
	 */
	public com.jeecms.cms.entity.main.AcWeekChampionExt getAcWeekChampionExt () {
		return acWeekChampionExt;
	}

	/**
	 * Set the value related to the column: AcWeekChampionExt
	 * @param acWeekChampionExt the AcWeekChampionExt value
	 */
	public void setAcWeekChampionExt (com.jeecms.cms.entity.main.AcWeekChampionExt acWeekChampionExt) {
		this.acWeekChampionExt = acWeekChampionExt;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcWeekChampion)) return false;
		else {
			com.jeecms.cms.entity.main.AcWeekChampion acWeekChampion = (com.jeecms.cms.entity.main.AcWeekChampion) obj;
			if (null == this.getId() || null == acWeekChampion.getId()) return false;
			else return (this.getId().equals(acWeekChampion.getId()));
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