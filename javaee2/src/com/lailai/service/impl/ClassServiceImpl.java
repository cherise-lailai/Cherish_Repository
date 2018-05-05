package com.lailai.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.text.StrBuilder;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lailai.dao.ClassDao;
import com.lailai.dao.CourseDao;
import com.lailai.dao.impl.ClassDaoImpl;
import com.lailai.dao.impl.CourseDaoImpl;
import com.lailai.dto.CtDto;
import com.lailai.dto.OpenClassDto;
import com.lailai.entity.Coursetime;
import com.lailai.entity.StuClass;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;
import com.lailai.service.ClassService;
import com.lailai.util.HibernateUtils;

public class ClassServiceImpl implements ClassService {

	private ClassDao classDao = new ClassDaoImpl();
	private CourseDao courseDao=new CourseDaoImpl();
	private Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);

	@Override
	public List<StuClass> findByYear(String year) {
		return classDao.getClassByYear(year);
	}

	@Override
	public List<OpenClassDto> getNowClass(DetachedCriteria dc) {
		List<OpenClassDto> openClassList = new ArrayList<OpenClassDto>();
		List<StuClass> classListNow = classDao.getClassListNow(dc);

		for (StuClass stuClass : classListNow) {
			// 班级编号
			String id = stuClass.getId();
			// 班级名
			String cname = stuClass.getNum();
			// 学费
			Double price = stuClass.getPrice();
			// 期度
			String year = stuClass.getYear();

			StrBuilder cnsb = new StrBuilder();
			StrBuilder teasb = new StrBuilder();
			StrBuilder timesb = new StrBuilder();
			// 课程、老师、上课时间处理
			Set coursetimes = stuClass.getCoursetimes();
			Iterator iterator = coursetimes.iterator();
			while (iterator.hasNext()) {
				Coursetime ct = (Coursetime) iterator.next();
				String courseName = ct.getCourseName();
				cnsb.append(courseName).append(";");
				String time = ct.getTime();
				// 对类似2-1这种时间存储数据修正为用户可理解的时间
				String[] tStr = time.split(",");
				String timeShow3 = "";
				for (int i = 0; i < tStr.length; i++) {
					String timeShow2 = "";
					String[] jNum = tStr[i].split("-");
					String timeShow = "";
					switch (jNum[0]) {
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
					switch (jNum[1]) {
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
					timeShow2 += timeShow;
					timeShow3 += timeShow2;
				}
				timesb.append(timeShow3).append(";");
				String teacher = ct.getTeacher();
				teasb.append(teacher).append(";");
			}
			String cnStr = cnsb.toString();
			String timeStr = timesb.toString();
			String teaStr = teasb.toString();
			OpenClassDto openClassDto = new OpenClassDto(id, cname, year, cnStr, timeStr, teaStr, price);
			openClassList.add(openClassDto);
		}
		return openClassList;
	}

	@Override
	public OpenClassDto getClassByCid(String cid) {
		StuClass stuClass = classDao.getClassByCid(cid);
		// 班级编号
		String id = stuClass.getId();
		// 班级名
		String cname = stuClass.getNum();
		// 学费
		Double price = stuClass.getPrice();
		// 期度
		String year = stuClass.getYear();

		StrBuilder cnsb = new StrBuilder();
		StrBuilder teasb = new StrBuilder();
		StrBuilder timesb = new StrBuilder();
		// 课程、老师、上课时间处理
		Set coursetimes = stuClass.getCoursetimes();
		Iterator iterator = coursetimes.iterator();
		while (iterator.hasNext()) {
			Coursetime ct = (Coursetime) iterator.next();
			String courseName = ct.getCourseName();
			cnsb.append(courseName).append(";");
			String time = ct.getTime();
			// 对类似2-1这种时间存储数据修正为用户可理解的时间
			String[] tStr = time.split(",");
			String timeShow3 = "";
			for (int i = 0; i < tStr.length; i++) {
				String timeShow2 = "";
				String[] jNum = tStr[i].split("-");
				String timeShow = "";
				switch (jNum[0]) {
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
				switch (jNum[1]) {
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
				timeShow2 += timeShow;
				timeShow3 += timeShow2;
			}
			timesb.append(timeShow3).append(";");
			String teacher = ct.getTeacher();
			teasb.append(teacher).append(";");
		}
		String cnStr = cnsb.toString();
		String timeStr = timesb.toString();
		String teaStr = teasb.toString();
		OpenClassDto openClassDto = new OpenClassDto(id, cname, year, cnStr, timeStr, teaStr, price);
		return openClassDto;
	}

	@Override
	public void openClass(StuClass stuClass, List<CtDto> ctDtoList) {
		List<Coursetime> ctList = new ArrayList<Coursetime>();
		for (CtDto ctDto : ctDtoList) {
			String xingqi = ctDto.getXingqi();// 格式1-7中的一个
			String stuTime = ctDto.getStuTime();// 格式1-4中的一个
			String time = xingqi + "-" + stuTime;
			Calendar date = Calendar.getInstance();
			String year = String.valueOf(date.get(Calendar.YEAR));
			// no class
			String uuidStr = UUID.randomUUID().toString().replaceAll("-", "");
			Coursetime coursetime = new Coursetime(uuidStr, ctDto.getCourse(), time, ctDto.getTeacher(), year);
			ctList.add(coursetime);
		}
		classDao.openClass(stuClass, ctList);
	}

	@Override
	public String deleteClass(String cid) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts=null;
		
		String result = null;
		try {
			ts= currentSession.beginTransaction();
			//更新class表的state为禁用
			result = classDao.updateClassBycid(cid);
			//根据cid删除该class下的课程
			courseDao.deleteCTByCid(cid);
			ts.commit();
		} catch (Exception e) {
			ts.rollback();
			e.printStackTrace();
			logger.error("删除班级与上课安排失败，事务回滚");
		}
		return result;
	}

	@Override
	public  List<StuClass> getNowSimClas(DetachedCriteria dc) {
		
		return classDao.getSimList(dc);
	}

	@Override
	public List<User> getStusByCid(String cid) {
		List<User> userList=classDao.getStusByCid(cid);
		return userList;
	}

	@Override
	public List<String> getAllActiveClass() {
		return classDao.getAllActiveClass();
	}

	@Override
	public String getCidByCname(String cname) {
		String cid=classDao.getCidByCname(cname);
		return cid;
	}

}
