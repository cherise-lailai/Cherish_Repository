package com.lailai.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lailai.common.enums.StateEnum;
import com.lailai.common.enums.TeacherScore;
import com.lailai.entity.Course;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;
import com.lailai.service.TeacherService;
import com.lailai.service.impl.TeacherServiceImpl;
import com.lailai.util.JsonUtils;
import com.lailai.util.exportTeacherUtil;
import com.lailai.util.exportUserUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

public class TeacherAction extends ActionSupport{
	private Logger logger = LoggerFactory.getLogger(TeacherAction.class);
	private TeacherService teacherService=new TeacherServiceImpl();
	
	
	private String teaName;
	private String password;
	private File headImage;
	private String headImageContentType;
	private String headImageFileName;
	private String email;
	//参数来自多选框
	private String goodAt;
	public String getTeaName() {return teaName;}
	public void setTeaName(String teaName) {this.teaName = teaName;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	public File getHeadImage() {return headImage;}
	public void setHeadImage(File headImage) {this.headImage = headImage;}
	public String getHeadImageContentType() {	return headImageContentType;}
	public void setHeadImageContentType(String headImageContentType) {this.headImageContentType = headImageContentType;}
	public String getHeadImageFileName() {	return headImageFileName;}
	public void setHeadImageFileName(String headImageFileName) {	this.headImageFileName = headImageFileName;}
	public String getEmail() {	return email;}
	public void setEmail(String email) {this.email = email;}
	public String getGoodAt() {return goodAt;}
	public void setGoodAt(String goodAt) {this.goodAt = goodAt;}
	
	
	private String tid;
	public String getTid() {return tid;}
	public void setTid(String tid) {this.tid = tid;}
	//添加老师或者修改老师
	public String add() {
		
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		String realpath = ServletActionContext.getServletContext().getRealPath("/image/teacherHeadImg");
		File realpathDir = new File(realpath);
		if (!realpathDir.exists()) {
			realpathDir.mkdirs();
		}
		File file = new File(realpath + "/" + headImageFileName);
		//文件为空，则使用默认图片	
		if(headImage==null&&tid==null){
			headImageFileName="default.jpg";
		}
		//文件不为空，做上传
		if(headImage!=null){
			FileOutputStream out;
			FileInputStream in;
			try {
				out = new FileOutputStream(file);
				in = new FileInputStream(headImage);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
				out.close();
				in.close();
				
			} catch (Exception e) {
				logger.error("上传{}文件失败！", realpath);
				e.printStackTrace();
				valueStack.set("userAddInfo", "用户添加失败");
				return "redi_user_add";
			}
		}
	
		String uuidStr;
		//根据uid的有无，判断是修改还是添加
		if(tid==null||"".equals(tid.trim())){
			uuidStr = UUID.randomUUID().toString().replace("-", "");
		}else{
			uuidStr=tid;
		}
		StringBuilder goodSB = new StringBuilder();
		System.out.println(goodAt);
		String[] goodAtArr = goodAt.split(", ");
		for (int i = 0; i < goodAtArr.length; i++) {
			if(i<goodAtArr.length-1)
				goodSB.append(goodAtArr[i]).append(",");
			else{
				goodSB.append(goodAtArr[i]);
			}
		}
		String goodStr = goodSB.toString();
		
		//修改用户没有修改图片
		if(tid!=null&&headImage==null){
			Teacher teacher = teacherService.findBytid(tid);
			String[] split = teacher.getImgPath().split("/");
		
			headImageFileName=split[split.length-1];			
		}
		
		Teacher teacher = new Teacher(uuidStr,teaName,password, "/image/teacherHeadImg" + "/" + headImageFileName, email,goodStr,TeacherScore.ordinaryTeacher.score,StateEnum.teacherAble.state);
		teacherService.add(teacher);
		logger.info("文件{}上传没有出现异常", realpath);
		valueStack.set("userAddInfo", "老师添加成功");
		//修改老师时获得到了tid,goodArr，必须将session中的teacher,goodArr删除
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("teacher");
		return "go_addPage";
	}
	

	//头像下载的文件名处理
    private String fileName;
    public String getFileName() {
    	Teacher teacher = teacherService.findBytid(tid);
    	String name = teacher.getName();
    	String imgPath = teacher.getImgPath();
    	int index = imgPath.indexOf(".");
    	String suffix = imgPath.substring(index);
    	fileName=name+suffix;
    	try {
			fileName = new String(fileName.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	//返回流的方法
    public InputStream getAttrInputStream(){
    	Teacher teacher = teacherService.findBytid(tid);
    	String imgPath = teacher.getImgPath();
		System.out.println(imgPath);
    	FileInputStream fis = null;
        String path=ServletActionContext.getServletContext().getRealPath(imgPath);
    	try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
    	return fis;
    }
    
	public String downImg() throws Exception {
		Teacher teacher = teacherService.findBytid(tid);
		String imgPath = teacher.getImgPath();
		System.out.println(imgPath);
		if(imgPath!=null&&!"".equals(imgPath)){
			 return "download";
		}
        return "redi_all";
    }
	
	
	
		//导出用户信息
		public String exportTeacherInfo(){
			return "export";
		}
		private String exportFileName;
		public String getExportFileName() {
			//处理导出相关业务
			return "teacher.xls";
		}
		
		public void setExportFileName(String exportFileName) {
			this.exportFileName = exportFileName;
		}
		
		public InputStream getExportTeacherInfo(){
			DetachedCriteria dc = DetachedCriteria.forClass(Teacher.class);			
			dc.add(Restrictions.eq("state",StateEnum.teacherAble.state));
			if(teaName!=null&&!"".equals(teaName.trim())){
				dc.add(Restrictions.like("name", "%"+teaName+"%"));
			}
			if(goodAt!=null&&!"".equals(goodAt.trim())){
				dc.add(Restrictions.like("goodAt", "%"+goodAt+"%"));
			}
			List<Teacher> teacherList = teacherService.findByDc(dc);
			InputStream is = exportTeacherUtil.createTeacherExecl("teacherList.xls", teacherList);
			return is;
		}
	
	
	//查询老师列表（无分页）
	//无需goodAt，teacherName在add处有声明
	public String all(){
		DetachedCriteria dc = DetachedCriteria.forClass(Teacher.class);
		if(teaName!=null&&!"".equals(teaName.trim())){
			dc.add(Restrictions.like("name", "%"+teaName+"%"));
		}
		if(goodAt!=null&&!"".equals(goodAt.trim())){
			dc.add(Restrictions.like("goodAt", "%"+goodAt+"%"));
		}
		dc.add(Restrictions.eq("state", StateEnum.teacherAble.state));
		List<Teacher> teacherList = teacherService.findByDc(dc);
		ActionContext context = ActionContext.getContext();
		context.put("teacherList", teacherList);
		context.put("teaNameConditon",teaName );
		context.put("goodAtConditon",goodAt );
		return "go_manage";
	}
	
	//删除老师
	public String delete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> request = (Map<String, Object>) context.get("request");
		String uid = (String) request.get("tid");
		teacherService.deleteTeacher(tid, StateEnum.teacherDisable.state);
		return "redi_all";
	}
	
	//更新用户
	//需要传入参数tid，但是前面有声明，所以略去
	public String update(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> request = (Map<String, Object>) context.get("request");
		Map<String, Object> session = context.getSession();
		Teacher teacher = teacherService.findBytid(tid);
		String[] goodArr = teacher.getGoodAt().split(",");
		session.put("teacher", teacher);
		return "go_addPage";
	}
	public void getGoodAtByid(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> request = (Map<String, Object>) context.get("request");
		Map<String, Object> session = context.getSession();
		
		String[] goodArr = null;
		if(tid==null||"".equals(tid.trim())){
			/*List<Course> courseLiset=teacherService.getAllCourse();
			goodArr = (String[]) courseLiset.toArray();*/
		}else{
			Teacher teacher = teacherService.findBytid(tid);
			 goodArr = teacher.getGoodAt().split(",");
		}

		String resultJson = JsonUtils.objectToJson(goodArr);
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
	
	//浏览器传来的课程名
	private String cname;
	
	public String getCname() {return cname;}
	public void setCname(String cname) {this.cname = cname;}
	public void getFixTeacher(){
		//fixTeacher 形容:听力,阅读理解 格式
		if(cname!=null){
			String fixTeacher = teacherService.fixTeacher(cname);
			String resultJson = JsonUtils.objectToJson(fixTeacher);
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=utf-8");
				PrintWriter writer = response.getWriter();
				writer.write(resultJson);
				writer.flush();
				writer.close();
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		logger.error("没有走正确的路径来访问ClassAction的getFixTeacher方法，参数cname没获得");
	}
	
	//获得所有的老师用于点赞
	public String getAllTeacher(){
		DetachedCriteria dc = DetachedCriteria.forClass(Teacher.class);
		dc.add(Restrictions.eq("state", StateEnum.teacherAble.state));
		List<Teacher> teacherList = teacherService.findByDc(dc);
		ActionContext context = ActionContext.getContext();
		context.put("teacherList", teacherList);
		context.put("size", teacherList.size());
		return "allTeacher";
	}
	
	
	public String getMyTeachTime(){
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		String permission = (String) session.get("permission");
/*		Teacher teacher=null;
		if(permission.equals("老师")){
			teacher = (Teacher)session.get("currentPeple");
		}*/
		Teacher teacher2 = new Teacher();
		teacher2.setName("赵老师");		
		List<HashMap<String, String>> teachTimeMapList=teacherService.getTeachTime(teacher2);
		context.put("teachTimeMapList", teachTimeMapList);
		return "showTeachTime";
	}
	
	//所有老师的页面点赞点赞
	//private String tid;因为前面定义了
	public String addScore(){
		teacherService.addScoreByTid(tid);
		return "redi_allTeacher";
	}
	
}
