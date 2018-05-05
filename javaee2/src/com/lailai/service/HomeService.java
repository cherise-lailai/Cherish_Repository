package com.lailai.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.entity.Content;
import com.lailai.entity.Teacher;

public interface HomeService {
	/**
	 * 将主页的信息机构相关信息修改  简介，风貌，温馨提示
	 * @param contentList
	 */
	public abstract void updateContent(List<Content> contentList );

	/**
	 * 查询所有的Content，用于主页展示
	 * @return
	 */
	public abstract List<Content> findAllContent();
	
	/**
	 * 查询上首页榜单的金牌老师   条件:state、score
	 * @return
	 */
	public abstract List<Teacher>findTeacherShow(DetachedCriteria dc);

}
