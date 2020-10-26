package com.wj.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnUtil {
	private static DataSource ds;
	
	static {
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myOracle");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
