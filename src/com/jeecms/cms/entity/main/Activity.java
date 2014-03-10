package com.jeecms.cms.entity.main;

import java.sql.Timestamp;
import java.util.List;

import com.jeecms.cms.entity.main.base.BaseActivity;



public class Activity extends BaseActivity {
	private static final long serialVersionUID = 1L;
	/**
	 * 活动状态
	 * 0:最新活动 还没开始
	 * 1:活动正在进行中...
	 * 2:活动已经结束
	 * */
	public static final Integer ACTIVITY_PREPARE=0;
	public static final Integer ACTIVITY_GOING=1;
	public static final Integer ACTIVITY_FINISHED=2;
	/**
	 * 活动是否通过审核
	 * 0:未通过
	 * 1:已通过
	 * */
	public static final Integer ACTIVITY_NOPASS=0;
	public static final Integer ACTIVITY_PASS=1;

	private String beginChinese;
	//活动图片
	private List<String> imgpaths;
	//发布人
	private CmsUser user;
	//发布团队
	private AcUserTeam team;
	//活动标签
	private List<ActivityTag> tags;
	//当前所属列
	private Integer nowPanel;
	//是否围观此活动 0 否 1 是
	private Integer isWatch=0;
	//是否参加此活动 0 否 1 是
	private Integer isSign=0;
	//活动的评论
	private List<ActivityComment> commentList;
	//活动参与的用户数量
	private Integer joinNum;
	//活动围观的用户数量
	private Integer watchNum;
	//活动参与的用户
	private List<ActivityJoin> joinList;
	//活动围观的用户
	private List<ActivityWatch> watchList;
	//活动直播情况
	private List<ActivityLive> liveList;
	
	public List<ActivityLive> getLiveList() {
		return liveList;
	}

	public void setLiveList(List<ActivityLive> liveList) {
		this.liveList = liveList;
	}

	public String getBeginChinese() {
		return beginChinese;
	}

	public void setBeginChinese(String beginChinese) {
		this.beginChinese = beginChinese;
	}

	/*[CONSTRUCTOR MARKER BEGIN]*/
	public Activity () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Activity (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Activity (
		java.lang.Integer id,
		java.lang.Integer acUserid,
		java.util.Date acCreatedate,
		java.lang.Integer acState,
		java.util.Date acStatedate,
		java.util.Date acBegindate,
		java.lang.Integer acCategory) {

	}

/*[CONSTRUCTOR MARKER END]*/
	
	public List<ActivityWatch> getWatchList() {
		return watchList;
	}

	public AcUserTeam getTeam() {
		return team;
	}

	public void setTeam(AcUserTeam team) {
		this.team = team;
	}

	public void setWatchList(List<ActivityWatch> watchList) {
		this.watchList = watchList;
	}

	public Integer getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
	}

	public Integer getWatchNum() {
		return watchNum;
	}

	public void setWatchNum(Integer watchNum) {
		this.watchNum = watchNum;
	}

	public List<ActivityJoin> getJoinList() {
		return joinList;
	}

	public void setJoinList(List<ActivityJoin> joinList) {
		this.joinList = joinList;
	}

	public List<ActivityComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<ActivityComment> commentList) {
		this.commentList = commentList;
	}

	public Integer getIsWatch() {
		return isWatch;
	}

	public void setIsWatch(Integer isWatch) {
		this.isWatch = isWatch;
	}

	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}

	public Integer getNowPanel() {
		return nowPanel;
	}

	public void setNowPanel(Integer nowPanel) {
		this.nowPanel = nowPanel;
	}

	public List<ActivityTag> getTags() {
		return tags;
	}

	public void setTags(List<ActivityTag> tags) {
		this.tags = tags;
	}

	public CmsUser getUser() {
		return user;
	}

	public void setUser(CmsUser user) {
		this.user = user;
	}

	public List<String> getImgpaths() {
		return imgpaths;
	}

	public void setImgpaths(List<String> imgpaths) {
		this.imgpaths = imgpaths;
	}

	//初始化活动字段
	public void init(){
		//设置创建活动时间
		this.setAcCreatedate(new Timestamp(System.currentTimeMillis()));
		//设置当前活动状态
		this.setAcState(ACTIVITY_PREPARE);
		//设置状态时间
		this.setAcStatedate(new Timestamp(System.currentTimeMillis()));
		//设置当前默认为未通过状态
		this.setAcPass(ACTIVITY_NOPASS);
	}
}