package com.lailai.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.dao.CheckDao;
import com.lailai.dao.EvaluationDao;
import com.lailai.dao.impl.CheckDaoImpl;
import com.lailai.dao.impl.EvaluationDaoImpl;
import com.lailai.entity.Check;
import com.lailai.entity.Evaluation;
import com.lailai.service.CheckService;

public class CheckServiceImpl implements CheckService {
	private CheckDao checkDao=new CheckDaoImpl();
	private EvaluationDao evaluationDao=new EvaluationDaoImpl();
	@Override
	public boolean addCheck(List<Check> checkList, List<Evaluation> evaluationList) {
		//添加考勤记录
		boolean checkIsOk=checkDao.addBatch(checkList);
		//添加老师反馈信息
		boolean evaluationIsOK = evaluationDao.insertEvaluation(evaluationList);
		if(checkIsOk==true&&evaluationIsOK==true){
			return true;
		}
		return false;
	}
	@Override
	public List<Check> findNeedReStuUser(DetachedCriteria dc2) {
		return checkDao.findNeedReStuUser(dc2);
	}
}
