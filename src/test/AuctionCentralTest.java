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
	private static final LocalDate AUCTION_START_DATE = today.plusDays(2);
	private static final LocalTime AUCTION_START_TIME = LocalTime.NOON;
	private static final LocalDate AUCTION_END_DATE = AUCTION_START_DATE;
	private static final LocalTime AUCTION_END_TIME = AUCTION_START_TIME.plusHours(4);
	NonProfit nonProfit1;
	NonProfit nonProfit2;
	NonProfit nonProfit3;
	NonProfit nonProfit4;
	NonProfit nonProfit5;
	Auction auction1; 
	Auction auction2; 
	Auction auction3; 
	Auction auction4; 
	Auction auction5;
	Auction pastAuction;
	public AuctionCentral central = new AuctionCentral();

	@Before
	public void setUp() throws Exception {
		nonProfit1 = new NonProfit("organization1", "nonProfitName1");
		nonProfit2 = new NonProfit("organization2", "nonProfitName2");
		nonProfit3 = new NonProfit("organization3", "nonProfitName3");
		nonProfit4 = new NonProfit("organization4", "nonProfitName4");
		nonProfit5 = new NonProfit("organization5", "nonProfitName5");
		auction1 = new Auction(AUCTION_START_DATE.plusDays(1), AUCTION_END_DATE.plusDays(1), AUCTION_START_TIME, AUCTION_END_TIME); 
		auction2 = new Auction(AUCTION_START_DATE.plusDays(2), AUCTION_END_DATE.plusDays(2), AUCTION_START_TIME, AUCTION_END_TIME); 
		auction3 = new Auction(AUCTION_START_DATE.plusDays(3), AUCTION_END_DATE.plusDays(3), AUCTION_START_TIME, AUCTION_END_TIME); 
		auction4 = new Auction(AUCTION_START_DATE.plusDays(4), AUCTION_END_DATE.plusDays(4), AUCTION_START_TIME, AUCTION_END_TIME); 
		auction5 = new Auction(AUCTION_START_DATE.plusDays(5), AUCTION_END_DATE.plusDays(5), AUCTION_START_TIME, AUCTION_END_TIME); 
		pastAuction = new Auction(today.minusDays(2), today.minusDays(2), AUCTION_START_TIME, AUCTION_END_TIME);

	}

	@Test
	public void addNonprofit_successfully_nonprofitListIncreases() {
		central.addNonprofit(nonProfit1);
		assertEquals(1, central.getAllNonProfits().size());
	}
	
	@Test
	public void isDateValidForBid_InvalidDate_false() {
		assertFalse(central.isDateValidForBid(pastAuction));
	}
	
	@Test
	public void isDateValidForBid_ValidDate_True() {
		assertTrue(central.isDateValidForBid(auction1));
	}
	
	@Test
	public void DisplayFutureAuctions_FutureAuctionsExist_AuctionListSize() {
		central.addNonprofit(nonProfit1);
		central.addNonprofit(nonProfit2);
		central.addNonprofit(nonProfit3);
		central.addNonprofit(nonProfit4);
		central.addNonprofit(nonProfit5);
		nonProfit1.addAuction(auction1);
		nonProfit2.addAuction(auction2);
		nonProfit3.addAuction(auction3);
		nonProfit4.addAuction(auction4);
		nonProfit5.addAuction(auction5);
		
		assertEquals(5, central.displayFutureAuctions());
	}


}
