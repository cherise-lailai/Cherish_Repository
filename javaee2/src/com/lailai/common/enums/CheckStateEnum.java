package com.lailai.common.enums;

public enum CheckStateEnum {
	normal("正常",0),
	late("迟到",1),
	leaveEarly("早退",2),
	truancy("旷课",3),
	askForLeave("请假",4);
	
	public String name;
	public int state;
	private CheckStateEnum(String name, int state) {
		this.name = name;
		this.state = state;
	}
	
}
