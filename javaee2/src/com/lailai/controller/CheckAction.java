package com.lailai.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lailai.common.enums.StateEnum;
import com.lailai.dao.ClassDao;
import com.lailai.dao.UserDao;
import com.lailai.dao.impl.ClassDaoImpl;
import com.lailai.dto.ClassDto;
import com.lailai.dto.CourseDto;
import com.lailai.dto.CtDto;
import com.lailai.dto.checkRecordDto;
import com.lailai.entity.Check;
import com.lailai.entity.Coursetime;
import com.lailai.entity.StuClass;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;
import com.lailai.service.CheckService;
import com.lailai.service.StudyService;
import com.lailai.service.TeacherService;
import com.lailai.service.UserService;
import com.lailai.service.impl.CheckServiceImpl;
import com.lailai.service.impl.StudyServiceImpl;
import com.lailai.service.impl.TeacherServiceImpl;
import com.lailai.service.impl.UserServiceImpl;
import com.lailai.util.JsonUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CheckAction extends ActionSupport {
	private TeacherService teacherService=new TeacherServiceImpl();
	private UserService userService=new UserServiceImpl();
	private StudyService studyService =new StudyServiceImpl();
	private ClassDao classDao=new ClassDaoImpl();
	private CheckService checkService=new CheckServiceImpl(); 
	
	
	//级联下拉框的公用代码抽取
	public DetachedCriteria getDC(){
		//临时测试 后期加 login session
		/*Teacher teacher = new Teacher();
		teacher.setId("2222");
		teacher.setName("赵老师");*/
		HttpSession session = ServletActionContext.getRequest().getSession();
		Teacher tea = (Teacher)session.getAttribute("currentPeople");
		DetachedCriteria dc = DetachedCriteria.forClass(Coursetime.class);
		Calendar date = Calendar.getInstance();
        String 	year = String.valueOf(date.get(Calendar.YEAR));
		dc.add(Restrictions.eq("teacher",tea.getName()));
		dc.add(Restrictions.eq("year", year));
		return dc;
	}

    
	//为考核页面查询出班级下拉框   是查询当前登入的老师今年在教授的有效课程
	public void getClassByDC(){
		//获得基本条件  年度，老师
		DetachedCriteria dc = getDC();
		List<Coursetime> ctList = studyService.getCTListByDC(dc);
		List<ClassDto> classList = new ArrayList<ClassDto>();
		for (Coursetime coursetime : ctList) {
			String className = coursetime.getStuClass().getNum();
			String classId = coursetime.getStuClass().getId();
			ClassDto classDto = new ClassDto(classId,className);
			classList.add(classDto);
		}
		
		//获取到cid的列表
		HttpSession session = ServletActionContext.getRequest().getSession();
		Teacher tea = (Teacher)session.getAttribute("currentPeople");
		
		List<String>claidList=studyService.getClassListByTea(tea.getName());
		//根据列表查询cname，并构成map
		
		List<HashMap<String, String>> claMapList =new ArrayList<HashMap<String, String>>();
		
		for (String cid : claidList) {
			HashMap<String, String> claMap = new HashMap<String, String>();
			StuClass cla = classDao.getClassByCid(cid);
			claMap.put("cid", cid);
			String cname = cla.getNum();
			claMap.put("cname", cname);
			claMapList.add(claMap);
		}
		String resultJson = JsonUtils.objectToJson(claMapList);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(resultJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private String classID;
	public String getClassID() {return classID;}
	public void setClassID(String classID) {this.classID = classID;}
	
	//根据班级查找用户
	public String all(){	
		List<User> userList = userService.findAllUserByCid(classID);
		ActionContext context = ActionContext.getContext();
		context.put("userList", userList);
		return "add";
	}
	
	
	//级联课程下拉框，响应流浪器传来的班级id ，查询课程
	private String cid;
	public String getCid() {return cid;}
	public void setCid(String cid) {this.cid = cid;}
	public void getCourseByCid(){
		DetachedCriteria dc = getDC();
		StuClass stuClass = new StuClass();
		stuClass.setId(cid);
		dc.add(Restrictions.eq("stuClass",stuClass));
		List<Coursetime> ctList = studyService.getCTListByDC(dc);
		StringBuilder courseSb = new StringBuilder();
		for (Coursetime coursetime : ctList) {
			courseSb.append(coursetime.getCourseName()).append(",");
		}
		String oleStr = courseSb.toString();
		String  courseStr="";
		if(oleStr.length()!=0){
			courseStr= courseSb.toString().substring(0,courseSb.toString().length()-1);
		}
		String resultJson = JsonUtils.objectToJson(courseStr);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(resultJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	
	//级联上课时间下拉框，响应浏览器老师传入的课程，查询出上课时间
	//课程名
	private String courseName;
	public String getCourseName() {return courseName;}
	public void setCourseName(String courseName) {this.courseName = courseName;}
	
	public void getStudyTimeByCourse(){
		//基本dc 当前老师，当前年查询上课时间
		DetachedCriteria dc = getDC();
		dc.add(Restrictions.eq("courseName", courseName));
		//模拟班级
		StuClass stuClass = new StuClass();
		stuClass.setId(cid);
		dc.add(Restrictions.eq("stuClass", stuClass));
		List<Coursetime> ctList = studyService.getCTListByDC(dc);
		ArrayList<String> timeList = new ArrayList<String>();
		for (Coursetime coursetime : ctList) {
			String timeStr = coursetime.getTime();
			String[] timeArr = timeStr.split(",");
			timeList.add(timeStr);
		}
		String resultJson = JsonUtils.objectToJson(timeList);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(resultJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
	
	/*
	alert(cid); 前面有定义，略去
	alert(courseName);   前面有定义，略去
	alert(stuTime);
	alert(checkTime);*/
	private String stuTime;
	private String checkTime;
	private String checkRecordeJson;
	public String getStuTime() {return stuTime;}
	public void setStuTime(String stuTime) {this.stuTime = stuTime;}
	public String getCheckTime() {return checkTime;}
	public void setCheckTime(String checkTime) {this.checkTime = checkTime;	}
	public String getCheckRecordeJson() {return checkRecordeJson;}
	public void setCheckRecordeJson(String checkRecordeJson) {this.checkRecordeJson = checkRecordeJson;}


	public void addCheck() throws JsonParseException, JsonMappingException, IOException{
		
		/*public Check(String id, String userName, String studyTime, String year, String className, String courseName,
				"String checkTeacher, Date checkDate, Integer checkState) {*/
		//获取当前年度
		Calendar date = Calendar.getInstance();
	    String year = String.valueOf(date.get(Calendar.YEAR));

	    
		//获取session中的老师  暂时模拟
	    /*Teacher teacher = new Teacher();
		teacher.setName("赵老师");*/
		Teacher teacher =(Teacher)ActionContext.getContext().getSession().get("currentPeople");

		//将Unix时间戳转换为年月日
		long longCheckTime = Long.parseLong(checkTime);
		
		Date checkDate = new Date(longCheckTime);
		
		
		ObjectMapper mapper = new ObjectMapper();
		checkRecordDto[] crArr = mapper.readValue(checkRecordeJson, checkRecordDto[].class);
		List<checkRecordDto> asList = Arrays.asList(crArr);
		List<Check> checkList = new ArrayList<Check>();
		for (checkRecordDto crd : asList) {
		    //生成主键
			String uuidStr = UUID.randomUUID().toString().replace("-", "");
			String className = classDao.getClassByCid(cid).getNum();
			int state = Integer.parseInt(crd.getCheckState());
			Check check = new Check(uuidStr,crd.getStuName(),stuTime,year,className,courseName,teacher.getName(),checkDate,state);
			checkList.add(check);
		}
		
		boolean isOk = checkService.addCheck(checkList);
		String resultJson = JsonUtils.objectToJson(isOk);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(resultJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	//根据班级id获取课程列表 cid在前面定义，略去
	public void getCourseListBycid(){
		DetachedCriteria dc = DetachedCriteria.forClass(Coursetime.class);
		StuClass stuClass = new StuClass();
		stuClass.setId(cid);
		dc.add(Restrictions.eq("stuClass",stuClass));
		List<Coursetime> ctList = studyService.getCTListByDC(dc);
		StringBuilder courseSb = new StringBuilder();
		for (Coursetime coursetime : ctList) {
			courseSb.append(coursetime.getCourseName()).append(",");
		}
		String oleStr = courseSb.toString();
		String  courseStr="";
		if(oleStr.length()!=0){
			courseStr= courseSb.toString().substring(0,courseSb.toString().length()-1);
		}
		String resultJson = JsonUtils.objectToJson(courseStr);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(resultJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//根据班级id与课程名获取上课时间列表 cid与courseName在前面定义，略去
	public void getStuTimeList(){
		DetachedCriteria dc = DetachedCriteria.forClass(Coursetime.class);
		StuClass stuClass = new StuClass();
		stuClass.setId(cid);
		dc.add(Restrictions.eq("stuClass",stuClass));
		dc.add(Restrictions.eq("courseName", courseName));
		List<Coursetime> ctList = studyService.getCTListByDC(dc);
		StringBuilder courseSb = new StringBuilder();
		for (Coursetime coursetime : ctList) {
			courseSb.append(coursetime.getTime()).append(",");
		}
		String oleStr = courseSb.toString();
		String  courseStr="";
		if(oleStr.length()!=0){
			courseStr= courseSb.toString().substring(0,courseSb.toString().length()-1);
		}
		String resultJson = JsonUtils.objectToJson(courseStr);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(resultJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
/*	alert(year);
	alert(courseName);  前面定义 略去
	alert(stuTime);		前面定义 略去
	checkTime前面定义 略去
*/
	private String year;
	private String className;
	public String getYear() {return year;}
	public void setYear(String year) {this.year = year;}
	public String getClassName() {return className;}
	public void setClassName(String className) {this.className = className;}

	public void findCheckList(){
		String resultJson =null;
		//防止前台不按规则操作
		if(checkTime!=null){
			DetachedCriteria dc = DetachedCriteria.forClass(Check.class);
			dc.add(Restrictions.eq("year", year));
			dc.add(Restrictions.eq("className",className));
			dc.add(Restrictions.eq("courseName", courseName));
			dc.add(Restrictions.eq("studyTime", stuTime));
			//将Unix时间戳转换为年月日
			long longCheckTime = Long.parseLong(checkTime);
			Date checkDate = new Date(longCheckTime);
			dc.add(Restrictions.eq("checkDate", checkDate));
			List<Coursetime> ctList = studyService.getCTListByDC(dc);
			resultJson= JsonUtils.objectToJson(ctList);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(resultJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}	
}
