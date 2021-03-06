package com.lailai.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.entity.Course;

public interface CourseDao {
	/**
	 * 根据课程状态查询课程列表
	 * @param state
	 * @return
	 */
	public List<Course> getAllCourse(int state);

	/**
	 * 根据cid删除CT
	 * @param cid
	 */
	public void deleteCTByCid(String cid);

}
