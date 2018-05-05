package com.lailai.service;

import com.lailai.entity.Merchant;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;

public interface LoginService {

	/**
	 * 从user表中查询User
	 * @param user
	 * @return
	 */
	public abstract User findUser(User user);
	/**
	 * 从teacher表中查询teacher
	 * @param teacher
	 * @return
	 */
	public abstract Teacher findTeacher(Teacher teacher);
	/**
	 * 从商家表中查询商家
	 * @param merchant
	 * @return
	 */
	public abstract Merchant findMerchant(Merchant merchant);
	/**
	 * 修改学生密码
	 * @param user2
	 */
	public abstract void changePassword(User user2);
	/**
	 * 修改老师密码
	 * @param teacher2
	 */
	public abstract void changePassword(Teacher teacher2);
	/**
	 * 修改商家的密码
	 * @param merchant2
	 */
	public abstract void changePassword(Merchant merchant2);
	
	
}
