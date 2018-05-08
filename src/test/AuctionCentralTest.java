package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.AuctionCentral;
import model.NonProfit;

public class AuctionCentralTest {
	
	static LocalDate today = LocalDate.now();
	private static final LocalDate AUCTION_START_DATE_VALID = today.plusDays(30);
	private static final LocalTime AUCTION_START_TIME = LocalTime.NOON;
	private static final LocalDate AUCTION_END_DATE_VALID = AUCTION_START_DATE_VALID;
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
	NonProfit nonProfitLessThanYearSinceLastAuction;
	NonProfit nonProfitNoPriorAuctions;
	NonProfit nonProfitExactlyOneYearAgoSinceLastAuction;
	
	Auction defaultFutureAuction;
	Auction defaultPastAuction;
	Auction pastAuction354DaysAgo;
	Auction invalidAuctionforNonProfit;
	Auction auctionExactlyOneYearAgo;
	Auction auctionOnSameDay1;
	Auction auctionOnSameDay2;
	Auction auctionOnSameDay3;
	Auction auctionMaxDaysFromNow;
	Auction auctionMaxDaysPlusOne;
	Auction auctionMinDaysFromNow;
	Auction auctionMinDaysMinusOne;
	
	
	
	AuctionCentral auctionCentral;
	AuctionCentral auctionCentralWithSeveralFutureAuctions;
	AuctionCentral auctionCentralWithMaxTotalAuctions;
	AuctionCentral auctionCentralWithOneLessThanMaxTotalAuctions;
	AuctionCentral auctionCentralWithMaxAuctionsOnOneDay;
	AuctionCentral auctionCentralWithNoNonProfits;
	AuctionCentral auctionCentralWithOneAuction;
	

	@Before
	public void setUp() throws Exception {
		auctionCentral = new AuctionCentral();
		auctionCentralWithSeveralFutureAuctions = new AuctionCentral();
		auctionCentralWithMaxTotalAuctions = new AuctionCentral();
		auctionCentralWithMaxAuctionsOnOneDay = new AuctionCentral();
		auctionCentralWithOneLessThanMaxTotalAuctions = new AuctionCentral();
		auctionCentralWithNoNonProfits = new AuctionCentral();
		auctionCentralWithOneAuction = new AuctionCentral();
		
		
		defaultNonProfit = new NonProfit("org", "name");
		nonProfitLessThanYearSinceLastAuction = new NonProfit("org", "name");
		nonProfitNoPriorAuctions = new NonProfit("org", "name");
		nonProfitExactlyOneYearAgoSinceLastAuction = new NonProfit("org", "name");
		
		
		pastAuction354DaysAgo = new Auction(DATE_364_DAYS_AGO, DATE_364_DAYS_AGO, 
											AUCTION_START_TIME, AUCTION_END_TIME);
		defaultFutureAuction = new Auction(AUCTION_START_DATE_VALID, 
					AUCTION_END_DATE_VALID, AUCTION_START_TIME, AUCTION_END_TIME); 
		defaultPastAuction = new Auction(today.minusDays(2), today.minusDays(2),
											AUCTION_START_TIME, AUCTION_END_TIME);
		auctionExactlyOneYearAgo = new Auction(DATE_MIN_DAYS_BEFORE_ANOTHER_AUCTION_BYSAMENONPROFIT,
												DATE_MIN_DAYS_BEFORE_ANOTHER_AUCTION_BYSAMENONPROFIT,
												AUCTION_START_TIME, AUCTION_END_TIME);
		auctionOnSameDay1 = new Auction(today, today, AUCTION_START_TIME, 
																AUCTION_END_TIME);
		auctionOnSameDay2 = new Auction(today, today, AUCTION_START_TIME, 
																AUCTION_END_TIME);
		auctionOnSameDay3 = new Auction(today, today, AUCTION_START_TIME,
																AUCTION_END_TIME);
		auctionMaxDaysFromNow = new Auction(DATE_MAX_DAYS_FROM_NOW, DATE_MAX_DAYS_FROM_NOW,
														AUCTION_START_TIME, AUCTION_END_TIME);
		auctionMaxDaysPlusOne = new Auction(DATE_ONEMORETHANMAX_DAYS_FROM_NOW, 
					DATE_ONEMORETHANMAX_DAYS_FROM_NOW, AUCTION_START_TIME, AUCTION_END_TIME);
		auctionMinDaysFromNow = new Auction(DATE_MIN_DAYS_FROM_NOW, DATE_MIN_DAYS_FROM_NOW,
														AUCTION_START_TIME, AUCTION_END_TIME);
		auctionMinDaysMinusOne = new Auction(DATE_ONELESSTHANMIN_DAYS_FROM_NOW, 
											 DATE_ONELESSTHANMIN_DAYS_FROM_NOW,
										 AUCTION_START_TIME, AUCTION_END_TIME);
		
		for (int i = 0; i < 5; i++) {
			Auction fillerAuction = new Auction(AUCTION_START_DATE_VALID.plusDays(i), 
					AUCTION_END_DATE_VALID.plusDays(i), AUCTION_START_TIME, AUCTION_END_TIME);
			NonProfit fillerNonProfit = new NonProfit("filler Org", "filler nonProfit");
			auctionCentral.addNonprofit(fillerNonProfit);
			auctionCentral.addAuction(fillerNonProfit, fillerAuction);
		}
		
		for (int i = 0; i < 25; i++) {
			Auction fillerAuction = new Auction(AUCTION_START_DATE_VALID.plusDays(i), 
					AUCTION_END_DATE_VALID.plusDays(i), AUCTION_START_TIME, AUCTION_END_TIME);
			NonProfit fillerNonProfit = new NonProfit("filler Org", "filler nonProfit");
			auctionCentralWithMaxTotalAuctions.addNonprofit(fillerNonProfit);
			auctionCentralWithMaxTotalAuctions.addAuction(fillerNonProfit, fillerAuction);
		}

		for (int i = 0; i < 24; i++) {
			Auction fillerAuction = new Auction(AUCTION_START_DATE_VALID.plusDays(i), 
					AUCTION_END_DATE_VALID.plusDays(i), AUCTION_START_TIME, AUCTION_END_TIME);
			NonProfit fillerNonProfit = new NonProfit("filler Org", "filler nonProfit");
			auctionCentralWithOneLessThanMaxTotalAuctions.addNonprofit(fillerNonProfit);
			auctionCentralWithOneLessThanMaxTotalAuctions.addAuction(fillerNonProfit, fillerAuction);
		}
	}

