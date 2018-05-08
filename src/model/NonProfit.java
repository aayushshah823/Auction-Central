package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * This class keeps tracks of non profit's auctions
 * items they place bid on in each auction,
 * their last day of auctin etc.
 * 
 * @author Aayush Shah
 * @version 5/7/18
 *
 */
public class NonProfit implements Serializable, User {


	/** Serial id. */
	private static final long serialVersionUID = 6004350978105195837L;

	/** Stores the type of user. */
	private static final String USER_TYPE = "nonprofit";


	public static final int MAX_DAYS_AWAY_FOR_AUCTION = 60;

	public static final int MIN_DAYS_AWAY_FOR_AUCTION = 14;

	// Date.
	private Long myDate;

	/** My Organizations.*/
	private String myOrg;

	/** Name of nonprofit org.*/
	private String myName;

	/** List that stores all the non-profit's auction .*/
	private ArrayList<Auction> myAuctions;

	/** Last day when non profit auctioned.*/
	private LocalDate myLastAuctionDate;


	/**
	 * Initialized all the variables.
	 * @param theOrg Name of organization.
	 * @param theName Type of organization.
	 */
	public NonProfit(String theOrg, String theName) {
		myOrg = theOrg;
		myName = theName;
		myAuctions = new ArrayList<Auction>();
		myLastAuctionDate = null;
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

	/*
	public static void main(String[] args) {
		NonProfit n = new NonProfit("Group 3", "nonP");
		LocalDate today = LocalDate.now();
		LocalTime noon = LocalTime.NOON;
		n.addAuction(new Auction(today.plus(10, ChronoUnit.DAYS), 
				today.plus(10, ChronoUnit.DAYS), noon, noon.plus(6, ChronoUnit.HOURS)));
		System.out.println("Main method working");
		Item i = new Item("Car", Bidder.MIN_AMOUNT_BID_PER_ITEM + 500,"BMW", 1);
		System.out.println("Item being add");
		n.addItem(i);

	}*/

	/**
	 * Get's the items for the auction
	 * @return list that stores the items of this auction.
	 */
	public List<Item> getItemsInAuction() {
		return getCurrentAuction().getItems();
	}


	//	public ArrayList<Auction> getListOfAuction() {
	//		if(myAuctions.size() < 0) 
	//			throw new IllegalArgumentException("No Auctions found");
	//		else
	//			return myAuctions;
	//	}


	/**
	 * Keeps track of all auction of non-profit.
	 * @return The list of auction of non profit.
	 */
	public List<Auction> getAuctions() {
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
	 * Sets the name of org.
	 * @param theOrg The name of org passed in.
	 */
	public void setOrg(String theOrg) {
		myOrg = theOrg;
	}

	/**
	 * Get User name method.
	 * @return Getter for user name.
	 */
	@Override
	public String getUsername() { 
		return this.myOrg;
	}

	/**
	 * Set's the name of user name.
	 * @param userName The user name passed in.
	 */
	@Override
	public void setUsername(String userName) {
		this.myOrg = userName;

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
	 * Setter for the name.
	 * @param name name for non profit.
	 */
	@Override
	public void setName(String name) {
		this.myName = name;

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

	/**
	 * Getter for user type.
	 * @return the type of user.
	 */
	@Override
	public String getUserType() {
		return USER_TYPE;
	}

	/**
	 * Keeps track of days away from auction.
	 * @return days away from auction
	 */
	public int getMaxDays() {
		return MAX_DAYS_AWAY_FOR_AUCTION;
	}

	/**
	 * Sets the max days after which 
	 * user_type can request an auction.
	 * @param theMaxDays The days after which they can book aution.
	 */
	public void setMaxDays(int theMaxDays) {
		theMaxDays = MAX_DAYS_AWAY_FOR_AUCTION;
	}

	public int getMinDays() {
		return MIN_DAYS_AWAY_FOR_AUCTION;
	}

	public void setMinDays(int theMinDays) {
		theMinDays = MIN_DAYS_AWAY_FOR_AUCTION;
	}

	public boolean isMaxDaysForAuction(int theDays) {
		return theDays <= MAX_DAYS_AWAY_FOR_AUCTION;
	}

	public boolean isMinDaysForAuctionValid(int theDays) {
		return theDays >= MIN_DAYS_AWAY_FOR_AUCTION;
	}

}