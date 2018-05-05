package com.lailai.common.enums;

public enum StateEnum {
	userDisable("禁用",0),
	userAble("活跃",1),
	teacherDisable("禁用",0),
	teacherAble("活跃",1),
	merchantDisable("禁用",0),
	merchantAble("活跃",1),
	classDisable("禁用",0),
	classAble("活跃",1),
	courseDisable("禁用",0),
	courseAble("活跃",1);
	public String name;
	public int state;

	private StateEnum(String name, int state) {
		this.name = name;
		this.state = state;
	}

	

	
}
