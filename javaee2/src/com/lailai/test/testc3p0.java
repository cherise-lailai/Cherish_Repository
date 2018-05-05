package com.lailai.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class testc3p0 {

	@Test
	public void testmyslq() throws SQLException{
		ComboPooledDataSource ds = new ComboPooledDataSource("c3p0");
		Connection connection = ds.getConnection();
		System.out.println(connection);
	}
}
