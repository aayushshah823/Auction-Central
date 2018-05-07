package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * this class contains the original methods 
 * for the prompt. Delete class after we are done.
 * @author Raisa
 *
 */
public class workbench {
	//Menu Prompts start
		//This should return the user's choice so we know what to do in the runner class
		//which will call this methods
		public static void logInMenu(Scanner sc, AuctionCentral auctionCentral) {
			System.out.println("Welcome to Auction Central.");
			System.out.print("\tPlease select an option: ");
			System.out.println("\t1: Login");
			System.out.println("\t2: Exit");
			
//			int userChoice = -1;
//			do {
//				userChoice = sc.nextInt();
//				if (userChoice != 1 && userChoice != 2) { 
//					System.out.print("    Please enter a number to select an option: ");
//				}
//			} while (userChoice != 1 && userChoice != 2);
//			if (userChoice == 1) {
//				logInScreen(sc, auctionCentral);
//			} else {
//				System.out.println("Goodbye!");
//			}
//			System.out.println();
		}
		
		public static void logInScreen(Scanner sc, AuctionCentral auctionCentral) {
			boolean verifiedUser = false;
			do {
				System.out.print("Enter your username or 0 to Exit: ");
				String userName = sc.next();
				if (userName.equals("0")) {
					System.out.println("Goodbye!");
					return;
				} else {
//					verifiedUser = AuctionCentral.isVerifiedUser(userName);
				}
			} while (verifiedUser == false);
//			String userRealName = auctionCentral.getRealName(verifiedUser);
//			if (verifiedUser is a Bidder) {
//				bidderWelcomeScreen(userRealName);
//			}
//			if (verifiedUser is a Non-Profit) {
//				nonProfitWelcomeScreen(userRealName);
//			}
		}
		
		public static void bidderWelcomeScreen(Scanner sc, String theRealName) {
			System.out.println("Welcome back " + theRealName + 
				"! You are logged in as a bidder. What would you like to do?");
			int bidderChoice = -1;
			do {
				System.out.println("\t1: View auctions");
				System.out.println("\t2: View your auctions with bids");
				System.out.println("\t3: View items you've bid on");
				System.out.println("\t0: Logout");
				System.out.print("\tPlease select an option:");
				bidderChoice = sc.nextInt();
			} while (bidderChoice != 1 && bidderChoice != 2 &&
					 bidderChoice != 3 && bidderChoice != 0);
			if (bidderChoice == 1) {
				System.out.println("\tThis would display all auctions");
				//displayAllAuctions(auctionCentral);
			} else if (bidderChoice == 2) {
				System.out.println("\tThis would display all auctions with my bids");
				//ArrayList biddersAuctions = bidder.getAllAuctions();
			} else if (bidderChoice == 3) {
				System.out.println("\tThis would display only Items I've bid on");
				//ArrayList biddersItems = bidder.getAllItemsInAllAuctions();
			} else {
				System.out.println("Goodbye!");
			}		
		}
		
		public static void nonProfitWelcomeScreen(Scanner sc, String theRealName) {
			System.out.println("Welcome back " + theRealName + 
				"! You are logged in as a non-profit user. What would you like to do?");
			int nonProfitChoice = -1;
			do {
				System.out.println("\t1: View auctions");
				System.out.println("\t2: View auctions I have submitted");
				System.out.println("\t3: Schedule a new auction");
				System.out.println("\t0: Logout");
				System.out.print("\tPlease select an option:");
				nonProfitChoice = sc.nextInt();
			} while (nonProfitChoice != 1 && nonProfitChoice != 2 &&
					 nonProfitChoice != 3 && nonProfitChoice != 0);
			if (nonProfitChoice == 1) {
				System.out.println("\tThis would display all auctions");
				//displayAllAuctions(auctionCentral);
			} else if (nonProfitChoice == 2) {
				System.out.println("\tThis would display all auctions I've submitted");
				//displayOneNonProfitsAuctions(auctionCentral);
			} else if (nonProfitChoice == 3) {
				System.out.println("\tThis would schedule a new auction");
				//nonProfit.submitAuctionRequest();
			} else {
				System.out.println("Goodbye!");
			}		
		}
		
		/*
		 * Method that displays every single auction
		 */
		public void displayAllAuctionsAndItems(AuctionCentral auctionCentral) {
			ArrayList<NonProfit> allNonProfits= auctionCentral.getAllNonProfits();
			List<Auction> allAuctions = new ArrayList<>();
			List<Item> allItems = new ArrayList<>();
			for (NonProfit np : allNonProfits) {
				allAuctions = np.getAuctions();
				System.out.println(np.getOrg());
				for (Auction auctions : allAuctions) {
					allItems = auctions.getItems();
					for (Item items : allItems) {
						System.out.println(items.toString());
					}
				}
			}
		}

}
