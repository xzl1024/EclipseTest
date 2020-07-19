package com.xzl.bean;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;



/**
 * @Description 操作数据库的工具类
 * @author xzl Email:dxzl1024@gmail.com
 * @version
 * @date 2020年7月10日下午4:04:16
 */

public class JDBCUtils {
	/**
	 * 
	 * @Description 获取数据库的连接
	 * @author dxzl
	 * @date 2020年7月10日下午4:05:48
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws Exception {
		// 1.读取配置文件中的四个基本信息
		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

		Properties pros = new Properties();
		pros.load(is);

		String user = pros.getProperty("user");
		String password = pros.getProperty("password");
		String url = pros.getProperty("url");
		String driverClass = pros.getProperty("driverClass");

		// 2.加载驱动
		Class.forName(driverClass);

		// 3.获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;

	}

	/**
	 * 
	 * @Description 关闭连接和Statement的操作
	 * @author dxzl
	 * @date 2020年7月10日下午4:15:02
	 * @param conn
	 * @param ps
	 */

	public static void closeResource(Connection conn, Statement ps) {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description 关闭资源的操作
	 * @author dxzl
	 * @date 2020年7月10日下午4:16:54
	 * @param conn
	 * @param ps
	 * @param rs
	 */

	public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
		try {
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
