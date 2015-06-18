/**
 * 
 */
package com.wd.model;

/**
 * @author Incredible
 *
 */
public class User {
	
	private Integer id;
	private String username;
	private String password;
	/**
	 * 
	 */
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String argUername,String argPassword){
		this.username = argUername;
		this.password = argPassword;
	}

	public User(Integer argId,String argUername,String argPassword){
		setId(argId);
		this.username = argUername;
		this.password = argPassword;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
