package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.Bidder;
import model.Item;
import model.NonProfit;

/**
 * This class tests NonProfit Class.
 * @author aayushshah
 * @version 5/8/18
 *
 */
public class NonProfitTest {

	private NonProfit nonProfit;
	private LocalDate preiousAuctionDate;
	private LocalDate today;
	private Item car;
	private Item shoes;
	private Auction auction1;

	private static final String USERNAME = "nonprofit";
	private static final String NAME = "Group 3 Org";

	@Before
	public void setUp() throws Exception {
		
		nonProfit = new NonProfit(NAME, USERNAME);
		nonProfit.setUsername(NAME);
		nonProfit.setName(USERNAME);
		preiousAuctionDate = LocalDate.of(2018, 05, 1);
		today = LocalDate.now();		
		car = new Item("Car", auction1.MIN_BIDS_PER_ITEM + 500,"BMW", 1);
		shoes = new Item("Shoes", auction1.MIN_BIDS_PER_ITEM + 5,"Nike", 1);
		auction1 = new Auction(today.minus(370, ChronoUnit.DAYS), 
				LocalTime.NOON, LocalTime.NOON.plus(4, ChronoUnit.HOURS), null);
		nonProfit.addAuction(auction1);
		nonProfit.setLastAuctionDate(preiousAuctionDate);

	}

	@Test
	public void getOrg() {
		assertEquals(NAME, nonProfit.getUsername());
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
	public void addItem_AddedItems_True() {
		nonProfit.addItem(car);
		nonProfit.addItem(shoes);

		assertEquals(2, nonProfit.getItemsInAuction().size());
	}

	@Test
	public void addItem_NoItemsAdded_True() {
		assertEquals(0, nonProfit.getItemsInAuction().size());
	}

	@Test
	public void getItemInAuction() {
		auction1.addItem(car);
		auction1.addItem(shoes);

		assertTrue(nonProfit.getItemsInAuction().size() == 2);
	}
}