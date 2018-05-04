package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NonProfit {
	

	private static int MAX_DAYS_AWAY_FOR_AUCTION = 60;
	private static int MIN_DAYS_AWAY_FOR_AUCTION = 14;
	private String myOrg;
	private String myName;
	private List<Auction> myAuctions;
	private LocalDate myLastAuctionDate;
	private int myCurrentAuction;
	
	public NonProfit(String theOrg, String theName) {
		myOrg = theOrg;
		myName = theName;
		myAuctions = new ArrayList<Auction>();
		myLastAuctionDate = null;
		myCurrentAuction = -1;
	}
	
	//Checks when the last auction was held
	//every time a nonprofit request for auction is accepted
	//the  lastAuctionDate must be updated 
	public boolean submitAuctionRequest(AuctionCentral auctions) {
//		if (myLastAuctionDate == null || myLastAuctionDate.getYear() - (new Date().getYear()) >= 1) {
//			myLastAuctionDate = new Date();
//			myCurrentAuction++;
//			return true;
//		}
		
		//can't be schedule more than 60 days from current day
		
		//Check all auction dates 
		return false;
	}
	
	public boolean isDateRangeValid() {
		
		//Checks for MAX_DAYS_AWAY_FOR_AUCTION
		//Checks for MIN_DAYS_AWAY_FOR_AUCTION 
		return true;
	}
	public void addItem(Item theItem) {
		if (myCurrentAuction < 0) {
			throw new NullPointerException();
		}
		myAuctions.get(myCurrentAuction).addItem(theItem);
	}
	
	
	//only works for current auction
//	public List<Item> getItemsInAuction() {
//		if (myCurrentAuction < 0) {
//			throw new NullPointerException();
//		}
//		//return myAuctions.get(myCurrentAuction).getItems();
//	}
	
	public String getOrg() {
		return myOrg;
	}
	
	public String getName() {
		return myName;
	}
	
	public LocalDate getLastAuctionDate() {
		return myLastAuctionDate;
	}
	
	public List<Auction> getAuctions() {
		return myAuctions;
	}

}
