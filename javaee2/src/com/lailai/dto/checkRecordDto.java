package com.lailai.dto;

public class checkRecordDto {
	private String stuName;
	private String checkState;
	private String feedback;
	
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public checkRecordDto() {
		super();
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getCheckState() {
		return checkState;
	}
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	public checkRecordDto(String stuName, String checkState, String feedback) {
		super();
		this.stuName = stuName;
		this.checkState = checkState;
		this.feedback = feedback;
	}
	@Override
	public String toString() {
		return "checkRecordDto [stuName=" + stuName + ", checkState=" + checkState + ", feedback=" + feedback + "]";
	}
	
	
}
