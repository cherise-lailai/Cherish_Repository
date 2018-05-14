package com.lailai.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.entity.Check;

public interface CheckDao {

	/**
	 * 添加一批考勤记录
	 * 
	 * @param checkList
	 * @return
	 */
	boolean addBatch(List<Check> checkList);

	/**
	 * 根据条件查询所有需要补课的学生
	 * @param dc2
	 * @return
	 */
	List<Check> findNeedReStuUser(DetachedCriteria dc2);

}
