package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.AuctionCentral;
import model.NonProfit;

public class AuctionCentralTest {
	
	static LocalDate today = LocalDate.now();
	private static final LocalDate AUCTION_START_DATE_VALID = today.plusDays(30);
	private static final LocalTime AUCTION_START_TIME = LocalTime.NOON;
	private static final LocalTime AUCTION_END_TIME = AUCTION_START_TIME.plusHours(4);
	private static final LocalDate DATE_364_DAYS_AGO = 
											LocalDate.now().minusYears(1).plusDays(1);
	private static final LocalDate DATE_MIN_DAYS_BEFORE_ANOTHER_AUCTION_BYSAMENONPROFIT
														= LocalDate.now().minusYears(1);
	private static final LocalDate DATE_MAX_DAYS_FROM_NOW = LocalDate.now().plusDays(60);
	private static final LocalDate DATE_ONEMORETHANMAX_DAYS_FROM_NOW = 
															LocalDate.now().plusDays(61);
	private static final LocalDate DATE_MIN_DAYS_FROM_NOW = LocalDate.now().plusDays(14);
	private static final LocalDate DATE_ONELESSTHANMIN_DAYS_FROM_NOW =
															LocalDate.now().plusDays(13);
		
	NonProfit defaultNonProfit;
	NonProfit nonProfitForMultipleAuctionsTest1;
	NonProfit nonProfitForMultipleAuctionsTest2;
	NonProfit nonProfitForMultipleAuctionsTest3;
	NonProfit nonProfitLessThanYearSinceLastAuction;
	NonProfit nonProfitNoPriorAuctions;
	NonProfit nonProfitExactlyOneYearAgoSinceLastAuction;
	
	Auction defaultFutureAuction;
	Auction defaultPastAuction;	
	
	AuctionCentral auctionCentral;
	AuctionCentral auctionCentralWithSeveralFutureAuctions;
	AuctionCentral auctionCentralWithMaxTotalAuctions;
	AuctionCentral auctionCentralWithOneLessThanMaxTotalAuctions;
	AuctionCentral auctionCentralWithOneAuction;
	AuctionCentral auctionCentralWithMaxAuctionsSameDay;

