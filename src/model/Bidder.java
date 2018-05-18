package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author Raisa
 *
 */
public class Bidder extends User implements Serializable {

	private static final long serialVersionUID = 8268184277531088723L;
	
	private Map<Auction, Map<Item, Double>> myBiddingHistory;
	

	public Bidder(String theUserName, String theName) { 
		super(theUserName, theName, "bidder");
		myBiddingHistory = new HashMap<Auction, Map<Item, Double>>();
	}

	/**
	 * Precondition: User has placed a bid in any auction at some point 
	 * Postcondition: Generates a list of all auction where bidder has participated
	 * @return ArrayList containing  all auction user has placed bids on
	 * 
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
	 * Precondition: Bidder has successfully placed a bid
	 * Postcondition: Auction, Item and bid amount is added to bidder bidding history 
	 * @param auction
	 * @param Map with Item Object and Bid amount
	 */
	public void addBid(Auction auction, Item item, Double bidAmount) {
		Map <Item, Double>temp = new HashMap<Item, Double>();
		temp.put(item, bidAmount);
		this.myBiddingHistory.put(auction, temp);
	}
	

	/**
	 * Precondition: User must have placed an bid in any items of the given auction	 	 * 
	 * @param auction
	 * @return ArrayList containing of items user has bid on for a give auction
	 * @return an empty ArrayList if user has no bidding history
	 */
	public ArrayList<Item> getAllItemsInOneAuction(Auction auction) {
		ArrayList <Item> items = new ArrayList<Item>();
		if(!this.myBiddingHistory.containsKey(auction)) {
			return items;
		} else {
			items.addAll(this.myBiddingHistory.get(auction).keySet());	
		}

		return items;

	} 
	/**
	 * Precondition: user must have placed bids on at least one item.
	 * @return Map with auction as key value and another Map with Item
	 * as the key and the bid amount as the value. Empty map if no auction
	 * recorded. Empty map if no bids have been placed in the past. 
	 * 
	 */
	public Map<Auction, Map<Item, Double>> getAllIntemsInAllAuctions(){
		return this.myBiddingHistory;
	}	
	
	public int totalBidAllFutureActions() {
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
}
