package com.lailai.test;

import java.util.List;

import org.junit.Test;

import com.lailai.dao.impl.ClassDaoImpl;
import com.lailai.entity.StuClass;

public class testClassDao {

	@Test
	public void test01(){
		List<StuClass> classByYear = new ClassDaoImpl().getClassByYear("1231");
		System.out.println(classByYear);
	}
}
