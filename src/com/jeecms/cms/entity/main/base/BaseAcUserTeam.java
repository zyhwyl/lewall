package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_user_team table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_user_team"
 */

public abstract class BaseAcUserTeam  implements Serializable {

	public static String REF = "AcUserTeam";
	public static String PROP_TEAM_APPLYALLOW = "TeamApplyallow";
	public static String PROP_TEAM_LEVEL = "TeamLevel";
	public static String PROP_TEAM_CREATEUSER = "TeamCreateuser";
	public static String PROP_TEAM_CREATEUSERDUTY = "TeamCreateuserduty";
	public static String PROP_TEAM_ISFORBID = "TeamIsforbid";
	public static String PROP_TEAM_SCORE = "TeamScore";
	public static String PROP_AC_USER_TEAM_FLAG = "AcUserTeamFlag";
	public static String PROP_TEAM_SCHOOLID = "TeamSchoolid";
	public static String PROP_AC_USER_TEAM_EXT = "AcUserTeamExt";
	public static String PROP_TEAM_NAME = "TeamName";
	public static String PROP_TEAM_CREATEUSERIMG = "TeamCreateuserimg";
	public static String PROP_TEAM_ISCERTIFICATE = "TeamIscertificate";
	public static String PROP_TEAM_RANK = "TeamRank";
	public static String PROP_TEAM_CREATEUSERNAME = "TeamCreateusername";
	public static String PROP_ID = "Id";
	public static String PROP_TEAM_UPDATETIME = "TeamUpdatetime";
	public static String PROP_TEAM_CREATETIME = "TeamCreatetime";


