package com.lailai.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.entity.Coursetime;
import com.lailai.entity.Teacher;

public interface TeacherDao {
	/**
	 * 查询某个老师的擅长技能
	 * @param tid
	 * @return
	 */
	public abstract String getGoodAtByTid(String tid);

	/**
	 * 插入一个老师
	 * @param teacher
	 */
	public abstract void insert(Teacher teacher);

	/**
	 * 根据离线条件查询老师列表
	 * @param dc
	 * @return
	 */
	public abstract List<Teacher> findByDc(DetachedCriteria dc);

	/**
	 * 根据老师id查询老师
	 * @param tid
	 * @return
	 */
	public abstract Teacher findBytid(String tid);

	/**
	 * 删除一个老师，实则修改
	 * @param tid
	 * @param newState
	 */
	public abstract void deleteTeacher(String tid, int newState);

	/**
	 * 根据老师编号添加分数  分数为1
	 * @param tid
	 */
	public abstract void addScoreByTid(String tid);
	
	/**
	 * 根据cname来模糊匹配GoodAt字段
	 * @return
	 */
	public abstract List<Teacher> findfixTeaByCname(String cname); 
	
	/**
	 * 根据老师查出上课时间
	 * @param teacher
	 * @return
	 */
	public abstract List<HashMap<String, String>> getTeacherTime(Teacher teacher);
	
	/**
	 * 根据账户密码查询出老师，用于登入模块
	 * @param teacher
	 * @return
	 */
	public abstract Teacher findTeacher(Teacher teacher);
	

	/**
	 * 修改老师密码
	 * @param teacher
	 */
	public abstract void updatePassword(Teacher teacher);
}
