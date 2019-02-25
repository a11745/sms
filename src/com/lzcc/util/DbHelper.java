package com.lzcc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC工具类
 * 
 * 
 * 
 */
public class DbHelper {
	private static String driverName;
	private static String url;
	private static String user;
	private static String password;
	private static Connection conn = null;
	private static PreparedStatement ps = null;

	// 从配置文件读取数据库信息
	static {
		Properties props = new Properties();
		try {
			props.load(DbHelper.class.getResourceAsStream("db.properties"));
			driverName = props.getProperty("driver");
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}

	}

	// 加载驱动
	private static void loadDriver() {
		try {
			Class.forName(driverName);
			System.out.println("【...loadDriver() running...】");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 初始化连接
	public static void initConnection() {
		//需要时再创建连接
		if(conn == null)
		{
		try {
				loadDriver();
				conn = DriverManager.getConnection(url.trim(), user.trim(),
						password.trim());
				System.out.println("【...initConnection() running...】");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("【...initConnection() error!...】");
		}
		}
		
	}
	//执行Insert语句，返回新得到的Id
	public static int insertSqlReturnNewId(String sql, Object[] values) {
		initConnection();
		int newId = 0;
		try {
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i + 1, values[i]);
				}
			}
			int result = ps.executeUpdate();
			if (result > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					newId = rs.getInt(1);
				}
				rs.close();
			}
			ps.clearParameters();
			System.out.println("【...insertSqlReturnNewId()... running...】");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("【...insertSqlReturnNewId()... error!...】");
		}
		return newId;
	}
	//执行DML语句，返回受影响函数
	public static int updateSql(String sql, Object[] values) {
		initConnection();
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i + 1, values[i]);
				}
			}
			result = ps.executeUpdate();
			ps.clearParameters();
			System.out.println("【...updateSql()... running!...】");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("【...updateSql()... error!...】");
		}
		return result;
	}
	//执行查询语句，返回查询到的结果集
	public static ResultSet querySql(String sql, Object[] values) {
		initConnection();
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i + 1, values[i]);
				}
			}
			rs = ps.executeQuery();
			ps.clearParameters();
			System.out.println("【...querySql()... running!...】");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("【...querySql()... error!...】");
		}
		return rs;
	}

	public static void beginTrans() {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commitTrans() {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollbackTrans() {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//关闭资源
	public static void closeResource() {
		try {
			if (ps != null) {
				ps.close();
			}
		/*	if (conn != null) {
				conn.close();
			}
			System.out.println("【...closeResource()... running!...】");*/
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("【...closeResource()... error!...】");
		}
	}
}
