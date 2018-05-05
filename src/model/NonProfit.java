package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class NonProfit implements Serializable {


	/** The id. */
	private static final long serialVersionUID = 6004350978105195837L;
	private static int MAX_DAYS_AWAY_FOR_AUCTION = 60;
	private static int MIN_DAYS_AWAY_FOR_AUCTION = 14;
	private String myOrg;
	private String myName;
	private List<Auction> myAuctions;
	private LocalDate myLastAuctionDate;
	private int myCurrentAuction;

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

	// Checks when the last auction was held
	// every time a nonprofit request for auction is accepted
	// the  lastAuctionDate must be updated 
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


	public List<Item> getItemsInAuction() {
		if (myCurrentAuction < 0) {
			throw new NullPointerException("No Auctions.");
		}
		return myAuctions.get(myCurrentAuction).getItems();
	}

	public void addItem(Item theItem) {
		if (myCurrentAuction < 0) {
			throw new NullPointerException("No Current Auctions.");
		}
		// This isn't recursive call. It's calling addItem from Auction class.
		myAuctions.get(myCurrentAuction).addItem(theItem);
	}

	public List<Auction> getListOfAuction() {
		return myAuctions;
	}

	public String getOrg() {
		return myOrg;
	}

	public String getName() {
		return myName;
	}

	public LocalDate getLastAuctionDate() {
		return myLastAuctionDate;
	}

	public List<Auction> getAuctions() {
		return myAuctions;
	}


}
