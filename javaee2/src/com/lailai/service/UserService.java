package com.lailai.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.common.Page;
import com.lailai.entity.User;

public interface UserService {

	/**
	 * 添加一个用户
	 * 
	 * @param user
	 */
	public void addUser(User user);
	/**
	 * 查询条件查询所有的User
	 */
	public abstract Page<User> findByDc(int pageNo,DetachedCriteria dc);
	/**
	 * 根据id查询User
	 * @param uid
	 * @return
	 */
	public abstract User findByUid(String uid);
	/**
	 * 根据班级查询user
	 * @param classNum
	 * @return
	 */
	public abstract List<User> findAllByClass(String classNum);
	
	public abstract List<User> findAllUser();
	
	public abstract List<User> findAllByDcNoPage(DetachedCriteria dc);
	/**
	 * 根据用户id删除用户，实则更新状态为禁用
	 * @param uid
	 * 
	 */

	public abstract void deleteUser(String uid,int newState);
	/**
	 * 根据班级号查询所有的用户
	 * @param cid
	 * @return
	 */
	public abstract List<User> findAllUserByCid(String cid);

	
}
