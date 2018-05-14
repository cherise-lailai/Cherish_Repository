package com.lailai.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.entity.Check;
import com.lailai.entity.ReStudy;

public interface ReStudyDao {
	/**
	 * 查询半个月时间内的有多少课程需要补课
	 * @return
	 */
	public List<String>getReStudyCourseList(DetachedCriteria dc);
	
	
	/**
	 * 插入一条补课记录表
	 * @param reStudy
	 * @return
	 */
	public abstract boolean insertReStudy(ReStudy reStudy);

	/**
	 * 通过课程名字查询补课记录表id
	 * @param un
	 * @return
	 */

	public String findIdByCourseName(DetachedCriteria dc);

}
