package com.lailai.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.entity.Content;
import com.lailai.entity.Teacher;

public interface HomeDao {
	/**
	 * 更新所有的Content
	 * @param contentList
	 */
	public abstract void update(List<Content> contentList);

	/**
	 * 查询所有的Content
	 * @return
	 */
	public abstract List<Content> findAllContent();

	/**
	 * 根据离线条件查询老师列表
	 * @return
	 */
	public abstract List<Teacher> findByDc(DetachedCriteria dc);
	
	

}
