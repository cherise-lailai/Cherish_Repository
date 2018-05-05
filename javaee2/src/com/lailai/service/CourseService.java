package com.lailai.service;

import org.hibernate.criterion.DetachedCriteria;

public interface CourseService {
	/**
	 * 根据state条件查询所有的课程 
	 * @param state
	 * @return
	 */
	public abstract String getAllCourseByDc(int state);
	

}
