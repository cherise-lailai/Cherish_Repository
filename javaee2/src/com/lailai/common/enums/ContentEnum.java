package com.lailai.common.enums;

public enum ContentEnum {
	Introduction("jj","简介"),
	style("fm","学校风貌"),
	notice("tz","温馨提示");
	public String contentId;
	public String ContentTitle;
	private ContentEnum(String contentId, String contentTitle) {
		this.contentId = contentId;
		ContentTitle = contentTitle;
	}
}
