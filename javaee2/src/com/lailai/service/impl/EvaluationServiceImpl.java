package com.lailai.service.impl;

import java.util.Date;
import java.util.List;

import com.lailai.dao.EvaluationDao;
import com.lailai.dao.impl.EvaluationDaoImpl;
import com.lailai.entity.Evaluation;
import com.lailai.service.EvaluationService;

public class EvaluationServiceImpl implements EvaluationService {

	private EvaluationDao evaluationDao = new EvaluationDaoImpl();

	@Override
	public boolean addFeedback(List<Evaluation> evaluationList) {
		boolean isok = evaluationDao.insertEvaluation(evaluationList);
		return isok;
	}

	@Override
	public List<Evaluation> getListBetweenTime(Date bt, Date et, Evaluation e) {
		List<Evaluation> evaList=null;
		//判断是老师还是学生
		//学生
		if(e.getTeacher()!=null){
			evaList=evaluationDao.getListByteacher(bt, et, e);
		}
		if(e.getUserName()!=null&&e.getTeacher()==null){
			evaList = evaluationDao.getListByUser(bt, et, e);
		}
		return evaList;
	}
}
