package com.lailai.common.enums;

public enum Scattered {
	oneTeacher(1),
	twoTeachers(2),
	ThreeTeachers(3),
	fourTeachers(4);
	
	public int number;
	
	
	private Scattered(int number) {
		this.number = number;
	}
	

}
