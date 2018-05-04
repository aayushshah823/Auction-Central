package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author Allen Whitemarsh
 * @version 5/4/2018
 */
public class AuctionCentral implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3851687924011616060L;
	private Map<NonProfit, ArrayList<Auction>> myOrganization;
	private ArrayList<NonProfit> nonProfits;
	
	private static final int MAX_NUM_UPCOMING_AUCTIONS = 25; 
	
	private int numCurrentAuctions;
	
	public AuctionCentral() {
		myOrganization = new HashMap<>();
		nonProfits = new ArrayList<>();
		numCurrentAuctions = 0;
	}
	
	public void addNonprofit(NonProfit theNonProfit) {
		if (theNonProfit != null) {
			if (!myOrganization.containsKey(theNonProfit)) {
				myOrganization.put(theNonProfit, new ArrayList<Auction>());
				nonProfits.add(theNonProfit);
			}
		} else 
			throw new IllegalArgumentException();
	}
	
	public void addAuction(NonProfit theNonProfit, Auction theAuction) {
		if (theNonProfit != null && theAuction != null
				&& numCurrentAuctions < MAX_NUM_UPCOMING_AUCTIONS) {
			myOrganization.get(theNonProfit).add(theAuction);
			numCurrentAuctions++;
		} else {
			throw new IllegalArgumentException();
		}
	}
}
