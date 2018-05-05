package com.lailai.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.components.Select;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.type.StandardBasicTypes;

import com.lailai.dao.StudyDao;
import com.lailai.entity.Coursetime;
import com.lailai.entity.StuClass;
import com.lailai.util.HibernateUtils;

public class StudyDaoImpl implements StudyDao{

	@Override
	public List<Coursetime> getCTListByDC(DetachedCriteria dc) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Criteria c = dc.getExecutableCriteria(session);
		List list =c.list();
		ts.commit();
//		session.close();  好像不能关闭，懒加载它的stuClass好像用到
		return list;
	}

	@Override
	public List<String> getClassIDListByTea(String teaname) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		SQLQuery sqlQuery = currentSession.createSQLQuery("Select distinct(classid) cid from coursetime "
				+ "where year= ? and teacher=?").addScalar("cid",StandardBasicTypes.STRING);
		Calendar date = Calendar.getInstance();
	    String year = String.valueOf(date.get(Calendar.YEAR));
		sqlQuery.setParameter(0, year);
		sqlQuery.setParameter(1, teaname);
		List<String> claList = new ArrayList<String>();
		List list = sqlQuery.list();
		for (Object object : list) {
			claList.add((String)object);
		}
		ts.commit();
		return claList;
	}

	@Override
	public List<Coursetime> getCTOfUser(String cid) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		StuClass stuClass = new StuClass();
		stuClass.setId(cid);
		Query query = currentSession.createQuery("from Coursetime where stuClass=:stuClass");
		query.setParameter("stuClass", stuClass);
		List<Coursetime> list = (List<Coursetime>)query.list();
		ts.commit();
		return list;
	}

	@Override
	public List<Coursetime> getCTOfTeacher(DetachedCriteria dc) {
		Session openSession = HibernateUtils.openSession();
		Transaction ts = openSession.beginTransaction();
		Criteria c = dc.getExecutableCriteria(openSession);
		List<Coursetime> list =(List<Coursetime>) c.list();
		ts.commit();
		return list;
	}

}
