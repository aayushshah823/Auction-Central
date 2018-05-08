package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class AuctionCentralMain {
	
	public static void main(String[] args) {
		// deserialization
		AuctionCentral auctionCentral = null;
		try {   
	        FileInputStream file = new FileInputStream("auctionCentralDefault.ser");
	        ObjectInputStream in = new ObjectInputStream(file);        
	        auctionCentral = (AuctionCentral)in.readObject();
	        in.close();
	        file.close();
	    } catch(IOException exception) {
	        System.out.println("IOException");
	    } catch(ClassNotFoundException exception) {
	        System.out.println("ClassNotFoundException");
	    }
		
		BidderMenuOptions bidderMenu = null;
		NonProfitMenuOptions nonProfitMenu = null;
		Scanner sc = new Scanner(System.in);
		
		logInMenu(sc, auctionCentral);
		
		// serialization
		try {
			FileOutputStream file = new FileOutputStream("auctionCentralDefault.ser");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(auctionCentral);
			out.close();
			file.close();
		} catch (IOException exception) {
			System.out.println("IOException");
		}

		

	}
	
	/**
	 * 
	 * @param Scanner
	 * @returns 1 if the user wants to login, 2 if the user wants to exit
	 */
	public static void logInMenu(Scanner sc, AuctionCentral ac) {
		System.out.println("------------ "
				+ " A U C T I O N   C E N T R A L ------------");
		System.out.println("Welcome to Auction Central.");
		System.out.println("\t1: Login as a Bidder");
		System.out.println("\t2: Login as a Non-Profit");
		System.out.println("\t0: Exit");
		System.out.print("\tPlease select an option: ");
		
		int userChoice;
		userChoice = sc.nextInt();	

		switch(userChoice) {
			case 1: 
				System.out.println("\n\tWelcome to the Bidder menu options!");
				System.out.println("\tPlease enter your username:");
				String bidderUsername = sc.next();				
				Bidder bidder = (Bidder) ac.login(bidderUsername);
				BidderMenuOptions bidderMenu = new BidderMenuOptions (sc, ac, bidder);
				bidderMenu.bidderMenuOptions(); 
				break;
			case 2: 
				System.out.println("\tPlease enter your username:");
				String nonprofitUserName = sc.next();				
				NonProfit np = (NonProfit) ac.login(nonprofitUserName);
				NonProfitMenuOptions nonProfitMenu = new NonProfitMenuOptions(sc, ac, np);
				nonProfitMenu.nonProfitMenuOptions(); 
				break;
				
			case 3: 				
				System.out.println("\tThank you for using Auction Central. Have a great day!");
				break;			
				
				
		}
	}
	
}
