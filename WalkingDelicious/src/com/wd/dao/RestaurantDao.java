package com.wd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.wd.model.Restaurant;
import com.wd.util.DBUtil;

public class RestaurantDao {	
	//restaurant实体在数据库中的表名
	private final String RESTAURANT_TABLE_NAME = "t_restaurant";
	//表中字段的名字
	private final String ID_TABEL = "id";
	private final String RESTAURANT_NAME_TABLE = "restaurant_name";
	private final String RESTAURANT_ADDRESS_TABLE="restaurant_address";
	private final String RESTAURANT_PHONE_TABLE = "restaurant_phone";
	//CRUD常用操作的SQL语句模板
	private final String RESTAURANT_ADD_STRING = "INSERT INTO "+RESTAURANT_TABLE_NAME+" VALUES(?,?,?,?)";
	private final String RESTAURANT_DELETE_STRING = "DELETE * FROM "+RESTAURANT_TABLE_NAME+" WHERE "+ID_TABEL+"=?";
	private final String RESTAURANT_UPDATE_STRING = "UPDATE "+RESTAURANT_TABLE_NAME+" SET "+
														RESTAURANT_NAME_TABLE+"=?,"+
														RESTAURANT_ADDRESS_TABLE+"=?,"+
														RESTAURANT_PHONE_TABLE+"=? WHERE "+ID_TABEL+"=?";
	private final String RESTAURANT_SELECT_STRING = "SELECT * FROM "+RESTAURANT_TABLE_NAME+" WHERE "+ID_TABEL+"=?";

	public RestaurantDao() {
	}

	//select restaurant
	public Restaurant getRestaurantById(Integer argId){
		if (argId==null) {
			return null;
		}
		Restaurant ret =null;
		try {
			PreparedStatement ps = DBUtil.getInstance().getConnection().prepareStatement(RESTAURANT_SELECT_STRING);
			ps.setInt(1, argId);
			ResultSet set = ps.executeQuery();
			if(set.next()){
				ret = new Restaurant(set.getInt(ID_TABEL),set.getString(RESTAURANT_NAME_TABLE),set.getString(RESTAURANT_ADDRESS_TABLE),set.getString(RESTAURANT_PHONE_TABLE));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		return ret;
	}
	
	public Restaurant getRestaurant(Restaurant value) {
		return getRestaurantById(value.getId());
	}
	
	//add restaurant
	public Boolean addRestaurant(Restaurant value){
		Boolean flag=false;
		if(value==null) return flag;
		try {
			PreparedStatement ps = DBUtil.getInstance().getConnection().prepareStatement(RESTAURANT_ADD_STRING);
			ps.setInt(1, value.getId());
			ps.setString(2, value.getRestaurantName());
			ps.setString(3, value.getRestaurantAddress());
			ps.setString(4, value.getRestaurantPhone());
			int result  = -1;
			result = ps.executeUpdate();
			flag = result>0?true :false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		
		return flag;
	}
	
	//delete restaurant
	public Boolean deleteRestaurantById(Integer value){
		Boolean flag=false;
		if(value ==null )return flag;
		try {
			PreparedStatement ps  = DBUtil.getInstance().getConnection().prepareStatement(RESTAURANT_DELETE_STRING);
			int result =-1;
			ps.setInt(1, value);
			result = ps.executeUpdate();
			flag  = result>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		return flag;
	}
	
	public Boolean deleteRestaurant(Restaurant value){
		return deleteRestaurantById(value.getId());
	}
	
	//update restaurant
	public Boolean updateRestaurant(Restaurant value) {
		Boolean flag=false;
		if(value ==null) return flag;
		try {
			PreparedStatement ps = DBUtil.getInstance().getConnection().prepareStatement(RESTAURANT_UPDATE_STRING);
			ps.setString(1, value.getRestaurantName());
			ps.setString(2, value.getRestaurantAddress());
			ps.setString(3, value.getRestaurantPhone());
			int result=-1;
			result = ps.executeUpdate();
			flag = result>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}	
		return flag;
	}
}
