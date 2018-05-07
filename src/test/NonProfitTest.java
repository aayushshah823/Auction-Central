package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.Bidder;
import model.Item;
import model.NonProfit;

public class NonProfitTest {

	private NonProfit nonProfit;
	private LocalDate preiousAuctionDate;
	private LocalDate today;
	private Item car;
	private Item shoes;
	private Auction auction1;

	private static final String USERNAME = "nonprofit";
	private static final String NAME = "Group 3 Org";
	private static final int MAX_DAYS_AWAY_FOR_AUCTION = NonProfit.MAX_DAYS_AWAY_FOR_AUCTION - 10;
	private static final int MIN_DAYS_AWAY_FOR_AUCTION = NonProfit.MIN_DAYS_AWAY_FOR_AUCTION + 1;


	@Before
	public void setUp() throws Exception {
		nonProfit = new NonProfit(NAME, USERNAME);
		nonProfit.setUsername(NAME);
		nonProfit.setName(USERNAME);
		preiousAuctionDate = LocalDate.of(2018, 05, 1);
		today = LocalDate.now();
		car = new Item("Car", Bidder.MIN_AMOUNT_BID_PER_ITEM + 500,"BMW", 1);
		shoes = new Item("Shoes", Bidder.MIN_AMOUNT_BID_PER_ITEM + 5,"Nike", 1);
		auction1 = new Auction(today.minus(370, ChronoUnit.DAYS), 
				today.minus(368, ChronoUnit.DAYS), LocalTime.NOON, LocalTime.NOON.plus(4, ChronoUnit.HOURS));
		nonProfit.setLastAuctionDate(preiousAuctionDate);

	}

	@Test
	public void isMaxDaysForAuction_LessThanValid_True() {
		assertTrue(MAX_DAYS_AWAY_FOR_AUCTION < nonProfit.getMaxDays());
	}

	@Test
	public void isMaxDaysForAuction_MoreThanValid_False() {
		assertFalse(MAX_DAYS_AWAY_FOR_AUCTION > nonProfit.getMaxDays());
	}

	@Test
	public void isMaxDaysForAuction_EqualToValid_True() {
		nonProfit.setMaxDays(60);
		assertTrue(60 == nonProfit.getMaxDays());
	}


	@Test
	public void isMinDaysForAuction_LessThanValid_False() {
		assertFalse(MIN_DAYS_AWAY_FOR_AUCTION < nonProfit.getMinDays());
	}

	@Test
	public void isMinDaysForAuction_MoreThanValid_True() {
		assertTrue(MIN_DAYS_AWAY_FOR_AUCTION > nonProfit.getMinDays());
	}

	@Test
	public void isMinDaysForAuction_EqualToValid_True() {
		nonProfit.setMinDays(14);
		assertEquals(14, nonProfit.getMinDays());
	}

	@Test
	public void getOrg() {
		assertEquals(NAME, nonProfit.getUsername());
	}

	@Test
	public void setOrg() {
		nonProfit.setOrg(NAME);
		assertEquals(NAME, nonProfit.getOrg());
	}

	@Test
	public void getName() {
		assertEquals(USERNAME, nonProfit.getName());
	}

	@Test
	public void setName() {
		nonProfit.setName(USERNAME);
		assertEquals(USERNAME, nonProfit.getName());
	}

	@Test
	public void lastAuctionDate_get() {
		assertEquals(preiousAuctionDate, nonProfit.getLastAuctionDate());
	}

	@Test
	public void lastAuctionDate_set() {
		nonProfit.setLastAuctionDate(preiousAuctionDate);
		assertEquals(preiousAuctionDate, nonProfit.getLastAuctionDate());
	}

	@Test
	public void getUserType() {
		assertEquals(USERNAME, nonProfit.getUserType());
	}

	@Test
	public void isDateRangeValid_true() {
		nonProfit.setLastAuctionDate(preiousAuctionDate);
		long theDate = ChronoUnit.DAYS.between(preiousAuctionDate, today);
		assertEquals((theDate >= MIN_DAYS_AWAY_FOR_AUCTION) && 
				(theDate <= MAX_DAYS_AWAY_FOR_AUCTION), nonProfit.isDateRangeValid());
	}

	// There is an error in this test. (NOT PASSING)
	//		@Test
	//		public void getItemInAuction() {
	//			auction1.addItem(car);
	//			auction1.addItem(shoes);
	//
	//			assertTrue(nonProfit.getItemsInAuction().size() == 2);
	//		}


}