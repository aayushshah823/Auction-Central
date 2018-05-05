package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Bidder extends User implements Serializable {

	private static final long serialVersionUID = 8268184277531088723L;
	
	public static final int MIN_AMOUNT_BID_PER_ITEM = 500;
	public static final int MIN_BIDS_PER_ITEM = 0;
	public static final int MAX_BIDS_PER_AUCTION = 4;
	public static final int MAX_BIDS_ALLOWED_PER_BIDDER = 10; 
	private Map<Auction, ArrayList<Item>> myAuctions;

	public Bidder() { 
		myAuctions = new TreeMap<Auction, ArrayList<Item>>();
	}

	/**
	 * Pre: User has placed a bid in any auction at some point Post:
	 * 
	 * @return ArrayList containing  all auction user has placed bids on
	 * @author Raisa
	 */
	public ArrayList<Auction> getAllAuctions() {
		ArrayList<Auction> auctions = null;
		if(this.myAuctions == null) {
			System.out.println("No Auction found in your records");
		} else {
			Set<Auction> a = myAuctions.keySet();
			auctions = new ArrayList<Auction>();
			auctions.addAll(a);
		}

		return auctions;
	
	}

	/**
	 * Pre: User must have placed an bid in any items of the given auction
	 * 
	 * @param auction
	 * @return ArrayList containing of items user has bid on
	 */
	public ArrayList<Item> getAllItemsInOneAuction(Auction auction) {
		ArrayList <Item> items = new ArrayList<Item>();
		if(!this.myAuctions.containsKey(auction)) {
			System.out.println("You have place no bids in this auction");
		
		} else {
			items.addAll(this.myAuctions.get(auction));	
		}

		return items;

	}
	/**
	 * Pre: user must have placed bids on at least one item.
	 * @return ArrayList containing of items user has bid on. 
	 */
	public ArrayList<Item> getAllIntemsInAllAuctions(){
		ArrayList<Item> items = new ArrayList<Item>();
		if(this.myAuctions == null) {
			System.out.println("No Items found in your records");
		} else {
			for(Auction auction : this.myAuctions.keySet()) {
				items.addAll(this.myAuctions.get(auction));				
			}
		}
		
		
		return items;
	}
	
	//TODO need to think about this one for a bit 
	public int myTotalBidAllFutureAuctions() {
		int totalBid = 0;		
		LocalDate today = LocalDate.now();
	
		for(Auction auction : this.myAuctions.keySet()) {
			if(today.compareTo(auction.getStartDate())<=0) {
				totalBid += (this.myAuctions.get(auction).size() - 1);
			}
		}
		
		return totalBid;
	}
	/**	  
	 * @param auction
	 * @return amount of bids placed in one auction
	 */
	public int myTotalBidPerAuction(Auction auction) {
		int numOfBids = 0;
		if(this.myAuctions.containsKey(auction)) {
			numOfBids = (this.myAuctions.get(auction).size() - 1);
		}	
		return numOfBids;
	}

	/**
	 * This option will only show if 
	 * the date is valid for a bid
	 * return 2 if bid amount < minMidAmout
	 * return 3 if the user has bid in the max # if bids fir auctions
	 * return 4 if the user has bid in the max # of total items
	 * return 5 if the bid was successful 
	 * @param bid amount and Item to bid on
	 * @param theItem
	 */
	public int makeBid(Item theItem, Auction theAuction, double theBid) {
		int success = 0;
		if(!isBidGreaterThanMinAmount(theBid))
			return 2;
		if(isMaxBidPerAuction(theAuction))
			return 3;		
		if(isMaxBidPerAuction(theAuction))
			return 3;
		if(isMaxTotalBid(theAuction))
			return 4;
		
		return success;
	}

	/**
	 * @author Raisa
	 * @param auction
	 * @return true if the bid is placed before 12:00 am on the day of the Auction
	 * @return False if Bid is placed after 12:00 am on the day of the Auction
	 */
	public boolean isDateValidForBid(Auction auction) {
		LocalDate today = LocalDate.now();
		return (today.compareTo(auction.getStartDate()) < 0);
	}
	/**
	 * 
	 * 
	 * @param myBid The bid passed by user.
	 * @return True if bid is valid, false otherwise.
	 */
	public boolean isBidGreaterThanMinAmount(double theBid) {
		return theBid > MIN_AMOUNT_BID_PER_ITEM;
	}

	
	public boolean isMaxBidPerAuction(Auction auction) {
		return (myTotalBidPerAuction(auction) >= MIN_AMOUNT_BID_PER_ITEM);
	}
	
	public boolean isMaxTotalBid(Auction auction) {
		return (myTotalBidAllFutureAuctions() >= MAX_BIDS_ALLOWED_PER_BIDDER);
	}
}
