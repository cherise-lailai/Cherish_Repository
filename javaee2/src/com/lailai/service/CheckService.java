package com.lailai.service;

import java.util.List;

import com.lailai.entity.Check;

public interface CheckService {
	/**
	 * 添加一次考勤记录，一个列表
	 * @param checkList
	 * @return
	 */
	public abstract boolean addCheck(List<Check> checkList);
}
