package com.lailai.common.enums;

public enum TeacherScore {
	goldTeacher("金牌讲师",1000),
	silverTeacher("银牌讲师",900),
	bronzeTeacher("铜牌讲师",800),
	ordinaryTeacher("普通讲师",700);
	public String scoreDesc;
	public Integer score;
	private TeacherScore(String scoreDesc, Integer score) {
		this.scoreDesc = scoreDesc;
		this.score = score;
	}
	
}
