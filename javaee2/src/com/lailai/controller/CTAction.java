package com.lailai.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.internal.classic.GroupByParser;

import com.lailai.common.converStuTime;
import com.lailai.entity.Coursetime;
import com.lailai.entity.Merchant;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;
import com.lailai.service.StudyService;
import com.lailai.service.impl.StudyServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CTAction extends ActionSupport {
	private StudyService studyService=new StudyServiceImpl();
	//根据权限分别查询课程表
	public String getCT(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> sessionMap = context.getSession();
		String permission =(String) sessionMap.get("permission");
		switch(permission){
			case "user":
				User user = (User)sessionMap.get("currentPeople");
				List<Coursetime> ctList = studyService.getCTOfUser(user.getClassID());
				for (Coursetime coursetime : ctList) {
					coursetime.setTime(converStuTime.converTime(coursetime.getTime()));
				}
				context.put("userCTList", ctList);
				break;
			case "teacher":
				Teacher teacher = (Teacher)sessionMap.get("currentPeople");
				DetachedCriteria dc = DetachedCriteria.forClass(Coursetime.class);
				Calendar date = Calendar.getInstance();
			    String year = String.valueOf(date.get(Calendar.YEAR));
				dc.add(Restrictions.eq("year", year))
				.add(Restrictions.eq("teacher", teacher.getName()));
				List<Coursetime> list=studyService.getCTOfTeacher(dc);
				/*for (int i = 0; i < list.size(); i++) {
					String num = list.get(i).getStuClass().getNum();
					//避免多个DTO或者吗，map 在此取巧
					list.get(i).setId(num);
				}*/
				for (Coursetime coursetime : list) {
					coursetime.setTime(converStuTime.converTime(coursetime.getTime()));
				}
				context.put("teacherCTList", list);
				break;
			case "merchant":
				
				break;
			default:
				break;
		}
		return "findCT";
	}
	

}
