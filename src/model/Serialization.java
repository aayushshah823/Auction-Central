package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Serialization {
	
	public static void main(String[] args) {
		LocalDate today = LocalDate.now();
		LocalTime noon = LocalTime.NOON;
		AuctionCentral auctionCentral = new AuctionCentral();
		NonProfit nonProfit1 = new NonProfit("organization1", "nonProfitName1");
		NonProfit nonProfit2 = new NonProfit("organization2", "nonProfitName2");
		NonProfit nonProfit3 = new NonProfit("organization3", "nonProfitName3");
		NonProfit nonProfit4 = new NonProfit("organization4", "nonProfitName4");
		NonProfit nonProfit5 = new NonProfit("organization5", "nonProfitName5");
		Auction auction1 = new Auction(today.minus(370, ChronoUnit.DAYS), 
				today.minus(368, ChronoUnit.DAYS), noon, noon.plus(4, ChronoUnit.HOURS));
		auction1.addItem(new Item("itemName1", 5.00, "itemDescription1", 2));
		auction1.addItem(new Item("itemName2", 10, "itemDescription2", 1));
		auction1.addItem(new Item("itemName3", 0.99, "itemDescription3", 150));
		auction1.addItem(new Item("itemName4", 200, "itemDescription4", 1));
		auction1.addItem(new Item("itemName5", 8000.35, "itemDescription5", 1));
		Auction auction2 = new Auction(today.minus(150, ChronoUnit.DAYS), 
				today.minus(149, ChronoUnit.DAYS), noon, noon.plus(2, ChronoUnit.HOURS));
		auction2.addItem(new Item("itemName1", 1.00, "itemDescription1", 23));
		auction2.addItem(new Item("itemName2", 3.00, "itemDescription2", 1));
		auction2.addItem(new Item("itemName3", 17.00, "itemDescription3", 1));
		auction2.addItem(new Item("itemName4", 1, "itemDescription4", 1));
		auction2.addItem(new Item("itemName5", 20, "itemDescription5", 1));
		auction2.addItem(new Item("itemName6", 300.00, "itemDescription6", 3));
		auction2.addItem(new Item("itemName7", 4.20, "itemDescription7", 1));
		auction2.addItem(new Item("itemName8", 1.88, "itemDescription8", 8));
		auction2.addItem(new Item("itemName9", 200000, "itemDescription9", 15));
		auction2.addItem(new Item("itemName10", 0.005, "itemDescription10", 1));
		Auction auction3 = new Auction(today.plus(10, ChronoUnit.DAYS), 
				today.plus(10, ChronoUnit.DAYS), noon, noon.plus(6, ChronoUnit.HOURS));
		auction3.addItem(new Item("itemName1", 1.00, "itemDescription1", 1));
		auction3.addItem(new Item("itemName2", 2.00, "itemDescription2", 1));
		auction3.addItem(new Item("itemName3", 3.00, "itemDescription3", 1));
		auction3.addItem(new Item("itemName4", 4.00, "itemDescription4", 5));
		auction3.addItem(new Item("itemName5", 6.00, "itemDescription5", 2));
		auction3.addItem(new Item("itemName6", 5.00, "itemDescription6", 3000));
		auction3.addItem(new Item("itemName7", 13, "itemDescription7", 1));
		auction3.addItem(new Item("itemName8", 27, "itemDescription8", 1));
		auction3.addItem(new Item("itemName9", 42, "itemDescription9", 1));
		Auction auction4 = new Auction(today.plus(50, ChronoUnit.DAYS), 
				today.plus(51, ChronoUnit.DAYS), noon, noon.plus(11, ChronoUnit.HOURS));
		auction4.addItem(new Item("itemName1", 42, "itemDescription1", 1));
		Auction auction5 = new Auction(today.minus(769, ChronoUnit.DAYS), 
				today.minus(767, ChronoUnit.DAYS), noon, noon.plus(7, ChronoUnit.HOURS));
		Auction auction6 = new Auction(today.minus(367, ChronoUnit.DAYS), 
				today.minus(367, ChronoUnit.DAYS), noon, noon.plus(8, ChronoUnit.HOURS));
		auction6.addItem(new Item("itemName1", 0.01, "itemDescription1", 1));
		auction6.addItem(new Item("itemName2", 0.0003158, "itemDescription2", 1));
		auction6.addItem(new Item("itemName3", 0.020252, "itemDescription3", 5));
		auction6.addItem(new Item("itemName4", 40.0215, "itemDescription4", 30));
		auction6.addItem(new Item("itemName5", 0.98, "itemDescription5", 9));
		auction6.addItem(new Item("itemName6", 1, "itemDescription6", 1));
		Auction auction7 = new Auction(today.minus(1500, ChronoUnit.DAYS), 
				today.minus(1499, ChronoUnit.DAYS), noon, noon.plus(10, ChronoUnit.HOURS));
		auction7.addItem(new Item("itemName1", 1.00, "itemDescription1", 23));
		auction7.addItem(new Item("itemName2", 3.00, "itemDescription2", 1));
		auction7.addItem(new Item("itemName3", 17.00, "itemDescription3", 1));
		auction7.addItem(new Item("itemName4", 1, "itemDescription4", 1));
		auction7.addItem(new Item("itemName5", 20, "itemDescription5", 1));
		auction7.addItem(new Item("itemName6", 300.00, "itemDescription6", 3));
		auctionCentral.addNonprofit(nonProfit1); // No previous auction
		auctionCentral.addNonprofit(nonProfit2); // Previous auction over a year ago
		auctionCentral.addNonprofit(nonProfit3); // Previous auction less than a year ago
		auctionCentral.addNonprofit(nonProfit4); // scheduled auction
		auctionCentral.addNonprofit(nonProfit5); // Multiple previous auctions (one empty) + scheduled auction
		auctionCentral.addAuction(nonProfit2, auction1);
		auctionCentral.addAuction(nonProfit3, auction2);
		auctionCentral.addAuction(nonProfit4, auction3);
		auctionCentral.addAuction(nonProfit5, auction4);
		auctionCentral.addAuction(nonProfit5, auction5);
		auctionCentral.addAuction(nonProfit5, auction6);
		auctionCentral.addAuction(nonProfit5, auction7);

		// serialization
		try {
			FileOutputStream file = new FileOutputStream("auctionCentralData.ser");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(auctionCentral);
			out.close();
			file.close();
			System.out.println("Serialized!!!!!");
		} catch (IOException exception) {
			System.out.println("IOException");
		}
		
		// deserialization
		try {   
            FileInputStream file = new FileInputStream("auctionCentralData.ser");
            ObjectInputStream in = new ObjectInputStream(file);        
            AuctionCentral auctionCentralCopy = (AuctionCentral)in.readObject();
            in.close();
            file.close(); 
            System.out.println("Deserialized!!!!!");
        } catch(IOException exception) {
            System.out.println("IOException");
        } catch(ClassNotFoundException exception) {
            System.out.println("ClassNotFoundException");
        }
 
	}
}

