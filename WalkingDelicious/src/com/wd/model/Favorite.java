package com.wd.model;

public class Favorite {

	private Integer id;
	private User user;
	private Restaurant restaurant;

	public Favorite(Integer id,User user,Restaurant restaurant){
		this.id=id;
		this.user=user;
		this.restaurant=restaurant;
	}
	
	public Favorite(Integer id,Integer userId,String restaurantId){
		this.id=id;
		this.user=new User(userId);
		this.restaurant = new Restaurant(restaurantId);
	}
	
	public Favorite(User user,Restaurant restaurant){
		this.user=user;
		this.restaurant=restaurant;
	}
	
	public Favorite(Integer userId,String restaurantId){
		this.user=new User(userId);
		this.restaurant = new Restaurant(restaurantId);
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
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

}
