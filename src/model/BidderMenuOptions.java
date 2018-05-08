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


	/**
	 * Pre: The Logged in to AuctionCentral as a bidder
	 *  
	 * 
	 * @param Scanner, AuctionCentral
	 */

	public void bidderMenuOptions() {
		banner();
		System.out.println("Welcome back " + bidder.getName() + 
				"! You are logged in as a"
				+ " " + bidder.getUserType() +". Here are your options:");
		System.out.println();
		mainMenu();
	}
	
	public void banner() {
		System.out.println();
		System.out.println("------------ "
				+ " A U C T I O N   C E N T R A L ------------");
		System.out.println();
	}
	
	public void mainMenu() {
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
				printFutureAuctions(ac.getFutureAuctions());
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

		this.endOfRequest();

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
    
		this.endOfRequest();

	}

	private void printAllAuctionsWithBids() {
		banner();
		ArrayList<Auction> auction = new ArrayList<Auction>();
		auction =	bidder.getAllAuctions();
		for(int i = 0; i < auction.size(); i++) {
			System.out.println("Auction Name:"
					+ " " + auction.get(i).getAuctionName());
			System.out.println("Auction Date:"
					+ " " + auction.get(i).getStartDate());
			System.out.println();
		}
		
		this.endOfRequest();

	}

	/**
	 * Options displayed if a user wants to see all auctions
	 */
	public void optionsAfterPrintingFutureAuctions() {
		banner();
		System.out.println("\tWhat would you like to do next?"); 
		int bidderChoice = -1;
		System.out.println("\t1: Select an auction to bid on");
		System.out.println("\t2: Go back");
		System.out.println("\t0: Logout");
		bidderChoice = sc.nextInt();

		switch (bidderChoice) {
		case 1:			
			System.out.println("\tThe auctions available to bid are: ");
			printFutureAuctions(ac.getFutureAuctions());
			System.out.println("\tPlease enter the auction name: ");
			String auctionName = sc.next();
			selectAnAuctionToBid(auctionName);	 
		case 2: 
			this.banner();
			mainMenu();
			break;
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
	 * Aucion available to bid. The auction date must 
	 * be > than today's date for this to show up. 
	 * @param sc
	 * @param auctionName
	 * @param bidder
	 */
	public void selectAnAuctionToBid(String auctionName) {		
		Auction auction = null;
		
		for(int i = 0; i < this.ac.getFutureAuctions().size(); i++) {
			if(this.ac.getFutureAuctions().get(i)
					.getAuctionName().equals(auctionName)) {
				auction = this.ac.getFutureAuctions().get(i);
			}
		}
		
		if(auction == null ) {			
			System.out.println("\tThe auction you have requested "
					+ "is not available for bids");
			System.out.println("\tThe auctions available to bid are: ");
			printFutureAuctions(ac.getFutureAuctions());
			endOfRequest();
			
		} else {
			System.out.println("\tThe items available in "
					+ "the " + auction.getAuctionName() + " are:");
			ArrayList<Item> items = (ArrayList<Item>) auction.getItems();
			for(int i = 0; i < items.size(); i ++) {
				System.out.println("\tItem Name: " + items.get(i).getItemName());
				System.out.println("\tItem Description: "
						+ "" + items.get(i).getItemDesciption());
				System.out.println("\tItem Starting Bid: "
						+ "" + items.get(i).getStartingBid());
			}
			
			String itemName = null;
			System.out.println("\tPlease enter the name of the item "
					+ "you would like to bid on:");
			itemName = sc.next();
			double bidAmount = 0.0;
			System.out.println("\tPlease enter your bid amount");
			bidAmount = sc.nextDouble();
			
			int result =
				this.bidder.makeBid(itemName, auctionName, bidAmount, this.ac);
			bidError(result, itemName, auctionName);
		}
	}
	
	public void bidError(int result, String item, String auction) {
		switch (result) {
		case 1:			
			System.out.println("\tYou have suceessfully placed a bid");
			endOfRequest();
		case 2: 
			System.out.println("\tThe minimum bid amount is " + bidder.MIN_AMOUNT_BID_PER_ITEM);
			endOfRequest();
		case 3:
			System.out.println("\tYou have reached the limit of bids allowed in this auction");
			endOfRequest();
			break;	
			
		case 4:
			System.out.println("\tYou have reached the limit of total bids in all auctions");
			endOfRequest();
			break;
		default:
			System.out.println("Auction not available to place bids");
			endOfRequest();
			break;
		}	
	}
	
	public void endOfRequest() {
		int bidderChoice = -1;
		System.out.println(" ");
		System.out.println("What would you like to do next?");
		System.out.println("Please select an option:");
		System.out.println("\t1: Go back to main menu");
		System.out.println("\t0: Logout");
		
		bidderChoice = sc.nextInt();
		
		switch(bidderChoice) {
		case 1: 
			banner();
			this.mainMenu();
			break;
		case 2:
			this.logout();
			break;
		}
		
	}

	public void logout() {
		System.out.println("\tThank you for using Auction Cenral. "
				+ "Have a great day!");
	}


	private void printFutureAuctions(ArrayList<Auction> displayFutureAuctions) {
		System.out.println("The auctions scheduled to take place "
				+ "in the near future are:");
		System.out.println();
		for(int i = 0; i < displayFutureAuctions.size(); i++) {
			System.out.println("\t" + (i + 1) + ".");
			System.out.println("\tAuction Name: "
					+ "" + displayFutureAuctions.get(i).getAuctionName());
			System.out.println("\tAuction Date:"
					+ " " + displayFutureAuctions.get(i).getEndDate());
			System.out.println("\tAuction Time:"
					+ " " + displayFutureAuctions.get(i).getStartTime());
			System.out.println( );
		}

	}

}
