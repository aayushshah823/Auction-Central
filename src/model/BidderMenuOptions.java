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

	public void bidderMenuOptions(Scanner sc, Bidder bidder, AuctionCentral ac) {
		System.out.println("Welcome back " + bidder.getName() + 
				"! You are logged in as a " + bidder.getUserType() +". What would you like to do?");
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
			    optionsAfterPrintingFutureAuctions(sc, bidder, ac);
			    break;
			case 2: 
				printAllAuctionsWithBids(sc, bidder);
				break;
			case 3: 
				System.out.println("Please enter the auction name: ");
				String auctionName = sc.next();
				selectAnAuctionToViewBids(auctionName, bidder);
				break;
			case 4:
				printAllItemsInAllAuctions(sc, bidder, ac);
				break;
			case 0:
				logout(sc);
				break;
			default: 
				System.out.println("Please enter a valid option. "
						+ "1 - 2 - 3 - 4 or 0");
				break;		

		}

	}

	private void printAllItemsInAllAuctions(Scanner sc, Bidder bidder, AuctionCentral ac) {
		ArrayList<Item> items = bidder.getAllIntemsInAllAuctions();
		for(int i = 0; i < items.size(); i++) {
			System.out.println((i + 1) + ": " + items.get(i).getItemName());
		}

	}

	private void selectAnAuctionToViewBids(String auctionName, Bidder bidder) {
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

	private void printAllAuctionsWithBids(Scanner sc, Bidder bidder) {
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
	public void optionsAfterPrintingFutureAuctions(Scanner sc, Bidder bidder, AuctionCentral ac) {
		System.out.println("\tWhat would you like to do nex?"); 
		int bidderChoice = -1;
		System.out.println("\t1: Select an auction");
		System.out.println("\t2: Go back");
		System.out.println("\t0: Logout");
		System.out.print("\tPlease select an option:");
		bidderChoice = sc.nextInt();

		switch (bidderChoice) {
		case 1:	
			System.out.println("The auctions available to bid are: ");
			printFutureAuctions(ac.displayFutureAuctions());
			System.out.println("");
			System.out.println("Please enter the auction name: ");
			String auctionName = sc.next();
			selectAnAuctionToBid(sc, auctionName, bidder);	//TODO 
		case 2: 
			bidderMenuOptions(sc, bidder, ac);
		case 0:
			logout(sc);
			break;	
		default:
			System.out.println("Please enter a valid option. "
					+ "1 - 2 or 0");
			bidderChoice = sc.nextInt();
			break;
		}	
	}

	private void selectAnAuctionToBid(Scanner sc, String auctionName, Bidder bidder) {
		ArrayList<Auction> auction = bidder.getAllAuctions();
		Auction myauction = null; 
		for(int i = 0; i < auction.size(); i++) {
			if(auction.get(i).getAuctionName().equals(auctionName)) {
				myauction = auction.get(i);
			}
		}

		//DISPLAY THE ITEMS
		//HAVE THE USER SELECT THE ITEMS
		//ASK FOR A BID AMOUNT 
		//BID

		//int result = bidder.makeBid(item, auction, bid);

	}

	public void logout(Scanner sc) {
		System.out.println("\tThank you for using Auction Cenral. Have a great day!");
		System.out.println("\tEnter 1 to log back in");
		sc.hasNextInt();
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
