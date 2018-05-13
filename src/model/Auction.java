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
public class Auction implements Serializable {


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
}
