package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
	}
}
