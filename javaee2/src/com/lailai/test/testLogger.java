package com.lailai.test;

import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Test;

import org.slf4j.LoggerFactory;


//具体是实现使用log4j
public class testLogger {

	@Test
	public void testLog(){
//		Logger logger = LoggerFactory.getLogger(testLogger.class);
//		System.out.println("开始");
//		logger.info("123");
//		logger.debug("debug");
//		logger.debug("我是slf4j{}","lailai");
//		logger.debug("我是slf4j{}",new Random());
	}
	@Test
	public void testLog2(){
		 Logger logger = Logger.getLogger("MongoDB");  
		    logger.info("{'provinceCode': null, 'channelSubCode': null, 'platFormCode': '002', 'optCode': null, 'startTimeLong': 1384599600000, 'processTime_sum': 10081,'count': 404, 'suc_count': 0,'suc_rate': '0.00000'}");  
		    Logger logger2 = Logger.getLogger("MongoDB2");  
		    logger2.info("{'provinceCode':123}");  
	}
}
