package model;

import java.io.Serializable;

public class AuctionCentralEmployee extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8888972440798471246L;
	
	public AuctionCentralEmployee(String theUserName, String theName) {
		super(theUserName, theName, "employee");
	}
	
	public void updateAuctionLimit(AuctionCentral ac, int max) {
		ac.updateAuctionLimit(max);
	}
	

}
