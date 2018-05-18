package model;


public abstract class User {
	
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
