package com.jeecms.cms.entity.main;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.jeecms.cms.entity.main.base.BaseAcUserTeamMember;



public class AcUserTeamMember extends BaseAcUserTeamMember {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 成员目前的状态<br/>
	 * 0:当前成员已被激活正常状态<br/>
	 * 
	 * */
	public static final Integer MEMBER_STATE_NORMAL=0;
	/**
	 * 成员目前的状态<br/>
	 * 1:当前成员被邀请中的状态 接受后置为0 或者删除<br/>
	 * 
	 * */
	public static final Integer MEMBER_STATE_SENDING=1;
	/**
	 * 成员目前的状态<br/>
	 * 2:当前成员申请入团状态 由创建人审核后 置为0 或者删除<br/>
	 * 
	 * */
	public static final Integer MEMBER_STATE_APPLYING=2;
	/**
	 * 成员目前的状态<br/>
	 * 3:当前成员被禁用
	 * 
	 * */
	public static final Integer MEMBER_STATE_DISABLE=3;
	/**
	 * 成员目前的状态<br/>
	 * 4:当前成员忽略了你的请求
	 * 
	 * */
	public static final Integer MEMBER_STATE_DISCARD=4;
/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcUserTeamMember () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcUserTeamMember (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public AcUserTeamMember (
		java.lang.String id,
		java.lang.Integer autTeamid,
		java.lang.Integer autUserid) {

		super (
			id,
			autTeamid,
			autUserid);
	}

/*[CONSTRUCTOR MARKER END]*/
	//生成主键
	public void getUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = StringUtils.remove(uuid, '-');
		this.setId(uuid);
	}

}