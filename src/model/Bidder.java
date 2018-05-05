package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Bidder extends User implements Serializable {

	private static final long serialVersionUID = 8268184277531088723L;
	
	private static final String USER_TYPE = "bidder";
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
			return auctions;
		} else {
			Set<Auction> a = myAuctions.keySet();
			auctions = new ArrayList<Auction>();
			auctions.addAll(a);
			return auctions;
		}	
	
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
	 * return 1 if the bid was successful 
	 * @param bid amount and Item to bid on
	 * @param theItem
	 */
	public int makeBid(Item item, Auction auction, double bid) {
		ArrayList<Item> items = new ArrayList<Item>();
		int success = 0;
		if(!isBidGreaterThanMinAmount(bid))
			success = 2;
		else if(isMaxBidPerAuction(auction))
			success = 3;		
		else if(isMaxBidPerAuction(auction))
			success = 3;
		else if(isMaxTotalBid(auction))
			success = 4;
		else {
			if (!this.myAuctions.containsKey(auction)) { //The user has not bid on this auction yet
				items.add(item);
				this.myAuctions.put(auction, items);
				updateItemHighestBid(item, bid);
			} if (isExistingBidOnItem(auction, item)){ //If I have already bid on this item
				updateItemHighestBid(item, bid);
				
			}else {
				this.myAuctions.get(auction).add(item);
				updateItemHighestBid(item, bid);
			} 
			success = 1;
		}
		
		return success;
	}
	
	private void updateItemHighestBid(Item item, double bid) {
		if(bid > item.getCurrentBid()) {
			item.setCurrentBid(bid);
		}
	}
	/**
	 * Checks if the user has a previous bid 
	 * on a Item. Used as a helper to not add the 
	 * same item twice to the list of items
	 * @param auction
	 * @param item
	 * @return true if item has already bid on for a specific auction
	 */
	private boolean isExistingBidOnItem(Auction auction, Item item) {
		for(int i = 0; i < this.myAuctions.get(auction).size(); i++) {
			if(this.myAuctions.get(auction).get(i).getItemName().equals(item.getItemName()))
				return true;
		}
		return false;
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
