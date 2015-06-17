package com.wd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wd.model.User;
import com.wd.util.DBUtil;

public class UserDao {
	//存储用户信息的表在数据库中的名称
	private final String USER_TABLE_NAME="t_user";
	//CRUD常用操作的SQL语句模板
	private final String USER_UPDATE_STRING = "UPDATE "+USER_TABLE_NAME+" SET  username=?,password=? WHERE username=?";
	private final String USER_ADD_STRING = "INSERT INTO "+USER_TABLE_NAME+" VALUES(NULL,?,?)";
	private final String USER_DELETE_STRING = "DELETE * FROM "+USER_TABLE_NAME+" WHERE username=? AND password=?";
	private final String USER_QUERY_STRING = "SELECT * FROM "+USER_TABLE_NAME+" WHERE username=?";
	//数据库的user表中用户名和密码两个字段的名称
	private final String USERNAME_TABLE = "username";
	private final String PASSWORD_TABLE = "password";
	//指示登录状态的枚举类型
	public enum LoginState{LOGIN_SUCCESS,USER_NOT_EXIT,PASSWORD_ERROR,ILLEGAL,NETWORK_ERROR}
	//指示注册注册状态的枚举类型
	public enum RegisterState{REGISTER_SUCCESS,ILLEGAL,USER_EXISTED};
	public UserDao() {
	}
	//添加用户
	public Boolean addUser(User user){
		Boolean flag=false;
		if (user==null) {
			return flag;
		}
		PreparedStatement ps=null;
		try {
			ps = DBUtil.getInstance().getConnection().prepareStatement(USER_ADD_STRING);
			ps.setString(1, user.getUsername()) ;
			ps.setString(2, user.getPassword());
			int result = -1;
			result = ps.executeUpdate();
			flag = result>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		return flag;
	}
	//查找用户
	public User findUser(User user){
		if(user==null) return null;
		User retUser = null;
		ResultSet ret = null;
		PreparedStatement ps =null;
		try {
			ps = DBUtil.getInstance().getConnection().prepareStatement(USER_QUERY_STRING);
			ps.setString(1, user.getUsername());
			ret  = ps.executeQuery();
			if(ret.next()){
				retUser = new User(ret.getString(USERNAME_TABLE), ret.getString(PASSWORD_TABLE));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		
		return retUser;
	}
	//删除用户
	public Boolean deleteUser(User user){
		Boolean flag=false;
		if(user==null) return false;
		PreparedStatement ps = null;
		try {
			ps = DBUtil.getInstance().getConnection().prepareStatement(USER_DELETE_STRING);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			int result = -1;
			result  =  ps.executeUpdate();
			flag = result>0?true:false;	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		return flag;
	}
	
	//更新用户信息
	public Boolean updateUser(User user){
		Boolean flag=false;
		if(user ==null) return false;
		
		PreparedStatement ps  =null;
		try {
			ps = DBUtil.getInstance().getConnection().prepareStatement(USER_UPDATE_STRING);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getUsername());
			int result = -1;
			result = ps.executeUpdate();
			flag = result>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}	
		return flag;
	}
	//验证用户的用户名和密码
	public LoginState userVerify(User user){
		if(!illegalVerifyUser(user)) return LoginState.ILLEGAL;
		LoginState state = LoginState.USER_NOT_EXIT;
		User tmpUser = this.findUser(user);
		if(tmpUser!=null) {
			if (!tmpUser.getPassword().equals(user.getPassword())) {
				state = LoginState.PASSWORD_ERROR;
			}else {
				state = LoginState.LOGIN_SUCCESS;
			}
		}
		return state;
	}
	//注册用户
	public RegisterState registerUser(User user){
		RegisterState state = RegisterState.USER_EXISTED;
		if (!illegalVerifyUser(user)) {
			return RegisterState.ILLEGAL;
		}
		if (findUser(user)!=null) {
			return RegisterState.USER_EXISTED;
		}
		if (addUser(user)) {
			state = RegisterState.REGISTER_SUCCESS;
		}
		return state;
				
	}
	//验证用户名和密码的合法性
	private Boolean illegalVerify(String arg){
		Boolean flag = true;
		//判断字符串的长度
		int strLength = arg.length();
		if(strLength<6||strLength>12) return false;
		
		//判断首字符是否是字母
		char firstChar = arg.charAt(0);
		if (!Character.isLetter(firstChar)) {
			return false;
		}
		//判断其余字符是否是字母或者是数字
		for (int i = 0; i < arg.length(); i++) {
			char tmpChar = arg.charAt(i);
			if(!Character.isLetter(tmpChar)&&!Character.isDigit(tmpChar)){
				flag = false;
				break;
			}
		}		
		return flag;
	}
	//验证user的用户名和密码是否是合法的
	private Boolean illegalVerifyUser(User user){
		return illegalVerify(user.getUsername())&&illegalVerify(user.getPassword());
	}
	
	
}
