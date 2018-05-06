package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

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
	private static final String USERNAME = "bidder1";
	private static final String NAME = "Jack";
	private Bidder bidder;
	private Auction futureAuction;
	private Auction pastAuction;
	private Auction presentAuction;
	private Item dress;
	private double bidMoreThanMin = 600;
	private double bidLessThanMin = Bidder.MIN_AMOUNT_BID_PER_ITEM - 1;
	@Before
	public void setUp() throws Exception {
		futureAuction = new Auction(AUCTION_START_DATE, AUCTION_END_DATE, AUCTION_START_TIME, AUCTION_END_TIME);
		bidder = new Bidder(USERNAME, NAME);
		dress = new Item("dress", bidMoreThanMin, "Pretty", 1);
		futureAuction.addItem(dress);
	}

	@Test
	public void getAllAuctions_NoExistingAuction_EmptyList() {
		assertTrue(bidder.getAllAuctions().isEmpty());
	}
	
	@Test
	public void getAllAuctions_ExistingAuction_ListOfAuctions() {
		bidder.makeBid(dress, futureAuction, bidMoreThanMin);
		assertTrue(!(bidder.getAllAuctions().isEmpty()));
	}
	
	@Test
	public void getAllItemsInOneAuction_NoItemsInAuction_EmptyList() {
		assertTrue(bidder.getAllItemsInOneAuction(futureAuction).isEmpty());
	}
	
	@Test
	public void getAllItemsInOneAuction_WithItemsInAuction_ListOfItems() {
		bidder.makeBid(dress, futureAuction, bidMoreThanMin);
		assertTrue(!(bidder.getAllItemsInOneAuction(futureAuction).isEmpty()));
	}
	
	@Test
	public void getAllIntemsInAllAuctions_NoAuction_EmptyList() {
		assertTrue(bidder.getAllIntemsInAllAuctions().isEmpty());
	}
	
	@Test
	public void getAllIntemsInAllAuctions_WithItemsInAuction_ListOfItems() {
		bidder.makeBid(dress, futureAuction, bidMoreThanMin);
		assertTrue(!(bidder.getAllIntemsInAllAuctions().isEmpty()));
	}
	
	@Test
	public void getAllIntemsInAllAuctions_WithItemsInAuction_CheckItem_ListOfItems() {
		bidder.makeBid(dress, futureAuction, bidMoreThanMin);
		assertTrue(bidder.getAllIntemsInAllAuctions().get(0).getItemName().equals("dress"));
	}
	
	@Test
	public void makeBid_BidLessThanMin_returnErrorNumber2() {
		int bidError = bidder.makeBid(dress, futureAuction, bidLessThanMin);
		assertEquals(2, bidError);
	}
	
	@Test
	public void makeBid_LimitBidPerAuctionReached_returnErrorNumber3() {
		Item shoes = new Item();
		Item sunglasses = new Item();
		Item lamp = new Item();
		Item book = new Item();;
		bidder.makeBid(dress, futureAuction, bidMoreThanMin);
		bidder.makeBid(lamp, futureAuction, bidMoreThanMin);
		bidder.makeBid(book, futureAuction, bidMoreThanMin);
		bidder.makeBid(shoes, futureAuction, bidMoreThanMin);
		int bidError = bidder.makeBid(sunglasses, futureAuction, bidMoreThanMin);
		assertEquals(3, bidError);
	}


}
