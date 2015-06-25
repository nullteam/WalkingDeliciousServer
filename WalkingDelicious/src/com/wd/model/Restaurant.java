package com.wd.model;

public class Restaurant {	
	
	private String 		id;
	private String		restaurantName;
	private String 		restaurantAddress;
	private String		restaurantPhone;
	private String		imgUrl;
	private double		price;
	
	public Restaurant() {
	}
	
	public Restaurant(String id, String restaurantName,
			String restaurantAddress, String restaurantPhone, String imgUrl,
			double price) {
		this.id = id;
		this.restaurantName = restaurantName;
		this.restaurantAddress = restaurantAddress;
		this.restaurantPhone = restaurantPhone;
		this.imgUrl = imgUrl;
		this.price = price;
	}



	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
