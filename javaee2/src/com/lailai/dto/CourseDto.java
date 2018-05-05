package com.lailai.dto;

public class CourseDto {
	private String courseId;
	private String CourseName;
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	public CourseDto(String courseId, String courseName) {
		super();
		this.courseId = courseId;
		CourseName = courseName;
	}
	@Override
	public String toString() {
		return "CourseDto [courseId=" + courseId + ", CourseName=" + CourseName + "]";
	};
	
}
