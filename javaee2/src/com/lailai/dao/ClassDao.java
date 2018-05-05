package com.lailai.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.dto.OpenClassDto;
import com.lailai.entity.Coursetime;
import com.lailai.entity.StuClass;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;

public interface ClassDao {
	/**
	 * 查询List<StuClass> 条件 stat=1 year=nowyear 
	 * @param year
	 * @return
	 */
	public abstract List<StuClass> getClassByYear(String year);
	/**
	 * 根据离线条件获得出列表开班信息
	 * @param dc
	 * @return
	 */
	public abstract List<StuClass> getClassListNow(DetachedCriteria dc);
	/**
	 * 根据班级id查询某个班级的开班信息
	 * @param cid
	 * @return
	 */
	public abstract StuClass getClassByCid(String cid);
	

	/**
	 * 开班级联选课
	 * @param stuClass
	 * @param ctList
	 */
	public abstract void openClass(StuClass stuClass,List<Coursetime> ctList);
	

	/**
	 * 更新班级的state字段为禁用
	 * @param cid
	 * @return
	 */
	public abstract String updateClassBycid(String cid);
	/**
	 * 查询简单class
	 * @param dc
	 * @return
	 */
	public abstract  List<StuClass> getSimList(DetachedCriteria dc);
	/**
	 * 根据班级id查询用户列表
	 * @return
	 */
	public abstract List<User> getStusByCid(String cid);
	/**
	 * ，即state不为0的班级
	 * @return
	 */
	public abstract List<String>getAllActiveClass();
	/**
	 * 根据班级名称精准查询班级id
	 * @param cname
	 * @return
	 */
	public abstract String getCidByCname(String cname);

}
