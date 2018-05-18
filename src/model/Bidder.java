package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/**
 * 
 * @author Raisa
 *
 */
public class Bidder extends User implements Serializable {

	private static final long serialVersionUID = 8268184277531088723L;
	
	private Map<Auction, Map<Item, Double>> myBiddingHistory;
	
	public static final int MIN_BIDS_PER_ITEM = 0;
	public static final int MAX_BIDS_PER_AUCTION = 4;
	public static final int MAX_BIDS_ALLOWED_PER_BIDDER = 10; 
	

	public Bidder(String theUserName, String theName) { 
		super(theUserName, theName, "bidder");
		myBiddingHistory = new HashMap<Auction, Map<Item, Double>>();
	}

	/**
	 * Pre: User has placed a bid in any auction at some point 
	 * Pos: Generates a list of all auction where bidder has participated
	 * @return ArrayList containing  all auction user has placed bids on
	 * @author Raisa
	 */
	public ArrayList<Auction> getAllAuctions() {
		ArrayList<Auction> auctions = new ArrayList<Auction>();
		if(this.myBiddingHistory == null) {
			return auctions;
		} else {
			Set<Auction> a = myBiddingHistory.keySet();
			for(Auction auction : a) {
				auctions.add(auction);
			}
			return auctions;
		}	
	
	}
	/**
	 * Pre: Bidder has successfully placed a bid
	 * Post: Auction, Item and bid amount is added to bidder bidding history 
	 * @param auction
	 * @param Map with Item Object and Bid amount
	 */
	public void addBid(Auction auction, Item item, Double bidAmount) {
		Map <Item, Double>temp = new HashMap<Item, Double>();
		temp.put(item, bidAmount);
		this.myBiddingHistory.put(auction, temp);
	}
	

	/**
	 * Pre: User must have placed an bid in any items of the given auction	 
	 * 
	 * @param auction
	 * @return ArrayList containing of items user has bid on
	 * @return an empty ArrayList if user has no bidding history
	 */
	public ArrayList<Item> getAllItemsInOneAuction(Auction auction) {
		ArrayList <Item> items = new ArrayList<Item>();
		if(!this.myBiddingHistory.containsKey(auction)) {
			return items;
		} else {
			items.addAll(this.myBiddingHistory.get(auction).keySet());	//TODO TEST THIS CAREFULLY !
		}

		return items;

	} 
	/**
	 * Pre: user must have placed bids on at least one item.
	 * @return Map with auction as key value and another Map with Item
	 * as the key and the bid amount as the value
	 * 
	 * TODO create display method. Look how to display information in SceneBuilder
	 */
	public Map<Auction, Map<Item, Double>> getAllIntemsInAllAuctions(){
		return this.myBiddingHistory;
	}
	
	
	private int myTotalBidAllFutureAuctions() {
		int totalBid = 0;		
		LocalDate today = LocalDate.now();	
		for(Auction auction : this.myBiddingHistory.keySet()) {
			if(today.compareTo(auction.getStartDate())<=0) {
				totalBid += (this.myBiddingHistory.get(auction).size());
			}
		}
		
		return totalBid;
	}
	

	/**	  
	 * @param auction
	 * @return amount of bids placed in one auction
	 * @return -1 if no bids have been placed
	 */
	public int myTotalBidPerAuction(Auction auction) {
		int numOfBids = 1; 
		if(this.myBiddingHistory.containsKey(auction)) {
			numOfBids = this.myBiddingHistory.get(auction).size();
		}
		return numOfBids;
	}


	
	public Auction findAuction(AuctionCentral ac, String auctionName) {
		ArrayList<Auction> auctions = ac.getFutureAuctions();
		Auction auction = null;
		for(int i = 0; i < auctions.size(); i++) {
			if(auctions.get(i).getAuctionName().equals(auctionName))
				auction = auctions.get(i);				
		}
		return auction;
	}

	/**
	 * 
	 * 
	 * @param myBid The bid passed by user.
	 * @return True if bid is valid, false otherwise.
	 * THIS GOES IN AUCTION
	 */
	public boolean isBidGreaterThanMinAmount(double theBid, Item item) {
		return theBid > item.getStartingBid();
	}
	public boolean isMaxTotalBid(Auction auction) {
		return (myTotalBidAllFutureAuctions() == MAX_BIDS_ALLOWED_PER_BIDDER);
	}

}
