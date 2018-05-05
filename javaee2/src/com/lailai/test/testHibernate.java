package com.lailai.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Test;

import com.lailai.dao.impl.CourseDaoImpl;
import com.lailai.dao.impl.TeacherDaoImpl;
import com.lailai.entity.Check;
import com.lailai.entity.Content;
import com.lailai.entity.User;
import com.lailai.service.impl.ClassServiceImpl;
import com.lailai.service.impl.CourseServiceImpl;

public class testHibernate {
	@Test
	public void test01(){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Query query = session.createQuery("from User");
		List<User> user = query.list();
		System.out.println(user);
	}
	
	@Test
	public void t2(){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Query query = session.createQuery("from Check");
		List<Check> c = query.list();
		System.out.println(c);
	}
	@Test
	public void t3(){
		TeacherDaoImpl t = new TeacherDaoImpl();
		String goodAtByTid = t.getGoodAtByTid("2222");
	}
	@Test
	public void t4(){
		CourseDaoImpl courseDaoImpl = new CourseDaoImpl();
		courseDaoImpl.deleteCTByCid("53dadc9404d5465082dcece9604f3449");
	}
	
	//测试回滚
	@Test
	public void t5(){
		ClassServiceImpl classServiceImpl = new ClassServiceImpl();
		classServiceImpl.deleteClass("ecc2cde65fe54ec198a0ec5357da982a");
	}
	

	
}
