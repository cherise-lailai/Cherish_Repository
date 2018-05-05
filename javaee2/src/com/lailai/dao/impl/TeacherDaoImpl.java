package com.lailai.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.components.Select;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lailai.common.enums.StateEnum;
import com.lailai.dao.TeacherDao;
import com.lailai.entity.Coursetime;
import com.lailai.entity.Merchant;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;
import com.lailai.util.HibernateUtils;

public class TeacherDaoImpl implements TeacherDao {
	private Logger logger = LoggerFactory.getLogger(TeacherDaoImpl.class);

	@Override
	public String getGoodAtByTid(String tid) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Teacher teacher = session.get(Teacher.class, tid);
		ts.commit();
		String goodAtStr = teacher.getGoodAt();
		return goodAtStr;
	}

	@Override
	public void insert(Teacher teacher) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		session.saveOrUpdate(teacher);
		ts.commit();
	}

	@Override
	public List<Teacher> findByDc(DetachedCriteria dc) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Criteria c = dc.getExecutableCriteria(session);
		List<Teacher> teacherList = c.list();
		ts.commit();
		return teacherList;
	}

	@Override
	public Teacher findBytid(String tid) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Query query = session.createQuery("from Teacher where id=:tid");
		query.setParameter("tid", tid);
		List list = query.list();
		ts.commit();
		Teacher teacher = (Teacher) list.get(0);
		return teacher;
	}

	@Override
	public void deleteTeacher(String tid, int newState) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Teacher teacher = session.get(Teacher.class, tid);
		teacher.setState(newState);
		// session.update(teacher); //获取出来的是持久态对象，可以直接修改
		ts.commit();
		session.close();
	}

	@Override
	public void addScoreByTid(String tid) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Teacher teacher = session.get(Teacher.class, tid);
		teacher.setScore(teacher.getScore()+1);
		ts.commit();
		session.close();
	}
	@Override
	public List<Teacher> findfixTeaByCname(String cname) {
		Session session = HibernateUtils.openSession();
		Query query = session.createQuery("from Teacher where goodAt like :cname and state=:state");
		query.setParameter("cname", "%"+cname+"%");
		query.setParameter("state", StateEnum.teacherAble.state);
		List<Teacher> teacherList = (List<Teacher>)query.list();
		return teacherList;
	}


	@Override
	public  List<HashMap<String, String>> getTeacherTime(Teacher teacher) {
		Session session = HibernateUtils.openSession();
		Calendar date = Calendar.getInstance();
	    String year = String.valueOf(date.get(Calendar.YEAR));
	    SQLQuery query = session.createSQLQuery("select courseName,time from class cla,coursetime cou where cla.id=cou.classid and state=1 and teacher like ? and cla.year=?")
	    		.addScalar("courseName", StandardBasicTypes.STRING)
	    		.addScalar("time",StandardBasicTypes.STRING);
	    query.setParameter(0, teacher.getName());
	    query.setParameter(1, year);
	    List list = query.list();
	 
	    List<HashMap<String, String>> ctMapList = new ArrayList< HashMap<String, String>>();
		for (Object o : list) {
			HashMap<String, String> teaTimeMap = new HashMap<String, String>();
			Object[] oArr=(Object[])o;
			teaTimeMap.put("courseName", (String)oArr[0]);
			teaTimeMap.put("time",(String)oArr[1]);
			ctMapList.add(teaTimeMap);
		}
		session.close();
		return ctMapList;
	}

	@Override
	public Teacher findTeacher(Teacher teacher) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Teacher where name=:name and password=:password and state=:state");
		query.setParameter("name", teacher.getName());
		query.setParameter("password", teacher.getPassword());
		query.setParameter("state", StateEnum.teacherAble.state);
		List list = query.list();
		ts.commit();
		if(list.size()>0){
			return (Teacher)list.get(0);
		}
		return null;
	}

	@Override
	public void updatePassword(Teacher teacher) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Teacher teacher2 = currentSession.get(Teacher.class, teacher.getId());
		teacher2.setPassword(teacher.getPassword());
		ts.commit();
	}
}
