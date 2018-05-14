package com.lailai.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.entity.Check;
import com.lailai.entity.Evaluation;

public interface CheckService {
	/**
	 * 添加一次考勤记录，一个列表
	 * @param checkList
	 * @param evaluationList 
	 * @return
	 */
	public abstract boolean addCheck(List<Check> checkList, List<Evaluation> evaluationList);

	/**
	 * 根据离线条件查询需要补课的学生姓名列表（当前半个月，某个课程下请假的学生列表）
	 * @param dc2
	 * @return
	 */
	public abstract List<Check> findNeedReStuUser(DetachedCriteria dc2);
}
