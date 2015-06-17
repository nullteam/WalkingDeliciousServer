package com.wd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.wd.dao.UserDao;
import com.wd.dao.UserDao.RegisterState;
import com.wd.model.User;

public class UserRegisterServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3971073144489613768L;
	
	//表示客户端传递参数的时候用的key的名称
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
		if (req.getParameter(USERNAME_CLIENT)==null||req.getParameter(PASSWORD_CLIENT)==null) {
			return;
		}
		User user = new User(req.getParameter(USERNAME_CLIENT), req.getParameter(PASSWORD_CLIENT));
		RegisterState state = null;
		UserDao dao  = new UserDao();
		state = dao.registerUser(user);
		JSONObject jsonObject = new JSONObject();
		switch (state) {
		case REGISTER_SUCCESS:
			jsonObject.put("registerResult", "0");//0代表注册成功
			break;
		case ILLEGAL:
			jsonObject.put("registerResult", "1");//1代表用户名或者密码不合法
			break;
		case USER_EXISTED:
			jsonObject.put("registerResult", "2");//2代表用户已经存在
			break;
		default:
			break;
		}
		PrintWriter out = resp.getWriter();
		resp.setCharacterEncoding("utf-8");	
		out.print(jsonObject.toString());
		out.flush();
		out.close();
	}
	public UserRegisterServlet() {
	}
}
