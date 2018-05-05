package model;

/**
 * @author Allen Whitemarsh
 * @version 5/4/2018
 *
 */
public class Item implements java.io.Serializable{
	
	private static final long serialVersionUID = 3631678504023639370L;
	private String myName;
	private double myStartingBid;
	private String myDescription;
	private int myItemCount;
	private double myCurrentBid;
	
	public Item() {
		//Default Constructor. Do nothing
	}
	
	public Item(String theName, double theStartingBid, String theDescription, int theItemCount) {
		myName = theName;
		myStartingBid = theStartingBid;
		myItemCount = theItemCount;
		myDescription = theDescription;
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

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item Name: " + myName);
		builder.append("Item Description: " + myDescription);
		builder.append("Starting Bid: $" + myStartingBid);
		return builder.toString();
	}

}
