package model;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuOptions {
	
	/**
	 * 
	 * @param Scanner
	 * @returns 1 if the user wants to login, 2 if the user wants to exit
	 */
	public int logInMenu(Scanner sc) {
		System.out.println("Welcome to Auction Central.");
		System.out.print("\tPlease select an option: ");
		System.out.println("\t1: Login as a Bidder");
		System.out.println("\t2: Login as a Non-Profit");
		System.out.println("\t3: Exit");
		
		int userChoice;
		do {
			userChoice = sc.nextInt();			
			System.out.print("    Please enter a number to select an option: ");
			
		} while (userChoice != 1 || userChoice != 2  || userChoice != 3);

		return userChoice;
	}
	
	public void exitOption() {
		System.out.println("Thank you for visiting Auction Central. Have a great rest of the day!");
	}
	
	/**
	 * Pre: The Login method for Auction Central is called to return the real name
	 * of the user
	 * @param sc
	 * @param theRealName, Scanner
	 */
	
	public void bidderMenuOptions(Scanner sc, Bidder bidder, AuctionCentral ac) {
		System.out.println("Welcome back " + bidder.getName() + 
			"! You are logged in as a " + bidder.getUserType() +". What would you like to do?");
		int bidderChoice = -1;
		do {
			System.out.println("\t1: View all Auctions in which I can bid");
			System.out.println("\t2: View all auctions in which I have placed bids");
			System.out.println("\t3: View all items I have bid on in an auction");
			System.out.println("\t4: View all items I have bid on in all auctions");
			System.out.println("\t0: Logout");
			System.out.print("\tPlease select an option:");
			bidderChoice = sc.nextInt();
		} while (bidderChoice != 1 || bidderChoice != 2 ||
				 bidderChoice != 3 || bidderChoice != 0);
		
		switch (bidderChoice) {
			case 1:
				printFutureAuctions(ac.displayFutureAuctions());
				System.out.println();
			    allFutureAuctionOptions();
			case 2: 
			case 3: 
			case 4:
			case 0:
				
		
		}
		
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

	/**
	 * Options displayed if a user wants to see all auctions
	 */
	private void allFutureAuctionOptions() {
		System.out.println("\tWhat would you like to do nex?"); 
		
	}

	//TODO WAITING ON BEN
	private void printFutureAuctions(ArrayList<Auction> displayFutureAuctions) {
		for(int i = 0; i < displayFutureAuctions.size(); i++) {
			System.out.println("Auction Name: " + displayFutureAuctions.get(i).getStartDate());
		}
		
	}

}
