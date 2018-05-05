package com.lailai.service.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.dao.CourseDao;
import com.lailai.dao.impl.CourseDaoImpl;
import com.lailai.entity.Course;
import com.lailai.service.CourseService;

public class CourseServiceImpl implements CourseService {

	private CourseDao couresDao=new CourseDaoImpl();
	@Override
	public String getAllCourseByDc(int state) {
		List<Course> courseList = couresDao.getAllCourse(state);
		StringBuilder courseSb = new StringBuilder();
		for (Course course : courseList) {
			courseSb.append(course.getName()).append(",");
		}
		String resultStr = courseSb.toString().substring(0, courseSb.toString().length()-1);
		return resultStr;
	}
	

}
