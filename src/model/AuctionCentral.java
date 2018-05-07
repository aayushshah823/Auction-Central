package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is where all the auctions are held. By storing each non-profit
 * that submits an auction we can access every auction and keep track of all
 * future auctions.
 * 
 * @author Allen Whitemarsh
 * @version 5/4/2018
 */
public class AuctionCentral implements java.io.Serializable {

	private static final long serialVersionUID = -3851687924011616060L;

	/*
	 * Constant to define the maximum number of auctions allowed to be scheduled at
	 * a time.
	 */
	private static final int MAX_NUM_UPCOMING_AUCTIONS = 25;
	/*
	 * List that stores all nonProfit's who have had an auction approved.
	 */
	private ArrayList<NonProfit> allNonProfits;
	private ArrayList<User> users;

	/*
	 * Variable to keep track of the number of total auctions scheduled.
	 */
	private int numCurrentAuctions;

	public AuctionCentral() {
		users = new ArrayList<User>();
		allNonProfits = new ArrayList<NonProfit>();
		numCurrentAuctions = 0;
	}

	/*
	 * Method that adds a nonProfit to the List of nonProfits
	 */
	public void addNonprofit(NonProfit theNonProfit) {
		if (theNonProfit != null) {
			allNonProfits.add(theNonProfit);
		} else
			throw new IllegalArgumentException();
	}

	public void addNewUser(User user) {
		if (!(user instanceof User))
			throw new IllegalArgumentException();
		users.add(user);
	}

	public ArrayList<NonProfit> getAllNonProfits() {
		return allNonProfits;
	}

	public void addAuction(NonProfit theNonProfit, Auction theAuction) {
		if (theNonProfit != null && theAuction != null && numCurrentAuctions < MAX_NUM_UPCOMING_AUCTIONS) {
			theNonProfit.addAuction(theAuction);
			updateNumberOfCurrentAuctions();
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * This method iterates through all auctions and compares the end date of each
	 * to the current date. This ensures that the number of current auctions stays
	 * below or at the maximum number of auctions.
	 */
	private void updateNumberOfCurrentAuctions() {
		numCurrentAuctions = 0;
		List<Auction> nonProfitAuctions = new ArrayList<>();
		LocalDateTime currentDate = LocalDateTime.now();
		for (NonProfit np : allNonProfits) {
			nonProfitAuctions = np.getAuctions();
			for (Auction auction : nonProfitAuctions) {
				if (auction.getEndDate().isAfter(currentDate.toLocalDate())) {
					numCurrentAuctions++;
				}
			}
		}
	}

	/**
	 * @author Raisa
	 * @return list of all future auctions
	 */
	public ArrayList<Auction> displayFutureAuctions() {
		LocalDate today = LocalDate.now();
		ArrayList<Auction> futureAuctions = new ArrayList<Auction>();
		ArrayList<Auction> tempAuction = new ArrayList<Auction>();
		for (int i = 0; i < this.allNonProfits.size(); i++) {
			tempAuction = (ArrayList<Auction>) this.allNonProfits.get(i).getAuctions();
			if (!tempAuction.isEmpty()) {
				for (int j = 0; j < tempAuction.size(); j++) {
					if (tempAuction.get(j).getStartDate().compareTo(today) > 0) {
						futureAuctions.add(tempAuction.get(j));
					}
				}
			}
		}

		return futureAuctions;
	}
	
	public User login(String username) {
		User user = null; 
		for(int i = 0; i < this.users.size(); i++) {
			if(this.users.get(i).getUsername().equals(username)){
				user = this.users.get(i);
			}
		}
		
		return user;
		
	}

	/**
	 * @author Raisa
	 * @param auction
	 * @return true if the bid is placed before 12:00 am on the day of the Auction
	 * @return False if Bid is placed after 12:00 am on the day of the Auction
	 */
	public boolean isDateValidForBid(Auction auction) {
		LocalDate today = LocalDate.now();
		return (today.compareTo(auction.getStartDate()) < 0);
	}

	/**
	 * This method is used for testing purposes only. Allows easy testing with
	 * different number of total Auctions.
	 * 
	 * @param theNumberOfAuctions
	 */
	public void setNumCurrentAuctions(int theNumberOfAuctions) {
		numCurrentAuctions = theNumberOfAuctions;
	}
}
