package com.jeecms.cms.entity.main.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_week_champion_comment table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_week_champion_comment"
 */

public abstract class BaseAcWeekChampionComment  implements Serializable {

	public static String REF = "AcWeekChampionComment";
	public static String PROP_CHAMPION_ID = "ChampionId";
	public static String PROP_COMMENT_USER = "CommentUser";
	public static String PROP_COMMENT_USER_NAME = "CommentUserName";
	public static String PROP_ID = "Id";
	public static String PROP_COMMENT_USER_IMG = "CommentUserImg";
	public static String PROP_COMMENT_TEXT = "CommentText";
	public static String PROP_COMMENT_PARENTID = "CommentParentid";


	// constructors
	public BaseAcWeekChampionComment () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcWeekChampionComment (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.lang.Integer commentUser;
	private java.lang.Integer championId;
	private byte[] commentText;
	private java.lang.String commentUserName;
	private java.lang.String commentUserImg;
	private java.lang.String commentParentid;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="comment_id"
     */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: comment_user
	 */
	public java.lang.Integer getCommentUser () {
		return commentUser;
	}

	/**
	 * Set the value related to the column: comment_user
	 * @param commentUser the comment_user value
	 */
	public void setCommentUser (java.lang.Integer commentUser) {
		this.commentUser = commentUser;
	}



	/**
	 * Return the value associated with the column: champion_id
	 */
	public java.lang.Integer getChampionId () {
		return championId;
	}

	/**
	 * Set the value related to the column: champion_id
	 * @param championId the champion_id value
	 */
	public void setChampionId (java.lang.Integer championId) {
		this.championId = championId;
	}



	/**
	 * Return the value associated with the column: comment_text
	 */
	public byte[] getCommentText () {
		return commentText;
	}

	/**
	 * Set the value related to the column: comment_text
	 * @param commentText the comment_text value
	 */
	public void setCommentText (byte[] commentText) {
		this.commentText = commentText;
	}



	/**
	 * Return the value associated with the column: comment_user_name
	 */
	public java.lang.String getCommentUserName () {
		return commentUserName;
	}

	/**
	 * Set the value related to the column: comment_user_name
	 * @param commentUserName the comment_user_name value
	 */
	public void setCommentUserName (java.lang.String commentUserName) {
		this.commentUserName = commentUserName;
	}



	/**
	 * Return the value associated with the column: comment_user_img
	 */
	public java.lang.String getCommentUserImg () {
		return commentUserImg;
	}

	/**
	 * Set the value related to the column: comment_user_img
	 * @param commentUserImg the comment_user_img value
	 */
	public void setCommentUserImg (java.lang.String commentUserImg) {
		this.commentUserImg = commentUserImg;
	}



	/**
	 * Return the value associated with the column: comment_parentid
	 */
	public java.lang.String getCommentParentid () {
		return commentParentid;
	}

	/**
	 * Set the value related to the column: comment_parentid
	 * @param commentParentid the comment_parentid value
	 */
	public void setCommentParentid (java.lang.String commentParentid) {
		this.commentParentid = commentParentid;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.jeecms.cms.entity.main.AcWeekChampionComment)) return false;
		else {
			com.jeecms.cms.entity.main.AcWeekChampionComment acWeekChampionComment = (com.jeecms.cms.entity.main.AcWeekChampionComment) obj;
			if (null == this.getId() || null == acWeekChampionComment.getId()) return false;
			else return (this.getId().equals(acWeekChampionComment.getId()));
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