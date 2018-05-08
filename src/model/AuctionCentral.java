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
	 * Method that adds an auction to the Calendar and informs the NonProfit
	 * that requested the auction that it has been created and scheduled.
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
	
	/**
	 * Method that adds an Auction to the calendar. The calendar is a map
	 * that contains a date and the number of auctions that day. This method
	 * adds an auction to each day within the Auction Timeframe.
	 * @param theAuction
	 */
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
	 * This Method is used to get all the auctions scheduled including and after
	 * today's date.
	 * @author Raisa
	 * @return list of all future auctions
	 */
	public ArrayList<Auction> getFutureAuctions() {
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
	
	/**
	 * This method is used when a user is logging in. It verifies that the
	 * user is in the system before logging the user in.
	 * @param username
	 * @return returns the user associated with the passed username.
	 */
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
	
	/**
	 * This method is the "master" method for handling an auction request. It
	 * calls several other methods to test to see if the auction can be held
	 * for the requested dates. The Map that is returned contains an error code
	 * (an Integer) and an ArrayList of LocalDates. If any test fails, the 
	 * error code is added to the Map, but when an auction is requested on a
	 * day that has more than the Maximum number of auctions per day, it adds
	 * an error code and an ArrayList of all the unavailable dates throughout
	 * the requested period. If all tests pass, an auction is created. The
	 * returned Map is used for giving a NonProfit a reason as to why they
	 * cannot schedule an auction.
	 * 
	 * 
	 * @param theNonProfit
	 * @param theStartDate
	 * @param theEndDate
	 * @param theStartTime
	 * @param theEndTime
	 * @return a Map that is empty if all tests pass, or contains error codes
	 * when they fail.
	 */
	public Map<Integer, ArrayList<LocalDate>> auctionRequest(NonProfit theNonProfit, LocalDate theStartDate,
							  LocalDate theEndDate, LocalTime theStartTime, LocalTime theEndTime) {
		HashMap<Integer, ArrayList<LocalDate>> errorMap = new HashMap<>();
		if (theStartDate.isAfter(theEndDate)) {
			errorMap.put(0, null); //Error code 0: The start date is after the end date.
		}
		if (!isYearSinceLastAuction(theNonProfit)) {
			errorMap.put(1, null); //Error code 1: Hasn't been a year since last auction
		}
		if (!isStartDateAfterMinDaysAway(theStartDate)) {
			errorMap.put(2, null); //Error code 2: The start date is less than 14 days away from todays date.
		}
		if (!isDateBeforeMaxDaysAway(theStartDate, theEndDate)) {
			errorMap.put(3, null); //Error code 3: The start date is less than 14 days away from todays date.
		}
		if (!isLessThanMaxAuctionsScheduled()) {
			errorMap.put(4, null); //Error code 4: The dates requested are more than 60 days away from todays date.
		}
		ArrayList<LocalDate> unavailableDates = isMoreThanMaxAuctionsPerDay(theStartDate, theEndDate);
		if (!unavailableDates.isEmpty()) {
				errorMap.put(5, unavailableDates); // Error code 5: There is a day or days requested with 2 or more auctions scheduled that day.
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
	
	/**
	 * Method that checks to see if the dates requested are after a set number of days
	 * from the current date.
	 * @param theStartDate
	 * @return
	 */
	public boolean isStartDateAfterMinDaysAway(LocalDate theStartDate) {
		long startDateDaysAway = ChronoUnit.DAYS.between(theStartDate, LocalDate.now());
		return startDateDaysAway >= MIN_DAYS_AWAY_FOR_AUCTION;
	}
	
	/**
	 * Method that checks to see if the dates requested are before a set number of days
	 * from the current date.
	 * @param theStartDate
	 * @return
	 */
	public boolean isDateBeforeMaxDaysAway(LocalDate theStartDate, LocalDate theEndDate) {
		long startDateDaysAway = ChronoUnit.DAYS.between(theStartDate, LocalDate.now());
		long endDateDaysAway = ChronoUnit.DAYS.between(theEndDate, LocalDate.now());
		return startDateDaysAway <= MAX_DAYS_AWAY_FOR_AUCTION && endDateDaysAway <= MAX_DAYS_AWAY_FOR_AUCTION;
	}
	
	/**
	 * Method that checks to see if there are already a set number of auctions
	 * per day in the requested date interval.
	 * @param theStartDate
	 * @param theEndDate
	 * @return
	 */
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
