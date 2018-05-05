package com.lailai.service.impl;

import com.lailai.dao.MerchantDao;
import com.lailai.dao.TeacherDao;
import com.lailai.dao.UserDao;
import com.lailai.dao.impl.MerchantDaoImpl;
import com.lailai.dao.impl.TeacherDaoImpl;
import com.lailai.dao.impl.UserDaoImpl;
import com.lailai.entity.Merchant;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;
import com.lailai.service.LoginService;

public class LoginServiceImpl implements LoginService{

	private UserDao userDao=new UserDaoImpl();
	private TeacherDao teacherDao=new TeacherDaoImpl();
	private MerchantDao merchantDao=new MerchantDaoImpl();
	@Override
	public User findUser(User user) {
		return userDao.findUser(user);
	}
	@Override
	public Teacher findTeacher(Teacher teacher) {
		return teacherDao.findTeacher(teacher);
	}
	@Override
	public Merchant findMerchant(Merchant merchant) {
		return 	merchantDao.findMerchant(merchant);
	}
	
	@Override
	public void changePassword(User user2) {
		 userDao.updatePassword(user2);
	}
	@Override
	public void changePassword(Teacher teacher2) {
		teacherDao.updatePassword(teacher2);
	}
	@Override
	public void changePassword(Merchant merchant2) {
		merchantDao.changePwd(merchant2);
	}
}
