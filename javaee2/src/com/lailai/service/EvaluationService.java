package com.lailai.service;

import java.util.Date;
import java.util.List;

import com.lailai.entity.Evaluation;

public interface EvaluationService {

	/**
	 * 添加一个反馈信息表
	 * @param evaluationList
	 * @return
	 */
	public abstract boolean addFeedback(List<Evaluation> evaluationList );
	/**
	 * 根据开始时间与结束时间与部分Evaluation条件查询出反馈记录
	 * @param bDate
	 * @param eDate
	 * @param e
	 * @return
	 */
	public abstract List<Evaluation> getListBetweenTime(Date bDate,Date eDate,Evaluation e);
}
