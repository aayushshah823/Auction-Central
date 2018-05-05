package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.Bidder;
import model.Item;

public class BidderTest {

	private static LocalDate today = LocalDate.now();
	private static final LocalDate AUCTION_START_DATE = today.plusDays(2);
	private static final LocalTime AUCTION_START_TIME = LocalTime.NOON;
	private static final LocalDate AUCTION_END_DATE = AUCTION_START_DATE;
	private static final LocalTime AUCTION_END_TIME = AUCTION_START_TIME.plusHours(4);
	private Bidder bidder;
	private Auction auction;
	private Item dress;
	private double bid = 600;
	@Before
	public void setUp() throws Exception {
		auction = new Auction(AUCTION_START_DATE, AUCTION_END_DATE, AUCTION_START_TIME, AUCTION_END_TIME);
		bidder = new Bidder();
		dress = new Item();
	}

	@Test
	public void getAllAuctions_NoExistingAuction_EmptyList() {
		assertTrue(bidder.getAllAuctions().isEmpty());
	}
	
//	@Test
//	public void getAllAuctions_ExistingAuction_AuctionList() {
//		bidder.makeBid(dress, auction, bid);
//		assertTrue(!bidder.getAllAuctions().isEmpty());
//	}


}
