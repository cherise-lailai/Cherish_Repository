package com.lailai.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.entity.Coursetime;

public interface StudyDao {
	
	/**
	 * 根据离线条件查询上课安排
	 * @param dc
	 * @return
	 */
	public abstract List<Coursetime> getCTListByDC(DetachedCriteria dc);

	/**
	 * 根据自己老师名字查询今年要上的班级列表
	 * @param teaname
	 * @return
	 */
	public abstract List<String> getClassIDListByTea(String teaname);

	/**
	 * 查询当前学生的课表
	 * @param cid
	 * @return
	 */
	public abstract List<Coursetime> getCTOfUser(String cid);
	/**
	 * 查询当前老师的课表
	 * @param dc
	 * @return
	 */
	public abstract List<Coursetime> getCTOfTeacher(DetachedCriteria dc);

}
