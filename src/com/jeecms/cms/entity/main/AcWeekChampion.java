package com.jeecms.cms.entity.main;

import com.jeecms.cms.entity.main.base.BaseAcWeekChampion;



public class AcWeekChampion extends BaseAcWeekChampion {
	private static final long serialVersionUID = 1L;

	private Activity activity;
	
	
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/*[CONSTRUCTOR MARKER BEGIN]*/
	public AcWeekChampion () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public AcWeekChampion (java.lang.Integer id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/


}