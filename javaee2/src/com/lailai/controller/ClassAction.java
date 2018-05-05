package com.lailai.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.lailai.common.enums.StateEnum;
import com.lailai.dto.CtDto;
import com.lailai.dto.OpenClassDto;
import com.lailai.entity.StuClass;
import com.lailai.entity.User;
import com.lailai.service.ClassService;
import com.lailai.service.impl.ClassServiceImpl;
import com.lailai.util.JsonUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClassAction extends ActionSupport{
	private ClassService classService=new ClassServiceImpl();
	private	Logger logger = LoggerFactory.getLogger(ClassAction.class);
	
	private String className;
	private String price;
	private String ctJson;
	public String getCtJson() {return ctJson;}
	public void setCtJson(String ctJson) {this.ctJson = ctJson;}
	public String getClassName() {return className;	}
	public void setClassName(String className) {this.className = className;}
	public String getPrice() {return price;}
	public void setPrice(String price) {this.price = price;}

	
	private String addInfo;
	
	public String getAddInfo() {
		return addInfo;
	}
	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
	public void openClass() throws IOException{
		double price=0;
		if(this.price!=null&&!"".equals(price)){
			price = Double.parseDouble(this.price);
		}
		Calendar date = Calendar.getInstance();
	    String year = String.valueOf(date.get(Calendar.YEAR));
//		 public StuClass(String id, String num, Double price, String year, Integer state, Set coursetimes) {
		String uuidStr=UUID.randomUUID().toString().replaceAll("-", "");
		StuClass stuClass = new StuClass(uuidStr,className,price,year,StateEnum.classAble.state);

		ObjectMapper mapper = new ObjectMapper();
		CtDto[] ctArr = mapper.readValue(ctJson, CtDto[].class);
		List<CtDto> asList = Arrays.asList(ctArr);
		classService.openClass(stuClass,asList );
		try {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String objectToJson = JsonUtils.objectToJson("添加成功");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.write(objectToJson);
		writer.flush();
		writer.close();
	} catch (IOException e) {
		e.printStackTrace();
	}	
	}
	
	public String all(){
		//开班信息
//		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		Calendar date = Calendar.getInstance();
	    String year = String.valueOf(date.get(Calendar.YEAR));
		DetachedCriteria openClassDc = DetachedCriteria.forClass(StuClass.class);
		openClassDc.add(Restrictions.eq("state", StateEnum.classAble.state))
		.add(Restrictions.eq("year", year));
		List<OpenClassDto> openClassList = classService.getNowClass(openClassDc);
		request.setAttribute("openClassList", openClassList);
		int size =openClassList.size();
		request.setAttribute("mysize", size);
		return "all";
	}
	
	private String cid;
	public String getCid() {return cid;}
	public void setCid(String cid) {this.cid = cid;}
	public void deleteClass(){
		String result = classService.deleteClass(cid);	
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String objectToJson = JsonUtils.objectToJson(result);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(objectToJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	//根据班级cid查询该班下的学生
	public void getStudentsByCid(){
		List<User> userList=classService.getStusByCid(cid);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String objectToJson = JsonUtils.objectToJson(userList);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(objectToJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//查询出所有的未被禁用的课程的年份列表
	public void getAllActiveyear(){
		String yearStr=null;
		List<String> allActiveClass = classService.getAllActiveClass();
		if(allActiveClass!=null&allActiveClass.size()>0){
			StringBuilder yearSb = new StringBuilder();
			for (String year : allActiveClass) {
				yearSb.append(year).append(",");
			}
			yearStr = yearSb.toString();
		}
		String result = yearStr.substring(0, yearStr.length()-1);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			String objectToJson = JsonUtils.objectToJson(result);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(objectToJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//	根据年度查询班级列表

}
