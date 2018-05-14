package com.lailai.test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lailai.common.enums.CheckStateEnum;
import com.lailai.dao.impl.TeacherDaoImpl;
import com.lailai.entity.Check;
import com.lailai.entity.Teacher;
import com.lailai.util.HibernateUtils;

public class testQingjia {

	public static void main(String[] args) {
		Session session = HibernateUtils.getCurrentSession();
		Transaction ts = session.beginTransaction();
		Date beginTime = null;
		Date endTime = null;

		Calendar c = Calendar.getInstance();
		int i = c.get(Calendar.DAY_OF_MONTH);
		System.out.println(i);
		if (i > 0 && i < 16) {
			c.set(Calendar.DAY_OF_MONTH, 1);
			beginTime = c.getTime();
			System.out.println(beginTime);
			c.set(Calendar.DAY_OF_MONTH, 15);
			endTime = c.getTime();
			System.out.println(endTime);
		} else {
			c.set(Calendar.DAY_OF_MONTH, 16);
			beginTime = c.getTime();
			System.out.println(beginTime);
			c.set(Calendar.DAY_OF_MONTH, 0);
			endTime = c.getTime();
			System.out.println(endTime);
		}
		
		

		// 获取这半个月的
		Query query = session.createQuery("select distinct courseName  from Check where"
				+ " checkState=:state and checkDate between :beginDate and :endDate");
		query.setParameter("beginDate", beginTime);
		query.setParameter("endDate", endTime);
		query.setParameter("state", CheckStateEnum.askForLeave.state);
		List<Check> askLeaveList =(List<Check>) query.list();
		//1、获得出半个月来有人请假的课程
		
		//2、根据课程查询出擅长该课程的老师
		TeacherDaoImpl teacherDaoImpl = new TeacherDaoImpl();
		System.out.println(askLeaveList);
		for (Check check : askLeaveList) {
			String courseName = check.getCourseName();
			List<Teacher> teacherList = teacherDaoImpl.findfixTeaByCname(courseName);
		}
	
		
		
		ts.commit();
	}
}
