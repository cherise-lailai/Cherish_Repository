package com.lailai.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.lailai.common.Page;
import com.lailai.dao.ClassDao;
import com.lailai.dao.UserDao;
import com.lailai.dao.impl.ClassDaoImpl;
import com.lailai.dao.impl.UserDaoImpl;
import com.lailai.entity.StuClass;
import com.lailai.entity.User;
import com.lailai.service.UserService;
import com.lailai.util.HibernateUtils;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();

	private ClassDao classDao=new ClassDaoImpl();
	@Override
	public void addUser(User user) {
		userDao.insert(user);
	}



	@Override
	public User findByUid(String uid) {
		return userDao.findByid(uid);

	}



	@Override
	public Page<User> findByDc(int pageNo,DetachedCriteria dc) {
		//查询出班级id与班级名称的映射map
		Calendar date = Calendar.getInstance();
	    String year = String.valueOf(date.get(Calendar.YEAR));

	    List<Map<String,String>> id2NumList =new ArrayList<Map<String,String>>();
		List<StuClass> claList = classDao.getClassByYear(year);
		for (StuClass stuClass : claList) {
			Map<String,String> id2num = new HashMap<String,String> ();
			id2num.put("id", stuClass.getId());
			id2num.put("num", stuClass.getNum());
			id2NumList.add(id2num);
		}
		
		Page<User> page = userDao.getByDC(pageNo, dc);
		
		List<User> list = page.getList();
		
		for (int i = 0; i < list.size(); i++) {
			String falseCId=null;
			String classID = list.get(i).getClassID();
			for (Map<String,String> m : id2NumList) {
				if(classID.equals(m.get("id"))){
					falseCId=m.get("num");
					break;
				}	
			}
			list.get(i).setClassID(falseCId);
		}
		//改装过的用户列表，取巧改装，id与name都是string，节省了一次数据传输对象的建立
		page.setList(list);
		
		
		int size = userDao.findAllUser().size();
		return page;
	}



	@Override
	public List<User> findAllByClass(String classNum) {
		return userDao.findAllByClass(classNum);
	}
	
	



	@Override
	public List<User> findAllUser() {
		return userDao.findAllUser();
	}



	@Override
	public List<User> findAllByDcNoPage(DetachedCriteria dc) {
		return userDao.findAllByDcNoPage(dc);
	}



	@Override
	public void deleteUser(String uid,int newState) {
		userDao.updateStateByid(uid, newState);
		
	}



	@Override
	public List<User> findAllUserByCid(String cid) {
		return userDao.findAllUserByCid(cid);
	}

}