	// constructors
	public BaseAcUserTeam () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcUserTeam (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String teamName;
	private java.util.Date teamCreatetime;
	private java.lang.Integer teamCreateuser;
	private java.lang.Integer teamLevel;
	private java.lang.String teamScore;
	private java.lang.Integer teamRank;
	private java.util.Date teamUpdatetime;
	private java.lang.Integer teamIscertificate;
	private java.lang.Integer teamIsforbid;
	private java.lang.Integer teamApplyallow;
	private java.lang.String teamCreateusername;
	private java.lang.String teamCreateuserduty;
	private java.lang.String teamCreateuserimg;
	private java.lang.Integer teamSchoolid;

	// one to one
	private com.jeecms.cms.entity.main.AcUserTeamExt acUserTeamExt;
	private com.jeecms.cms.entity.main.AcUserTeamFlag acUserTeamFlag;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
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
	 * Return the value associated with the column: team_name
	 */
	public java.lang.String getTeamName () {
		return teamName;
	}

	/**
	 * Set the value related to the column: team_name
	 * @param teamName the team_name value
	 */
	public void setTeamName (java.lang.String teamName) {
		this.teamName = teamName;
	}



	/**
	 * Return the value associated with the column: team_createtime
	 */
	public java.util.Date getTeamCreatetime () {
		return teamCreatetime;
	}

	/**
	 * Set the value related to the column: team_createtime
	 * @param teamCreatetime the team_createtime value
	 */
	public void setTeamCreatetime (java.util.Date teamCreatetime) {
		this.teamCreatetime = teamCreatetime;
	}



	/**
	 * Return the value associated with the column: team_createuser
	 */
	public java.lang.Integer getTeamCreateuser () {
		return teamCreateuser;
	}

	/**
	 * Set the value related to the column: team_createuser
	 * @param teamCreateuser the team_createuser value
	 */
	public void setTeamCreateuser (java.lang.Integer teamCreateuser) {
		this.teamCreateuser = teamCreateuser;
	}



	/**
	 * Return the value associated with the column: team_level
	 */
	public java.lang.Integer getTeamLevel () {
		return teamLevel;
	}

	/**
	 * Set the value related to the column: team_level
	 * @param teamLevel the team_level value
	 */
	public void setTeamLevel (java.lang.Integer teamLevel) {
		this.teamLevel = teamLevel;
	}



	/**
	 * Return the value associated with the column: team_score
	 */
	public java.lang.String getTeamScore () {
		return teamScore;
	}

	/**
	 * Set the value related to the column: team_score
	 * @param teamScore the team_score value
	 */
	public void setTeamScore (java.lang.String teamScore) {
		this.teamScore = teamScore;
	}



	/**
	 * Return the value associated with the column: team_rank
	 */
	public java.lang.Integer getTeamRank () {
		return teamRank;
	}

	/**
	 * Set the value related to the column: team_rank
	 * @param teamRank the team_rank value
	 */
	public void setTeamRank (java.lang.Integer teamRank) {
		this.teamRank = teamRank;
	}



	/**
	 * Return the value associated with the column: team_updatetime
	 */
	public java.util.Date getTeamUpdatetime () {
		return teamUpdatetime;
	}

	/**
	 * Set the value related to the column: team_updatetime
	 * @param teamUpdatetime the team_updatetime value
	 */
	public void setTeamUpdatetime (java.util.Date teamUpdatetime) {
		this.teamUpdatetime = teamUpdatetime;
	}



	/**
	 * Return the value associated with the column: team_iscertificate
	 */
	public java.lang.Integer getTeamIscertificate () {
		return teamIscertificate;
	}

	/**
	 * Set the value related to the column: team_iscertificate
	 * @param teamIscertificate the team_iscertificate value
	 */
	public void setTeamIscertificate (java.lang.Integer teamIscertificate) {
		this.teamIscertificate = teamIscertificate;
	}



	/**
	 * Return the value associated with the column: team_isforbid
	 */
	public java.lang.Integer getTeamIsforbid () {
		return teamIsforbid;
	}

	/**
	 * Set the value related to the column: team_isforbid
	 * @param teamIsforbid the team_isforbid value
	 */
	public void setTeamIsforbid (java.lang.Integer teamIsforbid) {
		this.teamIsforbid = teamIsforbid;
	}



	/**
	 * Return the value associated with the column: team_applyallow
	 */
	public java.lang.Integer getTeamApplyallow () {
		return teamApplyallow;
	}

	/**
	 * Set the value related to the column: team_applyallow
	 * @param teamApplyallow the team_applyallow value
	 */
	public void setTeamApplyallow (java.lang.Integer teamApplyallow) {
		this.teamApplyallow = teamApplyallow;
	}



	/**
	 * Return the value associated with the column: team_createusername
	 */
	public java.lang.String getTeamCreateusername () {
		return teamCreateusername;
	}

	/**
	 * Set the value related to the column: team_createusername
	 * @param teamCreateusername the team_createusername value
	 */
	public void setTeamCreateusername (java.lang.String teamCreateusername) {
		this.teamCreateusername = teamCreateusername;
	}



	/**
	 * Return the value associated with the column: team_createuserduty
	 */
	public java.lang.String getTeamCreateuserduty () {
		return teamCreateuserduty;
	}

	/**
	 * Set the value related to the column: team_createuserduty
	 * @param teamCreateuserduty the team_createuserduty value
	 */
	public void setTeamCreateuserduty (java.lang.String teamCreateuserduty) {
		this.teamCreateuserduty = teamCreateuserduty;
	}



	/**
	 * Return the value associated with the column: team_createuserimg
	 */
	public java.lang.String getTeamCreateuserimg () {
		return teamCreateuserimg;
	}

	/**
	 * Set the value related to the column: team_createuserimg
	 * @param teamCreateuserimg the team_createuserimg value
	 */
	public void setTeamCreateuserimg (java.lang.String teamCreateuserimg) {
		this.teamCreateuserimg = teamCreateuserimg;
	}



	/**
	 * Return the value associated with the column: team_schoolid
	 */
	public java.lang.Integer getTeamSchoolid () {
		return teamSchoolid;
	}

	/**
	 * Set the value related to the column: team_schoolid
	 * @param teamSchoolid the team_schoolid value
	 */
	public void setTeamSchoolid (java.lang.Integer teamSchoolid) {
		this.teamSchoolid = teamSchoolid;
	}



	/**
	 * Return the value associated with the column: AcUserTeamExt
	 */
	public com.jeecms.cms.entity.main.AcUserTeamExt getAcUserTeamExt () {
		return acUserTeamExt;
	}

	/**
	 * Set the value related to the column: AcUserTeamExt
	 * @param acUserTeamExt the AcUserTeamExt value
	 */
	public void setAcUserTeamExt (com.jeecms.cms.entity.main.AcUserTeamExt acUserTeamExt) {
		this.acUserTeamExt = acUserTeamExt;
	}



	/**
	 * Return the value associated with the column: AcUserTeamFlag
	 */
	public com.jeecms.cms.entity.main.AcUserTeamFlag getAcUserTeamFlag () {
		return acUserTeamFlag;
	}

	/**
	 * Set the value related to the column: AcUserTeamFlag
	 * @param acUserTeamFlag the AcUserTeamFlag value
	 */
	public void setAcUserTeamFlag (com.jeecms.cms.entity.main.AcUserTeamFlag acUserTeamFlag) {
		this.acUserTeamFlag = acUserTeamFlag;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcUserTeam)) return false;
		else {
			com.jeecms.cms.entity.main.AcUserTeam acUserTeam = (com.jeecms.cms.entity.main.AcUserTeam) obj;
			if (null == this.getId() || null == acUserTeam.getId()) return false;
			else return (this.getId().equals(acUserTeam.getId()));
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