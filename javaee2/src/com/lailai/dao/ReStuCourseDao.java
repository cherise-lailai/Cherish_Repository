package com.lailai.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.common.enums.ReStudyCourse;
import com.lailai.entity.ReStuCourse;

public interface ReStuCourseDao {

	/**
	 * 保存一个补课信息
	 * 
	 * @param reStuiCourse
	 * @return
	 */
	public abstract boolean insertReStuCourse(ReStuCourse reStuiCourse);

	/**
	 * 查询处于活跃状态的补课信息列表
	 * @return
	 */
	public abstract List<ReStuCourse> findReStuCourseList(DetachedCriteria dc);
	/**
	 * 根据id删除
	 * @param rscid
	 * @return
	 */
	public abstract boolean deleteById(ReStuCourse reStuCourse);
	
	/**
	 * 更改studytime（即null->have）
	 * @param reStuCourse
	 * @return
	 */
	public abstract boolean updateStudyTime(ReStuCourse reStuCourse);

	/**
	 * 更新开课字段为活跃（即管理员安排了，老师未设置补课时间）
	 * @return
	 */
	public abstract boolean updateState2Active(ReStuCourse reStuCourse);

	/**
	 * 查询某个时间段
	 * @return
	 */
	public abstract List<ReStuCourse> getAll(String periodStr);

	/**
	 * 根据补课课程删除下面的学生
	 * @param periodStr
	 */
	public abstract boolean deleteUserByreStuCouId(String reStuCourseId);

	/**
	 * 查询出上半个月需要开的补课
	 * @param dc
	 * @return
	 */
	public abstract List<ReStuCourse> getAllPreHalfMonth(DetachedCriteria dc);
}
