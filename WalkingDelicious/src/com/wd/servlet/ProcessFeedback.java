package com.wd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;


import com.wd.dao.FeedbackDao;

public class ProcessFeedback extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6069685128010431480L;


	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String resultString = "0";
		if(request.getParameter("code")==null) return ;
		int code = Integer.parseInt(request.getParameter("code"));
		switch (code) {
		case 1:
			if(addFeedback(request.getParameter("userId"), request.getParameter("content"))) resultString = "1";
			else {
				resultString="0";
			}
			break;
		default:
			break;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", resultString);
		PrintWriter out = response.getWriter();
		out.println(jsonObject.toString());
		out.flush();
		out.close();
	}
	
	
	private Boolean addFeedback(String userid,String content){
		Boolean flag = false;
		if(userid==null||content==null){
			System.out.println("some parameter is null");
			return flag;
		}
		return new FeedbackDao().addFeedback(userid, content);
	}

}
