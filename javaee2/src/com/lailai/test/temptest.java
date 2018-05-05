package com.lailai.test;

import org.junit.Test;

import com.lailai.dao.impl.StudyDaoImpl;

public class temptest {
	@Test
	public void test01(){
		StudyDaoImpl studyDaoImpl = new StudyDaoImpl();
		studyDaoImpl.getClassIDListByTea("赵老师");
	}

}
