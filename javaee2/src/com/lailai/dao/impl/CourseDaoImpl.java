package com.lailai.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.lailai.dao.ClassDao;
import com.lailai.dao.CourseDao;
import com.lailai.entity.Course;
import com.lailai.entity.StuClass;
import com.lailai.entity.Teacher;
import com.lailai.util.HibernateUtils;

public class CourseDaoImpl implements CourseDao {

	@Override
	public List<Course> getAllCourse(int state) {
		Session session = HibernateUtils.openSession();
		Query query = session.createQuery("from Course where state=:state");
		query.setParameter("state", state);
		List<Course> courseLsit =(List<Course>) query.list();
		session.close();
		return courseLsit;
	}

	@Override
	public void deleteCTByCid(String cid) {
		Session currentSession = HibernateUtils.getCurrentSession();
		StuClass stuClass = new StuClass();
		stuClass.setId(cid);
		Query delQuery = currentSession.createQuery("delete Coursetime where stuClass=:stuClass");
		delQuery.setParameter("stuClass", stuClass);	
		delQuery.executeUpdate();
//		throw new RuntimeException();  测试事务回滚
	}
}
