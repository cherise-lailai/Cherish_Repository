package com.lailai.controller;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.internal.Classes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lailai.common.Page;
import com.lailai.common.enums.StateEnum;
import com.lailai.entity.StuClass;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;
import com.lailai.service.ClassService;
import com.lailai.service.UserService;
import com.lailai.service.impl.ClassServiceImpl;
import com.lailai.service.impl.UserServiceImpl;
import com.lailai.util.HibernateUtils;
import com.lailai.util.JsonUtils;
import com.lailai.util.exportUserUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;

import ognl.internal.ClassCacheImpl;

public class UserAction extends ActionSupport {
	private String username;
	private String password;
	private File headImage;
	private String headImageContentType;
	private String headImageFileName;
	private String email;
	private String cla;

	//	服务
	private UserService userService = new UserServiceImpl();
	private ClassService classService=new ClassServiceImpl();

	private Logger logger = LoggerFactory.getLogger(UserAction.class);

	public String getHeadImageContentType() {
		return headImageContentType;
	}

	public void setHeadImageContentType(String headImageContentType) {
		this.headImageContentType = headImageContentType;
	}

	public String getHeadImageFileName() {
		return headImageFileName;
	}

	public void setHeadImageFileName(String headImageFileName) {
		this.headImageFileName = headImageFileName;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public File getHeadImage() {
		return headImage;
	}

	public void setHeadImage(File headImage) {
		this.headImage = headImage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCla() {
		return cla;
	}

	public void setCla(String cla) {this.cla = cla;}

	
	private String classNum;
	private String stuName;
	private String pageStrNo;
	public String getClassNum() {return classNum;}
	public void setClassNum(String classNum) {this.classNum = classNum;}
	public String getStuName() {return stuName;}
	public void setStuName(String stuName) {this.stuName = stuName;}
	public String getPageStrNo() {return pageStrNo;}
	public void setPageStrNo(String pageStrNo) {this.pageStrNo = pageStrNo;}
	

	public String all(){
		int pageNo=1;
		
		if(pageStrNo!=null&&!"".equals(pageStrNo.trim())){
			try{
				pageNo = Integer.parseInt(pageStrNo);
			}catch(Exception e){
				logger.error("页码字符串转成int失败");
			}
		}
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		if(stuName!=null&&!"".equals(stuName.trim())){
			dc.add(Restrictions.like("name", "%"+stuName+"%"));
		}
		
		if(classNum!=null&&!"".equals(classNum.trim())){
			//根据传递的班级名称查询班级id
			String findCid = classService.getCidByCname(classNum);
			if(findCid!=null){
				dc.add(Restrictions.like("classID",findCid));
			}
		}
		dc.add(Restrictions.eq("state",StateEnum.userAble.state));
		
		Page<User> userPage = userService.findByDc(pageNo, dc);
		ActionContext context = ActionContext.getContext();
		context.put("userPage", userPage);
		context.put("nameConditon",stuName );
		context.put("classConditon",classNum );
		return "go_manage";
	}
	
	private String uid;
    public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String downImg() throws Exception {
	   	User user = userService.findByUid(uid);
    	String imgPath = user.getImgPath();
		System.out.println(imgPath);
		if(imgPath!=null&&!"".equals(imgPath)){
			 return "download";
		}
		
        return "redi_all";
    }
	
	
	//头像下载的文件名处理
    private String fileName;
    public String getFileName() {
    	User user = userService.findByUid(uid);
    	String name = user.getName();
    	String imgPath = user.getImgPath();
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
    	
    	User user = userService.findByUid(uid);
    	String imgPath = user.getImgPath();
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
    
    //添加用户时需要的一些班级列表，使用ajax传递json
    private String year;  
	public String getYear() {return year;}
	public void setYear(String year) {this.year = year;}

	public void getClassByYear(){
		List<StuClass> classList = classService.findByYear(year);
		if(classList!=null){
			StringBuilder sb = new StringBuilder();
			
			List<Map<String,String>> mapList =new ArrayList();
			for (StuClass stuClass : classList) {
				HashMap<String, String> classMap = new HashMap<String, String>();
				classMap.put("id", stuClass.getId());
				classMap.put("num", stuClass.getNum());
				mapList.add(classMap);
			}

			String resultJson = JsonUtils.objectToJson(mapList);
			
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
	
	
	//更新用户
	public String update(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> request = (Map<String, Object>) context.get("request");
		Map<String, Object> session = context.getSession();
		String uid = (String) request.get("uid");
		User u = userService.findByUid(uid);
		session.put("user", u);
		return "go_addPage";
	}
	
	//删除用户
	public String delete(){
		ActionContext context = ActionContext.getContext();
		Map<String,Object> request = (Map<String, Object>) context.get("request");
		String uid = (String) request.get("uid");
		userService.deleteUser(uid,StateEnum.userDisable.state);
		return "redi_all";
	}

	//参数来自下拉框
	private String classId;
    public String getClassId() {return classId;}
	public void setClassId(String classId) {this.classId = classId;}
	//添加用户
	public String add() {
		//获得到了uid，必须将session中的user删除
		ActionContext.getContext().getSession().remove("user");
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		String realpath = ServletActionContext.getServletContext().getRealPath("/image/userHeadImg");
		File realpathDir = new File(realpath);
		if (!realpathDir.exists()) {
			realpathDir.mkdirs();
		}
		File file = new File(realpath + "/" + headImageFileName);
		//文件为空，则使用默认图片	
		if(headImage==null&&uid==null){
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
		if(uid==null||"".equals(uid.trim())){
			uuidStr = UUID.randomUUID().toString().replace("-", "");
		}else{
			uuidStr=uid;
		}
		
		//修改用户没有修改图片
		if(uid!=null&&headImage==null){
			User u = userService.findByUid(uid);
			String[] split = u.getImgPath().split("/");
			headImageFileName=split[split.length-1];			
		}
		User user = new User(uuidStr,username, password, "/image/userHeadImg" + "/" + headImageFileName, email, 1,StateEnum.userAble.state,classId);
		userService.addUser(user);
		logger.info("文件{}上传没有出现异常", realpath);
		valueStack.set("userAddInfo", "用户添加成功");
		return "go_addPage";
	}
	//导出用户信息
	public String exportUserInfo(){
		return "export";
	}
	private String exportFileName;
	public String getExportFileName() {
		//处理导出相关业务
		return "user.xls";
	}
	
	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}
	
	public InputStream getExportUserInfo(){
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		if(stuName!=null&&!"".equals(stuName.trim())){
			dc.add(Restrictions.like("name", "%"+stuName+"%"));
		}
		if(classNum!=null&&!"".equals(classNum.trim())){
			dc.add(Restrictions.like("classID", "%"+classNum+"%"));
		}
		dc.add(Restrictions.eq("state",StateEnum.userAble.state));
		List<User> userList = userService.findAllByDcNoPage(dc);
		InputStream is = exportUserUtil.createUserExecl("userList.xls", userList);
		return is;
	}
	

	
}
