package com.lailai.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lailai.common.enums.ReStudyCourse;
import com.lailai.dao.ReStuCourseDao;
import com.lailai.entity.ReStuCourse;
import com.lailai.entity.ReStudy;
import com.lailai.util.HibernateUtils;

public class ReStuCourseDaoImpl implements ReStuCourseDao{

	@Override
	public boolean insertReStuCourse(ReStuCourse reStuiCourse) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		currentSession.save(reStuiCourse);
		ts.commit();
		return true;
	}

	@Override
	public List<ReStuCourse> findReStuCourseList(DetachedCriteria dc) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Criteria c = dc.getExecutableCriteria(currentSession);
		List<ReStuCourse> list =(List<ReStuCourse>) c.list();
		ts.commit();
		return list;
	}

	@Override
	public boolean deleteById(ReStuCourse reStuCourse) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		ReStuCourse reStuCourse2 = currentSession.get(ReStuCourse.class, reStuCourse.getId());
		currentSession.delete(reStuCourse2);
		ts.commit();
		return true;
	}

	@Override
	public boolean updateStudyTime(ReStuCourse reStuCourse) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		ReStuCourse reStuCourse2 = currentSession.get(ReStuCourse.class, reStuCourse.getId());
		reStuCourse2.setStudyTime(reStuCourse.getStudyTime());
		reStuCourse2.setState(reStuCourse.getState());
		currentSession.save(reStuCourse2);

		ts.commit();
		return true;
	}

	@Override
	public boolean updateState2Active(ReStuCourse reStuCourse) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		ReStuCourse reStuCourse2 = currentSession.get(ReStuCourse.class, reStuCourse.getId());
		reStuCourse2.setState(reStuCourse.getState());
		currentSession.save(reStuCourse2);
		ts.commit();
		return true;
	}

	@Override
	public List<ReStuCourse> getAll(String period) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from ReStuCourse where period=:period and state!=0");
		
		query.setParameter("period", period);
		List<ReStuCourse>  list =(List<ReStuCourse>) query.list();
		ts.commit();
		return list;
	}

	@Override
	public boolean deleteUserByreStuCouId(String reStuCourseId) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Query query = currentSession.createQuery("delete from ReStudy where restucourseid=:restucourseid");
		query.setParameter("restucourseid", reStuCourseId);
		query.executeUpdate();
		ts.commit();
		return true;
	}
}
