package com.lailai.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Connection;

public class JDBCUtils {
	private static DataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource("c3p0");
	}
	
	public static java.sql.Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}
	
	public static void releaseDB(java.sql.Connection connection) {
		try {
			if(connection!=null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void releaseDB(ResultSet resultSet,Statement statement) {
		try {
			if(resultSet!=null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(statement!=null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
