package model;

import java.util.ArrayList;

public class AuctionCentral {

	
	
	//private Map<NonProfit, ArrayList<Auction>> myOrganization;
	//Username / name of the person
	private ArrayList<NonProfit> nonProfits;
	
	private static final int MAX_NUM_UPCOMING_AUCTIONS = 25; 
	
	//Decrement once an auction finishes.. ? 
	private int numCurrentAuctions;
	
	public AuctionCentral() {
		numCurrentAuctions = 0;
	}
	
	public void addNonprofit() {
		//If the nonprofit is not in the system yet
		//add a new key value and the name of the auction if any
		// "None" if not auction is booked yet
	}
	
	public void addAuction() {
		
	}
}
