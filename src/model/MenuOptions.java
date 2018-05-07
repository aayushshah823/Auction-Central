package model;

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
		System.out.println("\t1: Login");
		System.out.println("\t2: Exit");
		
		int userChoice;
		do {
			userChoice = sc.nextInt();			
			System.out.print("    Please enter a number to select an option: ");
			
		} while (userChoice != 1 || userChoice != 2);

		return userChoice;
	}
	

}
