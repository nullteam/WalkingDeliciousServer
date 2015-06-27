package com.wd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.wd.model.Order;
import com.wd.model.Restaurant;
import com.wd.model.User;
import com.wd.util.DBUtil;
import com.wd.util.SqlAssembling;



public class OrderDao {
	//order实体在数据库中的表名
	private final String ORDER_TABLE_NAME="t_order";
	//表中字段的名字
	private final String ID_TABLE="id";
	private final String USER_ID_TABLE = "user_id";
	private final String RESTAURANT_ID_TABLE ="restaurant_id";
	//private final String ORDER_NUM_TABLE = "order_num";
	//private final String ORDER_TIME_TABLE = "order_time";
	//CRUD常用操作的SQL语句模板
	private final String ORDER_ADD_STRING	 = "INSERT INTO "+ORDER_TABLE_NAME+" VALUES(NULL,?,?,?,NULL)";
	private final String ORDER_DELETE_STRING = "DELETE FROM "+ORDER_TABLE_NAME+" WHERE 1=1";
	//private final String ORDER_UPDATE_STRING  ="UPDATE "+ORDER_TABLE_NAME+" SET "+USER_ID_TABLE+"=?,"+RESTAURANT_ID_TABLE+"=? WHERE "+ID_TABLE+"=?";
	private final String ORDER_SELECT_STRING ="SELECT * FROM "+ORDER_TABLE_NAME+" WHERE 1=1";
	public OrderDao() {
	}
	//select order
//	public Order getOrderById(Integer value){
//		Order retOrder =null;
//		if (value==null) {
//			return retOrder;
//		}
//		List<Order> orders= getOrderByEachId(ID_TABLE, value);
//		if (orders.isEmpty()) {
//			return retOrder;
//		}
//		retOrder = orders.get(0);
//		return retOrder;
//	}
//
//	public List<Order> getOrderByUserId(Integer value){
//		if(value==null) return new ArrayList<Order>();
//		return getOrderByEachId(USER_ID_TABLE, value);
//	}
	
	public List<Order> getOrderByUserId(String userId){
		if(userId==null) return new ArrayList<Order>();
		return getOrderByEachId(USER_ID_TABLE, userId);
	}
	
//	public List<Order> getOrderByUser(User user){
//		return getOrderByUserId(user.getId());
//	}
		
	//delete order
	
	public Boolean deleteOrderById(Integer value){
		return deleteOrderByEachId(ID_TABLE, value);
	}
	
//	public Boolean deleteOrder(Order order){
//		if(order==null) return new Boolean(false);
//		return deleteOrderById(order.getId());
//	}
	
	public Boolean deleteOrderByUserId(Integer value){
		return deleteOrderByEachId(USER_ID_TABLE, value);
	}
	
	public Boolean deleteOrderByUser(User user) {
		if(user==null)	return  new Boolean(false);
		return deleteOrderByUserId(user.getId());
	}
	
	
	//add order
	public Boolean addOrder(Restaurant restaurant,Order order){
		Boolean flag = false;
		if(order==null) return flag;
		try {
			RestaurantDao dao = new RestaurantDao();
			Restaurant restaurantTmp = dao.getRestaurant(restaurant);
			if(restaurantTmp==null) if(!dao.addRestaurant(restaurant)) return flag;
			PreparedStatement ps =DBUtil.getInstance().getConnection().prepareStatement(ORDER_ADD_STRING);
			ps.setString(1, order.getUserId());
			ps.setString(2,order.getRestaurantId());
			ps.setInt(3,order.getOrderNum());
			int result = -1; 
			result = ps.executeUpdate();
			flag = result>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		return flag;
	}
	
	
	private Boolean deleteOrderByEachId(String field, Integer value) {
		Boolean flag= false;
		if(value==null) return flag;		
		try {
			SqlAssembling assembling = new SqlAssembling(ORDER_DELETE_STRING);
			assembling.addRestriction(field, value);
			PreparedStatement ps = DBUtil.getInstance().getConnection().prepareStatement(assembling.toString());
			int result = -1;
			result  = ps.executeUpdate();
			flag = result>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		return flag;
	}
	
	
	private List<Order> getOrderByEachId(String field,String value){
		List<Order> retOrder = new ArrayList<Order>();
		if(value==null) return retOrder;
		try {
			SqlAssembling assembling = new SqlAssembling(ORDER_SELECT_STRING);
			assembling.addRestriction(field, value);
			PreparedStatement ps = DBUtil.getInstance().getConnection().prepareStatement(assembling.toString());
			ResultSet set =ps.executeQuery();
			while(set.next()){
				String userInteger = set.getString(USER_ID_TABLE);
				String restaurantInteger = set.getString(RESTAURANT_ID_TABLE);
				UserDao dao =new UserDao();
				User user = dao.getUserByName(userInteger);
				RestaurantDao restaurantDao = new RestaurantDao();
				Restaurant restaurant=restaurantDao.getRestaurantById(restaurantInteger);
				retOrder.add(new Order(set.getInt(ID_TABLE),user.getUsername(), restaurant.getId(), restaurant.getRestaurantName(), restaurant.getRestaurantAddress(), 
						restaurant.getRestaurantPhone(),set.getInt("order_num"),set.getTimestamp("order_time").toString()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}		
		return retOrder;
	}
	
	public List<JSONObject> getCompleteOrderByUserId(String value) {
		List<JSONObject> jsonArray = new ArrayList<JSONObject>();
		if (value == null)
			return jsonArray;		
		try {
			String sqlString = "SELECT t_order.id AS order_id ,t_order.order_num, t_order.order_time,t_order.restaurant_id,t_restaurant.restaurant_name, t_restaurant.restaurant_address ,t_restaurant.restaurant_phone ,t_restaurant.img_url ,t_restaurant.price FROM t_order ,t_restaurant WHERE t_order.restaurant_id = t_restaurant.id";
			PreparedStatement ps = DBUtil.getInstance().getConnection().prepareStatement(sqlString);
			ResultSet set = ps.executeQuery();
			JSONObject jsonObject;
			while(set.next()){
				jsonObject = new JSONObject();
				jsonObject.put("orderId", set.getInt("order_id"));
				jsonObject.put("restaurantId", set.getString("restaurant_id"));
				jsonObject.put("orderNum", set.getInt("order_num"));
				jsonObject.put("restaurantName", set.getString("restaurant_name"));
				jsonObject.put("restaurantPhone", set.getString("restaurant_phone"));
				jsonObject.put("restaurantAddress", set.getString("restaurant_address"));
				jsonObject.put("imgUrl", set.getString("img_url")==null?"":set.getString("img_url"));
				jsonObject.put("price", set.getDouble("price"));
				jsonObject.put("orderTime", set.getTimestamp("order_time").getTime());
				jsonArray.add(jsonObject);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}	
		return jsonArray;
	}

}
