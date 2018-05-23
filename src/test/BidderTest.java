package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.AuctionCentral;
import model.Bidder;
import model.Item;
import model.NonProfit;

public class BidderTest {

	private static LocalDate today = LocalDate.now();
	private Bidder bidder = new Bidder("bidderTest", "Test Jones");
	private static final LocalDate AUCTION_START_DATE = today.plusDays(2);
	private static final LocalTime AUCTION_START_TIME = LocalTime.NOON;
	private static final LocalTime AUCTION_END_TIME = AUCTION_START_TIME.plusHours(5);
	private static final int ITEM_PRICE = 10;
	private Auction futureAuction = new Auction(AUCTION_START_DATE, AUCTION_START_TIME,
			AUCTION_END_TIME, "Future Auction");
	private Item dress = new Item("dress", ITEM_PRICE, "Pretty", 1);
	
	@Test
	public void getBiddingHistory_NoBiddingHistory_EmptyList() {
		assertTrue(bidder.getAllAuctionsIveBidOn().isEmpty());
	}
	@Test
	public void getBiddingHistory_NoBiddingHistory_NotEmptyList() {
		double bidAmount = ITEM_PRICE + 10;
		bidder.addNewToBiddingHistory(futureAuction, dress, bidAmount);
		assertTrue(!bidder.getAllAuctionsIveBidOn().isEmpty());
	}
	
	@Test
	public void addNewToBiddingHistory_oneBid_AddOneItem_MapNotEmpty() {
		double bidAmount = ITEM_PRICE + 10;
		bidder.addNewToBiddingHistory(futureAuction, dress, bidAmount);
		assertTrue(!bidder.getAllAuctionsIveBidOn().isEmpty());
	}
	
	@Test
	public void getBidsInOneAuction_OneItem_returnTrue() {
		double bidAmount = ITEM_PRICE + 10;
		bidder.addNewToBiddingHistory(futureAuction, dress, bidAmount);		
		assertTrue(bidder.getBidsInOneAuction(futureAuction).size() == 1);
	}
	
	@Test
	public void getAllIntemsInAllAuctions_getBidsInOneAuction_OneItem() {
		double bidAmount = ITEM_PRICE + 10;
		bidder.addNewToBiddingHistory(futureAuction, dress, bidAmount);		
		assertTrue(bidder.getBidsInOneAuction(futureAuction).size() == 1);
	}
	@Test
	public void getTotalNumberOfBids_OneBid_One() {
		double bidAmount = ITEM_PRICE + 10;
		bidder.addNewToBiddingHistory(futureAuction, dress, bidAmount);		
		assertTrue(bidder.getTotalNumberOfBids() == 1);
	}



}
