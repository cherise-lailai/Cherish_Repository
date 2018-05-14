package com.lailai.dao;

import java.util.Date;
import java.util.List;

import com.lailai.entity.Evaluation;

public interface EvaluationDao {
	/**
	 * 插入一条评价信息
	 * @param evaluationList
	 */
	public abstract boolean insertEvaluation(List<Evaluation> evaluationList);
	
	/**
	 * 查询出某个时间段的反馈信息(使用者老师)
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public abstract List<Evaluation>  getListByteacher(Date beginTime,Date endTime,Evaluation evaluation);
	/**
	 * 查询出某个时间段的反馈信息(学生老师)
	 * @param beginTime
	 * @param endTime
	 * @param evaluation
	 * @return
	 */
	
	public abstract List<Evaluation>  getListByUser(Date beginTime,Date endTime,Evaluation evaluation);

}
