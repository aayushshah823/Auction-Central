package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Benjamin Yuen, Raisa Meneses, Aayush Shah, 
 * 		   Allen Whitemarsh, Jake Yang
 * @version May 4, 2018
 */
public class Auction implements Serializable, Comparable<Auction> {


	//Serial Number ID
	private static final long serialVersionUID = -5620536666756226632L;
	
	private static final int MAX_AMOUNT_OF_ITEMS = 10;
	private ArrayList<Item> myItems;
	private LocalDate myStartDate;
	private LocalTime myStartTime;
	private LocalDate myEndDate;
	private LocalTime myEndTime;
	private String myAuctionName;
	
	public Auction(LocalDate theStartDate, LocalTime theStartTime, 
					LocalTime theEndTime, String theName) {
		myItems = new ArrayList<Item>();
		myStartDate = theStartDate;
		myStartTime = theStartTime;
		myEndTime = theEndTime;
		myAuctionName = theName;
	}
	
	/**
	 * 
	 * @param theItem
	 */
	public void addItem(Item theItem) {
		myItems.add(theItem);
	}
	
	public boolean isAddItemValid() {
		boolean result = false;
		if (myItems.size() <= MAX_AMOUNT_OF_ITEMS) {
			result = true;
		}
		return result;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Item> getItems() {
		return myItems;
	}

	/**
	 * 
	 * @param myItems
	 */
	public void setMyItems(ArrayList<Item> myItems) {
		this.myItems = myItems;
	}
	
	/**
	 * Getter for name of auction.
	 * @return auction name.
	 */
	public String getAuctionName() {
		return myAuctionName;
	}

	/**
	 * Setter for auction name
	 * @param name 
	 */
	public void setAuctionName(String name) {
		myAuctionName = name;
	}

	/**
	 * 
	 * @return
	 */
	public LocalDate getStartDate() {
		return myStartDate;
	}


	public void setStartDate(LocalDate myDate) {
		if (myDate != null) {
			this.myStartDate = myDate;
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * 
	 * @return
	 */
	public LocalTime getStartTime() {
		return myStartTime;
	}


	public void setStartTime(LocalTime myTime) {
		this.myStartTime = myTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public LocalDate getEndDate() {
		return myEndDate;
	}


	public void setEndDate(LocalDate myDate) {
		this.myEndDate = myDate;
	}

	/**
	 * 
	 * @return
	 */
	public LocalTime getMyEndTime() {
		return myEndTime;
	}


	public void setEndTime(LocalTime theEndTime) {
		this.myEndTime = theEndTime;
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
	//TODO: TRANSFER TO AUCTION
//	public int makeBid(String itemName, String auctionName, double bid, AuctionCentral ac) {
//		Auction auction = findAuction(ac, auctionName);		
//		Item item = findItemInAuction(auction, itemName);
//		ArrayList<Item> items = new ArrayList<Item>();
//		int success = 0;
//		if(!(isBidGreaterThanMinAmount(bid, item)))
//			success = 2;
//		else if(isMaxBidPerAuction(auction))
//			success = 3;	
//		else if(isMaxTotalBid(auction))
//			success = 4;
//		else {
//			if (!this.myBiddingHistory.containsKey(auction)) { //The user has not bid on this auction yet
//				items.add(item);
//				//this.myBiddingHistory.put(auction, items); //TODO refactor to new map
//				updateItemHighestBid(item, bid);
//			} 
//			
//			if(this.myBiddingHistory.containsKey(auction) && !(isExistingBidOnItem(auction, item))) {
//				//this.myBiddingHistory.get(auction).add(item); //TODO refactor to new map
//				updateItemHighestBid(item, bid);
//			} 
//			success = 1;
//		}
//		
//		return success;
//	}
//	
	//AUCTION SHOULD DO THIS
//	private Item findItemInAuction(Auction auction, String itemName) {
//		ArrayList<Item> items = (ArrayList<Item>) auction.getItems();
//		Item theItem = null;
//		for(int i = 0; i < items.size(); i ++) {
//			if(items.get(i).getItemName().equals(itemName)) {
//				theItem = items.get(i);
//			}
//		}
//		return theItem;
//	}
	
	//AUCTION SHOULD DO THIS

//	private void updateItemHighestBid(Item item, double bid) {
//		if(bid > item.getCurrentBid()) {
//			item.setCurrentBid(bid);
//		}
//	}
	
	
	//THIS GOES IN AUCTION	
//	public boolean isMaxBidPerAuction(Auction auction) {
//		return (myTotalBidPerAuction(auction) == MAX_BIDS_PER_AUCTION);
//	}
	@Override
	public int compareTo(Auction other) {
		if (this.getStartDate().isBefore(other.getStartDate())) {
			return -1;
		} else if (this.getStartDate().isAfter(other.getStartDate())) {
			return 1;
		} else {
			if (this.getStartTime().isBefore(other.getStartTime())) {
				return -1;
			} else if (this.getStartTime().isAfter(other.getStartTime())) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
