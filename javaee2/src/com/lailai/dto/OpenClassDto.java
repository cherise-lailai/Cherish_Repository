package com.lailai.dto;

public class OpenClassDto {
	
	private String cid;
	//班级名
	private String cname;
	private String year;
	private String courseName;
	private String studyTime;
	private String teachers;
	private Double price;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}
	public String getTeachers() {
		return teachers;
	}
	public void setTeachers(String teachers) {
		this.teachers = teachers;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "OpenClassDto [cid=" + cid + ", cname=" + cname + ", year=" + year + ", courseName=" + courseName
				+ ", studyTime=" + studyTime + ", teachers=" + teachers + ", price=" + price + "]";
	}
	public OpenClassDto(String cid, String cname, String year, String courseName, String studyTime, String teachers,
			Double price) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.year = year;
		this.courseName = courseName;
		this.studyTime = studyTime;
		this.teachers = teachers;
		this.price = price;
	}
	public OpenClassDto() {
		super();
	}
	
	
}
