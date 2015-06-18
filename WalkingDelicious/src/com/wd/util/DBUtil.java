package com.wd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.wd.config.AppConfig;

public class DBUtil {
	private  final String dbDriver;
	private  final String dbURL;
	private  final String dbUsername;
	private  final String dbPassword;
	
	private static DBUtil singleton=null;
	
	//数据库连接
	private Connection conn;
	// 
	public static DBUtil getInstance(){
		if(singleton==null){
			singleton = new DBUtil();
		}
		return singleton;
	}
	
	
	//在构造函数里面载入MySql数据库连接驱动
	public DBUtil(){
		AppConfig config = AppConfig.getInstance();
		dbDriver = config.getParameter("dbDriver");
		dbURL = config.getParameter("dbURL");
		dbUsername = config.getParameter("dbUsername");
		dbPassword =config.getParameter("dbPassword");
		
		try{
			Class.forName(dbDriver);
			System.out.println("get database driver success!!!");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("get database driver failed!!!");
		}
	}
	//获取数据库连接
	public Connection getConnection(){
		try{
			conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	public void releaseConn() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
