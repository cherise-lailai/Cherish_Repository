package com.lailai.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.lailai.common.enums.Permission;
import com.lailai.entity.Merchant;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;
import com.lailai.service.LoginService;
import com.lailai.service.TeacherService;
import com.lailai.service.UserService;
import com.lailai.service.impl.LoginServiceImpl;
import com.lailai.service.impl.TeacherServiceImpl;
import com.lailai.service.impl.UserServiceImpl;
import com.lailai.util.JsonUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private LoginService loginService = new LoginServiceImpl();

	private String username;
	private String password;
	private String role;

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void myLogin() {
		String result = "false";
		if (username != null && !"".equals(username.trim()) && password != null && !"".equals(password.trim())
				&& role != null && !"".equals(role.trim())) {
			Map<String, Object> sessionMap = ActionContext.getContext().getSession();
			switch (role) {
			case "user":
				User user = new User();
				user.setName(username);
				user.setPassword(password);
				User findUser = loginService.findUser(user);
				if (findUser != null) {
					sessionMap.put("currentPeople", findUser);
					sessionMap.put("permission", "user");
					result = findUser.getName();
				}
				break;
			case "teacher":
				Teacher teacher = new Teacher();
				teacher.setName(username);
				teacher.setPassword(password);
				Teacher findTeacher = loginService.findTeacher(teacher);
				if (findTeacher != null) {
					sessionMap.put("currentPeople", findTeacher);
					sessionMap.put("permission", "teacher");
					result = findTeacher.getName();
				}
				break;
			case "merchant":
				Merchant merchant = new Merchant();
				merchant.setName(username);
				merchant.setPassword(password);
				Merchant findMerchant = loginService.findMerchant(merchant);
				if (findMerchant != null) {
					sessionMap.put("currentPeople", findMerchant);
					sessionMap.put("permission", "merchant");
					result = findMerchant.getName();
				}
				break;
			default:
				result = "fasle";
			}
		}

		String resultJson = JsonUtils.objectToJson(result);
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

	public void exitLogin() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.invalidate();
		String resultJson = JsonUtils.objectToJson("true");
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

	/*
	 * private String password; 前面已经定义了，在此不加
	 */
	public String changePassowrd() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> sessionMap = context.getSession();
		String permission = (String) sessionMap.get("permission");
		switch (permission) {
		case "user":
			User user = (User) sessionMap.get("currentPeople");
			User user2 = new User();
			user2.setId(user.getId());
			user2.setPassword(password);
			loginService.changePassword(user2);
			break;
		case "teacher":
			Teacher teacher = (Teacher) sessionMap.get("currentPeople");
			Teacher Teacher2 = new Teacher();
			Teacher2.setId(teacher.getId());
			Teacher2.setPassword(password);
			loginService.changePassword(Teacher2);
			break;
		case "merchant":
			Merchant merchant = (Merchant) sessionMap.get("currentPeople");
			Merchant merchant2 = new Merchant();
			merchant2.setId(merchant.getId());
			merchant2.setPassword(password);
			loginService.changePassword(merchant2);
			break;
		default:
			break;
		}
		context.put("changeInfo", "修改成功");
		return "change_password";
	}

}
