package com.lailai.common.enums;


public enum Permission {
	MERCHANT("merchant","商家"),
	USER("user","学生"),
	TEACHER("teacher","老师");
	private String role;
	private String desc;
	private Permission(String role, String desc) {
		this.role = role;
		this.desc = desc;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	
	
}
