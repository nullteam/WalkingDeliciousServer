package com.wd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	private final String ORDER_NUM_TABLE = "order_num";
	private final String ORDER_TIME_TABLE = "order_time";
	//CRUD常用操作的SQL语句模板
	private final String ORDER_ADD_STRING	 = "INSERT INTO "+ORDER_TABLE_NAME+" VALUES(NULL,?,?,?,NULL)";
	private final String ORDER_DELETE_STRING = "DELETE * FROM "+ORDER_TABLE_NAME+" WHERE 1=1";
	//private final String ORDER_UPDATE_STRING  ="UPDATE "+ORDER_TABLE_NAME+" SET "+USER_ID_TABLE+"=?,"+RESTAURANT_ID_TABLE+"=? WHERE "+ID_TABLE+"=?";
	private final String ORDER_SELECT_STRING ="SELECT * FROM "+ORDER_TABLE_NAME+" WHERE 1=1";
	public OrderDao() {
	}
	//select order
	public Order getOrderById(Integer value){
		Order retOrder =null;
		if (value==null) {
			return retOrder;
		}
		List<Order> orders= getOrderByEachId(ID_TABLE, value);
		if (orders.isEmpty()) {
			return retOrder;
		}
		retOrder = orders.get(0);
		return retOrder;
	}

	public List<Order> getOrderByUserId(Integer value){
		if(value==null) return new ArrayList<Order>();
		return getOrderByEachId(USER_ID_TABLE, value);
	}
	
	public List<Order> getOrderByUser(User user){
		return getOrderByUserId(user.getId());
	}
		
	//delete order
	
	public Boolean deleteOrderById(Integer value){
		return deleteOrderByEachId(ID_TABLE, value);
	}
	
	public Boolean deleteOrder(Order order){
		if(order==null) return new Boolean(false);
		return deleteOrderById(order.getId());
	}
	
	public Boolean deleteOrderByUserId(Integer value){
		return deleteOrderByEachId(USER_ID_TABLE, value);
	}
	
	public Boolean deleteOrderByUser(User user) {
		if(user==null)	return  new Boolean(false);
		return deleteOrderByUserId(user.getId());
	}
	
	
	//add order
	public Boolean addOrder(Order order){
		Boolean flag = false;
		if(order==null) return flag;
		try {
			RestaurantDao dao = new RestaurantDao();
			Restaurant restaurant = dao.getRestaurant(order.getRestaurant());
			if(restaurant==null) dao.addRestaurant(order.getRestaurant());
			PreparedStatement ps =DBUtil.getInstance().getConnection().prepareStatement(ORDER_ADD_STRING);
			ps.setInt(1, order.getUserId());
			ps.setString(2, order.getRestaurantId());
			ps.setInt(3, order.getOrderNum());
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
	
	
	private List<Order> getOrderByEachId(String field,Integer value){
		List<Order> retOrder = new ArrayList<Order>();
		if(value==null) return retOrder;
		try {
			SqlAssembling assembling = new SqlAssembling(ORDER_SELECT_STRING);
			assembling.addRestriction(field, value);
			PreparedStatement ps = DBUtil.getInstance().getConnection().prepareStatement(assembling.toString());
			ResultSet set =ps.executeQuery();
			while(set.next()){
				Integer userInteger = set.getInt(USER_ID_TABLE);
				String restaurantInteger = set.getString(RESTAURANT_ID_TABLE);
				UserDao dao =new UserDao();
				User user = dao.getUserById(userInteger);
				RestaurantDao restaurantDao = new RestaurantDao();
				Restaurant restaurant=restaurantDao.getRestaurantById(restaurantInteger);
				retOrder.add(new Order(set.getInt(ID_TABLE),user,restaurant,set.getInt(ORDER_NUM_TABLE),set.getTimestamp(ORDER_TIME_TABLE)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}		
		return retOrder;
	}

}
