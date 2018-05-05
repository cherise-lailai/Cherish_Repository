package com.lailai.dto;

public class ClassDto {
	private String cid;
	private String cname;
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
	@Override
	public String toString() {
		return "ClassDto [cid=" + cid + ", cname=" + cname + "]";
	}
	public ClassDto(String cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	
	
}
