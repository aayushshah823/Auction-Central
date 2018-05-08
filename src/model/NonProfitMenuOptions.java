package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author Benjamin, Jake
 */
public class NonProfitMenuOptions {

	private NonProfit myNonProfit;
	private Scanner myScan;
	private AuctionCentral myAucCent;
	private boolean isSubmitAuct;
	
	public NonProfitMenuOptions(Scanner sc, AuctionCentral ac, NonProfit nonprofit) {
		this.myScan = sc;
		this.myAucCent = ac;
		this.myNonProfit = nonprofit;
		isSubmitAuct = false;
	}
	

	//------------------------------------------- NonProfit PROMPTS START -------------------------------------------------
	public void nonProfitMenuOptions() {
		System.out.println("------------  A U C T I O N   C E N T R A L ------------");
		System.out.println("Welcome back " + myNonProfit.getName() + 
				"! You are logged in as a " + myNonProfit.getUserType() +". What would you like to do?");
		int nonprofitChoice = -1;
		System.out.println("\t1: View active auctions");
		System.out.println("\t2: View auctions I have submitted");
		System.out.println("\t3: Schedule a new auction");
		System.out.println("\t0: Logout");
		System.out.print("\tPlease select an option:");
		nonprofitChoice = myScan.nextInt();	

		switch(nonprofitChoice) {
		case 0:
			logout();
			break;
		case 1: 
			//View active auctions 
			System.out.println("Here is a list of all active auctions. Select an option:");
			displayActiveAuctions(myAucCent.getFutureAuctions());
			break;
		case 2: 
			//View auctions I have submitted, 
			System.out.println("Here is a list of all auctions you have submitted. Select an option:");			
			printSubmittedAuctions(myNonProfit.getAuctions());
			break;
		case 3: 			
			displayAuctionRequest();
			break;		
		}
	}
	
	private void displayAuctionRequest() {
		System.out.println("\tSchedule a new auction");
		System.out.println("\tPlease enter the start date in the form of \"day-month-year\"");
		LocalDate theStartDate = getDate();
		System.out.println("\tPlease enter the end date in the form of \"day-month-year\"");
		LocalDate theEndDate = getDate();
		System.out.println("\tPlease enter the start time in the form of \"hour : minutes\"");
		LocalTime theStartTime = getTime();
		System.out.println("\tPlease enter the end time in the form of \"hour : minutes\"");
		LocalTime theEndTime = getTime();
		
		Map<Integer, ArrayList<LocalDate>> aucReqMap = myAucCent.auctionRequest(myNonProfit, 
													theStartDate, theEndDate, theStartTime, theEndTime);
		if (aucReqMap.isEmpty()) {	
			System.out.println("Congratulations!! Your Auction Request has been submitted!");
			Auction auction = new Auction(theStartDate, theEndDate, theStartTime, theEndTime);
			myNonProfit.addAuction(auction);
			System.out.println("\t1: Nonprofit Main Menu ");
			System.out.println("\t0: Logout");
			
			int choice = myScan.nextInt();		
			if (choice == 0) {
				logout();
			} else if (choice == 1){
				nonProfitMenuOptions();
			}			
		} else if (aucReqMap.containsKey(0)) {
			System.out.println("The start date is after the end date.");
		} else if (aucReqMap.containsKey(1)) {
			System.out.println("Hasn't been a year since last auction");
		} else if (aucReqMap.containsKey(2)) {
			System.out.println("The start date is less than 14 days away from todays date.");
		} else if (aucReqMap.containsKey(3)) {
			System.out.println("The start date is less than 14 days away from todays date.");
		} else if (aucReqMap.containsKey(4)) {
			System.out.println("The dates requested are more than 60 days away from todays date.");
		} else if (aucReqMap.containsKey(5)) {
			System.out.println("There is a day or days requested with 2 or more auctions scheduled that day.");
		}
		
	}
	
	private LocalTime getTime() {
		String response = myScan.next();
		String[] values = response.split(":");
        int hour = Integer.parseInt(values[0]);
        int min = Integer.parseInt(values[1]);
        
		LocalTime time = LocalTime.of(hour, min, 0);		
		return time;
	}
	
	private LocalDate getDate() {
		String response = myScan.next();
		String[] values = response.split("-");
        int day = Integer.parseInt(values[0]);
        int month = Integer.parseInt(values[1]);
        int year = Integer.parseInt(values[2]);
		
		LocalDate date = LocalDate.of(year, month, day);		
		return date;
	}
	
	/**
	 * Display active actions
	 */
	private void displayActiveAuctions(List<Auction> auctions) {
		int nonprofitChoice = -1;
		System.out.println();
		for(int i = 0; i < auctions.size(); i++) {
			System.out.println("\t" + (i + 1) + ".");
			System.out.println("\tAuction Name: " + auctions.get(i).getAuctionName());
			System.out.println("\tAuction Date: " + auctions.get(i).getStartDate());
			System.out.println("\tAuction Time: " + auctions.get(i).getStartTime());
			System.out.println();
		}
		System.out.println("\t1: Back");
		System.out.println("\t0: Logout");
		
		nonprofitChoice = myScan.nextInt();		
		if (nonprofitChoice == 0) {
			logout();
		} else if (nonprofitChoice == 1){
			nonProfitMenuOptions();
		} else {
			aucOpt(auctions.get(nonprofitChoice), auctions);
		}
	}
	
