package model;

public class Item {
	
	private String myName;
	private double myStartingBid;
	private long myItemNumber;
	private String myDescription;
	private int myItemCount;
	private double myCurrentBid;
//	private boolean myBundle; IS THIS NEEDED?
	
	public Item() {
		//Default Constructor. Do nothing
	}
	
	public Item(String theName, double theStartingBid, String theDescription, int theItemCount) {
		myName = theName;
		myStartingBid = theStartingBid;
//		myItemNumber = generateItemNumber(); Should this be done in another class?
		myItemCount = theItemCount;
		myDescription = theDescription;
		//myBundle = false;
	}
	
	public void setCurrentBid(double theBid){
		myCurrentBid = theBid;
	}
	
	public void setStartingBid(double theBid) {
		myStartingBid = theBid;
	}
	
	public String getItemName() {
		return myName;
	}
	
	public long getItemNumber() {
		return myItemNumber;
	}
	
	public String getItemDesciption() {
		return myDescription;
	}
	
	public int getItemCount() {
		return myItemCount;
	}
	
	public double getCurrentBid() {
		return myCurrentBid;
	}
	
	public double getStartingBid() {
		return myStartingBid;
	}
	
//	public boolean isBundle() {
//		return myBundle;
//	}

	public String toString() {
		return "";
	}

}
