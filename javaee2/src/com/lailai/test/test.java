package com.lailai.test;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class test {
	public static void main(String[] args) throws UnknownHostException {
		org.slf4j.Logger logger = LoggerFactory.getLogger(test.class);
		logger.info("ddddd");
		Logger logger2 = Logger.getLogger("MongoDB");
		logger2.info("aa");
		logger2.error("agdas");

		/*
		 * Mongo mongo = new Mongo("localhost", 27017); DB db =
		 * mongo.getDB("logs"); DBCollection collection =
		 * db.getCollection("log"); DBCursor find = collection.find(); while
		 * (find.hasNext()) { System.out.println(find.next()); }
		 */
	}
	
}
