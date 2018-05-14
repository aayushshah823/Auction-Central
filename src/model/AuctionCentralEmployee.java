package model;

import java.io.Serializable;

public class AuctionCentralEmployee implements Serializable, User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8888972440798471246L;
	private static final String USER_TYPE = "employee";
	private String userName;
	private String name;
	
	@Override
	public void setUsername(String userName) {
		this.userName = userName;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getUserType() {
		return USER_TYPE;
	}
	
	public void updateAuctionLimit(AuctionCentral ac, int max) {
		ac.updateAuctionLimit(max);
	}
	

}
