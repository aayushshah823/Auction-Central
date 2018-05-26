package test;

import static org.junit.Assert.*;

import java.awt.ItemSelectable;
import java.awt.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.Bidder;
import model.Item;
/**
 * 
 * @author Benjamin Yuen
 * @version May 4, 2018
 */
public class AuctionTest {
	
	private static final LocalDate AUCTION_START_DATE= LocalDate.now();
	private static final LocalTime AUCTION_START_TIME= LocalTime.now();
	private static final LocalTime AUCTION_END_TIME= LocalTime.now().plusHours(5);
	private static final LocalDate NEW_AUCTION_START_DATE = AUCTION_START_DATE;
	private static final LocalTime NEW_AUCTION_START_TIME= LocalTime.MIDNIGHT;
	private static final LocalTime NEW_AUCTION_END_TIME= LocalTime.NOON;
	private static final double START_BIDPRICE_BAT = 30;
	private static final double START_BIDPRICE_BALL = 30;
	private static final int ITEM_COUNT_BAT = 1;
	private static final int ITEM_COUNT_BALL = 3;
	private static final String AUCTION_NAME_GOODW = "Goodwill";
	private static final String AUCTION_NAME_SAL = "Salvation Army";

	private Auction auction;
	private Item batItem;
	private Item ballItem;
	private Bidder bidder;
	private ArrayList<Item> itemsList;

	@Before
	public void setUp() throws Exception {
		auction = new Auction(AUCTION_START_DATE, AUCTION_START_TIME, AUCTION_END_TIME, AUCTION_NAME_GOODW);
		batItem = new Item("bat", START_BIDPRICE_BAT, "light, high-quality", ITEM_COUNT_BAT);
		ballItem = new Item("ball", START_BIDPRICE_BALL, "round, white", ITEM_COUNT_BALL);
		bidder = new Bidder("bidder3", "Steve");
		itemsList = new ArrayList<>();
		auction.setAuctionName(AUCTION_NAME_GOODW);
	}
	
	@Test
	public void getAuctionName_GoodwillAuction_equals() {
		assertEquals(AUCTION_NAME_GOODW, auction.getAuctionName());
	}
	
	@Test
	public void setAuctionName_SalArmy_equals() {
		auction.setAuctionName(AUCTION_NAME_SAL);
		assertEquals(AUCTION_NAME_SAL, auction.getAuctionName());
	}
	
	@Test
	public void addItem_AddedItems_True() {
		auction.addItem(batItem);
		auction.addItem(ballItem);
		assertEquals(2, auction.getItems().size());
	}
	
	@Test
	public void addItem_NoItemsAdded_True() {
		assertEquals(0, auction.getItems().size());
	}
	
	@Test
	public void isAddItemValid_EmptyList_true() {
		assertTrue(auction.isAddItemValid());
	}
	
	@Test
	public void isAddItemValid_FullItemList_false() {
		for (int i = 1; i <= 11; i++) {
			auction.addItem(batItem);
		}
		assertFalse(auction.isAddItemValid());
	}
		
	@Test
	public void getItems_NonEmptyList_true() {
		auction.addItem(batItem);
		assertTrue(!auction.getItems().isEmpty());
	}
	
	@Test
	public void getItems_EmptyList_true() {
		assertTrue(auction.getItems().isEmpty());
	}
	
	@Test
	public void setMyItems_SetEmptyList_True() {
		auction.setMyItems(new ArrayList<Item>());
		assertEquals(0, auction.getItems().size());
	}
	
	@Test
	public void setMyItems_SetNonEmptyList_True() {
		itemsList.add(batItem);
		itemsList.add(ballItem);
		auction.setMyItems(itemsList);
		assertEquals(2, auction.getItems().size());
	}
	
	@Test
	public void getStartDate_StartDateEqual_equal() {
		assertEquals(AUCTION_START_DATE, auction.getStartDate());
	}
	
	@Test
	public void setStartDate_StartDateEqual_equal() {
		auction.setStartDate(NEW_AUCTION_START_DATE);
		assertEquals(NEW_AUCTION_START_DATE, auction.getStartDate());
	}
	
	@Test
	public void getStartTime_StartTimeEqual_equal() {
		assertEquals(AUCTION_START_TIME, auction.getStartTime());
	}
	
	@Test
	public void setStartTime_StartTimeEqual_equal() {
		auction.setStartTime(NEW_AUCTION_START_TIME);
		assertEquals(NEW_AUCTION_START_TIME, auction.getStartTime());
	}
	
	
	@Test
	public void getEndTime_EndTimeEqual_equal() {
		assertEquals(AUCTION_END_TIME, auction.getEndTime());
	}
	
	@Test
	public void setEndTime_EndTimeEqual_equal() {
		auction.setEndTime(NEW_AUCTION_END_TIME);
		assertEquals(NEW_AUCTION_END_TIME, auction.getEndTime());
	}
	
	@Test
	public void isBidGreaterThanMinAmount_BidGreater_true() {
		assertTrue(auction.isBidGreaterThanMinAmount(31, batItem));
	}
	
	@Test
	public void isBidGreaterThanMinAmount_BidLess_false() {
		assertFalse(auction.isBidGreaterThanMinAmount(29, batItem));
	}
	
	@Test
	public void isMaxBidsPerAuction_NotReachedMaxBids_false() {
		auction.makeBid(ballItem, START_BIDPRICE_BALL + 2, bidder);
		assertFalse(auction.isMaxBidsPerAuction(bidder));
	}
	
	@Test
	public void isMaxBidsPerAuction_ReachedMaxNumBids_true() {
		double bid = START_BIDPRICE_BALL;
		for (int i = 1; i <= 4; i++) {
			auction.makeBid(ballItem, bid, bidder);
			bid++;
		}
		assertTrue(auction.isMaxBidsPerAuction(bidder));
	}
	
	@Test 
	public void checkBiddingConditions_TotalNumberAllowedBids_Error1() {
		for (int i = 1; i <= 12; i++) {
			auction.makeBid(ballItem, START_BIDPRICE_BALL + 2, bidder);
		}
		ArrayList<Integer> biddingConditions = auction.checkBiddingConditions(bidder);
		assertTrue(biddingConditions.contains(1));
	}
	
	@Test
	public void checkBiddingConditions_MaxBidsOver_Error2() {
		for (int i = 1; i <= 4; i++) {
			auction.makeBid(ballItem, START_BIDPRICE_BALL + 2, bidder);
		}
		ArrayList<Integer> biddingConditions = auction.checkBiddingConditions(bidder);
		System.out.println(biddingConditions.size());
		assertTrue(biddingConditions.contains(2));
	}

	@Test
	public void checkBiddingConditions_StartDatesEqual_Error3() {
		for (int i = 1; i <=5; i++) {
			auction.makeBid(ballItem, START_BIDPRICE_BALL + 2, bidder);
		}		
		ArrayList<Integer> biddingConditions = auction.checkBiddingConditions(bidder);		
		assertTrue(biddingConditions.contains(3));
	}

	@Test
	public void makeBid_BidLessThanMinAmnt_NotSuccessfulBid() {
		int makeBidResult = auction.makeBid(ballItem, START_BIDPRICE_BALL - 1, bidder);		
		assertEquals(2, makeBidResult);
	}	
	
	@Test
	public void makeBid_BidEqualGreaterMinAmnt_SuccessfulBid() {
		int makeBidResult = auction.makeBid(ballItem, START_BIDPRICE_BALL + 2, bidder);		
		assertEquals(0, makeBidResult);
	}
}
