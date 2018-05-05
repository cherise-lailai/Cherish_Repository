package com.lailai.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.dao.StudyDao;
import com.lailai.dao.impl.StudyDaoImpl;
import com.lailai.dto.OpenClassDto;
import com.lailai.entity.Coursetime;
import com.lailai.service.ClassService;
import com.lailai.service.StudyService;

public class StudyServiceImpl implements StudyService{

	private StudyDao studyDao=new StudyDaoImpl();
	private ClassService classService=new ClassServiceImpl();
	@Override
	public List<Coursetime> getCTListByDC(DetachedCriteria dc) {
		return studyDao.getCTListByDC(dc);
	}
	@Override
	public List<String> getClassListByTea(String teaname) {
		List<String> list=studyDao.getClassIDListByTea(teaname);
		return list;
	}
	@Override
	public List<Coursetime>  getCTOfUser(String classID) {
		return studyDao.getCTOfUser(classID);
	}
	@Override
	public List<Coursetime> getCTOfTeacher(DetachedCriteria dc) {
		List<Coursetime> ctLsit= studyDao.getCTOfTeacher(dc);
		return ctLsit;
	}
	

}
