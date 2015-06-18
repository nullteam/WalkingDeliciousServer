package com.wd.model;

public class Restaurant {	
	
	private Integer 	id;
	private String		restaurantName;
	private String 		restaurantAddress;
	private String		restaurantPhone;
	public Restaurant() {
	}
	public Restaurant(String argName){
		setRestaurantName(argName);
	}
	
	public Restaurant(Integer argId,String argName,String address,String phone){
		setId(argId);
		setRestaurantName(argName);
		setRestaurantAddress(address);
		setRestaurantPhone(phone);
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	private void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the restaurantNameString
	 */
	public String getRestaurantName() {
		return restaurantName;
	}
	/**
	 * @param restaurantNameString the restaurantNameString to set
	 */
	public void setRestaurantName(String restaurantNameString) {
		this.restaurantName = restaurantNameString;
	}
	/**
	 * @return the restaurantAddress
	 */
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	/**
	 * @param restaurantAddress the restaurantAddress to set
	 */
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
	}
	/**
	 * @return the restaurantPhone
	 */
	public String getRestaurantPhone() {
		return restaurantPhone;
	}
	/**
	 * @param restaurantPhone the restaurantPhone to set
	 */
	public void setRestaurantPhone(String restaurantPhone) {
		this.restaurantPhone = restaurantPhone;
	}

}
