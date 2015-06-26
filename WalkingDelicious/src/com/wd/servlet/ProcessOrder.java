package com.wd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wd.dao.OrderDao;
import com.wd.model.Order;
import com.wd.model.Restaurant;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProcessOrder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1007771447419598090L;

	/**
	 * Constructor of the object.
	 */
	public ProcessOrder() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		String codeString = request.getParameter("code");
		if(codeString==null) return ;
		Integer code = Integer.parseInt(codeString);
		JSONObject jsonObject = new JSONObject();
		switch (code) {
		case 1:
			 Boolean flagBoolean= addOrder(
					 request.getParameter("userId"),
					 request.getParameter("restaurantId"),
					 request.getParameter("orderNum"),
					 request.getParameter("restaurantName"),
					 request.getParameter("restaurantAddress"),
					 request.getParameter("restaurantPhone"),
					 request.getParameter("imgUrl"),
					 request.getParameter("price")
					 );
			 if(flagBoolean) jsonObject.put("result", "1");
			 else jsonObject.put("result", "0");
			break;
		case 2:
			Boolean flag= deleteOrder(
						request.getParameter("orderId")
					 );
			 if(flag) jsonObject.put("result", "1");
			 else jsonObject.put("result", "0");
			break;
		case 3:
			break;
		case 4:
			JSONArray ja = JSONArray.fromObject(queryOrder(request.getParameter("userId")));
			jsonObject.put("result", ja);
			break;
		default:
			break;
		}
		
		PrintWriter out = response.getWriter();
		response.setCharacterEncoding("utf-8");	
		out.println(jsonObject.toString());
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	public Boolean addOrder(String userId,String restaurantId,String orderNum,String name,String address,String phone,String url,String price) {
		boolean flag=false;
		if(
				userId==null||
				restaurantId==null||
				orderNum==null||
				name==null||
				address==null
		) {
			System.out.println("add order parameter has null");
			return flag;
			}
		
		if(phone==null) phone="unknown";
		if(url==null) url="";
		if(price==null) price="0.00";
		OrderDao dao = new OrderDao();
		Restaurant restaurant = new Restaurant(restaurantId, name, address, phone,url,Double.parseDouble(price));
		Order order  = new Order(userId, restaurantId, name, address, phone, Integer.parseInt(orderNum));
		flag=dao.addOrder(restaurant,order);
		return flag;
	}
	
	public Boolean deleteOrder(String orderId) {
		Boolean flag=false;
		if(orderId==null) return flag;
		flag = new OrderDao().deleteOrderById(Integer.parseInt(orderId));
		return flag;
	}
	
	public List<JSONObject> queryOrder(String userId) {
		if(userId==null) return new ArrayList<JSONObject>();
		return new OrderDao().getCompleteOrderByUserId(userId);
	}
}
