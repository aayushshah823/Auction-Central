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

public class BidderTest {

	private static LocalDate today = LocalDate.now();
	private static final LocalDate AUCTION_START_DATE = today.plusDays(2);
	private static final LocalTime AUCTION_START_TIME = LocalTime.NOON;
	private static final LocalDate AUCTION_END_DATE = AUCTION_START_DATE;
	private static final LocalTime AUCTION_END_TIME = AUCTION_START_TIME.plusHours(4);
	private static final String USERNAME = "bidder1";
	private static final String NAME = "Jack";
	private static final String FUTURE_NONPROFIT_NAME = "nonprofit for all";
	private Bidder bidder;
	private Auction futureAuction;
	private Item dress;
	private double bidMoreThanMin = 600;
	private double bidLessThanMin = Bidder.MIN_AMOUNT_BID_PER_ITEM - 1;
	AuctionCentral ac;
	@Before
	public void setUp() throws Exception {
		futureAuction = new Auction(AUCTION_START_DATE, AUCTION_END_DATE, AUCTION_START_TIME, AUCTION_END_TIME);
		futureAuction.setAuctionName(FUTURE_NONPROFIT_NAME);
		bidder = new Bidder(USERNAME, NAME);
		dress = new Item("dress", bidMoreThanMin, "Pretty", 1);
		futureAuction.addItem(dress);
		ac = new AuctionCentral();
		
		//ADD THIS TO AUCTIONCENTRAL -- CREATE SEVERAL NONPROFITS AND ADD AUCTIONS
		Auction auction1 = new Auction(AUCTION_START_DATE.plusDays(1), 
				AUCTION_END_DATE.plusDays(1), AUCTION_START_TIME, AUCTION_END_TIME);
		auction1.setAuctionName("auction1");
		Auction auction2 = new Auction(AUCTION_START_DATE.plusDays(2), 
				AUCTION_END_DATE.plusDays(2), AUCTION_START_TIME, AUCTION_END_TIME);
		Auction auction3 = new Auction(AUCTION_START_DATE.plusDays(3), 
				AUCTION_END_DATE.plusDays(3), AUCTION_START_TIME, AUCTION_END_TIME);
		Auction auction4 = new Auction(AUCTION_START_DATE.plusDays(4), 
				AUCTION_END_DATE.plusDays(4), AUCTION_START_TIME, AUCTION_END_TIME);
		Auction auction5 = new Auction(AUCTION_START_DATE.plusDays(5), 
				AUCTION_END_DATE.plusDays(5), AUCTION_START_TIME, AUCTION_END_TIME);
		Auction auction6 = new Auction(AUCTION_START_DATE.plusDays(6), 
				AUCTION_END_DATE.plusDays(6), AUCTION_START_TIME, AUCTION_END_TIME);
		Auction auction7 = new Auction(AUCTION_START_DATE.plusDays(7), 
				AUCTION_END_DATE.plusDays(7), AUCTION_START_TIME, AUCTION_END_TIME);
		Auction auction8 = new Auction(AUCTION_START_DATE.plusDays(8), 
				AUCTION_END_DATE.plusDays(8), AUCTION_START_TIME, AUCTION_END_TIME);
		Auction auction9 = new Auction(AUCTION_START_DATE.plusDays(9), 
				AUCTION_END_DATE.plusDays(9), AUCTION_START_TIME, AUCTION_END_TIME);
		Auction auction10 = new Auction(AUCTION_START_DATE.plusDays(10), 
				AUCTION_END_DATE.plusDays(10), AUCTION_START_TIME, AUCTION_END_TIME);
		Auction auction11 = new Auction(AUCTION_START_DATE.plusDays(11), 
				AUCTION_END_DATE.plusDays(11), AUCTION_START_TIME, AUCTION_END_TIME);
       
		Item shoes = new Item("shoes", bidMoreThanMin, "Pretty", 1);
		Item sunglasses = new Item("sunglasses", bidMoreThanMin, "Pretty", 1);
		Item lamp = new Item("lamp", bidMoreThanMin, "Pretty", 1);
		Item book = new Item("book", bidMoreThanMin, "Pretty", 1);
	}

	@Test
	public void getAllAuctions_NoExistingAuction_EmptyList() {
		assertTrue(bidder.getAllAuctions().isEmpty());
	}
	
	@Test
	public void getAllAuctions_ExistingAuction_ListOfAuctions() {
		bidder.makeBid("dress", FUTURE_NONPROFIT_NAME, bidMoreThanMin, ac);
		assertTrue(!(bidder.getAllAuctions().isEmpty()));
	}
	
	@Test
	public void getAllItemsInOneAuction_NoItemsInAuction_EmptyList() {
		assertTrue(bidder.getAllItemsInOneAuction(futureAuction).isEmpty());
	}
	
