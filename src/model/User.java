package model;

import java.util.Map;

public class User {

	private Map<String, String> users;
	
	public void addUser(String theUserName, String theRealName) {
		users.put(theUserName, theRealName);
	}
	
	public String welcomeUser() {
		StringBuilder sb = new StringBuilder();
		//check for userName and 
		//Greet user
		
		return sb.toString();
	}

}
