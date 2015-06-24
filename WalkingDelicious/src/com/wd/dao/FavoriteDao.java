package com.wd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.wd.model.Favorite;
import com.wd.model.Restaurant;
import com.wd.model.User;
import com.wd.util.DBUtil;
import com.wd.util.SqlAssembling;



public class FavoriteDao {
	private final String FAVORITE_TABLE_NAME = "t_favorite";
	
	private final String ID_TABLE = "id";
	private final String USER_ID_TABLE = "user_id";
	private final String RESTAURANT_ID_TABLE = "restaurant_id";
	private final String FAVORITE_ADD_STRRING = "INSERT INTO "+FAVORITE_TABLE_NAME+" VALUES(NULL,?,?)";
	private final String FAVORITE_DELETE_STRING = "DELETE FROM "+FAVORITE_TABLE_NAME+" WHERE "+USER_ID_TABLE+"=? AND "+RESTAURANT_ID_TABLE+"=?";
	private final String FAVORITE_SELECT_STRING = "SELECT * FROM "+FAVORITE_TABLE_NAME+" WHERE 1=1";
	
	
	

	public FavoriteDao() {
	}
	
	private List<Favorite> getFavoritesByEachId(String field,Integer value){
		List<Favorite> ret= new ArrayList<Favorite>();
		if(value==null) return ret;
		try {
			SqlAssembling assembling = new SqlAssembling(FAVORITE_SELECT_STRING);
			assembling.addRestriction(field,value);
			ResultSet set = DBUtil.getInstance().getConnection().prepareStatement(assembling.toString()).executeQuery();
			while (set.next()) {
				Integer userIdInteger = set.getInt(USER_ID_TABLE);
				UserDao userDao = new UserDao();
				User user = userDao.getUserById(userIdInteger);			
				String restaurantIdString = set.getString(RESTAURANT_ID_TABLE);
				RestaurantDao restaurantDao = new RestaurantDao();
				Restaurant restaurant  = restaurantDao.getRestaurantById(restaurantIdString);
				ret.add(new Favorite(set.getInt(ID_TABLE), user, restaurant));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		return ret;
	}
	
	public List<Favorite> getFavoritesByUserId(Integer userId){
		return getFavoritesByEachId(USER_ID_TABLE, userId);
	}
	
	public List<Favorite> getFavoritesById(Integer value){
		return getFavoritesByEachId(ID_TABLE, value);
	}
	
	public Boolean deleteFavoriteById(String userName,String restaurantId){
		Boolean flag = false;
		if(userName==null||restaurantId==null) return flag;
		try {
			 PreparedStatement ps = DBUtil.getInstance().getConnection().prepareStatement(FAVORITE_DELETE_STRING);
			 ps.setString(1, userName);
			 ps.setString(2, restaurantId);
			int result=-1;
			result = ps.executeUpdate();
			flag = result>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		
		return flag;
	}
	
	public Boolean addFavorite(Favorite value){
		Boolean flag = false;
		if(value==null) return flag;
		try {
			
			RestaurantDao dao = new RestaurantDao();
			if(dao.getRestaurant(value.getRestaurant())==null) dao.addRestaurant(value.getRestaurant());
			PreparedStatement ps = DBUtil.getInstance().getConnection().prepareStatement(FAVORITE_ADD_STRRING);
			ps.setInt(1, value.getUser().getId());
			ps.setString(2, value.getRestaurant().getId());
			
			int result= -1;
			result  = ps.executeUpdate();
			flag = result>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		
		return flag;
	}

}
