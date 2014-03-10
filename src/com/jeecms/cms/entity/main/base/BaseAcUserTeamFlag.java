package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_user_team_flag table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_user_team_flag"
 */

public abstract class BaseAcUserTeamFlag  implements Serializable {

	public static String REF = "AcUserTeamFlag";
	public static String PROP_TEAM_DREAM = "TeamDream";
	public static String PROP_TEAM_FIGHT = "TeamFight";
	public static String PROP_TEAM_BROTHER = "TeamBrother";
	public static String PROP_AC_USER_TEAM = "AcUserTeam";
	public static String PROP_TEAM_LOVERS = "TeamLovers";
	public static String PROP_TEAM_SISTER = "TeamSister";
	public static String PROP_ID = "id";


	// constructors
	public BaseAcUserTeamFlag () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcUserTeamFlag (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Byte teamDream;
	private java.lang.Byte teamBrother;
	private java.lang.Byte teamSister;
	private java.lang.Byte teamLovers;
	private java.lang.Byte teamFight;

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
	 * Return the value associated with the column: team_dream
	 */
	public java.lang.Byte getTeamDream () {
		return teamDream;
	}

	/**
	 * Set the value related to the column: team_dream
	 * @param teamDream the team_dream value
	 */
	public void setTeamDream (java.lang.Byte teamDream) {
		this.teamDream = teamDream;
	}



	/**
	 * Return the value associated with the column: team_brother
	 */
	public java.lang.Byte getTeamBrother () {
		return teamBrother;
	}

	/**
	 * Set the value related to the column: team_brother
	 * @param teamBrother the team_brother value
	 */
	public void setTeamBrother (java.lang.Byte teamBrother) {
		this.teamBrother = teamBrother;
	}



	/**
	 * Return the value associated with the column: team_sister
	 */
	public java.lang.Byte getTeamSister () {
		return teamSister;
	}

	/**
	 * Set the value related to the column: team_sister
	 * @param teamSister the team_sister value
	 */
	public void setTeamSister (java.lang.Byte teamSister) {
		this.teamSister = teamSister;
	}



	/**
	 * Return the value associated with the column: team_lovers
	 */
	public java.lang.Byte getTeamLovers () {
		return teamLovers;
	}

	/**
	 * Set the value related to the column: team_lovers
	 * @param teamLovers the team_lovers value
	 */
	public void setTeamLovers (java.lang.Byte teamLovers) {
		this.teamLovers = teamLovers;
	}



	/**
	 * Return the value associated with the column: team_fight
	 */
	public java.lang.Byte getTeamFight () {
		return teamFight;
	}

	/**
	 * Set the value related to the column: team_fight
	 * @param teamFight the team_fight value
	 */
	public void setTeamFight (java.lang.Byte teamFight) {
		this.teamFight = teamFight;
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
		if (!(obj instanceof com.jeecms.cms.entity.main.AcUserTeamFlag)) return false;
		else {
			com.jeecms.cms.entity.main.AcUserTeamFlag acUserTeamFlag = (com.jeecms.cms.entity.main.AcUserTeamFlag) obj;
			if (null == this.getId() || null == acUserTeamFlag.getId()) return false;
			else return (this.getId().equals(acUserTeamFlag.getId()));
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