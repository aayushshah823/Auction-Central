package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bidder {
	
	/** Constant for minimum about of bid. */
	public static final int MIN_AMOUNT_BID_PER_ITEM = 500;
	
	public static final int MIN_BIDS_PER_ITEM = 0;
	public static final int MAX_BIDS_PER_ITEM = 4;
	public static final int MAX_BIDS_ALLOWED_PER_BIDDER = 10;

	//Private
	private int myTotalBids;
	private Map<Auction, ArrayList<Item>> myAuctions;	
	
	/* This was already there. Not sure if we need this
	   or not as bid amount is going to be double.
	
	private static int myBid;
	*/
	
	//Change here to test

	public Bidder() {
		myTotalBids = 0;
		myAuctions = new HashMap<Auction, ArrayList<Item>>();
	}
	
//	Bid(double): void
//	getAuctionsWithBid(): List<Auction>
//	getMyItemsWithBidsSingleAuction(Auction): List
//	getMyItemsWithBidsAllAuctions(): List
//	getMyBids(): void
//	getMyItems(): void
//	getAllAuctions(): List
	
	public void makeBid(double bid, Item theItem) {
		//if (isBidValid()) {}
	}
	
	public boolean isUnderMaxBidsPerAuction() {
		return myTotalBids < MAX_BIDS_ALLOWED_PER_BIDDER;
		//No more than 4 bids per auction
	}
	public boolean isUnderMaxBidsTotal() {
		return myTotalBids < MAX_BIDS_ALLOWED_PER_BIDDER;
		//NO mre than 10 items for all future auctions.
	}
	
	/**
	 * @author Raisa
	 * @param auction
	 * @return true if the bid is placed before 12:00 am on the day of the Auction
	 * @return False if Bid is placed after 12:00 am on the day of the Auction
	 */
	public boolean isDateValidForBid(Auction auction) {
		//checks if the bid is not on the current day
		//or if the bid already passed
		return true;
	}
	
	public ArrayList<Auction> getAuctionsWithBid(){
		return null;
	}
	
	public ArrayList<Auction> getMyItemsWithBidsSingleAuction(Auction theAuction){
		return null;
	}
	
	public ArrayList<Auction> getMyItemsWithBidsAllAuctions(){
		return null;
	}
	
	public void getMyBids() {
		
	}	
	
	public void getMyItems() {
		
	}
	
	public int getBidsPerItem(Item theItem) {
		return 0;
	}
	
	public ArrayList<Auction> getAllAuctions() {
		return null;
	}


	/**
	 * Checks if bid is valid or not.
	 * @param myBid The bid passed by user.
	 * @return True if bid is valid, false otherwise.
	 */
	public boolean isBidGreaterThanMinAmount(double myBid) {
		return myBid > MIN_AMOUNT_BID_PER_ITEM;
	}

}