	@Before
	public void setUp() throws Exception {
		auctionCentral = new AuctionCentral();
		defaultNonProfit = new NonProfit("username", "org", "name");
		nonProfitForMultipleAuctionsTest1 = new NonProfit("username", "org", "name");
		nonProfitForMultipleAuctionsTest2 = new NonProfit("username", "org", "name");
		nonProfitForMultipleAuctionsTest3 = new NonProfit("username", "org", "name");
		auctionCentralWithSeveralFutureAuctions = new AuctionCentral();
		auctionCentralWithMaxTotalAuctions = new AuctionCentral();
		auctionCentralWithOneLessThanMaxTotalAuctions = new AuctionCentral();
		auctionCentralWithOneAuction = new AuctionCentral();
		HashMap<Integer, String> auction1 = auctionCentralWithOneAuction.auctionRequest(nonProfitForMultipleAuctionsTest1, 
				AUCTION_START_DATE_VALID, AUCTION_START_TIME, 5, "Testing");
		auctionCentralWithMaxAuctionsSameDay = new AuctionCentral();
		HashMap<Integer, String> auction2 = auctionCentralWithMaxAuctionsSameDay.auctionRequest(nonProfitForMultipleAuctionsTest2,
				AUCTION_START_DATE_VALID, AUCTION_START_TIME, 5, "Testing");
		HashMap<Integer, String> auction3 = auctionCentralWithMaxAuctionsSameDay.auctionRequest(nonProfitForMultipleAuctionsTest3,
				AUCTION_START_DATE_VALID, AUCTION_START_TIME, 5, "Testing");
		nonProfitLessThanYearSinceLastAuction = new NonProfit("username", "org", "name");
		nonProfitNoPriorAuctions = new NonProfit("username", "org", "name");
		nonProfitExactlyOneYearAgoSinceLastAuction = new NonProfit("username", "org", "name");
		
		defaultFutureAuction = new Auction(AUCTION_START_DATE_VALID, 
					AUCTION_START_TIME, AUCTION_END_TIME, ""); 
		defaultPastAuction = new Auction(today.minusDays(2), AUCTION_START_TIME,
											AUCTION_END_TIME, "");
		ArrayList<NonProfit> npListMaxAuctions = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			npListMaxAuctions.add(new NonProfit("","",""));
		}
		ArrayList<NonProfit> npListOneLessThanMaxAuctions = new ArrayList<>();
		for (int i = 0; i < 24; i++) {
			npListOneLessThanMaxAuctions.add(new NonProfit("","",""));
		}
		ArrayList<NonProfit> fiveNonProfits = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			fiveNonProfits.add(new NonProfit("","",""));
		}
		
		for (int i = 0; i < 25; i++) {
			HashMap<Integer, String> auctions = auctionCentralWithMaxTotalAuctions.auctionRequest(npListMaxAuctions.get(i), 
					AUCTION_START_DATE_VALID.plusDays(i), AUCTION_START_TIME, 5, "Testing" + i);
		}

		for (int i = 0; i < 24; i++) {
			HashMap<Integer, String> auctions2 = auctionCentralWithOneLessThanMaxTotalAuctions.auctionRequest(npListOneLessThanMaxAuctions.get(i), 
					AUCTION_START_DATE_VALID.plusDays(i), AUCTION_START_TIME, 5, "Testing" + i);
			HashMap<Integer, String> auctions3 = auctionCentralWithSeveralFutureAuctions.auctionRequest(npListOneLessThanMaxAuctions.get(i), 
					AUCTION_START_DATE_VALID.plusDays(i), AUCTION_START_TIME, 5, "Testing" + i);
		}
		for (int i = 0; i < 5; i++) {
			HashMap<Integer, String> auctions3 = auctionCentralWithSeveralFutureAuctions.auctionRequest(fiveNonProfits.get(i), 
					AUCTION_START_DATE_VALID.plusDays(i), AUCTION_START_TIME, 5, "Testing" + i);
		}
	}
	
	@Test
	public void getAuctionsSortedByDate_NoAuctions_Empty() {
		assertEquals(new ArrayList<Auction>(), auctionCentral.getAuctionsSortedByDate());
	}
	
	@Test
	public void getAuctionsSortedByDate_MultipleNonProfits_SortedList() {
		NonProfit nonProfit1 = new NonProfit("username1", "org1", "name1");
		NonProfit nonProfit2 = new NonProfit("username2", "org2", "name2");
		NonProfit nonProfit3 = new NonProfit("username3", "org3", "name3");
		auctionCentral.auctionRequest(nonProfit1, AUCTION_START_DATE_VALID.plusDays(2), AUCTION_START_TIME, 5, "second");
		auctionCentral.auctionRequest(nonProfit2, AUCTION_START_DATE_VALID.plusDays(5), AUCTION_START_TIME, 5, "third");
		auctionCentral.auctionRequest(nonProfit3, AUCTION_START_DATE_VALID, AUCTION_START_TIME, 5, "first");
		assertEquals("first", auctionCentral.getAuctionsSortedByDate().get(0).getAuctionName());
		assertEquals("second", auctionCentral.getAuctionsSortedByDate().get(1).getAuctionName());
		assertEquals("third", auctionCentral.getAuctionsSortedByDate().get(2).getAuctionName());
	}
	
	@Test
	public void getAuctionsSortedByDate_OneNoneProfit_SortedList() {
		NonProfit nonProfit = new NonProfit("username", "org", "name");
		Auction auction1 = new Auction(AUCTION_START_DATE_VALID.minusDays(370), AUCTION_START_TIME, AUCTION_START_TIME.plusHours(5), "second");
		Auction auction2 = new Auction(AUCTION_START_DATE_VALID.minusDays(1000), AUCTION_START_TIME, AUCTION_START_TIME.plusHours(5), "first");
		auctionCentral.addAuction(nonProfit, auction1);
		auctionCentral.addAuction(nonProfit, auction2);
		auctionCentral.auctionRequest(nonProfit, AUCTION_START_DATE_VALID, AUCTION_START_TIME, 5, "third");
		assertEquals("first", auctionCentral.getAuctionsSortedByDate().get(0).getAuctionName());
		assertEquals("second", auctionCentral.getAuctionsSortedByDate().get(1).getAuctionName());
		assertEquals("third", auctionCentral.getAuctionsSortedByDate().get(2).getAuctionName());
	}
	
	@Test
	public void getAuctionsSortedByDate_SameDay_SortedList() {
		NonProfit nonProfit1 = new NonProfit("username1", "org1", "name1");
		NonProfit nonProfit2 = new NonProfit("username2", "org2", "name2");
		auctionCentral.auctionRequest(nonProfit1, AUCTION_START_DATE_VALID, AUCTION_START_TIME.plusHours(2), 5, "second");
		auctionCentral.auctionRequest(nonProfit2, AUCTION_START_DATE_VALID, AUCTION_START_TIME, 5, "first");
		assertEquals("first", auctionCentral.getAuctionsSortedByDate().get(0).getAuctionName());
		assertEquals("second", auctionCentral.getAuctionsSortedByDate().get(1).getAuctionName());
	}
	
	@Test
	public void isDurationValid_One_Hour_Before_NextDay_True() {
		System.out.println(LocalDate.now());
		assertTrue(auctionCentral.isDurationValid(AUCTION_START_TIME, 11));
	}
	
	@Test
	public void isDurationValid_Midnight_NextDay_False() {
		assertFalse(auctionCentral.isDurationValid(AUCTION_START_TIME, 12));
	}
	
	@Test
	public void isYearSinceLastAuction_LessThanYearSinceLastAuction_FALSE() {
		nonProfitLessThanYearSinceLastAuction.setLastAuctionDate(DATE_364_DAYS_AGO);
		assertFalse(auctionCentral.isYearSinceLastAuction(nonProfitLessThanYearSinceLastAuction));
	}
	
	@Test
	public void isYearSinceLastAuction_NoPriorAuction_TRUE() {
		assertTrue(auctionCentral.isYearSinceLastAuction(nonProfitNoPriorAuctions));
	}
	
	@Test
	public void isYearSinceLastAuction_ExactlyOneYearSinceLastAuction_TRUE() {
		nonProfitExactlyOneYearAgoSinceLastAuction.setLastAuctionDate(DATE_MIN_DAYS_BEFORE_ANOTHER_AUCTION_BYSAMENONPROFIT);
		assertTrue(auctionCentral.isYearSinceLastAuction(nonProfitExactlyOneYearAgoSinceLastAuction));
	}
	
	@Test
	public void isStartDateAfterMinDaysAway_MoreThanMin_TRUE() {
		assertTrue(auctionCentral.isStartDateAfterMinDaysAway(AUCTION_START_DATE_VALID));
	}
	
	@Test
	public void isStartDateAfterMinDaysAway_ExactlyMin_TRUE() {
		assertTrue(auctionCentral.isStartDateAfterMinDaysAway(DATE_MIN_DAYS_FROM_NOW));
	}
	
	@Test
	public void isStartDateAfterMinDaysAway_LessThanMin_FALSE() {
		assertFalse(auctionCentral.isStartDateAfterMinDaysAway(
										DATE_ONELESSTHANMIN_DAYS_FROM_NOW));
	}
	
	@Test
	public void isStartDateBeforeMaxDaysAway_MoreThanMax_FALSE() {
		assertFalse(auctionCentral.isDateBeforeMaxDaysAway(
										DATE_ONEMORETHANMAX_DAYS_FROM_NOW));
	}
	
	@Test
	public void isStartDateBeforeMaxDaysAway_ExactlyMax_TRUE() {
		assertTrue(auctionCentral.isDateBeforeMaxDaysAway(
												   DATE_MAX_DAYS_FROM_NOW));
	}
	
	@Test
	public void isStartDateBeforeMaxDaysAway_LessThanMax_TRUE() {
		assertTrue(auctionCentral.isDateBeforeMaxDaysAway(
												 AUCTION_START_DATE_VALID));
	}
	
	@Test
	public void isLessThanMaxAuctionsScheduled_MAX_AUCTIONS_SCHEDULED_FALSE() {
		assertFalse(auctionCentralWithMaxTotalAuctions.isLessThanMaxAuctionsScheduled());
	}
	
	@Test
	public void isLessThanMaxAuctionsScheduled_TRUE() {
		assertTrue(auctionCentralWithOneLessThanMaxTotalAuctions.isLessThanMaxAuctionsScheduled());
	}
	
	@Test
	public void checkNumberOfAuctionsPerDay_NoAuctionsThatDay_TRUE() {
		assertTrue(auctionCentral.checkNumberOfAuctionsPerDay(defaultFutureAuction.getStartDate()));
	}

	@Test
	public void checkNumberOfAuctionsPerDay_ExactlyOneLessThanMaxAuctionsThatDay_TRUE() {
		assertTrue(auctionCentralWithOneAuction.checkNumberOfAuctionsPerDay(
				defaultFutureAuction.getStartDate()));
	}
	
	@Test
	public void checkNumberOfAuctionsPerDay_ExactlyMaxAuctionsThatDay_FALSE() {
		assertFalse(auctionCentralWithMaxAuctionsSameDay.checkNumberOfAuctionsPerDay(
				defaultFutureAuction.getStartDate()));
	}
	
	@Test
	public void isDateValidForBid_InvalidDate_false() {
		assertFalse(auctionCentral.isDateValidForBid(defaultPastAuction));
	}
	
	@Test
	public void isDateValidForBid_ValidDate_True() {
		assertTrue(auctionCentral.isDateValidForBid(defaultFutureAuction));
	}
	
	@Test
	public void getFutureAuctions_FutureAuctionsExist_AuctionListSize() {
		assertEquals(5, auctionCentralWithSeveralFutureAuctions.getFutureAuctions().size());
	}
}
