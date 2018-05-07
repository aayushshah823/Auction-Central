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
	        FileInputStream file = new FileInputStream("auctionCentralData.ser");
	        ObjectInputStream in = new ObjectInputStream(file);        
	        auctionCentral = (AuctionCentral)in.readObject();
	        in.close();
	        file.close(); 
	        System.out.println("Deserialized!!!!!");
	    } catch(IOException exception) {
	        System.out.println("IOException");
	    } catch(ClassNotFoundException exception) {
	        System.out.println("ClassNotFoundException");
	    }
		
		//logInMenu(sc, auctionCentral);
		//bidderWelcomeScreen(sc, "John Doe");
		
		
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
		
		BidderMenuOptions bidderMenu = null;
		NonProfitMenuOptions nonprofit = null;
		Scanner sc = new Scanner(System.in);
		
		logInMenu(sc, auctionCentral, bidderMenu, nonprofit);
		
	}
	
	/**
	 * 
	 * @param Scanner
	 * @returns 1 if the user wants to login, 2 if the user wants to exit
	 */
	public static void logInMenu(Scanner sc, AuctionCentral ac, BidderMenuOptions bidderMenu, NonProfitMenuOptions nonprofit) {
		System.out.println("Welcome to Auction Central.");
		System.out.print("\tPlease select an option: ");
		System.out.println("\t1: Login as a Bidder");
		System.out.println("\t2: Login as a Non-Profit");
		System.out.println("\t0: Logout");
		
		int userChoice;
		do {
			userChoice = sc.nextInt();			
			System.out.print("    Please enter a number to select an option: ");
			
		} while (userChoice != 1 || userChoice != 2  || userChoice != 3);

		switch(userChoice) {
			case 1: 
				System.out.println("\tWelcome to the Bidder menu options!");
				System.out.println("\tPlease enter your username:");
				String bidderUsername = sc.next();				
				Bidder bidder = (Bidder) ac.login(bidderUsername);
				bidderMenu = new BidderMenuOptions (sc, ac, bidder);
				break;
			case 2: 
				System.out.println("\tPlease enter your username:");
				String nonprofitUserName = sc.next();				
				NonProfit np = (NonProfit) ac.login(nonprofitUserName);
				nonprofit = new NonProfitMenuOptions(); //needs to add the parameters BEN
				break;
				
			case 3: 				
				System.out.println("\tThank you for using Auction Cenral. Have a great day!");
				break;			
				
				
		}
	}
	
}