	@Test
	public void addNonprofit_nonprofitListIncreases_PASS() {
		auctionCentralWithNoNonProfits.addNonprofit(defaultNonProfit);
		assertEquals(1, auctionCentralWithNoNonProfits.getAllNonProfits().size());
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
		
		assertEquals(5, auctionCentral.getFutureAuctions().size());
	}
	
	@Test
	public void isLessThanMaxAuctionsScheduled_FALSE() {
		assertFalse(auctionCentralWithMaxTotalAuctions.isLessThanMaxAuctionsScheduled());
	}
	
	@Test
	public void isLessThanMaxAuctionsScheduled_TRUE() {
		assertTrue(auctionCentralWithOneLessThanMaxTotalAuctions.isLessThanMaxAuctionsScheduled());
	}

	@Test
	public void isYearSinceLastAuction_LessThanYearSinceLastAuction_FALSE() {
		auctionCentral.addAuction(nonProfitLessThanYearSinceLastAuction, pastAuction354DaysAgo);
		assertFalse(auctionCentral.isYearSinceLastAuction(nonProfitLessThanYearSinceLastAuction));
	}
	
	@Test
	public void isYearSinceLastAuction_NoPriorAuction_TRUE() {
		auctionCentral.addNonprofit(nonProfitNoPriorAuctions);
		assertTrue(auctionCentral.isYearSinceLastAuction(nonProfitNoPriorAuctions));
	}
	
	@Test
	public void isYearSinceLastAuction_ExactlyOneYearSinceLastAuction_TRUE() {
		auctionCentral.addNonprofit(nonProfitExactlyOneYearAgoSinceLastAuction);
		assertTrue(auctionCentral.isYearSinceLastAuction(nonProfitExactlyOneYearAgoSinceLastAuction));
	}
	
	@Test
	public void checkNumberOfAuctionsPerDay_NoAuctionsThatDay_TRUE() {
		assertTrue(auctionCentralWithOneAuction.checkNumberOfAuctionsPerDay(
				defaultFutureAuction.getStartDate(),defaultFutureAuction.getEndDate()).isEmpty());
	}
	
	@Test
	public void checkNumberOfAuctionsPerDay_ExactlyOneLessThanMaxAuctionsThatDay_TRUE() {
		auctionCentralWithOneAuction.addAuction(defaultNonProfit, auctionOnSameDay1);
		assertTrue(auctionCentralWithOneAuction.checkNumberOfAuctionsPerDay(
				defaultFutureAuction.getStartDate(),defaultFutureAuction.getEndDate()).isEmpty());
	}
	
	@Test
	public void checkNumberOfAuctionsPerDay_ExactlyMaxAuctionsThatDay_FALSE() {
		auctionCentralWithOneAuction.addAuction(defaultNonProfit, auctionOnSameDay1);
		auctionCentralWithOneAuction.addAuction(nonProfitNoPriorAuctions, auctionOnSameDay2);
		assertFalse(auctionCentralWithOneAuction.checkNumberOfAuctionsPerDay(
				auctionOnSameDay1.getStartDate(),auctionOnSameDay1.getEndDate()).isEmpty());
	}
	
	@Test
	public void isStartDateAfterMinDaysAway_MoreThanMin_TRUE() {
		assertTrue(auctionCentralWithOneAuction.isStartDateAfterMinDaysAway(
										defaultFutureAuction.getStartDate()));
	}
	
	@Test
	public void isStartDateAfterMinDaysAway_ExactlyMin_TRUE() {
		assertTrue(auctionCentralWithOneAuction.isStartDateAfterMinDaysAway(
										auctionMinDaysFromNow.getStartDate()));
	}
	
	@Test
	public void isStartDateAfterMinDaysAway_LessThanMin_FALSE() {
		assertFalse(auctionCentralWithOneAuction.isStartDateAfterMinDaysAway(
										auctionMinDaysMinusOne.getStartDate()));
	}
	
	@Test
	public void isStartDateBeforeMaxDaysAway_MoreThanMax_FALSE() {
		assertFalse(auctionCentralWithOneAuction.isDateBeforeMaxDaysAway(
				auctionMaxDaysPlusOne.getStartDate(), auctionMaxDaysPlusOne.getEndDate()));
	}
	
	@Test
	public void sStartDateBeforeMaxDaysAway_ExactlyMax_TRUE() {
		assertTrue(auctionCentralWithOneAuction.isDateBeforeMaxDaysAway(
				auctionMaxDaysFromNow.getStartDate(), auctionMaxDaysFromNow.getEndDate()));
	}
	
	@Test
	public void sStartDateBeforeMaxDaysAway_LessThanMax_TRUE() {
		assertTrue(auctionCentralWithOneAuction.isDateBeforeMaxDaysAway(
				defaultFutureAuction.getStartDate(), defaultFutureAuction.getEndDate()));
	}
}
