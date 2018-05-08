package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.AuctionCentral;
import model.Item;
import model.NonProfit;

public class AuctionCentralTest {
	
	static LocalDate today = LocalDate.now();
	private static final LocalDate AUCTION_START_DATE_VALID = today.plusDays(14);
	private static final LocalTime AUCTION_START_TIME = LocalTime.NOON;
	private static final LocalDate AUCTION_END_DATE_VALID = AUCTION_START_DATE_VALID;
	private static final LocalTime AUCTION_END_TIME = AUCTION_START_TIME.plusHours(4);
	private static final LocalDate AUCTION_6_MONTHS_AGO = LocalDate.now().minusMonths(6);
	
	
	
	NonProfit nonProfitForFutureAuction1;
	NonProfit nonProfitForFutureAuction2;
	NonProfit nonProfitForFutureAuction3;
	NonProfit nonProfitForFutureAuction4;
	NonProfit nonProfitForFutureAuction5;
	NonProfit beenLessThanYearSinceLastAuction;
	
	Auction futureAuction1; 
	Auction futureAuction2; 
	Auction futureAuction3; 
	Auction futureAuction4; 
	Auction futureAuction5;
	Auction pastAuction;
	Auction validAuctionforNonProfit;
	Auction invalidAuctionforNonProfit;
	
	AuctionCentral auctionCentralWithSeveralFutureAuctions;
	AuctionCentral auctionCentralWithMaxTotalAuctions;
	AuctionCentral auctionCentralWithMaxAuctionsOnOneDay;
	AuctionCentral auctionCentral;
	

	@Before
	public void setUp() throws Exception {
		auctionCentral = new AuctionCentral();
		auctionCentralWithSeveralFutureAuctions = new AuctionCentral();
		auctionCentralWithMaxTotalAuctions = new AuctionCentral();
		auctionCentralWithMaxAuctionsOnOneDay = new AuctionCentral();
		
		
		nonProfitForFutureAuction1 = new NonProfit("organization1", "nonProfitName1");
		nonProfitForFutureAuction2 = new NonProfit("organization2", "nonProfitName2");
		nonProfitForFutureAuction3 = new NonProfit("organization3", "nonProfitName3");
		nonProfitForFutureAuction4 = new NonProfit("organization4", "nonProfitName4");
		nonProfitForFutureAuction5 = new NonProfit("organization5", "nonProfitName5");
		beenLessThanYearSinceLastAuction = new NonProfit("org6", "name6");
		
		
		validAuctionforNonProfit = new Auction(AUCTION_6_MONTHS_AGO, AUCTION_6_MONTHS_AGO, AUCTION_START_TIME, AUCTION_END_TIME);
		futureAuction1 = new Auction(AUCTION_START_DATE_VALID.plusDays(1), AUCTION_END_DATE_VALID.plusDays(1), AUCTION_START_TIME, AUCTION_END_TIME); 
		futureAuction2 = new Auction(AUCTION_START_DATE_VALID.plusDays(2), AUCTION_END_DATE_VALID.plusDays(2), AUCTION_START_TIME, AUCTION_END_TIME); 
		futureAuction3 = new Auction(AUCTION_START_DATE_VALID.plusDays(3), AUCTION_END_DATE_VALID.plusDays(3), AUCTION_START_TIME, AUCTION_END_TIME); 
		futureAuction4 = new Auction(AUCTION_START_DATE_VALID.plusDays(4), AUCTION_END_DATE_VALID.plusDays(4), AUCTION_START_TIME, AUCTION_END_TIME); 
		futureAuction5 = new Auction(AUCTION_START_DATE_VALID.plusDays(5), AUCTION_END_DATE_VALID.plusDays(5), AUCTION_START_TIME, AUCTION_END_TIME); 
		pastAuction = new Auction(today.minusDays(2), today.minusDays(2), AUCTION_START_TIME, AUCTION_END_TIME);
		
		for (int i = 0; i < 25; i++) {
			Auction fillerAuction = new Auction(AUCTION_START_DATE_VALID.plusDays(i), AUCTION_END_DATE_VALID.plusDays(i), AUCTION_START_TIME, AUCTION_END_TIME);
			NonProfit fillerNonProfit = new NonProfit("filler Org", "filler nonProfit");
			auctionCentralWithMaxTotalAuctions.addNonprofit(fillerNonProfit);
			auctionCentralWithMaxTotalAuctions.addAuction(fillerNonProfit, fillerAuction);
		}

	}

	@Test
	public void addNonprofit_successfully_nonprofitListIncreases() {
		auctionCentral.addNonprofit(nonProfitForFutureAuction1);
		assertEquals(1, auctionCentral.getAllNonProfits().size());
	}
	
	@Test
	public void isDateValidForBid_InvalidDate_false() {
		assertFalse(auctionCentral.isDateValidForBid(pastAuction));
	}
	
	@Test
	public void isDateValidForBid_ValidDate_True() {
		assertTrue(auctionCentral.isDateValidForBid(futureAuction1));
	}
	
	@Test
	public void getFutureAuctions_FutureAuctionsExist_AuctionListSize() {
		auctionCentral.addNonprofit(nonProfitForFutureAuction1);
		auctionCentral.addNonprofit(nonProfitForFutureAuction2);
		auctionCentral.addNonprofit(nonProfitForFutureAuction3);
		auctionCentral.addNonprofit(nonProfitForFutureAuction4);
		auctionCentral.addNonprofit(nonProfitForFutureAuction5);
		nonProfitForFutureAuction1.addAuction(futureAuction1);
		nonProfitForFutureAuction2.addAuction(futureAuction2);
		nonProfitForFutureAuction3.addAuction(futureAuction3);
		nonProfitForFutureAuction4.addAuction(futureAuction4);
		nonProfitForFutureAuction5.addAuction(futureAuction5);
		
		assertEquals(5, auctionCentral.getFutureAuctions().size());
	}
	
	@Test
	public void isLessThanMaxAuctionsScheduled_FALSE() {
		System.out.println(auctionCentralWithMaxTotalAuctions.getNumCurrentAuctions());
		assertFalse(auctionCentralWithMaxTotalAuctions.isLessThanMaxAuctionsScheduled());
	}

	@Test
	public void isYearSinceLastAuction_FALSE() {
		auctionCentral.addAuction(beenLessThanYearSinceLastAuction, validAuctionforNonProfit);
		System.out.println(beenLessThanYearSinceLastAuction.getLastAuctionDate());
		assertFalse(auctionCentral.isYearSinceLastAuction(beenLessThanYearSinceLastAuction));
	}
}
