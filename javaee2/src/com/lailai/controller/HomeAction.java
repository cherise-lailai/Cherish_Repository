package com.lailai.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.lailai.common.enums.ContentEnum;
import com.lailai.common.enums.StateEnum;
import com.lailai.common.enums.TeacherScore;
import com.lailai.dto.OpenClassDto;
import com.lailai.entity.Content;
import com.lailai.entity.StuClass;
import com.lailai.entity.Teacher;
import com.lailai.service.ClassService;
import com.lailai.service.HomeService;
import com.lailai.service.TeacherService;
import com.lailai.service.impl.ClassServiceImpl;
import com.lailai.service.impl.HomeServiceImpl;
import com.lailai.service.impl.TeacherServiceImpl;
import com.lailai.util.HibernateUtils;
import com.lailai.util.JsonUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport{
	private HomeService homeService=new HomeServiceImpl();
	private TeacherService teacherService=new TeacherServiceImpl();
	private ClassService classService=new ClassServiceImpl();
	
	//修改内容
	private String intro;
	private String style;
	private String notice;
	public String getIntro() {return intro;}
	public void setIntro(String intro) {this.intro = intro;}
	public String getStyle() {return style;	}
	public void setStyle(String style) {this.style = style;}
	public String getNotice() {return notice;}
	public void setNotice(String notice) {this.notice = notice;}
	public void updateContent(){
		Content introContent= new Content(ContentEnum.Introduction.contentId,ContentEnum.Introduction.ContentTitle,intro,0);
		Content styleContent= new Content(ContentEnum.style.contentId,ContentEnum.style.ContentTitle,style,0);
		Content noticeContent= new Content(ContentEnum.notice.contentId,ContentEnum.notice.ContentTitle,notice,0);
		ArrayList<Content> contentList = new ArrayList<Content>();
		contentList.add(introContent);
		contentList.add(styleContent);
		contentList.add(noticeContent);
		homeService.updateContent(contentList);
		String resultJson = JsonUtils.objectToJson("更改成功");
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(resultJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * 使用查询findAllContent工具方法查询出信息回显到content修改页面
	 * @return
	 */
	public String contentManage(){
		ActionContext context = ActionContext.getContext();
		context = findAllContent(context);
		return "go_contentUpdatePage";
	}
	
	/**
	 * 这是工具方法：查询首页简介，风貌，通知后放入request域
	 * @return
	 */
	private ActionContext findAllContent(ActionContext context){
		List<Content> contentList=homeService.findAllContent();
		for (Content content : contentList) {
			if(content.getId().equals(ContentEnum.Introduction.contentId)){
				context.put("introContent", content);
			}else if(content.getId().equals(ContentEnum.style.contentId)){
				context.put("styleContent", content);
			}else if(content.getId().equals(ContentEnum.notice.contentId)){
				context.put("noticeContent", content);
			}
		}
		return context;
	}
	
	/**
	 * 获得上榜的金牌老师
	 * @param context
	 * @return
	 */
	private ActionContext getOnShowTeacher(ActionContext context){
		DetachedCriteria dc = DetachedCriteria.forClass(Teacher.class);
		dc.add(Restrictions.eq("state", StateEnum.teacherAble.state))
		.add(Restrictions.ge("score",TeacherScore.goldTeacher.score))
		.addOrder(Order.desc("score"));
		List<Teacher> teacherList = homeService.findTeacherShow(dc);
		context.put("teacherList", teacherList);
		return context;
	}
	
	

	/**
	 * 查询到简介，风貌，通知后返回给主页
	 * @return
	 */
	public String all(){
		ActionContext context = ActionContext.getContext();
		//内容
		List<Content> contentList=homeService.findAllContent();
		for (Content content : contentList) {
			if(content.getId().equals(ContentEnum.Introduction.contentId)){
				context.put("introContent", content);
			}else if(content.getId().equals(ContentEnum.style.contentId)){
				context.put("styleContent", content);
			}else if(content.getId().equals(ContentEnum.notice.contentId)){
				context.put("noticeContent", content);
			}
		}
		//分数
		DetachedCriteria dc = DetachedCriteria.forClass(Teacher.class);
		dc.add(Restrictions.eq("state", StateEnum.teacherAble.state))
		.add(Restrictions.ge("score",TeacherScore.goldTeacher.score))
		.addOrder(Order.desc("score"));
		List<Teacher> teacherList = homeService.findTeacherShow(dc);
		context.put("teacherList", teacherList);
		//轮播图
		String homeRunPath = ServletActionContext.getServletContext().getRealPath("/image/homeRunImg/slider");
		File file = new File(homeRunPath);
		String[] runImgPathArr = file.list();
		for (int i = 0; i < runImgPathArr.length; i++) {
			runImgPathArr[i] = "/image/homeRunImg/slider/"+runImgPathArr[i];
		}
		context.put("runImgPathArr", runImgPathArr);
		
		//开班信息
		Calendar date = Calendar.getInstance();
	    String year = String.valueOf(date.get(Calendar.YEAR));
		DetachedCriteria openClassDc = DetachedCriteria.forClass(StuClass.class);
		openClassDc.add(Restrictions.eq("state", StateEnum.classAble.state))
		.add(Restrictions.eq("year", year));
		List<OpenClassDto>openClassList=classService.getNowClass(openClassDc);
		context.put("openClassList", openClassList);
		return "go_home";
	}
	

	private String tid;
	public String getTid() {	return tid;}
	public void setTid(String tid) {this.tid = tid;}
	public String addScore(){
		teacherService.addScoreByTid(tid);
		return "redi_all";
	}
	
	private String cid;	
	public String getCid() {return cid;}
	public void setCid(String cid) {	this.cid = cid;}
	public void getClassBycid(){
		OpenClassDto openClassDto = classService.getClassByCid(cid);
		String resultJson = JsonUtils.objectToJson(openClassDto);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.write(resultJson);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	

}
