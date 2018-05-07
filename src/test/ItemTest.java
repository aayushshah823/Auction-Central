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
	private static final double START_BIDPRICE_BALL = 30;
	private static final int ITEM_COUNT_BAT = 1;
	private static final int ITEM_COUNT_BALL = 3;
	
	private Item batItem;
	private Item ballItem;
	
	@Before
	public void setUp() throws Exception {
		batItem = new Item("bat", START_BIDPRICE_BAT, "light, high-quality", ITEM_COUNT_BAT);
		ballItem = new Item("ball", START_BIDPRICE_BALL, "round, white", ITEM_COUNT_BALL);
	}

	@Test
	public void setCurrentBid() {
		//assertTrue
	}
	
	@Test
	public void setStartingBid() {
		//assertTrue
	}

	@Test
	public void getItemName() {
		//assertTrue
	}
	
	@Test
	public void getItemDesciption() {
		//assertTrue
	}
	
	@Test
	public void getItemCount() {
		//assertTrue
	}
	
	@Test
	public void getCurrentBid() {
		//assertTrue
	}
	
	@Test
	public void getStartingBid() {
		//assertTrue
	}
	
	@Test
	public void toString_() {
		//assertTrue
	}
}
