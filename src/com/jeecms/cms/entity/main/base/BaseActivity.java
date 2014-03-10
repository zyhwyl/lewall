package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the activity table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="activity"
 */

public abstract class BaseActivity  implements Serializable {

	public static String REF = "Activity";
	public static String PROP_AC_LEVELID = "AcLevelid";
	public static String PROP_AC_ISRECOMMEND = "AcIsrecommend";
	public static String PROP_AC_USERID = "AcUserid";
	public static String PROP_AC_STATEDATE = "AcStatedate";
	public static String PROP_AC_TXT = "AcTxt";
	public static String PROP_AC_ISTOP = "AcIstop";
	public static String PROP_AC_BEGINDATE = "AcBegindate";
	public static String PROP_AC_CREATEDATE = "AcCreatedate";
	public static String PROP_AC_STATE = "AcState";
	public static String PROP_AC_ADDRESS = "AcAddress";
	public static String PROP_AC_SIGNMAX = "AcSignmax";
	public static String PROP_AC_TYPEID = "AcTypeid";
	public static String PROP_AC_SIGNMIN = "AcSignmin";
	public static String PROP_ID = "Id";


	// constructors
	public BaseActivity () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseActivity (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseActivity (
		java.lang.Integer id,
		java.lang.Integer acUserid,
		java.util.Date acCreatedate,
		java.lang.Integer acState,
		java.util.Date acStatedate,
		java.util.Date acBegindate) {

		this.setId(id);
		this.setAcUserid(acUserid);
		this.setAcCreatedate(acCreatedate);
		this.setAcState(acState);
		this.setAcStatedate(acStatedate);
		this.setAcBegindate(acBegindate);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer acUserid;
	private java.util.Date acCreatedate;
	private java.lang.Integer acState;
	private java.util.Date acStatedate;
	private java.util.Date acBegindate;
	private java.lang.Integer acSignmax;
	private java.lang.Integer acSignmin;
	private java.lang.String acAddress;
	private java.lang.String acTxt;
	private java.lang.Integer acTypeid;
	private java.lang.Integer acLevelid;
	private boolean acIsrecommend;
	private boolean acIstop;
	private Integer acCategory;
	private Integer acSchool;
	private Integer acPass;
	private Integer acIsshow;
	private String acContactmethod;
	private Integer acDuration;
	private Integer acTeamid;

	// collections
	private java.util.List<com.jeecms.cms.entity.main.TagToActivity> tagToActivities;
	private java.util.List<com.jeecms.cms.entity.main.ActivityPicture> activityPictures;
	private java.util.List<com.jeecms.cms.entity.main.ActivityJoin> activityJoins;
	
	public Integer getAcDuration() {
		return acDuration;
	}

	public void setAcDuration(Integer acDuration) {
		this.acDuration = acDuration;
	}

	public Integer getAcTeamid() {
		return acTeamid;
	}

	public void setAcTeamid(Integer acTeamid) {
		this.acTeamid = acTeamid;
	}

	public Integer getAcPass() {
		return acPass;
	}

	public void setAcPass(Integer acPass) {
		this.acPass = acPass;
	}

	public Integer getAcCategory() {
		return acCategory;
	}

	public void setAcCategory(Integer acCategory) {
		this.acCategory = acCategory;
	}

	
	public Integer getAcIsshow() {
		return acIsshow;
	}

	public void setAcIsshow(Integer acIsshow) {
		this.acIsshow = acIsshow;
	}

	
	public String getAcContactmethod() {
		return acContactmethod;
	}

	public void setAcContactmethod(String acContactmethod) {
		this.acContactmethod = acContactmethod;
	}

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="ac_id"
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
	 * Return the value associated with the column: ac_userid
	 */
	public java.lang.Integer getAcUserid () {
		return acUserid;
	}

	/**
	 * Set the value related to the column: ac_userid
	 * @param acUserid the ac_userid value
	 */
	public void setAcUserid (java.lang.Integer acUserid) {
		this.acUserid = acUserid;
	}



	/**
	 * Return the value associated with the column: ac_createdate
	 */
	public java.util.Date getAcCreatedate () {
		return acCreatedate;
	}

	/**
	 * Set the value related to the column: ac_createdate
	 * @param acCreatedate the ac_createdate value
	 */
	public void setAcCreatedate (java.util.Date acCreatedate) {
		this.acCreatedate = acCreatedate;
	}



	/**
	 * Return the value associated with the column: ac_state
	 */
	public java.lang.Integer getAcState () {
		return acState;
	}

	/**
	 * Set the value related to the column: ac_state
	 * @param acState the ac_state value
	 */
	public void setAcState (java.lang.Integer acState) {
		this.acState = acState;
	}



	/**
	 * Return the value associated with the column: ac_statedate
	 */
	public java.util.Date getAcStatedate () {
		return acStatedate;
	}

	/**
	 * Set the value related to the column: ac_statedate
	 * @param acStatedate the ac_statedate value
	 */
	public void setAcStatedate (java.util.Date acStatedate) {
		this.acStatedate = acStatedate;
	}



	/**
	 * Return the value associated with the column: ac_begindate
	 */
	public java.util.Date getAcBegindate () {
		return acBegindate;
	}

	/**
	 * Set the value related to the column: ac_begindate
	 * @param acBegindate the ac_begindate value
	 */
	public void setAcBegindate (java.util.Date acBegindate) {
		this.acBegindate = acBegindate;
	}



	/**
	 * Return the value associated with the column: ac_signmax
	 */
	public java.lang.Integer getAcSignmax () {
		return acSignmax;
	}

	/**
	 * Set the value related to the column: ac_signmax
	 * @param acSignmax the ac_signmax value
	 */
	public void setAcSignmax (java.lang.Integer acSignmax) {
		this.acSignmax = acSignmax;
	}



	/**
	 * Return the value associated with the column: ac_signmin
	 */
	public java.lang.Integer getAcSignmin () {
		return acSignmin;
	}

	/**
	 * Set the value related to the column: ac_signmin
	 * @param acSignmin the ac_signmin value
	 */
	public void setAcSignmin (java.lang.Integer acSignmin) {
		this.acSignmin = acSignmin;
	}



	/**
	 * Return the value associated with the column: ac_address
	 */
	public java.lang.String getAcAddress () {
		return acAddress;
	}

	/**
	 * Set the value related to the column: ac_address
	 * @param acAddress the ac_address value
	 */
	public void setAcAddress (java.lang.String acAddress) {
		this.acAddress = acAddress;
	}



	/**
	 * Return the value associated with the column: ac_txt
	 */
	public java.lang.String getAcTxt () {
		return acTxt;
	}

	/**
	 * Set the value related to the column: ac_txt
	 * @param acTxt the ac_txt value
	 */
	public void setAcTxt (java.lang.String acTxt) {
		this.acTxt = acTxt;
	}



	/**
	 * Return the value associated with the column: ac_typeid
	 */
	public java.lang.Integer getAcTypeid () {
		return acTypeid;
	}

	/**
	 * Set the value related to the column: ac_typeid
	 * @param acTypeid the ac_typeid value
	 */
	public void setAcTypeid (java.lang.Integer acTypeid) {
		this.acTypeid = acTypeid;
	}



	/**
	 * Return the value associated with the column: ac_levelid
	 */
	public java.lang.Integer getAcLevelid () {
		return acLevelid;
	}

	/**
	 * Set the value related to the column: ac_levelid
	 * @param acLevelid the ac_levelid value
	 */
	public void setAcLevelid (java.lang.Integer acLevelid) {
		this.acLevelid = acLevelid;
	}



	/**
	 * Return the value associated with the column: ac_isrecommend
	 */
	public boolean isAcIsrecommend () {
		return acIsrecommend;
	}

	/**
	 * Set the value related to the column: ac_isrecommend
	 * @param acIsrecommend the ac_isrecommend value
	 */
	public void setAcIsrecommend (boolean acIsrecommend) {
		this.acIsrecommend = acIsrecommend;
	}



	/**
	 * Return the value associated with the column: ac_istop
	 */
	public boolean isAcIstop () {
		return acIstop;
	}

	/**
	 * Set the value related to the column: ac_istop
	 * @param acIstop the ac_istop value
	 */
	public void setAcIstop (boolean acIstop) {
		this.acIstop = acIstop;
	}

	public java.util.List<com.jeecms.cms.entity.main.TagToActivity> getTagToActivities() {
		return tagToActivities;
	}

	public void setTagToActivities(
			java.util.List<com.jeecms.cms.entity.main.TagToActivity> tagToActivities) {
		this.tagToActivities = tagToActivities;
	}
	

	public java.util.List<com.jeecms.cms.entity.main.ActivityPicture> getActivityPictures() {
		return activityPictures;
	}

	public void setActivityPictures(
			java.util.List<com.jeecms.cms.entity.main.ActivityPicture> activityPictures) {
		this.activityPictures = activityPictures;
	}

	public java.util.List<com.jeecms.cms.entity.main.ActivityJoin> getActivityJoins() {
		return activityJoins;
	}

	public void setActivityJoins(
			java.util.List<com.jeecms.cms.entity.main.ActivityJoin> activityJoins) {
		this.activityJoins = activityJoins;
	}
	public Integer getAcSchool() {
		return acSchool;
	}

	public void setAcSchool(Integer acSchool) {
		this.acSchool = acSchool;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.Activity)) return false;
		else {
			com.jeecms.cms.entity.main.Activity activity = (com.jeecms.cms.entity.main.Activity) obj;
			if (null == this.getId() || null == activity.getId()) return false;
			else return (this.getId().equals(activity.getId()));
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