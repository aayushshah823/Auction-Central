package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Auction {
	
	private static int MAX_AMOUNT_OF_ITEMS = 10;
	private List<Item> myItems;
	private LocalDate myDate;
	private LocalTime myTime;
	
	public Auction() {
		
	}
	
	public void addItem(Item theItem) {
		//add items
	}
	
	public List<Item> getItems() {
		return myItems;
	}

	public List<Item> getMyItems() {
		return myItems;
	}

	public void setMyItems(List<Item> myItems) {
		this.myItems = myItems;
	}

	public LocalDate getMyDate() {
		return myDate;
	}

	public void setMyDate(LocalDate myDate) {
		this.myDate = myDate;
	}

	public LocalTime getMyTime() {
		return myTime;
	}

	public void setMyTime(LocalTime myTime) {
		this.myTime = myTime;
	}


}
