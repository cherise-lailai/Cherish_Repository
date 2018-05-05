package com.lailai.service.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import com.lailai.common.enums.StateEnum;
import com.lailai.dao.TeacherDao;
import com.lailai.dao.impl.TeacherDaoImpl;
import com.lailai.entity.Course;
import com.lailai.entity.Teacher;
import com.lailai.service.TeacherService;
import com.lailai.util.HibernateUtils;

public class TeacherServiceImpl implements TeacherService {

	private TeacherDao teacherDao = new TeacherDaoImpl();

	@Override
	public String getTeaGoodAt(String tid) {
		return teacherDao.getGoodAtByTid(tid);
	}

	@Override
	public void add(Teacher teacher) {
		teacherDao.insert(teacher);

	}

	@Override
	public List<Teacher> findByDc(DetachedCriteria dc) {
		return teacherDao.findByDc(dc);
	}

	@Override
	public Teacher findBytid(String tid) {
		return teacherDao.findBytid(tid);
	}

	@Override
	public void deleteTeacher(String tid, int newState) {
		teacherDao.deleteTeacher(tid, newState);
	}

	@Override
	public void addScoreByTid(String tid) {
		teacherDao.addScoreByTid(tid);
		
	}
	
	@Override
	public String fixTeacher(String cname) {
		List<Teacher> findfixTeaByCname = teacherDao.findfixTeaByCname(cname);
		StringBuilder teaSb = new StringBuilder();
		for (Teacher teacher : findfixTeaByCname) {
			teaSb.append(teacher.getName()).append(",");
		}
		String fixTeaStr = teaSb.toString();
		String result = "";
		if(fixTeaStr.length()>0){
			result=fixTeaStr.substring(0, fixTeaStr.length()-1);
		}
		return result;
	}

	@Override
	public List<HashMap<String, String>> getTeachTime(Teacher teacher) {
		List<HashMap<String, String>> ttMapList = teacherDao.getTeacherTime(teacher);
		for (int i = 0; i < ttMapList.size(); i++) {
			String time = ttMapList.get(i).get("time");  //格式1-2
			String[] timeArr = time.split("-");
			String timeShow="";
			switch (timeArr[0]) {
				case "1":
					timeShow = "星期一的";
					break;
				case "2":
					timeShow = "星期二的";
					break;
				case "3":
					timeShow = "星期三的";
					break;
				case "4":
					timeShow = "星期四的";
					break;
				case "5":
					timeShow = "星期五的";
					break;
				case "6":
					timeShow = "星期六的";
					break;
				case "7":
					timeShow = "星期天的";
					break;
				}
				switch (timeArr[1]) {
				case "1":
					timeShow += "8:30~9:30 ";
					break;
				case "2":
					timeShow += "10:00~11:00 ";
					break;
				case "3":
					timeShow += "14:30~15:30 ";
					break;
				case "4":
					timeShow += "16:00~17:00 ";
					break;
				}
				ttMapList.get(i).put("time", timeShow);
			}
		return ttMapList;
	}

	@Override
	public List<Course> getAllCourse() {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from Course where state=:state");
		query.setParameter("state", StateEnum.courseAble.state);
		List<Course> list = (List<Course>)query.list();
		ts.commit();
		return list;
	}
}
