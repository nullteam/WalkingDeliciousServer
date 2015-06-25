package com.wd.model;

public class Favorite {
	
	private Integer id;
	private String user_id;
	private String restaurant_id;

	

	public Favorite( String user_id, String restaurant_id) {
		this.user_id = user_id;
		this.restaurant_id = restaurant_id;
	}

	

	public String getUser_id() {
		return user_id;
	}



	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



	public String getRestaurant_id() {
		return restaurant_id;
	}



	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

}
