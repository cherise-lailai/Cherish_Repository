package com.lailai.dao;

import java.util.List;

import com.lailai.entity.Check;

public interface CheckDao {

	/**
	 * 添加一批考勤记录
	 * @param checkList
	 * @return
	 */
	boolean addBatch(List<Check> checkList);
	
}
