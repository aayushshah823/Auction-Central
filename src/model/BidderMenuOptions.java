package model;

import java.util.ArrayList;
import java.util.Scanner;

public class BidderMenuOptions {
	
	Scanner sc;
	AuctionCentral ac;
	Bidder bidder;

	BidderMenuOptions(Scanner sc, AuctionCentral ac, Bidder bidder) {
		this.sc = sc;
		this.ac = ac;
		this.bidder = bidder;
	}


	//------------------------------------------- BIDDER PROMPTS START -------------------------------------------------
	/**
	 * Pre: The Logged in to AuctionCentral as a bidder
	 *  
	 * 
	 * @param Scanner, AuctionCentral
	 */

	public void bidderMenuOptions() {
		System.out.println("------------  A U C T I O N   C E N T R A L ------------");
		System.out.println("Welcome back " + bidder.getName() + 
				"! You are logged in as a " + bidder.getUserType() +". What would you like to do?");
		mainMenu();
	}
	
	public void mainMenu() {
		
		System.out.println("------------  A U C T I O N   C E N T R A L ------------");
		System.out.println();
		int bidderChoice = -1;
		System.out.println("\t1: View all Auctions in which I can bid");
		System.out.println("\t2: View all auctions in which I have placed bids");
		System.out.println("\t3: View all items I have bid on in an auction");
		System.out.println("\t4: View all items I have bid on in all auctions");
		System.out.println("\t0: Logout");
		System.out.print("\tPlease select an option:");
		bidderChoice = sc.nextInt();		

		switch (bidderChoice) {
			case 1:
				printFutureAuctions(ac.displayFutureAuctions());
				System.out.println();
			    optionsAfterPrintingFutureAuctions();
			    break;
			case 2: 
				printAllAuctionsWithBids();
				break;
			case 3: 
				System.out.println("Please enter the auction name: ");
				String auctionName = sc.next();
				selectAnAuctionToViewBids(auctionName);
				break;
			case 4:
				printAllItemsInAllAuctions();
				break;
			case 0:
				logout();
				break;
			default: 
				System.out.println("Please enter a valid option. "
						+ "1 - 2 - 3 - 4 or 0");
				break;	

		}

	}

	private void printAllItemsInAllAuctions() {
		ArrayList<Item> items = bidder.getAllIntemsInAllAuctions();
		for(int i = 0; i < items.size(); i++) {
			System.out.println((i + 1) + ": " + items.get(i).getItemName());
		}

	}

	private void selectAnAuctionToViewBids(String auctionName) {
		ArrayList<Auction> auction = bidder.getAllAuctions();
		Auction myauction = null; 
		for(int i = 0; i < auction.size(); i++) {
			if(auction.get(i).getAuctionName().equals(auctionName)) {
				myauction = auction.get(i);
			}
		}
		ArrayList<Item> items = bidder.getAllItemsInOneAuction(myauction);
		for(int j = 0; j < items.size(); j++) {
			System.out.println((j + 1) + ": " + items.get(j).getItemName()); 
		}


	}

	private void printAllAuctionsWithBids() {
		ArrayList<Auction> auction = bidder.getAllAuctions();
		for(int i = 0; i < auction.size(); i++) {
			System.out.println("Auction Name: " + auction.get(i).getAuctionName());
			System.out.println("Auction Date: " + auction.get(i).getEndDate());
			System.out.println();
		}

	}

	/**
	 * Options displayed if a user wants to see all auctions
	 */
	public void optionsAfterPrintingFutureAuctions() {
		System.out.println("\tWhat would you like to do nex?"); 
		int bidderChoice = -1;
		System.out.println("\t1: Select an auction to bid on");
		System.out.println("\t2: Go back");
		System.out.println("\t0: Logout");
		System.out.print("\tPlease select an option:");
		bidderChoice = sc.nextInt();

		switch (bidderChoice) {
		case 1:	
			System.out.println("\tThe auctions available to bid are: ");
			printFutureAuctions(ac.displayFutureAuctions());
			System.out.println("");
			System.out.println("\tPlease enter the auction name: ");
			String auctionName = sc.next();
			selectAnAuctionToBid(auctionName);	//TODO 
		case 2: 
			bidderMenuOptions();
		case 0:
			logout();
			break;	
		default:
			System.out.println("\tPlease enter a valid option. "
					+ "1 - 2 or 0");
			bidderChoice = sc.nextInt();
			break;
		}	
	}

	/**
	 * Aucion available to bid. The auction date must be > than today's date for this to show up. 
	 * @param sc
	 * @param auctionName
	 * @param bidder
	 */
	public void selectAnAuctionToBid(String auctionName) {		
		Auction auction = null;
		
		for(int i = 0; i < this.ac.displayFutureAuctions().size(); i++) {
			if(this.ac.displayFutureAuctions().get(i).getAuctionName().equals(auctionName)) {
				auction = this.ac.displayFutureAuctions().get(i);
			}
		}
		
		if(auction == null) {			
			System.out.println("\tThe auction you have requested is not available for bids");
			System.out.println("\tThe auctions available to bid are: ");
			printFutureAuctions(ac.displayFutureAuctions());
			failedOperation();
			
		} else {
			System.out.println("\tThe items available in the " + auction.getAuctionName() + " are:");
			ArrayList<Item> items = (ArrayList<Item>) auction.getItems();
			for(int i = 0; i < items.size(); i ++) {
				System.out.println("\tItem Name: " + items.get(i).getItemName());
				System.out.println("\tItem Description: " + items.get(i).getItemDesciption());
				System.out.println("\tItem Starting Bid: " + items.get(i).getStartingBid());
			}
		}
		
		

	}
	
	public void failedOperation() {
		int bidderChoice = -1;
		System.out.println(" ");
		System.out.println("Please select an option:");
		System.out.println("\t1: Go back to main menu");
		System.out.println("\t0: Logout");
		
		bidderChoice = sc.nextInt();
		
		switch(bidderChoice) {
		case 1: 
			this.mainMenu();
			break;
		case 2:
			this.logout();
			break;
		}
		
	}

	public void logout() {
		System.out.println("\tThank you for using Auction Cenral. Have a great day!");
	}


	//Displays the current available auction
	private void printFutureAuctions(ArrayList<Auction> displayFutureAuctions) {
		for(int i = 0; i < displayFutureAuctions.size(); i++) {
			System.out.println("\t" + (i + 1) + ".");
			System.out.println("\tAuction Name: " + displayFutureAuctions.get(i).getAuctionName());
			System.out.println("\tAuction Location: " + displayFutureAuctions.get(i).getAuctionLocation());
			System.out.println("\tAuction Date: " + displayFutureAuctions.get(i).getEndDate());
			System.out.println("\tAuction Time: " + displayFutureAuctions.get(i).getStartTime());
			System.out.println( );
		}

	}

}
