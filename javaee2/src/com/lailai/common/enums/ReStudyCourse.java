package com.lailai.common.enums;

public enum ReStudyCourse {
	//补课信息状态
	reStuCourseDisable("禁用",0),
	reStuCourseAble("活跃",1),
	teacherOK("老师选好时间",2),
	//学生补课记录状态
	reStudyDisable("禁用",0),
	reStudyAble("活跃",1);

	
	public String name;
	public int state;

	private ReStudyCourse(String name, int state) {
		this.name = name;
		this.state = state;
	}
}
