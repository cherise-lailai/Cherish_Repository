package com.lailai.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.lailai.entity.Coursetime;
import com.lailai.entity.StuClass;
import com.lailai.util.HibernateUtils;

public class testOne2money {
	/**
	 * 1对多，无级联，我inverse，结果1条inser class 2条insert ct 2条update ct
	 */
	@Test
	public void test1(){
		Session s = HibernateUtils.openSession();
		Transaction ts = s.beginTransaction();

		StuClass stuClass = new StuClass("11","2222",123.1,"2018",1);
		Coursetime  ct1 = new Coursetime("aa","阅读","1-1,2-1","李海生","2018");
		Coursetime  ct2 = new Coursetime("bb","阅读","1-1,2-1","李海生","2018");
		
		stuClass.getCoursetimes().add(ct1);
		stuClass.getCoursetimes().add(ct2);
		
		ct1.setStuClass(stuClass);	//没做级联必须互相维护外键关系
		ct2.setStuClass(stuClass);
		s.save(stuClass);
		s.save(ct1);
		s.save(ct2);
		ts.commit();
	}
	
	//测试双方维护了关系，只保存一方,失败Batch update returned unexpected row count from update [0]; actual row count: 0; expected: 1
	@Test
	public void test01(){
		Session s = HibernateUtils.openSession();
		Transaction ts = s.beginTransaction();

		StuClass stuClass = new StuClass("23","2222",123.1,"2018",1);
		Coursetime  ct1 = new Coursetime("ttt","阅读","1-1,2-1","李海生","2018");
		stuClass.getCoursetimes().add(ct1);
		ct1.setStuClass(stuClass);	//没做级联必须互相维护外键关系
		s.save(stuClass);
		ts.commit();
	}
	
	
	@Test
	public void test02(){
		Session s = HibernateUtils.openSession();
		Transaction ts = s.beginTransaction();

		StuClass stuClass = new StuClass("23","2222",123.1,"2018",1);
		Coursetime  ct1 = new Coursetime("ttt","阅读","1-1,2-1","李海生","2018");
		stuClass.getCoursetimes().add(ct1);
		ct1.setStuClass(stuClass);	//没做级联必须互相维护外键关系
//		s.save(stuClass);	
		/*只保存了ct,班级没出持久化  Cannot add or update a child row: a foreign key constraint fails
		(`javaee2`.`coursetime`, CONSTRAINT `fk-classid` FOREIGN KEY (`classid`) 
				REFERENCES `class` (`id`))*/
		s.save(ct1);
		ts.commit();
	}
	
	//保存客户，使用了cascade save-update
	@Test
	public void test03(){
		Session s = HibernateUtils.openSession();
		Transaction ts = s.beginTransaction();

		StuClass stuClass = new StuClass("23","2222",123.1,"2018",1);
		Coursetime  ct1 = new Coursetime("ttt","阅读","1-1,2-1","李海生","2018");
		stuClass.getCoursetimes().add(ct1);
		ct1.setStuClass(stuClass);	//没做级联必须互相维护外键关系
		s.save(stuClass);	
//		s.save(ct1);
		ts.commit();
	}
	
	//测试1单向维护关系，此时没有单向维护,即没有添加 在class映射文件中添加inverse。结果是两次update
	//测试2,class 放弃维护关系，那就是一次update,性能提高
	@Test
	public void test04(){
		Session s = HibernateUtils.openSession();
		Transaction ts = s.beginTransaction();

		//get获取到的是持久态
		StuClass stuClass = s.get(StuClass.class, "1");
		Coursetime coursetime = s.get(Coursetime.class, "bb");
		
		stuClass.getCoursetimes().add(coursetime);
		coursetime.setStuClass(stuClass);
		
		ts.commit();
	}
	
	
	//测试5  class 有配置sava-update  inverse
	@Test  
	public void test05(){
		Session s = HibernateUtils.openSession();
		Transaction ts = s.beginTransaction();
		StuClass stuClass = new StuClass("113","2222",123.1,"2018",1);
		Coursetime  ct1 = new Coursetime("ppp","阅读","1-1,2-1","李海生","2018");
		stuClass.getCoursetimes().add(ct1);    //因为级联操作
		ct1.setStuClass(stuClass);
		s.save(stuClass);
		ts.commit();
	}
	
	
}
