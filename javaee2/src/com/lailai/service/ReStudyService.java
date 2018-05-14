package com.lailai.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.common.enums.ReStudyCourse;
import com.lailai.entity.ReStuCourse;
import com.lailai.entity.ReStudy;

public interface ReStudyService {
	/**
	 * 根据离线条件查询出补课的课程列表
	 * @param dc
	 * @return
	 */
	public abstract List<String> getReStudyList(DetachedCriteria dc);
	/**
	 * 添加一个补课记录
	 * @param reStudy
	 * @return
	 */
	public abstract boolean addReStuCourse(ReStuCourse reStuCourse);
	
	/**
	 * 找出补课信息列表
	 * @return
	 */
	public abstract List<ReStuCourse> findReStuCourseList(DetachedCriteria dc);
	/**
	 * 根据id删除补课表
	 * @param rscid
	 * @return
	 */
	public abstract boolean deleteReStuCourseByid(String rscid);
	/**
	 * 老师对商家开设的补课进行填写上课时间
	 * @param reStuCourse
	 * @return
	 */
	public abstract boolean addReStuCourseByTea(ReStuCourse reStuCourse);
	/**
	 * 老师取消安排的时间，重新设置时间
	 * @param reStuCourse
	 * @return
	 */
	public abstract boolean reSetStuTimeBytea(ReStuCourse reStuCourse);
	/**
	 * 查询已经开设的课程
	 * @return
	 */
	public abstract List<ReStuCourse> getAleadyopen(String periodStr2);
	



}
