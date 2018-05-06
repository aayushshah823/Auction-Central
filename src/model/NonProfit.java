package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Aayush, Allen, Ben, Raisa
 * @version 5/5/18
 *
 */
public class NonProfit implements Serializable, User {


	/** */
	private static final long serialVersionUID = 6004350978105195837L;
	private static final String USER_TYPE = "nonprofit";
	private static final int MAX_DAYS_AWAY_FOR_AUCTION = 60;
	private static final int MIN_DAYS_AWAY_FOR_AUCTION = 14;
	
	
	/** My Organizations.*/
	private String myOrg;
	
	/** Name of nonprofit org.*/
	private String myName;
	
	/** List that stores all the non-profit's auction .*/
	private List<Auction> myAuctions;
	
	/** Last day when non profit auctioned.*/
	private LocalDate myLastAuctionDate;
	
	/** .*/
	private int myCurrentAuction;

	/**
	 * 
	 * @param theOrg
	 * @param theName 
	 */
	public NonProfit(String theOrg, String theName) {
		myOrg = theOrg;
		myName = theName;
		myAuctions = new ArrayList<Auction>();
		myLastAuctionDate = null;

		// WHY IS IT -1?
		// myCurrentAuction = -1;

		myCurrentAuction = 0;
	}


	/**

		Checks when the last auction was held
		every time a non-profit request for auction is accepted
		the  lastAuctionDate must be updated 

	public boolean submitAuctionRequest(AuctionCentral auctions) {

		if (myLastAuctionDate == null || (myLastAuctionDate.getYear() - (LocalDate.now().getYear()) >= 1)) {
			myLastAuctionDate = LocalDate.now();
			myCurrentAuction++;
			return true;
		} 
		else{ 
			return false;
		}

	}

	 */

	/**
	 * Add's Non Profit's auction to list.
	 * @param theAuction The auction happening.
	 */
	public void addAuction(Auction theAuction) {
		if(myAuctions.isEmpty())
			throw new IllegalArgumentException("No Non Profit auctions");
		else
			myAuctions.add(theAuction);
	}

	/**
	 * Checking if non profit can do or not.
	 * @return True if within date range, false otherwise.
	 */
	public boolean isDateRangeValid() {

		long a = ChronoUnit.DAYS.between(myLastAuctionDate, LocalDate.now());
		return (a >= MIN_DAYS_AWAY_FOR_AUCTION && a <= MAX_DAYS_AWAY_FOR_AUCTION);
	}

	/**
	 * Get's the items for the auction
	 * @return list that stores the items of this auction.
	 */
	public List<Item> getItemsInAuction() {
		if (myCurrentAuction < 0) {
			throw new NullPointerException("No Auctions.");
		}
		return myAuctions.get(myCurrentAuction).getItems();
	}

	/**
	 * Adds the item to list of my auctions.
	 * @param theItem The name of item.
	 */
	public void addItem(Item theItem) {
		if (myCurrentAuction < 0) {
			throw new NullPointerException("No Current Auctions.");
		}
		// This isn't recursive call. It's calling addItem from Auction class.
		myAuctions.get(myCurrentAuction).addItem(theItem);
	}

	/**
	 * 
	 * @return The list of auctions.
	 */
	public List<Auction> getListOfAuction() {
		if(myAuctions.size() <= 0) 
			throw new IllegalArgumentException("No Auctions found");
		else
			return myAuctions;
	}

	/**
	 * Getter for non profit.
	 * @return The organization.
	 */
	public String getOrg() {
		return myOrg;
	}
	
	/**
	 * Name of non- profit.
	 * @return name associated with non-profit.
	 */
	@Override
	public String getName() {
		return myName;
	}

	/**
	 * Method that keeps track of when
	 * was last auction of Non-profit.
	 * @return The date of last auction.
	 */
	public LocalDate getLastAuctionDate() {
		return myLastAuctionDate;
	}


	public List<Auction> getAuctions() {
		return myAuctions;
	}


	@Override
	public void setUsername(String userName) {
		this.myOrg = userName;
		
	}


	@Override
	public void setName(String name) {
		this.myName = name;
		
	}


	@Override
	public String getUsername() { 
		return this.myOrg;
	}


	@Override
	public String getUserType() {
		return USER_TYPE;
	}


}
