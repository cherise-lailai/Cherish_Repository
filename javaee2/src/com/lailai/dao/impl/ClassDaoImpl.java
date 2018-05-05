package com.lailai.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.lailai.common.enums.StateEnum;
import com.lailai.dao.ClassDao;
import com.lailai.dto.OpenClassDto;
import com.lailai.entity.Coursetime;
import com.lailai.entity.StuClass;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;
import com.lailai.util.HibernateUtils;

public class ClassDaoImpl implements ClassDao {

	@Override
	public List<StuClass> getClassByYear(String year) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Query query = session.createQuery("from StuClass where year=:year and state=:state");
		query.setParameter("year", year);
		query.setParameter("state", StateEnum.classAble.state);
		List<StuClass> list = query.list();
		ts.commit();
		session.close();
		return list;
	}

	@Override
	public List<StuClass> getClassListNow(DetachedCriteria dc) {
		Session session = HibernateUtils.openSession();
		Criteria c = dc.getExecutableCriteria(session);
		List<StuClass> stuClassList =(List<StuClass>)c.list();
		return stuClassList;
	}

	@Override
	public StuClass getClassByCid(String cid) {
		Session session = HibernateUtils.openSession();
		StuClass stuClass = session.get(StuClass.class, cid);
/*		session.close();  好像是懒加载，后面需要级联保存，不能把session管关了，不懂能够可以使用currentSession进行代替*/  
		return stuClass;
	}

	@Override
	public void openClass(StuClass stuClass, List<Coursetime> ctList) {
		Session session = HibernateUtils.openSession();
		for (int i = 0; i < ctList.size(); i++) {
			Coursetime coursetime = ctList.get(i);
			coursetime.setStuClass(stuClass);
			stuClass.getCoursetimes().add(coursetime);
		}
		Transaction ts = session.beginTransaction();
		session.save(stuClass);
		ts.commit();
		session.close();
	}

	@Override
	public String updateClassBycid(String cid) {
		Session session = HibernateUtils.getCurrentSession();
		StuClass cla = session.get(StuClass.class,cid);
		cla.setState(StateEnum.classDisable.state);
		session.save(cla);
		//未提交，在service中控制事务
		return "ok";
	}

	@Override
	public List<StuClass> getSimList(DetachedCriteria dc) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Criteria c = dc.getExecutableCriteria(session);
		List<StuClass>  list =(List<StuClass>) c.list();
		ts.commit();
		session.close();
		return list;
	}

	@Override
	public List<User> getStusByCid(String cid) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from User where classID=:cid");
		query.setParameter("cid", cid);
		List<User> userList =(List<User>)query.list();
		ts.commit();
		return userList;
	}

	@Override
	public List<String> getAllActiveClass() {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Query query = currentSession.createQuery("select distinct year from StuClass where state=:state")
				.setParameter("state", StateEnum.classAble.state);
		List<String> allActiveClass =(List<String> ) query.list();
		ts.commit();
		return allActiveClass;
	}

	@Override
	public String getCidByCname(String cname) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from StuClass where state=:state and num=:cname");
		query.setParameter("state", StateEnum.classAble.state);
		query.setParameter("cname", cname);
		List<StuClass> list =(List<StuClass>) query.list();
		ts.commit();
		StuClass stuClass=null;
		if(list.size()>0){
			stuClass = list.get(0);
			return stuClass.getId();
		}else{
			return null;
		}
	}
}
