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
import model.Item;
/**
 * 
 * @author Benjamin Yuen, Raisa Meneses, Aayush Shah, 
 * 		   Allen Whitemarsh, Jake Yang
 * @version May 4, 2018
 */
public class AuctionTest {
	
	private static final LocalDate AUCTION_START_DATE= LocalDate.now();
	private static final LocalDate AUCTION_END_DATE= LocalDate.now().plusDays(30);
	private static final LocalTime AUCTION_START_TIME= LocalTime.now();
	private static final LocalTime AUCTION_END_TIME= LocalTime.now().plusHours(5);
	private static final LocalDate NEW_AUCTION_START_DATE = AUCTION_START_DATE;
	private static final LocalDate NEW_AUCTION_END_DATE= LocalDate.now().plusDays(2);
	private static final LocalTime NEW_AUCTION_START_TIME= LocalTime.MIDNIGHT;
	private static final LocalTime NEW_AUCTION_END_TIME= LocalTime.NOON;
	private static final double START_BIDPRICE_BAT = 30;
	private static final double START_BIDPRICE_BALL = 30;
	private static final int ITEM_COUNT_BAT = 1;
	private static final int ITEM_COUNT_BALL = 3;
	private static final String AUCTION_NAME_GOODW = "Goodwill";
	private static final String AUCTION_LOCATION_TAC = "Tacoma, WA";
	private static final String AUCTION_NAME_SAL = "Salvation Army";
	private static final String AUCTION_LOCATION_PORT = "Portland, OR";

	private Auction auction;
	private Item batItem;
	private Item ballItem;
	private ArrayList<Item> itemsList;

	@Before
	public void setUp() throws Exception {
		auction = new Auction(AUCTION_START_DATE, AUCTION_END_DATE, AUCTION_START_TIME, 
								AUCTION_END_TIME);
		batItem = new Item("bat", START_BIDPRICE_BAT, "light, high-quality", ITEM_COUNT_BAT);
		ballItem = new Item("ball", START_BIDPRICE_BALL, "round, white", ITEM_COUNT_BALL);
		itemsList = new ArrayList<>();
		auction.setAuctionName(AUCTION_NAME_GOODW);
		auction.setAuctionLocation(AUCTION_LOCATION_TAC);
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
	public void getAuctionLocation_TacWA_equals() {
		assertEquals(AUCTION_LOCATION_TAC, auction.getAuctionLocation());
	}
	
	@Test
	public void setAuctionLocation_PortOR_equals() {
		auction.setAuctionLocation(AUCTION_LOCATION_PORT);
		assertEquals(AUCTION_LOCATION_PORT, auction.getAuctionLocation());
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
	
//	@Test
//	public void addItem_ItemsAddedNull_True() {
//		auction.addItem(null);
//		assertEquals(null, auction.getItems());
//	}
	
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
		auction.setEndDate(NEW_AUCTION_START_DATE);
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
	public void getEndDate_EndDateEqual_equal() {
		assertEquals(AUCTION_END_DATE, auction.getEndDate());
	}
	
	@Test
	public void setEndDate_EndDateEqual_equal() {
		auction.setEndDate(NEW_AUCTION_END_DATE);
		assertEquals(NEW_AUCTION_END_DATE, auction.getEndDate());
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

}
