package com.jeecms.cms.entity.main;

import java.sql.Timestamp;
import java.util.List;

import com.jeecms.cms.entity.main.base.BaseAcUserTeam;



public class AcUserTeam extends BaseAcUserTeam {
	private static final long serialVersionUID = 1L;

	private List<AcUserTeamMember> members;
	private List<AcUserTeamTag> tags;
	
	/**
	 * 当前用户是否能以此团队的名义发布活动  或者参加活动
	 * 0：不能
	 * 1：能
	 * */
	private Integer canpub;
	
	//当前登录用户是否可以这些操作 0：不能 1：能
	private Integer canAttention=1; 
	private Integer canJoin=1;
	
	
	public Integer getCanJoin() {
		return canJoin;
	}

	public void setCanJoin(Integer canJoin) {
		this.canJoin = canJoin;
	}

	public Integer getCanAttention() {
		return canAttention;
	}

	public void setCanAttention(Integer canAttention) {
		this.canAttention = canAttention;
	} 

	public Integer getCanpub() {
		return canpub;
	}

	public void setCanpub(Integer canpub) {
		this.canpub = canpub;
	}

	public List<AcUserTeamTag> getTags() {
		return tags;
	}

	public void setTags(List<AcUserTeamTag> tags) {
		this.tags = tags;
	}

	public List<AcUserTeamMember> getMembers() {
		return members;
	}

	public void setMembers(List<AcUserTeamMember> members) {
		this.members = members;
	}

	/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcUserTeam () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcUserTeam (java.lang.Integer id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/

	public void init(){
		this.setTeamCreatetime(new Timestamp(System.currentTimeMillis()));
		this.setTeamLevel(0);
		this.setTeamScore("0");
		this.setTeamRank(1);
		this.setTeamIscertificate(0);
		this.setTeamIsforbid(0);
		this.setTeamUpdatetime(new Timestamp(System.currentTimeMillis()));
		
	}

}