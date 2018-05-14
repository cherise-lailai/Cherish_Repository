package com.lailai.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import com.lailai.common.enums.Permission;
import com.lailai.entity.Evaluation;
import com.lailai.entity.Merchant;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;
import com.lailai.service.EvaluationService;
import com.lailai.service.impl.EvaluationServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class EvaluationAction extends ActionSupport {
	private EvaluationService evaluationService=new EvaluationServiceImpl();
	
	
	//获取出时间段参数
	private String beginTime;
	private String endTime;
	private String userName;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBeginTime() {return beginTime;}
	public void setBeginTime(String beginTime) {this.beginTime = beginTime;}
	public String getEndTime() {	return endTime;}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getList(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> sessionMap = context.getSession();
		String permission =(String) sessionMap.get("permission");
		if(permission!=null){
			Date bDate=null;
			Date eDate=null;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			//时间处理
			if(beginTime==null||"".equals(beginTime.trim())){
				bDate=null;
			}else{	
				try {
					bDate=simpleDateFormat.parse(beginTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(endTime==null||"".equals(endTime.trim())){
				eDate=null;
			}else{	
				try {
					eDate=simpleDateFormat.parse(endTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			List<Evaluation> evaLsit=null;
			//用户查询日期段中，学生自己的反馈信息
			if(permission.equals(Permission.USER.getRole())){
				User user = (User)sessionMap.get("currentPeople");
				Evaluation evaluation = new Evaluation();
				evaluation.setUserName(user.getName());
				evaLsit=evaluationService.getListBetweenTime(bDate,eDate, evaluation);
			}else if(permission.equals(Permission.TEACHER.getRole())){
				Teacher teacher = (Teacher)sessionMap.get("currentPeople");
				Evaluation evaluation = new Evaluation();
				evaluation.setTeacher(teacher.getName());
				//如果是老师还能够更根据学生名模糊查询
				if(userName!=null&&!"".equals(userName)){
					evaluation.setUserName(userName);
				}
				evaLsit= evaluationService.getListBetweenTime(bDate,eDate, evaluation);
			}else if(permission.equals(Permission.MERCHANT.getRole())){
				//Merchant merchant = (Merchant)sessionMap.get("currentPeople");
				return "EvaManage";
			}
			context.put("evaLsit", evaLsit);
			context.put("userName", userName);
			//context.put(key, value);
		}
		return "EvaManage";
	}

}
