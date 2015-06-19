package com.wd.model;

import java.sql.Timestamp;

public class Order {
	private Integer id;
	private User user;
	private Restaurant restaurant;
	private Integer orderNum;
	private Timestamp orderTime;

	public Order() {
	}
	
	public Order(Integer id,Integer userId,String restaurantId,Integer num){
		this.id = id;
		this.user =new User(userId,"","");
		this.restaurant = new Restaurant(restaurantId,"","","");
		this.orderNum=num;	
	}
	public Order(Integer id,Integer userId,String restaurantId,Integer num,Timestamp time){
		this.id = id;
		this.user =new User(userId,"","");
		this.restaurant = new Restaurant(restaurantId,"","","");
		this.orderNum=num;
		this.orderTime = time;
	}
	
	public Order(Integer id,Integer userId,String restaurantId,Integer num,String time){
		this.id = id;
		this.user =new User(userId,"","");
		this.restaurant = new Restaurant(restaurantId,"","","");
		this.orderNum=num;
		this.orderTime = new Timestamp(Integer.parseInt(time));
	}
	
	public Order(Integer userId,String restaurantId,Integer num){
		this.user =new User(userId,"","");
		this.restaurant = new Restaurant(restaurantId,"","","");
		this.orderNum=num;
	}
	
	public Order(Integer userId,String restaurantId,Integer num,Timestamp time){
		this.user =new User(userId,"","");
		this.restaurant = new Restaurant(restaurantId,"","","");
		this.orderNum=num;
		this.orderTime = time;
	}
	
	public Order(Integer userId,String restaurantId,Integer num,String time){
		this.user =new User(userId,"","");
		this.restaurant = new Restaurant(restaurantId,"","","");
		this.orderNum=num;
		this.orderTime = new Timestamp(Integer.parseInt(time));
	}
	
	public Order(Integer id,User user,Restaurant restaurant,Integer num,Timestamp time){
		this.id = id;
		this.user = user;
		this.restaurant=restaurant;
		this.orderNum=num;
		this.orderTime=time;
	}

	public Order(Integer userId,Restaurant restaurant,Integer num){
		this.user = new User(userId,"","");
		this.restaurant = restaurant;
		this.orderNum =num;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}



	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}



	/**
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}



	/**
	 * @param restaurant the restaurant to set
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}



	/**
	 * @return the orderNum
	 */
	public Integer getOrderNum() {
		return orderNum;
	}



	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	/**
	 * @return the orderTime
	 */
	public Timestamp getOrderTime() {
		return orderTime;
	}

	/**
	 * @param orderTime the orderTime to set
	 */
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getUserId() {
		return this.user.getId();
	}
	
	public String getRestaurantId() {
		return this.restaurant.getId();
	}

}
