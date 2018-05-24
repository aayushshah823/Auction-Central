package Graphics;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Auction;
import model.AuctionCentral;
import model.AuctionCentralEmployee;

public class AuctionsBetweenDates implements Initializable{
	
	private static final String ArrayList = null;
	private AuctionCentral myAuctionCentral;
	private AuctionCentralEmployee myEmployee;
	
	@FXML
	Label myLabel;

	//These are for controlling the list of auction 
	@FXML private ListView<String> listOfAuctions; 
	
	public void construct(AuctionCentral ac, AuctionCentralEmployee employee, LocalDate theStartDate, LocalDate theEndDate) {
		this.myAuctionCentral = ac;
		this.myEmployee = employee;
		myLabel.setText("Viewing Auctions between " + theStartDate + " : " + theEndDate);
		ArrayList<Auction> auctions = myAuctionCentral.getAuctionsBetweenDates(theStartDate, theEndDate);
		for(int i = 0; i < auctions.size(); i ++) {
			this.listOfAuctions.getItems().add(auctions.get(i).getAuctionName());
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
