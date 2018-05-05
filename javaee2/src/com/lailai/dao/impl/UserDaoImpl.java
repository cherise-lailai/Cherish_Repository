package com.lailai.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lailai.common.Page;
import com.lailai.common.enums.StateEnum;
import com.lailai.dao.UserDao;
import com.lailai.entity.Merchant;
import com.lailai.entity.Teacher;
import com.lailai.entity.User;
import com.lailai.util.HibernateUtils;

public class UserDaoImpl implements UserDao {
	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	public void insert(User user) {
		
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
//		currentSession.save(user);
		currentSession.saveOrUpdate(user);
		ts.commit();
		logger.info("插入/更新了一条User{}",user.toString());
	}


	@Override
	public User findByid(String uid) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Query query = session.createQuery("from User where id=:uid");
		query.setParameter("uid", uid);
		List<User> list = query.list();
		ts.commit();
		session.close();
		return list.get(0);
	}

	@Override
	public Page<User> getByDC(int pageNo,DetachedCriteria dc) {
		
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		
		Criteria c = dc.getExecutableCriteria(session);
		List<User> userAllList = c.list();
		
		c.setFirstResult((pageNo-1)*2);
		c.setMaxResults(2);
		
		List<User> uList = c.list();
		ts.commit();
		session.close();
		Page<User> page = new Page<User>(pageNo);
		page.setList(uList);
		page.setTotalItemNumber(userAllList.size());
		
		return page;
	}
	


	@Override
	public List<User> findAllByClass(String classNum) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Query query = session.createQuery("from User");
		List<User> list = query.list();
		ts.commit();
		session.close();
		return list;
	}


	@Override
	public List<User> findAllUser() {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Query query = session.createQuery("from User where state=:state");
		query.setParameter("state", StateEnum.userAble.state);
		List<User> list = query.list();
		ts.commit();
		session.close();
		return list;
	}
	
	
	


	@Override
	public List<User> findAllByDcNoPage(DetachedCriteria dc) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		
		Criteria c = dc.getExecutableCriteria(session);
		List<User> userAllList = c.list();
		ts.commit();
		session.close();
		return userAllList;
	}


	@Override
	public void updateStateByid(String uid,int newState) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		User user = session.get(User.class, uid);
		Integer oldState = user.getState();
		user.setState(newState);
		session.update(user);
		ts.commit();
		session.close();
		logger.info("修改用户{}的状态,原state{}，修改后的state{}",user.toString(),oldState,newState);
	}


	@Override
	public List<User> findAllUserByCid(String cid) {
		Session session = HibernateUtils.openSession();
		Transaction ts = session.beginTransaction();
		Query query = session.createQuery("from User where state=:state and  classID=:classID");
		query.setParameter("state", StateEnum.userAble.state);
		query.setParameter("classID",cid);
		List<User> list = query.list();
		ts.commit();
		session.close();
		return list;
	}


	@Override
	public User findUser(User user) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		Query query = currentSession.createQuery("from User where name=:name and password=:password and state=:state");
		query.setParameter("name", user.getName());
		query.setParameter("password", user.getPassword());
		query.setParameter("state", StateEnum.userAble.state);
		List list = query.list();
		ts.commit();
		if(list.size()>0){
			return (User)list.get(0);
		}
		return null;
	}
	
	@Override
	public void updatePassword(User user2) {
		Session currentSession = HibernateUtils.getCurrentSession();
		Transaction ts = currentSession.beginTransaction();
		User user = currentSession.get(User.class, user2.getId());
		user.setPassword(user2.getPassword());
		ts.commit();
	}
}

