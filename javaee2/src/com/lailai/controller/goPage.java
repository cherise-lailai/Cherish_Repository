package com.lailai.controller;

import com.opensymphony.xwork2.ActionSupport;

public class goPage extends ActionSupport {
	public String index() {

		return "index";
	}

	public String userAdd() {
		return "userAdd";
	}

	public String teacherAdd() {
		return "teacherAdd";
	}

	public String userManage() {
		return "userManage";
	}

	public String checkAdd() {
		return "checkAdd";
	}

	public String checkManage() {
		return "checkManage";
	}

	public String findCT() {
		return "findCT";
	}

	public String changePWD() {
		return "changePWD";
	}

	public String homeManage() {
		return "homeManage";
	}

	public String courseTimeAdd() {
		return "courseAdd";
	}

	public String openReStuCourse() {
		return "openReCourse";
	}

}
