package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * @author Benjamin Yuen, Raisa Meneses
 * @version May 4, 2018
 */
public class Auction implements Serializable, Comparable<Auction> {


	//Serial Number ID
	private static final long serialVersionUID = -5620536666756226632L;
	
	private static final int MAX_AMOUNT_OF_ITEMS = 10;
	public static final int MAX_BIDS_PER_AUCTION = 8;
	public static final int MAX_BIDS_ALLOWED_PER_BIDDER = 12; 	
	public static final int MIN_BIDS_PER_ITEM = 0;
	private ArrayList<Item> myItems;
	private Map<Bidder, Integer> myBidders;
	private LocalDate myStartDate;
	private LocalTime myStartTime;
	private LocalDate myEndDate;
	private LocalTime myEndTime;
	private String myAuctionName;
	
	public Auction(LocalDate theStartDate, LocalTime theStartTime, 
					LocalTime theEndTime, String theName) {
		myItems = new ArrayList<Item>();
		myBidders = new HashMap<Bidder, Integer>();
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
	
	//............Biding methods start .......................//

	/**
	 *  
	 * @param myBid amount
	 * @param bidder who wishes to make a bid
	 * @return True if bid amount is more than the item's min bid.
	 */
	public boolean isBidGreaterThanMinAmount(double theBid,Item item) {
		return theBid > item.getStartingBid();
	}
	
	/**
	 * Pre: bidder wishes to make a bid on an auction
	 * @param bidder 
	 * @return true if the bidder has reached the max number of allowed bids 
	 * per auction
	 */
	public boolean isMaxBidsPerAuction(Bidder bidder) { 
		if( this.myBidders.containsKey(bidder)) {
			return this.myBidders.get(bidder) == 4;
		} 
		
		  return false; 
	}

	/**
	 * This option will only show if 
	 * the date is valid for a bid
	 * @return 2 if bid amount < minMidAmout 
	 * @return 0 if the bid has been placed successfully 
	 * @param bid amount and Item to bid on
	 * @param theItem
	 */
	
	public int makeBid(Item item, double bidAmount, Bidder bidder) {
		int success = 0;		
		if(bidAmount < item.getStartingBid()){
			success = 2;
		} else {
			if(!this.myBidders.containsKey(bidder)) {
				this.myBidders.put(bidder, 1);
			} else {
				int numberOfBids = this.myBidders.get(bidder);
				numberOfBids++;
				this.myBidders.put(bidder, numberOfBids);
			}
			
			bidder.addNewToBiddingHistory(this, item, bidAmount);
		}
				
		
		return success;
	}

	/**
	 * 
	 * Precondition: Bidder has logged in. The bidding options
	 * is about to be displayed
	 * @return Arraylist with list of errors 
	 * if the user can't bid. The List of auctions in which
	 * the bidder can bid will only be displayed if 
	 * this map is empty. 
	 * 1: Bidder has reached total number of allowed bids.
	 * 2: Bidder has reached total number of allowed bids per auction.
	 * 3: The auction will take place today.
	 */

	public ArrayList<Integer>checkBiddingConditions(Bidder bidder){
		LocalDate today = LocalDate.now();
		ArrayList<Integer> errors = new ArrayList<Integer>();
		if(bidder.getTotalNumberOfBids() == this.MAX_BIDS_ALLOWED_PER_BIDDER)
			errors.add(1);
		if(this.isMaxBidsPerAuction(bidder))
			errors.add(2);
		if(today.equals(this.myStartDate))
			errors.add(3);
		return errors;
	}
	//...............Bidding Methods end ........................//

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
