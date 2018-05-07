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
	private List<Item> myItems;
	private LocalDate myStartDate;
	private LocalTime myStartTime;
	private LocalDate myEndDate;
	private LocalTime myEndTime;
	private String myAuctionName;
	private String myAuctionLocation;
	
	
	public Auction(LocalDate theStartDate, LocalDate theEndDate, 
					LocalTime theStartTime, LocalTime theEndTime) {
		myItems = new ArrayList<Item>();
		myStartDate = theStartDate;
		myStartTime = theStartTime;
		myEndDate = theEndDate;
		myEndTime = theEndTime;
		myAuctionName = "";
		myAuctionLocation = "";
	}
	
	/**
	 * 
	 * @param theItem
	 */
	public void addItem(Item theItem) {
		myItems.add(theItem);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Item> getItems() {
		return myItems;
	}

	/**
	 * 
	 * @param myItems
	 */
	public void setMyItems(List<Item> myItems) {
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
	 * Getter for location of auction.
	 * @return auction location
	 */
	public String getAuctionLocation() {
		return myAuctionLocation;
	}

	public void setAuctionLocation(String location) {
		myAuctionLocation = location;
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
	public LocalTime getEndTime() {
		return myEndTime;
	}


	public void setEndTime(LocalTime myTime) {
		this.myEndTime = myTime;
	}

}
