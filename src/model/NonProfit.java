package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Aayush Shah
 * @version 5/7/18
 *
 */
public class NonProfit extends User implements Serializable {

	/** Serial id. */
	private static final long serialVersionUID = 6004350978105195837L;

	/** List that stores all the non-profit's auction .*/
	private ArrayList<Auction> myAuctions;

	/** Last day when non profit auctioned.*/
	private LocalDate myLastAuctionDate;
	
	private String myOrg;


	/**
	 * Initialized all the variables.
	 * @param theOrg Name of organization.
	 * @param theName Type of organization.
	 */
	public NonProfit(String theUsername, String theOrg, String theName) {
		super(theUsername, theName, "nonprofit");
		myAuctions = new ArrayList<Auction>();
		myOrg = theOrg;
		myLastAuctionDate = null;
	}

	public String getOrg() {
		return myOrg;
	}
	
	/**
	 * Add's Non Profit's auction to list.
	 * @param theAuction The auction happening.
	 */
	public void addAuction(Auction theAuction) {
		myAuctions.add(theAuction);
	}

	/** 
	 * Keeps track of total auction of non profit.
	 * @return Gives the current auction number of this Non profit. 
	 */
	public Auction getCurrentAuction() {
		return myAuctions.get(myAuctions.size() - 1);
	}

	/**
	 * Adds the item to list of my auctions.
	 * @param theItem The name of item.
	 */
	public void addItem(Item theItem) {
		getCurrentAuction().addItem(theItem);
	}

	/**
	 * Get's the items for the auction
	 * @return list that stores the items of this auction.
	 */
	public List<Item> getItemsInAuction() {
		return getCurrentAuction().getItems();
	}


	/**
	 * Keeps track of all auction of non-profit.
	 * @return The list of auction of non profit.
	 */
	public List<Auction> getAuctions() {
		return myAuctions;
	}

	/**
	 * Method that keeps track of when
	 * was last auction of Non-profit.
	 * @return The date of last auction.
	 */
	public LocalDate getLastAuctionDate() {
		return myLastAuctionDate;
	}

	/**
	 * Set's the last auction date for non profit.
	 * @param ld The date passed in.
	 */
	public void setLastAuctionDate(LocalDate ld) {
		myLastAuctionDate = ld;
	}
}