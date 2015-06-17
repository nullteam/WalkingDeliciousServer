package com.wd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.wd.dao.UserDao;
import com.wd.dao.UserDao.LoginState;
import com.wd.model.User;

public class UserLoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9010580618131806737L;
	private final String USERNAME_CLIENT = "username";
	private final String PASSWORD_CLIENT = "password";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String usernameString = req.getParameter(USERNAME_CLIENT);
		String passwordString = req.getParameter(PASSWORD_CLIENT);
		
		User user = new User(usernameString,passwordString);
		UserDao dao  = new UserDao();
		LoginState state  = dao.userVerify(user);
		JSONObject jsonObject = new JSONObject();
		switch (state) {
		case LOGIN_SUCCESS:
			jsonObject.put("loginResult", "0");//0代表登陆成功
			break;
		case USER_NOT_EXIT:
			jsonObject.put("loginResult", "1");//1代表用户不存在
			break;
		case PASSWORD_ERROR:
			jsonObject.put("loginResult", "2");//2代表用户输入的密码错误
			break;
		case ILLEGAL:
			jsonObject.put("loginResult", "3");//3代表用户名或密码不合法
			break;
			//可以添加一个服务器错误
		default:
			break;
		}
		PrintWriter out = resp.getWriter();
		resp.setCharacterEncoding("utf-8");	
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}

	public UserLoginServlet() {
	}

}
