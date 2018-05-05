package com.lailai.service.impl;

import java.util.List;

import com.lailai.dao.CheckDao;
import com.lailai.dao.impl.CheckDaoImpl;
import com.lailai.entity.Check;
import com.lailai.service.CheckService;

public class CheckServiceImpl implements CheckService {
	private CheckDao checkDao=new CheckDaoImpl();
	@Override
	public boolean addCheck(List<Check> checkList) {
		boolean isOk=checkDao.addBatch(checkList);
		return isOk;
	}
	

}
