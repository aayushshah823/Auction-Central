package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is where all the auctions are held. By storing each non-profit
 * that submits an auction we can access every auction and keep track of all
 * future auctions. 
 *
 * @author Allen Whitemarsh, Raisa, Jake
 * @version 5/4/2018
 */
public class AuctionCentral implements java.io.Serializable {

	/**
	 * Automatically Generated serialVersionUID
	 */
	private static final long serialVersionUID = -3851687924011616060L;
	
	public static final int HOURS_IN_DAY = 23;
	
	public static final int MINS_IN_HOUR = 59;
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
	 * Constant to define the maximum number of auctions allowed to be 
	 * scheduled at a time.
	 */
	private int maxUpcomingAuctions = 25;
	
	private HashMap<NonProfit, ArrayList<Auction>> myAuctions;
	
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
		myAuctions = new HashMap<>();
		myCalendar = new HashMap<>();
		numCurrentAuctions = 0;
	}

	/**
	 * Method that adds a new user to the List of users.
	 * 
	 * @param user
	 */
	public void addNewUser(User user) {
		if (!(user instanceof User))
			throw new IllegalArgumentException();
		users.add(user);
	}
	

	/**
	 * Modifies the number of future auctions that can be scheduled.
	 * 
	 * @param max - the new future auction limit- Must be an integer.
	 * @return a map of errors encountered from given max
	 */
	public HashMap<Integer, String> updateAuctionLimit(int max) {
		HashMap<Integer, String> errorMap = new HashMap<>();
		if (max <= numCurrentAuctions) {
			errorMap.put(0, "Current amount of auctions is equal to or "
					+ "greater than the number of current Auctions"); 
		}
		if (max < 1) {
			errorMap.put(1, "Given max is non-positive");
		}
		if (errorMap.isEmpty()) {
			maxUpcomingAuctions = max;
		}
		return errorMap;
	}
	
	/**
	 * Method to cancel an auction.
	 * 
	 * Pre: There must be an auction to cancel.
	 * Post: The auction is cancelled if and only if:
	 * 		 There have not been any bids on any items within the auction.
	 * 		 The auction is in the future.
	 * @param theAuction
	 * @return 1 if the auction has been cancelled. 0 otherwise.
	 */
	public int cancelAuction(Auction theAuction) {
		if (theAuction.isCancelAvailable() == true) {
			for (Map.Entry<NonProfit, ArrayList<Auction>> map : myAuctions.entrySet()) {
				NonProfit np = map.getKey();
				ArrayList<Auction> auctions = map.getValue();
				for (Auction auction : auctions) {
					if (auction.getAuctionName().equals(theAuction.getAuctionName()) &&
							auction.getStartDate().isEqual(theAuction.getStartDate())) {
						if (myAuctions.get(np).size() == 1) {
							myAuctions.remove(np);
						} else {
							myAuctions.get(np).remove(auction);
						}
						numCurrentAuctions--;
						if (myCalendar.get(theAuction.getStartDate()) == 1) {
							myCalendar.remove(theAuction.getStartDate());
						} else {
							int auctionsThatDay = myCalendar.get(theAuction.getStartDate());
							auctionsThatDay--;
							myCalendar.put(theAuction.getStartDate(), auctionsThatDay);
						}
						return 1;
					}
				}
			}
		}
		return 0;
	}
	

	/**
	 * Method to return all auctions within the system.
	 * 
	 * @return a Map of all auctions within the system.
	 */
	public Map<NonProfit, ArrayList<Auction>> getAllAuctions() {
		return myAuctions;
	}

	/**
	 * Method that returns all the auctions from a single NonProfit.
	 * 
	 * @param theNonProfit
	 * @return
	 */
	public ArrayList<Auction> getNonProfitAuctions(NonProfit theNonProfit) {
		return myAuctions.get(theNonProfit);
	}
		
	/**
	 * Method that disperses the work of adding an auction to the system.
	 * 
	 * Post: an auction is added to the Calendar and to the Map of nonprofits
	 * 		 and auctions.
	 * @param theNonProfit - must not be null.
	 * @param theAuction - must not be null.
	 */
	public void addAuction(NonProfit theNonProfit, Auction theAuction) {
			addAuctionToCalendar(theAuction);
			if (!myAuctions.containsKey(theNonProfit)) {
				ArrayList<Auction> newAuction = new ArrayList<>();
				newAuction.add(theAuction);
				myAuctions.put(theNonProfit, newAuction);
			} else {
				ArrayList<Auction> auctions = myAuctions.get(theNonProfit);
				auctions.add(theAuction);
				myAuctions.put(theNonProfit,  auctions);
			}
			theNonProfit.setLastAuctionDate(theAuction.getStartDate());
			updateNumberOfCurrentAuctions();
	}
	
	/**
	 * Method that adds an Auction to the calendar. The calendar is a map
	 * that contains a date and the number of auctions that day. This method
	 * adds an auction to each day within the Auction Timeframe.
	 * 
	 * Post: the auction is added to the calendar.
	 * 
	 * @param theAuction the Auction must not be null.
	 */
	private void addAuctionToCalendar(Auction theAuction) {
		LocalDate temp = theAuction.getStartDate();
		if (!myCalendar.containsKey(temp)) {
			myCalendar.put(temp, 1);
		} else {
			int days = myCalendar.get(temp);
			days++;
			myCalendar.put(temp, days);
		}
	}

	/**
	 * This method iterates through all auctions and compares the end date of 
	 * each to the current date. This ensures that the number of current
	 * auctions stays below or at the maximum number of auctions.
	 * 
	 * post-the number of currentAuctions is updated.
	 */
	private void updateNumberOfCurrentAuctions() {
		numCurrentAuctions = 0;
		for (Map.Entry<NonProfit, ArrayList<Auction>> map : myAuctions.entrySet()) {
			ArrayList<Auction> tempAuctions = map.getValue();
			for (Auction auction : tempAuctions) {
				if (auction.getStartDate().isAfter(LocalDate.now())) {
					numCurrentAuctions++;
				}
			}
		}
	}

	/**
	 * This Method is used to get all the auctions scheduled including and
	 * after today's date.
	 * 
	 * @author Raisa
	 * @return list of all future auctions
	 */
	public ArrayList<Auction> getFutureAuctions() {
		LocalDate today = LocalDate.now();
		ArrayList<Auction> futureAuctions = new ArrayList<Auction>();
		for (Map.Entry<NonProfit, ArrayList<Auction>> map : myAuctions.entrySet()) {
			ArrayList<Auction> tempAuctions = map.getValue();
			for (Auction auction : tempAuctions) {
				if (auction.getStartDate().isAfter(today) || auction.getStartDate().isEqual(today)) {
					futureAuctions.add(auction);
				}
			}
		}	
		return futureAuctions;
	}
	
	
	/**
	 * Gets a list of all auctions sorted by chronological order
	 * 
	 * @author Jake
	 * @return list of sorted auctions by date. Past - Future.
	 */
	public ArrayList<Auction> getAuctionsSortedByDate() {
		ArrayList<Auction> auctions = new ArrayList<Auction>();
		for (NonProfit nonProfit : myAuctions.keySet()) {
			auctions.addAll(myAuctions.get(nonProfit));
		}
		Collections.sort(auctions);
		return auctions;
	}
	
	
	/**
	 * Gets a list of all auctions between two given dates
	 * 
	 * @param theFirstDate - the date that comes first
	 * @param theSecondDate - the date that comes second
	 * @return list of auctions between two dates
	 */
	public ArrayList<Auction> getAuctionsBetweenDates(LocalDate theFirstDate, LocalDate theSecondDate) {
		ArrayList<Auction> auctions = new ArrayList<Auction>();
		if (theFirstDate.isAfter(theSecondDate)) {
			return auctions;
		}
		for (NonProfit nonProfit : myAuctions.keySet()) {
			for (Auction auction : myAuctions.get(nonProfit)) {
				if ((auction.getStartDate().isAfter(theFirstDate) || auction.getStartDate().isEqual(theFirstDate)) && 
						(auction.getStartDate().isBefore(theSecondDate) || auction.getStartDate().isEqual(theSecondDate))) {
					auctions.add(auction);
				}
			}
		}
		return auctions;
	}

	
	/**
	 * This method is used when a user is logging in. It verifies that the
	 * user is in the system before logging the user in.
	 * 
	 * @param username - username must not be null.
	 * @return returns the user associated with the passed username.
	 * @return null user if the user doesn't exist
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
	 * Method that checks to see if a bid can be placed based on the date.
	 * 
	 * @author Raisa
	 * @param auction - auction must not be null.
	 * @return True if bid is placed before 12:00 am on the day of the Auction
	 * @return False if is placed after 12:00 am on the day of the Auction
	 */
	public boolean isDateValidForBid(Auction auction) {
		LocalDate today = LocalDate.now();
		return (today.compareTo(auction.getStartDate()) < 0);
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
	 * @param theNonProfit - must not be null.
	 * @param theStartDate - must not be null.
	 * @param theStartTime - must not be null.
	 * @param theDuration - must not be null and must be positive.
	 * @param theName - must not be null.
	 * @return a Map that is empty if all tests pass, or contains error codes
	 * when they fail.
	 */
	public HashMap<Integer, String> auctionRequest(NonProfit theNonProfit, LocalDate theStartDate,  
												   LocalTime theStartTime, int theDuration, String theName) {
		HashMap<Integer, String> errorMap = new HashMap<>();
		updateNumberOfCurrentAuctions();
		if (!isDurationValid(theStartTime, theDuration)) {
			errorMap.put(0, "Duration extends auction into the next day");
		}
		LocalTime theEndTime = theStartTime.plusHours(theDuration);
		if (!isYearSinceLastAuction(theNonProfit)) {
			errorMap.put(1, "There has not been a year since your last auction date");
		}
		if (!isStartDateAfterMinDaysAway(theStartDate)) {
			errorMap.put(2, "The start date is too close to today's date.");
		}
		if (!isDateBeforeMaxDaysAway(theStartDate)) {
			errorMap.put(3, "The start date is too far away from today's date");
		}
		if (!isLessThanMaxAuctionsScheduled()) {
			errorMap.put(4, "The maximum number of auctions are scheduled");
		}
		if (!checkNumberOfAuctionsPerDay(theStartDate)) {
			errorMap.put(5, "The maximum number of auctions are scheduled for " + theStartDate);
		}
		if (errorMap.isEmpty()) {
			createAuction(theNonProfit, theStartDate, theStartTime, theEndTime, theName);
		}
		return errorMap;
	}
	
	/**
	 * Checks to see if the duration makes the auction go past midnight that night,
	 * thus moving the "end date" to the next day.
	 * 
	 * @param theStartTime - must not be null.
	 * @param duration - must not be null and must be positive.
	 * @return True if the duration is a valid duration. False otherwise.
	 */
	public boolean isDurationValid(LocalTime theStartTime, int duration) {
		if (theStartTime.plusHours(duration).isBefore(theStartTime)) {
			return false;
		}
		return theStartTime.plusHours(duration).isBefore(LocalTime.of(HOURS_IN_DAY, MINS_IN_HOUR));
	}
	
	/**
	 * Creates an auction once all preTests pass.
	 * 
	 * pre: the date is valid.
	 * 	    There are less than the max number of auctions scheduled.
	 * 		There are less than the max number of auctions that day.
	 * post: an auction is created.
	 * 
	 * @param theNonProfit - must not be null.
	 * @param theStartDate - must not be null.
	 * @param theStartTime - must not be null.
	 * @param theEndTime - must not be null.
	 * @param theName - must not be null.
	 */
	private void createAuction(NonProfit theNonProfit, LocalDate theStartDate, 
							LocalTime theStartTime, LocalTime theEndTime, String theName) {
		Auction newAuction = new Auction(theStartDate, theStartTime, theEndTime, theName);
		addAuction(theNonProfit, newAuction);		
	}
	
	/**
	 * Method that checks if it has been a year since the last Auction by the 
	 * same Non-Profit.
	 * 
	 * @param theNonProfit - must not be null.
	 * @return Returns 1 if it has been a year, returns a 0 otherwise.
	 */
	public boolean isYearSinceLastAuction(NonProfit theNonProfit) {
		if (theNonProfit.getLastAuctionDate() == null) {
			return true;
		}
		return ChronoUnit.DAYS.between(theNonProfit.getLastAuctionDate(), 
				LocalDate.now()) >= 365;
	}
	
	/**
	 * Method that checks to see if the dates requested are after a set number
	 * of days from the current date.
	 * 
	 * @param theStartDate - must not be null
	 * @return True if the start date is more than the min days away. False otherwise
	 */
	public boolean isStartDateAfterMinDaysAway(LocalDate theStartDate) {
		long startDateDaysAway = 
					    ChronoUnit.DAYS.between(LocalDate.now(), theStartDate);
		return startDateDaysAway >= MIN_DAYS_AWAY_FOR_AUCTION;
	}
	
	/**
	 * Method that checks to see if the dates requested are before a set number
	 * of days from the current date.
	 * 
	 * @param theStartDate - must not be null.
	 * @return True if the start date is less than the max days away. false otherwise.
	 */
	public boolean isDateBeforeMaxDaysAway(LocalDate theStartDate) {
		long startDateDaysAway = 
						ChronoUnit.DAYS.between(LocalDate.now(), theStartDate);
		return startDateDaysAway <= MAX_DAYS_AWAY_FOR_AUCTION;
	}
	
	/**
	 * Method that checks to see if there are already a set number of auctions
	 * per day in the requested date interval.
	 * 
	 * @param theStartDate - must not be null
	 * @return true if there are less than the max number of auctions per day.
	 * @return false otherwise.
	 */
	public boolean checkNumberOfAuctionsPerDay(LocalDate theStartDate) {
		if (myCalendar.containsKey(theStartDate)) {
			return myCalendar.get(theStartDate) < MAX_AUCTIONS_PER_DAY;
		} else
			return true;
	}
	
	/**
	 * Checks to see if there are less than a set number of total auctions
	 * scheduled at a time.
	 * 
	 * @return true if there are less scheduled auctions then the number
	 * @return of Total auctions possible to schedule.
	 */
	public boolean isLessThanMaxAuctionsScheduled() {
		return numCurrentAuctions < maxUpcomingAuctions;
	}
	
	/**
	 * Gets the number of current auctions within the system.
	 * @return the number of current auctions.
	 */
	public int getNumCurrentAuctions() {
		return numCurrentAuctions;
	}
	
	
	public int getMaxUpcomingAuctions() {
		return maxUpcomingAuctions;
	}
	
	/**
	 * Method specifically for testing purposes.
	 * 
	 * @param theNonProfit - must not be null.
	 * @return the NonProfit's auction.
	 */
	public Auction getSingleAuction(NonProfit theNonProfit) {
		for (NonProfit nonProfit : myAuctions.keySet()) {
			for (Auction auction : myAuctions.get(nonProfit)) {
				return auction;
			}
		}
		return null;
	}
}
