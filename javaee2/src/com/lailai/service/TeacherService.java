package com.lailai.service;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.common.Page;
import com.lailai.entity.Course;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;

public interface TeacherService {
	/**
	 * 添加一个老师
	 * @param teacher
	 */
	public abstract void add(Teacher teacher);
	/**
	 * 根据离线条件查询老师列表
	 * @param dc
	 * @return
	 */
	public abstract List<Teacher> findByDc(DetachedCriteria dc);
	/**
	 * 根据老师id查询老师
	 * @return
	 */
	public abstract Teacher findBytid(String tid);
	/**
	 * 删除一个老师，实则更新状态为禁用
	 * @param tid
	 * @param newState
	 */
	public abstract void deleteTeacher(String tid,int newState);
	
	
	
	/**
	 * 根据老师id，获取老师擅长的课程
	 * @param tid
	 * @return
	 */
	public String getTeaGoodAt(String tid);
	/**
	 * 根据老师id,添加老师的分数
	 * @param tid
	 */
	public abstract void addScoreByTid(String tid);
	
	/**
	 * 根据课程来选择合适的老师：即goodAt该课程
	 */
	public abstract String fixTeacher(String cname);
	/**
	 * 根据老师的名字来查询老师的上课时间
	 * @param teacher
	 * @return
	 */
	public abstract List<HashMap<String, String>> getTeachTime(Teacher teacher);
	/**
	 * 查询所有的课程
	 * @return
	 */
	public abstract List<Course> getAllCourse();
}
