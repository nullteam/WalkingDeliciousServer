package com.wd.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wd.dao.FavoriteDao;
import com.wd.model.Restaurant;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProcessFavorite extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1478992370574610776L;

	/**
	 * Constructor of the object.
	 */
	public ProcessFavorite() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		if (request.getParameter("code") == null)
			return;
		Integer code = Integer.parseInt(request.getParameter("code"));
		JSONObject jsonObject = new JSONObject();
		switch (code) {
		case 1:
			Boolean flag = addFavorite(request.getParameter("userId"),
					request.getParameter("restaurantId"),
					request.getParameter("restaurantName"),
					request.getParameter("restaurantAddress"),
					request.getParameter("restaurantPhone"),
					request.getParameter("imgUrl"),
					request.getParameter("price")
					);
			if (flag)
				jsonObject.put("result", "1");
			else {
				jsonObject.put("result", "0");
			}
			break;
		case 2:
			Boolean flagBoolean = deleteFavorite(
					request.getParameter("userName"),
					request.getParameter("restaurantId"));
			if (flagBoolean)
				jsonObject.put("result", "1");
			else {
				jsonObject.put("result", "0");
			}
			break;
		case 3:
			String userName = request.getParameter("userId");
			String restaurantId = request.getParameter("restaurantId");
			int result = queryIsLiked(userName, restaurantId);
			jsonObject.put("result", result);
			break;
		case 4:
			JSONArray ja =  JSONArray.fromObject(queryFavorites(request
					.getParameter("userId")));
			jsonObject.put("result", ja);
			break;
		default:
			break;
		}

		PrintWriter out = response.getWriter();
		
		out.println(jsonObject.toString());
		out.flush();
		out.close();
	}

	private int queryIsLiked(String userName, String restaurantId) {
		int result = new FavoriteDao().queryIsLiked(userName, restaurantId);
		if (result > 0) {
			return 1;
		}
		return result;
	}

	public List<JSONObject> queryFavorites(String value) {
		if (value == null) {
			return new ArrayList<JSONObject>();
		}
		return new FavoriteDao().getFavoritesByUserId(value);
	}

	public Boolean deleteFavorite(String userName, String restaurantId) {
		if (userName == null)
			return new Boolean(false);
		return new FavoriteDao().deleteFavoriteById(userName, restaurantId);
	}

	public Boolean addFavorite(String userId, String restaurantId,
			String restaurantName, String restauRantAddess,
			String restaurantPhone,String url,String price) {
		if (userId == null || restaurantId == null || restauRantAddess == null
				|| restaurantName == null) {
			return false;
		}
		if(restaurantPhone==null)
			restaurantPhone="unKnown";
		if(url==null) url="";
		if(price==null) price ="0.00";
		// User user =new User(Integer.parseInt(userId));
		Restaurant restaurant = new Restaurant(restaurantId, restaurantName,
				restauRantAddess, restaurantPhone,url,Double.parseDouble(price));
		FavoriteDao dao = new FavoriteDao();
		return dao.addFavorite(restaurant, userId);
	}
}
