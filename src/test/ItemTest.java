package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Item;
/**
 * 
 * @author Benjamin Yuen, Raisa Meneses, Aayush Shah, 
 * 		   Allen Whitemarsh, Jake Yang
 * @version May 4, 2018
 */
public class ItemTest {

	private static final double START_BIDPRICE_BAT = 30;
	private static final double NEW_START_BIDPRICE_BAT = 35;
	private static final double CURRENT_BIDPRICE_BAT = 45;
	private static final int ITEM_COUNT_BAT = 1;
	private static final String ITEM_NAME = "bat";
	private static final String ITEM_DESCRIP = "light, high-quality";
	private static final String ITEM_NAME_BALL = "ball";
	private static final String ITEM_DESCRIP_BALL = "round, white";
	private static final double START_BIDPRICE_BALL = 20;
	private static final int ITEM_COUNT_BALL = 3;
	
	private Item batItem;
	private Item ballItem;
	private String batString;
	private StringBuilder builder;
	
	@Before
	public void setUp() throws Exception {
		batItem = new Item(ITEM_NAME, START_BIDPRICE_BAT, ITEM_DESCRIP, ITEM_COUNT_BAT);
		ballItem = new Item(ITEM_NAME_BALL, START_BIDPRICE_BALL, ITEM_DESCRIP_BALL, ITEM_COUNT_BALL);
		builder = new StringBuilder();
		builder.append("\tItem Name: " + ITEM_NAME);
		builder.append("\tItem Description: " + ITEM_DESCRIP);
		builder.append("\tStarting Bid: $" + START_BIDPRICE_BAT);
		batString = builder.toString();		
	}

	@Test
	public void setCurrentBid_BatItemCurrBid_equals() {
		batItem.setCurrentBid(CURRENT_BIDPRICE_BAT);
		assertEquals(CURRENT_BIDPRICE_BAT, batItem.getCurrentBid(), 1);
	}
	
	@Test
	public void setStartingBid_BatItemStartBid_equals() {
		batItem.setStartingBid(NEW_START_BIDPRICE_BAT);
		assertEquals(NEW_START_BIDPRICE_BAT, batItem.getStartingBid(), 0.01);
	}

	@Test
	public void getItemName_ItemName_equals() {
		assertEquals(ITEM_NAME, batItem.getItemName());
	}
	
	@Test
	public void getItemDesciption_ItemDescription_equals() {
		assertEquals(ITEM_DESCRIP, batItem.getItemDesciption());
	}
	
	@Test
	public void getItemCount_BallItemCount_equals() {
		assertEquals(ITEM_COUNT_BALL, ballItem.getItemCount());
	}
	
	@Test
	public void getItemCount_BatItemCount_equals() {
		assertEquals(ITEM_COUNT_BAT, batItem.getItemCount());
	}
	
	@Test
	public void getCurrentBid_BatItemCurrBid_equal() {
		batItem.setCurrentBid(CURRENT_BIDPRICE_BAT);
		assertEquals(CURRENT_BIDPRICE_BAT, batItem.getCurrentBid(), 0.1);
	}
	
	@Test
	public void getStartingBid_BatItemStartBid_equal() {
		assertEquals(START_BIDPRICE_BAT, batItem.getStartingBid(), 1);
	}
	
	@Test
	public void toString_BatItemString_equal() {
		assertEquals(batString, batItem.toString());
	}
}
