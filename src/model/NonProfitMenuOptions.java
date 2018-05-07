package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NonProfitMenuOptions {

	private NonProfit myNonProfit;
	/**
	 * 
	 * @param Scanner
	 * @returns 1 if the user wants to login, 2 if the user wants to exit
	 */
	public void logInMenu(Scanner sc, AuctionCentral ac) {
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
			System.out.println("\tPlease enter your username:");
			String bidderUsername = sc.next();				
			Bidder bidder = (Bidder) ac.login(bidderUsername);
			//bidderMenuOptions(sc, bidder, ac);
			break;
		case 2: 
			System.out.println("\tPlease enter your username:");
			String nonprofitUserName = sc.next();				
			myNonProfit = (NonProfit) ac.login(nonprofitUserName);
			nonProfitMenuOptions(sc, ac);
			break;

		case 3: 				
			//logout(sc);
			break;
		}
	}

	//------------------------------------------- NonProfit PROMPTS START -------------------------------------------------
	public void nonProfitMenuOptions(Scanner sc, AuctionCentral ac) {
		System.out.println("Welcome back " + myNonProfit.getName() + 
				"! You are logged in as a " + myNonProfit.getUserType() +". What would you like to do?");
		int nonprofitChoice = -1;
		System.out.println("\t1: View active auctions");
		System.out.println("\t2: View auctions I have submitted");
		System.out.println("\t3: Schedule a new auction");
		System.out.println("\t0: Logout");
		System.out.print("\tPlease select an option:");
		nonprofitChoice = sc.nextInt();	

		switch(nonprofitChoice) {
		case 0:
			//logout
			break;
		case 1: 
			//View active auctions 
			printFutureAuctions(ac.displayFutureAuctions());
			System.out.println("3: Back");
			System.out.println("4: Logout");

			break;
		case 2: 
			//View auctions I have submitted, 
			System.out.println("Here is a list of all auctions you have submitted: Select an option:");			
			printFutureAuctions(myNonProfit.getAuctions(), sc, ac);			

			break;
		case 3: 
			System.out.println("\t1: Please enter the auction start date");
			//Schedule a new auction
			break;		
		}
	}

	private void printFutureAuctions(List<Auction> displayFutureAuctions, Scanner sc, AuctionCentral ac) {
		int i;
		int nonprofitChoice = -1;
		for(i = 0; i < displayFutureAuctions.size(); i++) {
			System.out.print("\t" + (i + 1) + ".");
			System.out.print(displayFutureAuctions.get(i).getAuctionName() + " - ");
			System.out.print(displayFutureAuctions.get(i).getAuctionLocation() + " - ");
			System.out.print(displayFutureAuctions.get(i).getStartDate() + " at ");
			System.out.print(displayFutureAuctions.get(i).getStartTime());
			
		}
		System.out.println("1: Back");
		System.out.println("0: Logout");
		
		nonprofitChoice = sc.nextInt();
		
		if (nonprofitChoice == 0) {
			//logout
		} else {
			nonProfitMenuOptions(sc, ac);
		}
		

	}


}
