package com.lailai.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lailai.common.enums.StateEnum;
import com.lailai.entity.Course;
import com.lailai.entity.Coursetime;
import com.lailai.service.CourseService;
import com.lailai.service.impl.CourseServiceImpl;
import com.lailai.util.JsonUtils;
import com.opensymphony.xwork2.ActionSupport;

public class CourseAction extends ActionSupport {
	private	Logger logger = LoggerFactory.getLogger(ClassAction.class);
	private CourseService courseService=new CourseServiceImpl();
	
	public void getAllCourse(){
		String resultStr = courseService.getAllCourseByDc(StateEnum.courseAble.state);
		String resultJson = JsonUtils.objectToJson(resultStr);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(resultJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	
}
