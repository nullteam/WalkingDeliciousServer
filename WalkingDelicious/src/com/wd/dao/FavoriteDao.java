package com.wd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.wd.model.Restaurant;
import com.wd.util.DBUtil;
import com.wd.util.SqlAssembling;

public class FavoriteDao {
	private final String FAVORITE_TABLE_NAME = "t_favorite";

	//private final String ID_TABLE = "id";
	private final String USER_ID_TABLE = "user_id";
	private final String RESTAURANT_ID_TABLE = "restaurant_id";
	private final String FAVORITE_ADD_STRRING = "INSERT INTO "
			+ FAVORITE_TABLE_NAME + "(" + USER_ID_TABLE + ","
			+ RESTAURANT_ID_TABLE + ")" + " VALUES(?,?)";
	private final String FAVORITE_DELETE_STRING = "DELETE FROM "
			+ FAVORITE_TABLE_NAME + " WHERE " + USER_ID_TABLE + "=? AND "
			+ RESTAURANT_ID_TABLE + "=?";
	private final String FAVORITE_SELECT_STRING = "SELECT * FROM "
			+ FAVORITE_TABLE_NAME + " WHERE 1=1";
	private final String SQL_QUERY_IS_LIKED = "SELECT * FROM "
			+ FAVORITE_TABLE_NAME + " WHERE 1=1 " + "AND " + "1=1";

	public FavoriteDao() {
	}

	private List<JSONObject> getFavoritesByEachId(String field, String value) {
		List<JSONObject> jsonArray = new ArrayList<JSONObject>();
		//String userId = value;
		if (value == null)
			return jsonArray;
		try {
			SqlAssembling assembling = new SqlAssembling(FAVORITE_SELECT_STRING);
			assembling.addRestriction(field, value);
			ResultSet set = DBUtil.getInstance().getConnection()
					.prepareStatement(assembling.toString()).executeQuery();
			JSONObject js;
			while (set.next()) {
				js = new JSONObject();
				String restaurantIdString = set.getString(RESTAURANT_ID_TABLE);
				RestaurantDao restaurantDao = new RestaurantDao();
				Restaurant restaurant = restaurantDao
						.getRestaurantById(restaurantIdString);
				js.put("id", restaurant.getId());
				js.put("restaurantName", restaurant.getRestaurantName());
				js.put("restaurantAddress", restaurant.getRestaurantAddress());
				js.put("restaurantPhone", restaurant.getRestaurantPhone());
				js.put("imgUrl", restaurant.getImgUrl());
				js.put("price", restaurant.getPrice());
				jsonArray.add(js);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		return jsonArray;
	}

	public List<JSONObject> getFavoritesByUserId(String userId) {
		return getFavoritesByEachId(USER_ID_TABLE, userId);
	}


	public Boolean deleteFavoriteById(String userName, String restaurantId) {
		Boolean flag = false;
		if (userName == null || restaurantId == null)
			return flag;
		try {
			PreparedStatement ps = DBUtil.getInstance().getConnection()
					.prepareStatement(FAVORITE_DELETE_STRING);
			ps.setString(1, userName);
			ps.setString(2, restaurantId);
			int result = -1;
			result = ps.executeUpdate();
			flag = result > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}

		return flag;
	}

	public Boolean addFavorite(Restaurant restaurant, String userId) {
		Boolean flag = false;
		if (restaurant == null || userId == null)
			return flag;
		try {

			RestaurantDao dao = new RestaurantDao();
			if (dao.getRestaurant(restaurant) == null)
				dao.addRestaurant(restaurant);
			PreparedStatement ps = DBUtil.getInstance().getConnection()
					.prepareStatement(FAVORITE_ADD_STRRING);
			ps.setString(1, userId);
			ps.setString(2, restaurant.getId());

			int result = -1;
			if (queryIsLiked(userId, restaurant.getId()) == 1) {
				result = -1;
			} else {
				result = ps.executeUpdate();
			}
			flag = result > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}

		return flag;
	}

	public int queryIsLiked(String userName, String restaurantId) {
		int result = -1;
		if (userName == null || restaurantId == null)
			return result;
		try {
			SqlAssembling assembling = new SqlAssembling(SQL_QUERY_IS_LIKED);
			assembling.addRestriction(USER_ID_TABLE, userName);
			assembling.addRestriction(RESTAURANT_ID_TABLE, restaurantId);
			ResultSet set = DBUtil.getInstance().getConnection()
					.prepareStatement(assembling.toString()).executeQuery();
			if (set.next()) {
				result = 1;
			} else {
				result = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		return result;
	}

}
