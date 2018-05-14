package com.lailai.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lailai.dao.EvaluationDao;
import com.lailai.entity.Check;
import com.lailai.entity.Evaluation;
import com.lailai.util.HibernateUtils;

public class EvaluationDaoImpl implements EvaluationDao {

	private Logger logger = LoggerFactory.getLogger(CheckDaoImpl.class);
	@Override
	public boolean insertEvaluation(List<Evaluation> evaluationList) {

		if(evaluationList!=null&&evaluationList.size()>0){
			Transaction ts = null;
			try {
				Session currentSession = HibernateUtils.getCurrentSession();
				ts = currentSession.beginTransaction();
				for (int i = 0; i < evaluationList.size(); i++) {
					Evaluation evaluation = evaluationList.get(i);
					currentSession.save(evaluation);
					// 批插入的对象立即写入数据库并释放内存
					if(i%2==0){
						currentSession.flush();
						currentSession.clear();
					}
					
				}
				ts.commit();
			} catch (HibernateException e) {
				logger.error("批量添加老师反馈数据失败，事务已经回滚！");
				e.printStackTrace();
				ts.rollback();
				return false;
			}finally{
				//资源关闭，由于是currentSession所以不需要关闭
			}
		}
		return true;
	}
	@Override
	public List<Evaluation> getListByteacher(Date beginTime, Date endTime, Evaluation e) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Query query=null;
		if(e.getUserName()==null){
			query = currentSession.createQuery("from Evaluation where teacher=:teacher and date between :beginTime and :endTime");
			query.setParameter("beginTime", beginTime);
			query.setParameter("endTime", endTime);
			query.setParameter("teacher", e.getTeacher());
		}else{
			query = currentSession.createQuery("from Evaluation where userName like :userName and teacher=:teacher and date between :beginTime and :endTime");
			query.setParameter("beginTime", beginTime);
			query.setParameter("endTime", endTime);
			query.setParameter("teacher",e.getTeacher());	
			query.setParameter("userName",  "%"+e.getUserName()+"%");
		}
		List<Evaluation> list =(List<Evaluation>) query.list();
		ts.commit();
		return list;
	}
	@Override
	public List<Evaluation> getListByUser(Date beginTime, Date endTime, Evaluation e) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Evaluation where userName=:userName and date between :beginTime and :endTime");
		query.setParameter("userName", e.getUserName());
		query.setParameter("beginTime", beginTime);
		query.setParameter("endTime", endTime);
		List<Evaluation> list =(List<Evaluation>) query.list();
		ts.commit();
		return list;
	}
	


}
