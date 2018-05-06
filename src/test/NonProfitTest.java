package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import model.NonProfit;

public class NonProfitTest {
	
	private static final String USERNAME = "non-profit";
	private static final String NAME = "Group 3 Org";
	private NonProfit nonProfit;
	private static final int MAX_DAYS_AWAY_FOR_AUCTION = NonProfit.MAX_DAYS_AWAY_FOR_AUCTION - 10;
	private static final int MIN_DAYS_AWAY_FOR_AUCTION = NonProfit.MIN_DAYS_AWAY_FOR_AUCTION + 1;
	
	@Before
	public void setUp() throws Exception {
		nonProfit = new NonProfit(NAME, USERNAME);
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
		assertTrue(NonProfit.MAX_DAYS_AWAY_FOR_AUCTION == nonProfit.getMaxDays());
	}

}