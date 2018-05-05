package com.lailai.entity;
// default package

import java.util.Date;

/**
 * Check entity. @author MyEclipse Persistence Tools
 */

public class Check implements java.io.Serializable {

	// Fields

	private String id;
	private String userName;
	private String studyTime;
	private String year;
	private String className;
	private String courseName;
	private String checkTeacher;
	private Date checkDate;
	private Integer checkState;

	// Constructors

	/** default constructor */
	public Check() {
	}

	/** full constructor */
	public Check(String id, String userName, String studyTime, String year, String className, String courseName,
			String checkTeacher, Date checkDate, Integer checkState) {
		this.id = id;
		this.userName = userName;
		this.studyTime = studyTime;
		this.year = year;
		this.className = className;
		this.courseName = courseName;
		this.checkTeacher = checkTeacher;
		this.checkDate = checkDate;
		this.checkState = checkState;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStudyTime() {
		return this.studyTime;
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCheckTeacher() {
		return this.checkTeacher;
	}

	public void setCheckTeacher(String checkTeacher) {
		this.checkTeacher = checkTeacher;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Integer getCheckState() {
		return this.checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

}