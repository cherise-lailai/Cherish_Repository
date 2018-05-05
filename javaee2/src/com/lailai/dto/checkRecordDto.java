package com.lailai.dto;

public class checkRecordDto {
	private String stuName;
	private String checkState;
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
	public checkRecordDto(String stuName, String checkState) {
		super();
		this.stuName = stuName;
		this.checkState = checkState;
	}
	@Override
	public String toString() {
		return "checkRecordDto [stuName=" + stuName + ", checkState=" + checkState + "]";
	}
	
}
