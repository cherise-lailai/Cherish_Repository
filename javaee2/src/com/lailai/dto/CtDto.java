package com.lailai.dto;
//前台传递过来的开班ct数组
public class CtDto {

	private String cid;
	private String course;
	private String teacher;
	private String xingqi;
	private String stuTime;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getXingqi() {
		return xingqi;
	}
	public void setXingqi(String xingqi) {
		this.xingqi = xingqi;
	}
	public String getStuTime() {
		return stuTime;
	}
	public void setStuTime(String stuTime) {
		this.stuTime = stuTime;
	}

	
	@Override
	public String toString() {
		return "CtDto [cid=" + cid + ", course=" + course + ", teacher=" + teacher + ", xingqi=" + xingqi + ", stuTime="
				+ stuTime + "]";
	}
	public CtDto() {
		super();
	}
	public CtDto(String cid, String course, String teacher, String xingqi, String stuTime) {
		super();
		this.cid = cid;
		this.course = course;
		this.teacher = teacher;
		this.xingqi = xingqi;
		this.stuTime = stuTime;
	}
	
	

}
