package com.lailai.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.lailai.dao.ReStudyDao;
import com.lailai.entity.Check;
import com.lailai.entity.ReStuCourse;
import com.lailai.entity.ReStudy;
import com.lailai.util.HibernateUtils;

public class ReStudyDaoImpl implements ReStudyDao{

	@Override
	public List<String> getReStudyCourseList(DetachedCriteria dc) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Criteria c = dc.getExecutableCriteria(currentSession);
		c.setProjection(Projections.distinct(Projections.property("courseName")));
		List<String> list =(List<String>) c.list();
		ts.commit();
		return list;
	}

	@Override
	public boolean insertReStudy(ReStudy reStudy) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		currentSession.save(reStudy);
		ts.commit();
		return true;
	}

	@Override
	public String findIdByCourseName(DetachedCriteria dc) {
		
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Criteria c = dc.getExecutableCriteria(currentSession);
		List list = c.list();
		String reStudyCourseId=null;
		if(list.size()==1){
			reStudyCourseId =((ReStuCourse) list.get(0)).getId();
		}
		ts.commit();
		return reStudyCourseId;
	}

	@Override
	public List<ReStudy> findUserNeedReStu(DetachedCriteria dc2) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Criteria c = dc2.getExecutableCriteria(currentSession);
		List<ReStudy> list = (List<ReStudy>)c.list();
		ts.commit();
		return list;
	}
	

}
