package com.lailai.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.lailai.dao.HomeDao;
import com.lailai.entity.Content;
import com.lailai.entity.Teacher;
import com.lailai.util.HibernateUtils;

public class HomeDaoImpl implements HomeDao {
	
	@Override
	public void update(List<Content> contentList) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		for (Content content : contentList) {
			session.update(content);
		}
		ts.commit();
		session.close();
	}

	@Override
	public List<Content> findAllContent() {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Query query = session.createQuery("from Content");
		List<Content> contentList =(List<Content>)query.list();
		ts.commit();
		session.close();
		return contentList;
	}

	@Override
	public List<Teacher> findByDc(DetachedCriteria dc) {
		Session session = HibernateUtils.openSession();
		Transaction ts= session.beginTransaction();
		Criteria c = dc.getExecutableCriteria(session);
		List<Teacher> teacherList = (List<Teacher>)c.list();
		ts.commit();
		session.close();
		return teacherList;
	}

}
