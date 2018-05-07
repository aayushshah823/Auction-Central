package model;

/**
 * 
 * @author Benjamin Yuen, Raisa Meneses, Aayush Shah, 
 * 		   Allen Whitemarsh, Jake Yang
 * @version May 4, 2018
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
		builder.append("\tItem Name: " + myName);
		builder.append("\tItem Description: " + myDescription);
		builder.append("\tStarting Bid: $" + myStartingBid);
		return builder.toString();
	}

}
