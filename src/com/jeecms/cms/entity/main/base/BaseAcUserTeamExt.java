package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_user_team_ext table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_user_team_ext"
 */

public abstract class BaseAcUserTeamExt  implements Serializable {

	public static String REF = "AcUserTeamExt";
	public static String PROP_TEAM_FLOWERS = "TeamFlowers";
	public static String PROP_TEAM_TAG = "TeamTag";
	public static String PROP_TEAM_FANS = "TeamFans";
	public static String PROP_AC_USER_TEAM = "AcUserTeam";
	public static String PROP_TEAM_MEDAL = "TeamMedal";
	public static String PROP_TEAM_EGGS = "TeamEggs";
	public static String PROP_ID = "id";
	public static String PROP_TEAM_LOGO = "TeamLogo";
	public static String PROP_TEAM_DESC = "TeamDesc";


	// constructors
	public BaseAcUserTeamExt () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcUserTeamExt (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String teamMedal;
	private java.lang.String teamTag;
	private java.lang.String teamLogo;
	private java.lang.String teamDesc;
	private java.lang.Integer teamFans;
	private java.lang.Integer teamFlowers;
	private java.lang.Integer teamEggs;

	// one to one
	private com.jeecms.cms.entity.main.AcUserTeam acUserTeam;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="foreign"
     *  column="team_id"
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
	 * Return the value associated with the column: team_medal
	 */
	public java.lang.String getTeamMedal () {
		return teamMedal;
	}

	/**
	 * Set the value related to the column: team_medal
	 * @param teamMedal the team_medal value
	 */
	public void setTeamMedal (java.lang.String teamMedal) {
		this.teamMedal = teamMedal;
	}



	/**
	 * Return the value associated with the column: team_tag
	 */
	public java.lang.String getTeamTag () {
		return teamTag;
	}

	/**
	 * Set the value related to the column: team_tag
	 * @param teamTag the team_tag value
	 */
	public void setTeamTag (java.lang.String teamTag) {
		this.teamTag = teamTag;
	}



	/**
	 * Return the value associated with the column: team_logo
	 */
	public java.lang.String getTeamLogo () {
		return teamLogo;
	}

	/**
	 * Set the value related to the column: team_logo
	 * @param teamLogo the team_logo value
	 */
	public void setTeamLogo (java.lang.String teamLogo) {
		this.teamLogo = teamLogo;
	}



	/**
	 * Return the value associated with the column: team_desc
	 */
	public java.lang.String getTeamDesc () {
		return teamDesc;
	}

	/**
	 * Set the value related to the column: team_desc
	 * @param teamDesc the team_desc value
	 */
	public void setTeamDesc (java.lang.String teamDesc) {
		this.teamDesc = teamDesc;
	}



	/**
	 * Return the value associated with the column: team_fans
	 */
	public java.lang.Integer getTeamFans () {
		return teamFans;
	}

	/**
	 * Set the value related to the column: team_fans
	 * @param teamFans the team_fans value
	 */
	public void setTeamFans (java.lang.Integer teamFans) {
		this.teamFans = teamFans;
	}



	/**
	 * Return the value associated with the column: team_flowers
	 */
	public java.lang.Integer getTeamFlowers () {
		return teamFlowers;
	}

	/**
	 * Set the value related to the column: team_flowers
	 * @param teamFlowers the team_flowers value
	 */
	public void setTeamFlowers (java.lang.Integer teamFlowers) {
		this.teamFlowers = teamFlowers;
	}



	/**
	 * Return the value associated with the column: team_eggs
	 */
	public java.lang.Integer getTeamEggs () {
		return teamEggs;
	}

	/**
	 * Set the value related to the column: team_eggs
	 * @param teamEggs the team_eggs value
	 */
	public void setTeamEggs (java.lang.Integer teamEggs) {
		this.teamEggs = teamEggs;
	}



	/**
	 * Return the value associated with the column: AcUserTeam
	 */
	public com.jeecms.cms.entity.main.AcUserTeam getAcUserTeam () {
		return acUserTeam;
	}

	/**
	 * Set the value related to the column: AcUserTeam
	 * @param acUserTeam the AcUserTeam value
	 */
	public void setAcUserTeam (com.jeecms.cms.entity.main.AcUserTeam acUserTeam) {
		this.acUserTeam = acUserTeam;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcUserTeamExt)) return false;
		else {
			com.jeecms.cms.entity.main.AcUserTeamExt acUserTeamExt = (com.jeecms.cms.entity.main.AcUserTeamExt) obj;
			if (null == this.getId() || null == acUserTeamExt.getId()) return false;
			else return (this.getId().equals(acUserTeamExt.getId()));
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