package com.wd.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private PreparedStatement pstmt;
	// 
	private Statement stmt;
	// 结果集
	public ResultSet rs;
	
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
	public boolean deleteResult(String sql)
		throws SQLException{
		boolean flag = false;
		stmt = conn.createStatement();
		int result = -1;
		result = stmt.executeUpdate(sql);
		flag  = result > 0? true:false ;
		return flag;
	}
	//
	public boolean deleteByBatch(String[] sql) throws SQLException{
		boolean flag = false;
		stmt = conn.createStatement();
		if(sql != null){
			for(int i=0;i<sql.length;i++){
				stmt.addBatch(sql[i]);
			}
		}
		int[] count = stmt.executeBatch();
		if(count != null){
			flag = true;
		}
		return flag;
	}
	//
	public boolean updateByStatement(String sql) throws SQLException{
		boolean flag = false;
		int result= -1;
		stmt =conn.createStatement();
		result  = stmt.executeUpdate(sql);
		flag = result > 0 ?true:false;
		return flag; 
	}
	//
	public boolean updateByPreparedStatement(String sql,List<Object> params)
			throws SQLException{
		boolean flag = false;
		int result = -1;//
		pstmt = conn.prepareStatement(sql);
		int index = 1;
		if(params != null && !params.isEmpty()){
			for(int i = 0; i < params.size(); i++){
				pstmt.setObject(index++,params.get(i));
			}
		}
		result = pstmt.executeUpdate();
		flag = result > 0?true:false;
		return flag;
	}
	//
	public ResultSet queryResult(String sql) 
			throws SQLException{
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		return rs;
	}
	//
	public Map<String,Object> findSingleResult(String sql,List<Object> params)
			throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		int index = 1;
		pstmt = conn.prepareStatement(sql);
		if(params != null && !params.isEmpty()){
			for(int i = 0;i < params.size();i++){
				pstmt.setObject(index++, params.get(i));
			}
		}
		rs = pstmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int col_len = metaData.getColumnCount();
		while (rs.next()){
			for(int i = 0;i < col_len;i++){
				String col_name = metaData.getColumnName(i+1);
				Object col_value = rs.getObject(col_name);
				if(col_value == null){
					col_value ="";
				}
				map.put(col_name, col_value);
			}
		}
		return map;
     }
	//
	public List<Map<String,Object>> findMoreResult(String sql,List<Object> params)
			throws SQLException{
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int index = 1;
		pstmt = conn.prepareStatement(sql);
		if (params !=null && !params.isEmpty()){
			for(int i = 0 ;i < params.size();i++){
				//Object ss=params.get(i);
				pstmt.setObject(index++, params.get(i));
				}
		}
		rs = pstmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int col_len = metaData.getColumnCount();
		while(rs.next()){
			Map<String,Object> map = new HashMap<String,Object>();
			for(int i = 0;i < col_len;i++){
				String col_name = metaData.getColumnName(i+1);
				Object col_value = rs.getObject(col_name);
				if(col_value == null){
					col_value = "";
				}
				map.put(col_name, col_value);
			}
			list.add(map);
		}
		return list;
	}
	// 
		public <T> T findSimpleRefResult(String sql, List<Object> params,
				Class<T> cls) throws Exception {
			T resultObject = null;
			int index = 1;
			pstmt = conn.prepareStatement(sql);
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(index++, params.get(i));
					}
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int cols_len = metaData.getColumnCount();
			while (rs.next()) {
				//
				resultObject = cls.newInstance();
				for (int i = 0; i < cols_len; i++) {
					String cols_name = metaData.getColumnName(i + 1);
					Object cols_value = rs.getObject(cols_name);
					if (cols_value == null) {
						cols_value = "";
					}
					Field field = cls.getDeclaredField(cols_name);
					field.setAccessible(true);//
					field.set(resultObject, cols_value);
				}
			}
			return resultObject;
		}
		/**
		 * 
		 * 
		 * @param <T>
		 * @param sql
		 * @param params
		 * @param cls
		 * @return
		 * @throws Exception
		 */
		public <T> List<T> findMoreRefResult(String sql, List<Object> params,
				Class<T> cls) throws Exception {
			List<T> list = new ArrayList<T>();
			int index = 1;
			pstmt = conn.prepareStatement(sql);
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					pstmt.setObject(index++, params.get(i));
				}
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int cols_len = metaData.getColumnCount();
			while (rs.next()) {
				T resultObject = cls.newInstance();
				for (int i = 0; i < cols_len; i++) {
					String cols_name = metaData.getColumnName(i + 1);
					Object cols_value = rs.getObject(cols_name);
					if (cols_value == null) {
						cols_value = "";
					}
					Field field = cls.getDeclaredField(cols_name);
					field.setAccessible(true);
					field.set(resultObject, cols_value);
				}
				list.add(resultObject);
			}
			return list;
		}
	public void releaseConn() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
