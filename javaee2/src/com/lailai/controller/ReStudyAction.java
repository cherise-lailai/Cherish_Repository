package com.lailai.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.lailai.common.enums.ReStudyCourse;
import com.lailai.entity.Check;
import com.lailai.entity.ReStuCourse;
import com.lailai.entity.Teacher;
import com.lailai.service.ReStudyService;
import com.lailai.service.impl.ReStudyServiceImpl;
import com.lailai.util.JsonUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ReStudyAction extends ActionSupport{
	private ReStudyService reStudyService=new ReStudyServiceImpl();
	//管理员开课时获取需要开补课班的课程
	public void findReStudyCourse() {		
		String courseStr=null;
		DetachedCriteria dc = DetachedCriteria.forClass(Check.class);
		List<String> reStudyList = reStudyService.getReStudyList(dc);
		
//		ArrayList<String> showList = new ArrayList<String>();
		//查询出已经开的补课课程
		DetachedCriteria mydc = DetachedCriteria.forClass(ReStudyCourse.class);
		//计算出补课期度  例如：5月的上半月
		Calendar calen = Calendar.getInstance();
		int month2 =calen.get(Calendar.MONDAY)+1;
		int dayIndex2 =calen.get(Calendar.DAY_OF_MONTH);
		String periodStr2=""+month2+"月的";
		if(dayIndex2>0&&dayIndex2<16){
			periodStr2+="上半月";
		}else{
			periodStr2+="下半月";
		}

		List<ReStuCourse> reStudyCourseList=reStudyService.getAleadyopen(periodStr2);
		

		ArrayList<String> removeList = new ArrayList<String>();
		for (int i = 0; i < reStudyCourseList.size(); i++) {
			removeList.add(reStudyCourseList.get(i).getCourseName());
		}
		reStudyList.removeAll(removeList);
		
		
		StringBuilder reStudyCourseSB = new StringBuilder();
		if(reStudyList.size()>0){
			for (String course : reStudyList) {
				reStudyCourseSB.append(course).append(",");
			}
			String reStudyCourseStr = reStudyCourseSB.toString();
			courseStr = reStudyCourseStr.substring(0, reStudyCourseStr.length()-1);
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
	
	
	private String courseName;
	private String teacherName;
	
	public String getCourseName() {return courseName;}
	public void setCourseName(String courseName) {this.courseName = courseName;}
	public String getTeacherName() {return teacherName;}
	public void setTeacherName(String teacherName) {this.teacherName = teacherName;}
	
	//BUG管理员页面无法刷新（表单列表同一个页面，刷新导致二次提交出错，偷懒）
	//管理员添加一个补课
	public String openReStudy(){
		if(courseName!=null&&teacherName!=null&&!"".equals(courseName)&&!"".equals(teacherName)){
			String cname=null;
			String tname=null;
			//public ReStuCourse(String id, String courseName, String teacher, Timestamp studyTime, String period, Integer state) 		
			if(courseName!=null&&teacherName!=null){
				String uuidStr = UUID.randomUUID().toString().replaceAll("-", "");
				ReStuCourse reStuCourse = new ReStuCourse(uuidStr,courseName,teacherName,null,null,null);
				boolean isOK = reStudyService.addReStuCourse(reStuCourse);
			}
		}
		DetachedCriteria dc = DetachedCriteria.forClass(ReStuCourse.class);
		dc.add(Restrictions.eq("state", ReStudyCourse.reStuCourseAble.state));
		List<ReStuCourse> adminOkList = reStudyService.findReStuCourseList(dc);
		
		DetachedCriteria dc2 = DetachedCriteria.forClass(ReStuCourse.class);
		dc2.add(Restrictions.eq("state", ReStudyCourse.teacherOK.state));
		List<ReStuCourse> teacherOkList = reStudyService.findReStuCourseList(dc2);
		ActionContext context = ActionContext.getContext();
		context.put("adminOkList", adminOkList);
		context.put("teacherOkList", teacherOkList);
		
		return "reStudyAdd";
	}
	
	//管理员删除一个补课
	private String rscid;
	public String getRscid() {return rscid;}
	public void setRscid(String rscid) {this.rscid = rscid;}
	public String deleteByid(){
		//事务
		boolean isOK=reStudyService.deleteReStuCourseByid(rscid);
		DetachedCriteria dc = DetachedCriteria.forClass(ReStuCourse.class);
		dc.add(Restrictions.eq("state", ReStudyCourse.reStuCourseAble.state));
		List<ReStuCourse> adminOkList = reStudyService.findReStuCourseList(dc);
		
		DetachedCriteria dc2 = DetachedCriteria.forClass(ReStuCourse.class);
		dc2.add(Restrictions.eq("state", ReStudyCourse.teacherOK.state));
		List<ReStuCourse> teacherOkList = reStudyService.findReStuCourseList(dc2);
		ActionContext context = ActionContext.getContext();
		context.put("adminOkList", adminOkList);
		context.put("teacherOkList", teacherOkList);
		return "redir_openReStudy";
	}
	
	
	//查询当前开的需要自己补课补课班
	public String halfMonthReStudy(){

		ActionContext context = ActionContext.getContext();
		Map<String, Object> sessionMap = context.getSession();
		String permission =(String) sessionMap.get("permission");
		Teacher teacher=null;
		List<ReStuCourse> reStuCourseList=null;
		List<ReStuCourse> havaTimeList=null;
		
		if("teacher".equals(permission)){
			//未安排的课程
			DetachedCriteria dc = DetachedCriteria.forClass(ReStuCourse.class);
			teacher=(Teacher) sessionMap.get("currentPeople");
			dc.add(Restrictions.eq("teacher",teacher.getName()));
			Integer[] stateArr={ReStudyCourse.reStuCourseAble.state};
			dc.add(Restrictions.in("state",stateArr));
			reStuCourseList= reStudyService.findReStuCourseList(dc);
			context.put("reStuCourseList", reStuCourseList);
			//已经安排的课程
			DetachedCriteria dc2 = DetachedCriteria.forClass(ReStuCourse.class);
			dc2.add(Restrictions.eq("teacher",teacher.getName()));
			Integer[] stateArr2={ReStudyCourse.teacherOK.state};
			dc2.add(Restrictions.in("state",stateArr2));
			havaTimeList= reStudyService.findReStuCourseList(dc2);
			context.put("havaTimeList", havaTimeList);
		}
		return "reStudyAddByTea";
	}
	
	//BUG老师添加页面无法刷新（表单列表同一个页面，刷新导致二次提交出错，偷懒）
	//参数：期度，老师安排的时间
	private String period;
	private String studyTime;
	public String getPeriod() {return period;}
	public void setPeriod(String period) {this.period = period;}
	public String getStudyTime() {return studyTime;}
	public void setStudyTime(String studyTime) {this.studyTime = studyTime;}
	//老师添加补课时间
	public String addByTeacher(){
//		public ReStuCourse(String id, String courseName, String teacher, Timestamp studyTime, String period, Integer state) {
		ReStuCourse reStuCourse = new ReStuCourse(rscid,courseName,teacherName,studyTime,period,new Integer(ReStudyCourse.teacherOK.state));
		//添加补课时间
		boolean isOK=reStudyService.addReStuCourseByTea(reStuCourse);
		//查询补课列表
		Teacher teacher=null;
		List<ReStuCourse> reStuCourseList=null;
		List<ReStuCourse> havaTimeList=null;
		ActionContext context = ActionContext.getContext();
		Map<String, Object> sessionMap = context.getSession();
		if("teacher".equals((String)sessionMap.get("permission"))){
			DetachedCriteria dc = DetachedCriteria.forClass(ReStuCourse.class);
			teacher=(Teacher) sessionMap.get("currentPeople");
			dc.add(Restrictions.eq("teacher",teacher.getName()));
			Integer[] stateArr={new Integer(ReStudyCourse.reStuCourseAble.state) };
			dc.add(Restrictions.in("state",stateArr));
			reStuCourseList= reStudyService.findReStuCourseList(dc);
			context.put("reStuCourseList", reStuCourseList);
			//已经安排的课程
			DetachedCriteria dc2 = DetachedCriteria.forClass(ReStuCourse.class);
			dc2.add(Restrictions.eq("teacher",teacher.getName()));
			Integer[] stateArr2={ReStudyCourse.teacherOK.state};
			dc2.add(Restrictions.in("state",stateArr2));
			havaTimeList= reStudyService.findReStuCourseList(dc2);
			context.put("havaTimeList", havaTimeList);
		}
		return "reStudyAddByTea";
	}
	
	//rscid已经在前面定义，在此省略
	//删除老师安排的补课时间，重新安排补课时间
	public String reSetStuTime(){
		ReStuCourse reStuCourse = new ReStuCourse();
		reStuCourse.setId(rscid);
		boolean isOK=reStudyService.reSetStuTimeBytea(reStuCourse);
		//查询补课列表
		Teacher teacher=null;
		List<ReStuCourse> reStuCourseList=null;
		List<ReStuCourse> havaTimeList=null;
		ActionContext context = ActionContext.getContext();
		Map<String, Object> sessionMap = context.getSession();
		if("teacher".equals((String)sessionMap.get("permission"))){
			DetachedCriteria dc = DetachedCriteria.forClass(ReStuCourse.class);
			teacher=(Teacher) sessionMap.get("currentPeople");
			dc.add(Restrictions.eq("teacher",teacher.getName()));
			Integer[] stateArr={new Integer(ReStudyCourse.reStuCourseAble.state) };
			dc.add(Restrictions.in("state",stateArr));
			reStuCourseList= reStudyService.findReStuCourseList(dc);
			context.put("reStuCourseList", reStuCourseList);
			//已经安排的课程
			DetachedCriteria dc2 = DetachedCriteria.forClass(ReStuCourse.class);
			dc2.add(Restrictions.eq("teacher",teacher.getName()));
			Integer[] stateArr2={ReStudyCourse.teacherOK.state};
			dc2.add(Restrictions.in("state",stateArr2));
			havaTimeList= reStudyService.findReStuCourseList(dc2);
			context.put("havaTimeList", havaTimeList);
		}
		return "reStudyAddByTea";
	}
	
}
