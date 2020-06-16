package com.api.utils;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * JDBC 工具类，用来获取数据库连接
 * @author yaopengtao
 * @data 2020年5月28日 下午3:51:09
 */
public class JDBCUtils {
	public static String jdbc_url = PropertiesUtils.prop.getProperty("jdbc_url");
	public static String jdbc_user = PropertiesUtils.prop.getProperty("jdbc_user");
	public static String jdbc_password = PropertiesUtils.prop.getProperty("jdbc_password");
	
	public static Connection getConnection() {
		//定义数据连接对象
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(jdbc_url, jdbc_user, jdbc_password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
