package com.lailai.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.common.Page;
import com.lailai.entity.User;

public interface UserDao {
	/**
	 * 插入User
	 * @param user
	 */
	public abstract void insert(User user);
	/**
	 * 根据条件获得所有User
	 * @return
	 */
	public abstract Page<User> getByDC(int pageNo,DetachedCriteria dc);
	/**
	 * 根据用户id获取用户
	 * @param uid
	 * @return
	 */
	public abstract User findByid(String uid);
	/**
	 * 根据班级查询所有用户
	 * @param classNum
	 * @return
	 */
	public List<User> findAllByClass(String classNum);
	
	/**
	 * 获取所有的用户
	 * @return
	 */
	public abstract List<User>  findAllUser();
	/**根据条件查询用户，无分页
	 * 
	 * @param dc
	 * @return
	 */
	public abstract List<User>  findAllByDcNoPage(DetachedCriteria dc);
	/**
	 * 根据用户id上修改用户状态
	 * @param uid
	 */
	public abstract void updateStateByid(String uid, int newState);
	/**
	 * 根据班级查询所有的用户
	 * @param cid
	 */
	public abstract List<User> findAllUserByCid(String cid);
	
	/**
	 * 根据账户密码查询user，用于登入模块
	 * @param user
	 * @return
	 */
	public abstract User findUser(User user);
	
	/**
	 * 修改学生密码
	 * @param user2
	 * 
	 */
	public abstract void updatePassword(User user2);

}
