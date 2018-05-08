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
	private NonProfit nonProfit1;
	private NonProfit nonProfit2;
	private NonProfit nonProfit3;
	private NonProfit nonProfit4;
	private NonProfit nonProfit5;
	AuctionCentral ac;
	@Before
	public void setUp() throws Exception {
		futureAuction = new Auction(AUCTION_START_DATE, AUCTION_END_DATE, AUCTION_START_TIME, AUCTION_END_TIME);
		futureAuction.setAuctionName("futureAuction");
		bidder = new Bidder(USERNAME, NAME);
		dress = new Item("dress", bidMoreThanMin, "Pretty", 1);
		Item shoes = new Item("shoes", bidMoreThanMin, "Pretty", 1);
		Item sunglasses = new Item("sunglasses", bidMoreThanMin, "Pretty", 1);
		Item lamp = new Item("lamp", bidMoreThanMin, "Pretty", 1);
		Item book = new Item("book", bidMoreThanMin, "Pretty", 1);
		
		futureAuction.addItem(dress);
		futureAuction.addItem(shoes);
		futureAuction.addItem(sunglasses);
		futureAuction.addItem(lamp);
		futureAuction.addItem(book);
		
		ac = new AuctionCentral();		
		nonProfit1 = new NonProfit("organization1", "nonProfitName1");
		ac.addNonprofit(nonProfit1);
		nonProfit1.addAuction(futureAuction);
	
		Auction auction1 = new Auction(AUCTION_START_DATE.plusDays(1), 
				AUCTION_END_DATE.plusDays(1), AUCTION_START_TIME,
				AUCTION_END_TIME);
		auction1.setAuctionName("auction1");
		auction1.addItem(dress);
		Auction auction2 = new Auction(AUCTION_START_DATE.plusDays(2), 
				AUCTION_END_DATE.plusDays(2), AUCTION_START_TIME, 
				AUCTION_END_TIME);
		auction2.setAuctionName("auction2");
		auction2.addItem(dress);
		Auction auction3 = new Auction(AUCTION_START_DATE.plusDays(3), 
				AUCTION_END_DATE.plusDays(3), AUCTION_START_TIME, 
				AUCTION_END_TIME);
		auction3.setAuctionName("auction3");
		auction3.addItem(dress);
		Auction auction4 = new Auction(AUCTION_START_DATE.plusDays(4), 
				AUCTION_END_DATE.plusDays(4), AUCTION_START_TIME, 
				AUCTION_END_TIME);
		auction4.setAuctionName("auction4");
		auction4.addItem(dress);
		Auction auction5 = new Auction(AUCTION_START_DATE.plusDays(5), 
				AUCTION_END_DATE.plusDays(5), AUCTION_START_TIME,
				AUCTION_END_TIME);
		auction5.setAuctionName("auction5");
		auction5.addItem(dress);
		Auction auction6 = new Auction(AUCTION_START_DATE.plusDays(6), 
				AUCTION_END_DATE.plusDays(6), AUCTION_START_TIME, 
				AUCTION_END_TIME);
		auction6.setAuctionName("auction6");
		auction6.addItem(dress);
		Auction auction7 = new Auction(AUCTION_START_DATE.plusDays(7), 
				AUCTION_END_DATE.plusDays(7), AUCTION_START_TIME,
				AUCTION_END_TIME);
		auction7.setAuctionName("auction7");
		auction7.addItem(dress);
		Auction auction8 = new Auction(AUCTION_START_DATE.plusDays(8), 
				AUCTION_END_DATE.plusDays(8), AUCTION_START_TIME,
				AUCTION_END_TIME);
		auction8.setAuctionName("auction8");
		auction8.addItem(dress);
		Auction auction9 = new Auction(AUCTION_START_DATE.plusDays(9), 
				AUCTION_END_DATE.plusDays(9), AUCTION_START_TIME, 
				AUCTION_END_TIME);
		auction9.setAuctionName("auction9");
		auction9.addItem(dress);
		Auction auction10 = new Auction(AUCTION_START_DATE.plusDays(10), 
				AUCTION_END_DATE.plusDays(10), AUCTION_START_TIME,
				AUCTION_END_TIME);
		auction10.setAuctionName("auction10");
		auction10.addItem(dress);
		Auction auction11 = new Auction(AUCTION_START_DATE.plusDays(11), 
				AUCTION_END_DATE.plusDays(11), AUCTION_START_TIME,
				AUCTION_END_TIME);
		auction11.setAuctionName("auction11");
		auction11.addItem(dress);
       
		nonProfit1.addAuction(auction1);
		nonProfit1.addAuction(auction2);
		nonProfit1.addAuction(auction3);
		nonProfit1.addAuction(auction4);
		nonProfit1.addAuction(auction5);
		nonProfit1.addAuction(auction6);
		nonProfit1.addAuction(auction7);
		nonProfit1.addAuction(auction8);
		nonProfit1.addAuction(auction9);
		nonProfit1.addAuction(auction10);
		nonProfit1.addAuction(auction11);
	}

	@Test
	public void getAllAuctions_NoExistingAuction_EmptyList() {
		assertTrue(bidder.getAllAuctions().isEmpty());
	}
	
	@Test
	public void getAllAuctions_ExistingAuction_ListOfAuctions() {
		
		bidder.makeBid("dress", "futureAuction", bidMoreThanMin, ac);
		assertTrue(!(bidder.getAllAuctions().isEmpty()));
	}
	
	@Test
	public void getAllItemsInOneAuction_NoItemsInAuction_EmptyList() {
		assertTrue(bidder.getAllItemsInOneAuction(futureAuction).isEmpty());
	}
	
	@Test
	public void getAllItemsInOneAuction_WithItemsInAuction_ListOfItems() {
		bidder.makeBid("dress", "futureAuction", bidMoreThanMin, ac);
		assertTrue(!(bidder.getAllItemsInOneAuction(futureAuction).isEmpty()));
	}
	
	@Test
	public void getAllIntemsInAllAuctions_NoAuction_EmptyList() {
		assertTrue(bidder.getAllIntemsInAllAuctions().isEmpty());
	}
	
	@Test
	public void getAllIntemsInAllAuctions_WithItemsInAuction_ListOfItems() {
		bidder.makeBid("dress", "futureAuction", bidMoreThanMin, ac);
		assertTrue(!(bidder.getAllIntemsInAllAuctions().isEmpty()));
	}
	
	@Test
	public void getAllIntemsInAllAuctions_WithItemsInAuction_CheckItem_ListOfItems() {
		bidder.makeBid("dress", "futureAuction", bidMoreThanMin, ac);
		assertTrue(bidder.getAllIntemsInAllAuctions().get(0).getItemName().equals("dress"));
	}
	
	@Test
	public void makeBid_BidLessThanMin_returnErrorNumber2() {		
		int bidError = bidder.makeBid("dress",  "futureAuction", bidLessThanMin, ac);
		assertEquals(2, bidError);
	}
	
	@Test
	public void makeBid_LimitBidPerAuctionReached_returnErrorNumber3() {
		bidder.makeBid("dress", "futureAuction", bidMoreThanMin, ac);
		bidder.makeBid("lamp", "futureAuction", bidMoreThanMin, ac);
		bidder.makeBid("book", "futureAuction", bidMoreThanMin, ac);
		bidder.makeBid("shoes", "futureAuction", bidMoreThanMin, ac);
		int bidError = bidder.makeBid("sunglasses", "futureAuction", bidMoreThanMin, ac);
		assertEquals(3, bidError);
	}
	
	@Test
	public void makeBid_SuccesfulBid_returnErrorNumber1() {
		
		bidder.makeBid("dress", "futureAuction", bidMoreThanMin, ac);
		bidder.makeBid("lamp", "futureAuction", bidMoreThanMin, ac);		
		int bidError = bidder.makeBid("sunglasses", "futureAuction", bidMoreThanMin, ac);
		
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
//		bidder.makeBid("dress", "auction1", bidMoreThanMin + 10, ac);
//		bidder.makeBid("dress", "auction1", bidMoreThanMin + 20, ac);
//		bidder.makeBid("dress", "auction1", bidMoreThanMin + 30, ac);
//		bidder.makeBid("dress", "auction1", bidMoreThanMin + 40, ac);
//		bidder.makeBid("dress", "auction1", bidMoreThanMin + 50, ac);
//		bidder.makeBid("dress", "auction1", bidMoreThanMin + 60, ac);
//		bidder.makeBid("dress", "auction1", bidMoreThanMin + 70, ac);	
//	
		
		assertEquals(1, bidder.myTotalBidPerAuction(futureAuction));
		
	}


}
