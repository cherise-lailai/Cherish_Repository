package com.lailai.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.dto.CtDto;
import com.lailai.dto.OpenClassDto;
import com.lailai.entity.Coursetime;
import com.lailai.entity.StuClass;
import com.lailai.entity.User;

public interface ClassService {
	
	/**
	 * 根据期度获取班级
	 * @param year
	 * @return
	 */
	public abstract List<StuClass> findByYear(String year);

	/**
	 * 获取开班信息列表  条件：state year
	 * @param dc
	 * @return
	 */
	public abstract List<OpenClassDto> getNowClass(DetachedCriteria dc);
	/**
	 * 根据cid查询班级信息
	 * @param cid
	 * @return
	 */
	public abstract OpenClassDto getClassByCid(String cid);
	/**
	 * 准备好开班Dao需要的班与CT信息
	 * @param stuClass
	 * @param asList
	 */
	public abstract void openClass(StuClass stuClass,List<CtDto> asList);

	/**
	 * 删除一个班级，实则更新状态
	 * @param cid
	 * @return
	 */
	public abstract String deleteClass(String cid);

	/**
	 * 获得简单的班级列表
	 * @return
	 */
	public abstract  List<StuClass> getNowSimClas(DetachedCriteria dc);

	/**
	 * 根据班级id查询所有的学生
	 * @param cid
	 * @return
	 */
	public abstract List<User> getStusByCid(String cid);
	/**
	 * 查询所有未被禁用的班级
	 * @return
	 */
	public abstract List<String>getAllActiveClass();
	
	
	/**
	 */
	public abstract String getCidByCname(String cname);

}