	@Test
	public void getAllItemsInOneAuction_WithItemsInAuction_ListOfItems() {
		bidder.makeBid("dress", FUTURE_NONPROFIT_NAME, bidMoreThanMin, ac);
		assertTrue(!(bidder.getAllItemsInOneAuction(futureAuction).isEmpty()));
	}
	
	@Test
	public void getAllIntemsInAllAuctions_NoAuction_EmptyList() {
		assertTrue(bidder.getAllIntemsInAllAuctions().isEmpty());
	}
	
	@Test
	public void getAllIntemsInAllAuctions_WithItemsInAuction_ListOfItems() {
		bidder.makeBid("dress", FUTURE_NONPROFIT_NAME, bidMoreThanMin, ac);
		assertTrue(!(bidder.getAllIntemsInAllAuctions().isEmpty()));
	}
	
	@Test
	public void getAllIntemsInAllAuctions_WithItemsInAuction_CheckItem_ListOfItems() {
		bidder.makeBid("dress", FUTURE_NONPROFIT_NAME, bidMoreThanMin, ac);
		assertTrue(bidder.getAllIntemsInAllAuctions().get(0).getItemName().equals("dress"));
	}
	
	@Test
	public void makeBid_BidLessThanMin_returnErrorNumber2() {		
		int bidError = bidder.makeBid("dress", FUTURE_NONPROFIT_NAME, bidLessThanMin, ac);
		assertEquals(2, bidError);
	}
	
	@Test
	public void makeBid_LimitBidPerAuctionReached_returnErrorNumber3() {
		bidder.makeBid("dress", FUTURE_NONPROFIT_NAME, bidMoreThanMin, ac);
		bidder.makeBid("lamp", FUTURE_NONPROFIT_NAME, bidMoreThanMin, ac);
		bidder.makeBid("book", FUTURE_NONPROFIT_NAME, bidMoreThanMin, ac);
		bidder.makeBid("shoes", FUTURE_NONPROFIT_NAME, bidMoreThanMin, ac);
		int bidError = bidder.makeBid("sunglasses", FUTURE_NONPROFIT_NAME, bidMoreThanMin, ac);
		assertEquals(3, bidError);
	}
	
	@Test
	public void makeBid_SuccesfulBid_returnErrorNumber1() {
		Item sunglasses = new Item();
		Item lamp = new Item();
		bidder.makeBid("dress", FUTURE_NONPROFIT_NAME, bidMoreThanMin, ac);
		bidder.makeBid("lamp", FUTURE_NONPROFIT_NAME, bidMoreThanMin, ac);		
		int bidError = bidder.makeBid("sunglasses", FUTURE_NONPROFIT_NAME, bidMoreThanMin, ac);
		
		assertEquals(1, bidError);
	}
	
	@Test
	public void makeBid_LimitTotalBidsReached_returnErrorNumber4() {

		bidder.makeBid("dress", "auction1", bidMoreThanMin, ac);
		bidder.makeBid("dress", "auction2", bidMoreThanMin, ac);
		bidder.makeBid("dress", "auction3", bidMoreThanMin, ac);
		bidder.makeBid("dress", "auction4", bidMoreThanMin, ac);
		bidder.makeBid("dress", "auction5", bidMoreThanMin, ac);
		bidder.makeBid("dress", "auction6", bidMoreThanMin, ac);
		bidder.makeBid("dress", "auction7", bidMoreThanMin, ac);
		bidder.makeBid("dress", "auction8", bidMoreThanMin, ac);
		bidder.makeBid("dress", "auction9", bidMoreThanMin, ac);
		bidder.makeBid("dress", "auction10", bidMoreThanMin, ac);
		int bidError = bidder.makeBid("dress", "auction11", bidMoreThanMin, ac);
		assertEquals(4, bidError);
	}
	
	@Test
	public void makeBid_BetOnTheSameItemMultipleTimes_BetNumberDoesntIncrease_ItemListSize1() {
		bidder.makeBid("dress", "auction1", bidMoreThanMin, ac);
		bidder.makeBid("dress", "auction1", bidMoreThanMin + 10, ac);
		bidder.makeBid("dress", "auction1", bidMoreThanMin + 20, ac);
		bidder.makeBid("dress", "auction1", bidMoreThanMin + 30, ac);
		bidder.makeBid("dress", "auction1", bidMoreThanMin + 40, ac);
		bidder.makeBid("dress", "auction1", bidMoreThanMin + 50, ac);
		bidder.makeBid("dress", "auction1", bidMoreThanMin + 60, ac);
		bidder.makeBid("dress", "auction1", bidMoreThanMin + 70, ac);	
	
		
		assertEquals(1, bidder.myTotalBidPerAuction(futureAuction) - 1);
		
	}


}
