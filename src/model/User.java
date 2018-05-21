package model;

import java.io.Serializable;

public abstract class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6717302967141036965L;
	private String name;
	private String userName;
	private String userType;
	
	public User(String userName, String name, String userType) {
		this.name = name;
		this.userName = userName;
		this.userType = userType;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return userName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUserType() {
		return userType;
	}

}
