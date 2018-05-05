package com.lailai.entity;

/**
 * Coursetime entity. @author MyEclipse Persistence Tools
 */

public class Coursetime implements java.io.Serializable {

	// Fields

	private String id;
	private StuClass stuClass;
	private String courseName;
	private String time;
	private String teacher;
	private String year;

	// Constructors

	/** default constructor */
	public Coursetime() {
	}

	/** no class */
	public Coursetime(String id,  String courseName, String time, String teacher, String year) {
		this.id = id;
		this.courseName = courseName;
		this.time = time;
		this.teacher = teacher;
		this.year = year;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StuClass getStuClass() {
		return this.stuClass;
	}

	public void setStuClass(StuClass stuClass) {
		this.stuClass = stuClass;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTeacher() {
		return this.teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}