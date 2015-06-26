package com.wd.model;

public class Order {
	private Integer id;
	private String userId;
	private String restaurantId;
	private String restaurantName;
	private String restaurantAddress;
	private String restaurantPhone;
	private Integer orderNum;
//	private Timestamp orderTime;
	private String orderTime;
	
	public Order(String userId, String restaurantId, String restaurantName,
			String restaurantAddress, String restaurantPhone, Integer orderNum) {
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.restaurantPhone = restaurantPhone;
		this.orderNum = orderNum;
	}
	
	public Order(String userId, String restaurantId, String restaurantName,
			String restaurantAddress, String restaurantPhone, Integer orderNum,
			String orderTime) {
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.restaurantPhone = restaurantPhone;
		this.orderNum = orderNum;
		this.orderTime = orderTime;
	}
	
	public Order(Integer id,String userId, String restaurantId, String restaurantName,
			String restaurantAddress, String restaurantPhone, Integer orderNum,
			String orderTime) {
		this.id = id;
		this.userId = userId;
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.restaurantPhone = restaurantPhone;
		this.orderNum = orderNum;
		this.orderTime = orderTime;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	public String getRestaurantPhone() {
		return restaurantPhone;
	}
	public void setRestaurantPhone(String restaurantPhone) {
		this.restaurantPhone = restaurantPhone;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
}
