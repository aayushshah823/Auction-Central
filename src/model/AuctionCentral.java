package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is where all the auctions are held. By storing each non-profit
 * that submits an auction we can access every auction and keep track of all
 * future auctions.
 * 
 * @author Allen Whitemarsh, Raisa
 * @version 5/4/2018
 */
public class AuctionCentral implements java.io.Serializable {

	/**
	 * Automatically Generated serialVersionUID
	 */
	private static final long serialVersionUID = -3851687924011616060L;
	
	/**
	 * Default max days away an auction can be scheduled.
	 */
	public static final int MAX_DAYS_AWAY_FOR_AUCTION = 60;
	
	/**
	 * Default min days away an auction can be scheduled.
	 */
	public static final int MIN_DAYS_AWAY_FOR_AUCTION = 14;
	
	/**
	 * Default max auctions scheduled per day.
	 */
	public static final int MAX_AUCTIONS_PER_DAY = 2;
	
	/**
	 * Constant to define the maximum number of auctions allowed to be scheduled at
	 * a time.
	 */
	private static final int MAX_NUM_UPCOMING_AUCTIONS = 25;
	
	/**
	 * List that stores all nonProfit's who have had an auction approved.
	 */
	private ArrayList<NonProfit> allNonProfits;
	
	/**
	 * List of all users within the System.
	 */
	private ArrayList<User> users;
	
	/**
	 * Map that tracks Auctions scheduled per day.
	 */
	private Map<LocalDate, Integer> myCalendar;

	/**
	 * Variable to keep track of the number of total auctions scheduled.
	 */
	private int numCurrentAuctions;

	/**
	 * Constructor to initialize AuctionCentral's fields.
	 */
	public AuctionCentral() {
		users = new ArrayList<User>();
		allNonProfits = new ArrayList<NonProfit>();
		myCalendar = new HashMap<>();
		numCurrentAuctions = 0;
	}

	/**
	 * Method that adds a NonProfit to the list of NonProfits.
	 * This allows easy access to all auctions within AuctionCentral.
	 * @param theNonProfit
	 */
	public void addNonprofit(NonProfit theNonProfit) {
		if (theNonProfit != null) {
			allNonProfits.add(theNonProfit);
		} else
			throw new IllegalArgumentException();
	}

	/**
	 * Method that adds a new user to the List of users.
	 * @param user
	 */
	public void addNewUser(User user) {
		if (!(user instanceof User))
			throw new IllegalArgumentException();
		users.add(user);
	}

	/**
	 * Method to return all NonProfits within the system.
	 * @return a List of NonProfits
	 */
	public ArrayList<NonProfit> getAllNonProfits() {
		return allNonProfits;
	}

	/**
	 * Method that adds an auction to 
	 * @param theNonProfit
	 * @param theAuction
	 */
	public void addAuction(NonProfit theNonProfit, Auction theAuction) {
		if (theNonProfit != null && theAuction != null) {
			addAuctionToCalendar(theAuction);
			theNonProfit.addAuction(theAuction);
			updateNumberOfCurrentAuctions();
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	private void addAuctionToCalendar(Auction theAuction) {
		if ((theAuction.getEndDate().isEqual(theAuction.getStartDate())) ||
				(theAuction.getEndDate().isAfter(theAuction.getStartDate()))) {
			long daysApart = ChronoUnit.DAYS.between(theAuction.getStartDate(),
															theAuction.getEndDate());
			for (int i = 0; i < daysApart; i++) {
				LocalDate temp = theAuction.getStartDate().plusDays(i);
				if (!myCalendar.containsKey(temp)) {
					myCalendar.put(temp, 1);
				} else {
					int days = myCalendar.get(temp);
					days++;
					myCalendar.put(temp, days);
				}
			}
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
				if ((auction.getEndDate().isAfter(currentDate.toLocalDate())) ||
					(auction.getEndDate().isEqual(currentDate.toLocalDate()))) {
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
	
	public Map<Integer, ArrayList<LocalDate>> auctionRequest(NonProfit theNonProfit, LocalDate theStartDate,
							  LocalDate theEndDate, LocalTime theStartTime, LocalTime theEndTime) {
		HashMap<Integer, ArrayList<LocalDate>> errorMap = new HashMap<>();
		if (theStartDate.isAfter(theEndDate)) {
			errorMap.put(0, null); //End Date is before the Start Date
		}
		if (!isYearSinceLastAuction(theNonProfit)) {
			errorMap.put(1, null);
		}
		if (!isDateRangeValid(theStartDate, theEndDate)) {
			errorMap.put(2, null);
		}
		if (!isLessThanMaxAuctionsScheduled()) {
			errorMap.put(3, null);
		}
		ArrayList<LocalDate> unavailableDates = isMoreThanMaxAuctionsPerDay(theStartDate, theEndDate);
		if (!unavailableDates.isEmpty()) {
				errorMap.put(4, unavailableDates); 
		}
		if (errorMap.isEmpty()) {
			Auction newAuction = new Auction(theStartDate, theEndDate, theStartTime, theEndTime);
			addAuction(theNonProfit, newAuction);
		}
		return errorMap;
	}
	
	/**
	 * Method that checks if it has been a year since the last Auction by the same
	 * Non-Profit. 
	 * @param theNonProfit
	 * @return Returns 1 if it has been a year, returns a 0 otherwise.
	 */
	public boolean isYearSinceLastAuction(NonProfit theNonProfit) {
		return (ChronoUnit.DAYS.between(theNonProfit.getLastAuctionDate(), LocalDate.now()) >= 365);
	}
	
	public boolean isDateRangeValid(LocalDate theStartDate, LocalDate theEndDate) {
		long startDate = ChronoUnit.DAYS.between(theStartDate, LocalDate.now());
		long endDate = ChronoUnit.DAYS.between(theEndDate, LocalDate.now());
		return (startDate >= MIN_DAYS_AWAY_FOR_AUCTION && endDate <= MAX_DAYS_AWAY_FOR_AUCTION);
	}
	
	public ArrayList<LocalDate> isMoreThanMaxAuctionsPerDay(LocalDate theStartDate, LocalDate theEndDate) {
		long lengthOfAuction = ChronoUnit.DAYS.between(theStartDate, theEndDate);
		ArrayList<LocalDate> unavailableDates = new ArrayList<>();
		for (int i = 0; i < lengthOfAuction; i++) {
			LocalDate temp = theStartDate.plusDays(i);
			if (myCalendar.containsKey(temp)) {
				int auctionsThatDay = myCalendar.get(temp);
				if (auctionsThatDay >= MAX_AUCTIONS_PER_DAY) {
					unavailableDates.add(temp);
				}
			}
		}
		return unavailableDates;
	}
	
	public boolean isLessThanMaxAuctionsScheduled() {
		return numCurrentAuctions < MAX_NUM_UPCOMING_AUCTIONS;
	}
}
