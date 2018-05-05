package com.lailai.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.dto.OpenClassDto;
import com.lailai.entity.Coursetime;

public interface StudyService {
	/**
	 * 
	 * @param 
	 * @return
	 */
	public abstract List<Coursetime> getCTListByDC(DetachedCriteria dc);

	//根据老师找到今年自己要上的班级
	public abstract List<String> getClassListByTea(String teaname);

	/**
	 * 获得当前学生课表
	 * @param classID
	 * @return
	 */
	public abstract List<Coursetime>  getCTOfUser(String classID);

	/**
	 * 获得当前老师课表
	 * @param dc
	 * @return
	 */
	public abstract List<Coursetime> getCTOfTeacher(DetachedCriteria dc);
	

}
