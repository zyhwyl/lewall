package com.jeecms.cms.entity.main;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.jeecms.cms.entity.main.base.BaseActivityWatch;



public class ActivityWatch extends BaseActivityWatch {
	private static final long serialVersionUID = 1L;

	private Integer nowPanel=0;
	private ActivityComment comment;
	
	
	public ActivityComment getComment() {
		return comment;
	}

	public void setComment(ActivityComment comment) {
		this.comment = comment;
	}

	public Integer getNowPanel() {
		return nowPanel;
	}

	public void setNowPanel(Integer nowPanel) {
		this.nowPanel = nowPanel;
	}


	/*[CONSTRUCTOR MARKER BEGIN]*/
	public ActivityWatch () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public ActivityWatch (java.lang.String id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/
	//生成主键
	public void getUUID(){
		String uuid = UUID.randomUUID().toString();
		uuid = StringUtils.remove(uuid, '-');
		this.setId(uuid);
	}

}