package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
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

	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
