package com.lailai.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.lailai.common.enums.Scattered;
import com.lailai.dao.HomeDao;
import com.lailai.dao.impl.HomeDaoImpl;
import com.lailai.entity.Content;
import com.lailai.entity.Teacher;
import com.lailai.service.HomeService;

public class HomeServiceImpl implements HomeService {

	private HomeDao homeDao=new HomeDaoImpl();
	@Override
	public void updateContent(List<Content> contentList) {
		homeDao.update(contentList);
	}
	@Override
	public List<Content> findAllContent() {
		List<Content> contentList=homeDao.findAllContent();
		return contentList;
	}
	@Override
	public List<Teacher> findTeacherShow(DetachedCriteria dc) {
		List<Teacher> onShowTeaList=homeDao.findByDc(dc);
		List<Teacher> subList=null;
		if (onShowTeaList.size()>Scattered.fourTeachers.number) {
			subList = onShowTeaList.subList(0, 3);
		}else{
			subList=onShowTeaList;
		}
		return subList;
	}

	
}
