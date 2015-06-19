package com.wd.model;

public class Restaurant {	
	
	private String 		id;
	private String		restaurantName;
	private String 		restaurantAddress;
	private String		restaurantPhone;
	public Restaurant() {
	}
	public Restaurant(String id){
		this.id=id;
		setRestaurantName("");
		setRestaurantAddress("");
		setRestaurantPhone("");
	}

	
	public Restaurant(String argId,String argName,String address,String phone){
		setId(argId);
		setRestaurantName(argName);
		setRestaurantAddress(address);
		setRestaurantPhone(phone);
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	private void setId(String id) {
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