	private void aucOpt(Auction auctions, List<Auction> auctionList) {
		System.out.print(auctions.getAuctionName() + " - ");
		System.out.print(auctions.getAuctionLocation() + " - ");
		System.out.print(auctions.getStartDate() + " at ");
		System.out.print(auctions.getStartTime());
		
		System.out.println("\t2: View Item List");
		System.out.println("\t3: Add an Item");
		System.out.println("\t1: Back");
		System.out.println("\t0: Logout");
		
		int choice = myScan.nextInt();		
		if (choice == 0) {
			logout();
		} else if (choice == 1){
			printSubmittedAuctions(auctionList);
		} else if (choice == 2){
			isSubmitAuct = false;
			displayAuctionItems(auctions.getItems(), auctionList, auctions);
		} else if (choice == 3){
			if (auctions.isAddItemValid()) {
				addItemMenu(auctions);
			} else {
				System.out.println("Sorry, you reached the maximum number of items for this auction");
			}
		}
	}
	
	private void addItemMenu(Auction auction) {
		System.out.println("Enter the name of the item: ");
		String itemName = myScan.next();
		Double startBid = myScan.nextDouble();
		String itemDes = myScan.next();
		int itemCount = myScan.nextInt();
		Item newItem = new Item(itemName, startBid, itemDes,  itemCount);
		auction.addItem(newItem);
	}

	/**
	 * Display submitted auctions
	 */
	private void printSubmittedAuctions(List<Auction> auctions) {
		int nonprofitChoice = -1;
		System.out.println();
		for(int i = 0; i < auctions.size(); i++) {
			System.out.println("\t" + (i + 1) + ".");
			System.out.println("\tAuction Name: " + auctions.get(i).getAuctionName());
			System.out.println("\tAuction Date: " + auctions.get(i).getStartDate());
			System.out.println("\tAuction Time: " + auctions.get(i).getStartTime());
			System.out.println();
		}
		System.out.println("\t1: Back");
		System.out.println("\t0: Logout");
		
		nonprofitChoice = myScan.nextInt();		
		if (nonprofitChoice == 0) {
			logout();
		} else if (nonprofitChoice == 1){
			nonProfitMenuOptions();
		} else {
			auctionOptions(auctions.get(0), auctions);
		}
	}
	
	public void auctionOptions(Auction auctions, List<Auction> auctionList) {
		System.out.println("\tAuction Name: " + auctions.getAuctionName());
		System.out.println("\tAuction Date: " + auctions.getStartDate());
		System.out.println("\tAuction Time: " + auctions.getStartTime());
		System.out.println();
		System.out.println("\t1: Back");
		System.out.println("\t2: View Item List");
		System.out.println("\t3: Add an Item");
		System.out.println("\t0: Logout");
		
		int choice = myScan.nextInt();		
		if (choice == 0) {
			logout();
		} else if (choice == 1){
			printSubmittedAuctions(auctionList);
		} else if (choice == 2){
			isSubmitAuct = true;			
			displayAuctionItems(auctions.getItems(), auctionList, auctions);
		} else if (choice == 3){
			if (auctions.isAddItemValid()) {
				addItemMenu(auctions);
			} else {
				System.out.println("Sorry, you reached the maximum number of items for this auction");
			}
		}		
	}
	
	public void displayAuctionItems(List<Item> items, List<Auction> auctionList, Auction auction) {
		System.out.println("\t1: Back");
		for (int i = 0 ; i < items.size(); i++) {
			System.out.print("\t" + (i + 2) + ":");
			System.out.println(" Item: " + items.get(i).getItemName());
		}
		System.out.println("\t0: Logout");
		
		int choice = myScan.nextInt();		
		if (choice == 0) {
			logout();
		} else if (choice == 1){
			if (isSubmitAuct) {
				auctionOptions(auction, auctionList);
			} else{
				aucOpt(auction, auctionList);
			}
		} else {
			displayItem(items.get(choice - 2), items, auctionList, auction);
		}
	}
	
	public void displayItem(Item item, List<Item> itemList, List<Auction> auctionList, Auction auction) {
		System.out.println("\tItem Name: " + item.getItemName());
		System.out.println("\tDescription: " + item.getItemDesciption());
		System.out.println("\tItem Count: " + item.getItemCount());
		System.out.println("\tCurrent Bid: $" + item.getCurrentBid());
		System.out.println();
		System.out.println("\t1: Back");
		System.out.println("\t0: Logout");
		
		int choice = myScan.nextInt();		
		if (choice == 0) {
			logout();
		} else if (choice == 1){
			displayAuctionItems(itemList, auctionList, auction);
		} 		
	}
	
	public void logout() {
		System.out.println("\tThank you for using Auction Central. Have a great day!");
	}


}
