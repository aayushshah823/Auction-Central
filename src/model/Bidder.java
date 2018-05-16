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
	 * If the bidding is successful, add item to 
	 * the user's history
	 * @param auction
	 * @param Map with Item Object and Bid amount
	 */
	public void addInfoToBidderHistory(Auction auction, Map<Item, Double> itemsAndBid) {
		this.myBiddingHistory.put(auction, itemsAndBid);
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
	public int makeBid(String itemName, String auctionName, double bid, AuctionCentral ac) {
		Auction auction = findAuction(ac, auctionName);		
		Item item = findItemInAuction(auction, itemName);
		ArrayList<Item> items = new ArrayList<Item>();
		int success = 0;
		if(!(isBidGreaterThanMinAmount(bid, item)))
			success = 2;
		else if(isMaxBidPerAuction(auction))
			success = 3;	
		else if(isMaxTotalBid(auction))
			success = 4;
		else {
			if (!this.myBiddingHistory.containsKey(auction)) { //The user has not bid on this auction yet
				items.add(item);
				//this.myBiddingHistory.put(auction, items); //TODO refactor to new map
				updateItemHighestBid(item, bid);
			} 
			
			if(this.myBiddingHistory.containsKey(auction) && !(isExistingBidOnItem(auction, item))) {
				//this.myBiddingHistory.get(auction).add(item); //TODO refactor to new map
				updateItemHighestBid(item, bid);
			} 
			success = 1;
		}
		
		return success;
	}
	
	private Item findItemInAuction(Auction auction, String itemName) {
		ArrayList<Item> items = (ArrayList<Item>) auction.getItems();
		Item theItem = null;
		for(int i = 0; i < items.size(); i ++) {
			if(items.get(i).getItemName().equals(itemName)) {
				theItem = items.get(i);
			}
		}
		return theItem;
	}

	private void updateItemHighestBid(Item item, double bid) {
		if(bid > item.getCurrentBid()) {
			item.setCurrentBid(bid);
		}
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
	 * Checks if the user has a previous bid 
	 * on a Item. Used as a helper to not add the 
	 * same item twice to the list of items
	 * @param auction
	 * @param item
	 * @return true if item has already bid on for a specific auction
	 */
	private boolean isExistingBidOnItem(Auction auction, Item item) {
		int numberOfItems = this.myBiddingHistory.get(auction).size() - 1;
		if(numberOfItems == 0) {
			return false; 
		}else {
			//ArrayList<Item> items = this.myBiddingHistory.get(auction); //TODO REFACTOR TO NEW MAP
			for(int i = 0; i < numberOfItems; i++) {
			//	if(items.get(i).getItemName().equals(item.getItemName())) //TODO REFACTOR TO NEW MAP
					return true;
			}
		}
	
		return false;
	}


	/**
	 * 
	 * 
	 * @param myBid The bid passed by user.
	 * @return True if bid is valid, false otherwise.
	 */
	public boolean isBidGreaterThanMinAmount(double theBid, Item item) {
		return theBid > item.getStartingBid();
	}

	
	public boolean isMaxBidPerAuction(Auction auction) {
		return (myTotalBidPerAuction(auction) == MAX_BIDS_PER_AUCTION);
	}
	
	public boolean isMaxTotalBid(Auction auction) {
		return (myTotalBidAllFutureAuctions() == MAX_BIDS_ALLOWED_PER_BIDDER);
	}

}
