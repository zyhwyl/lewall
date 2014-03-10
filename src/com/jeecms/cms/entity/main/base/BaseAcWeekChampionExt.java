package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_week_champion_ext table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_week_champion_ext"
 */

public abstract class BaseAcWeekChampionExt  implements Serializable {

	public static String REF = "AcWeekChampionExt";
	public static String PROP_AC_WEEK_CHAMPION = "AcWeekChampion";
	public static String PROP_CHAMPION_LOGO = "ChampionLogo";
	public static String PROP_ID = "id";
	public static String PROP_CHAMPION_DESC = "ChampionDesc";


	// constructors
	public BaseAcWeekChampionExt () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcWeekChampionExt (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String championLogo;
	private java.lang.String championDesc;

	// one to one
	private com.jeecms.cms.entity.main.AcWeekChampion acWeekChampion;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
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
	 * Return the value associated with the column: champion_logo
	 */
	public java.lang.String getChampionLogo () {
		return championLogo;
	}

	/**
	 * Set the value related to the column: champion_logo
	 * @param championLogo the champion_logo value
	 */
	public void setChampionLogo (java.lang.String championLogo) {
		this.championLogo = championLogo;
	}



	/**
	 * Return the value associated with the column: champion_desc
	 */
	public java.lang.String getChampionDesc () {
		return championDesc;
	}

	/**
	 * Set the value related to the column: champion_desc
	 * @param championDesc the champion_desc value
	 */
	public void setChampionDesc (java.lang.String championDesc) {
		this.championDesc = championDesc;
	}



	/**
	 * Return the value associated with the column: AcWeekChampion
	 */
	public com.jeecms.cms.entity.main.AcWeekChampion getAcWeekChampion () {
		return acWeekChampion;
	}

	/**
	 * Set the value related to the column: AcWeekChampion
	 * @param acWeekChampion the AcWeekChampion value
	 */
	public void setAcWeekChampion (com.jeecms.cms.entity.main.AcWeekChampion acWeekChampion) {
		this.acWeekChampion = acWeekChampion;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcWeekChampionExt)) return false;
		else {
			com.jeecms.cms.entity.main.AcWeekChampionExt acWeekChampionExt = (com.jeecms.cms.entity.main.AcWeekChampionExt) obj;
			if (null == this.getId() || null == acWeekChampionExt.getId()) return false;
			else return (this.getId().equals(acWeekChampionExt.getId()));
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