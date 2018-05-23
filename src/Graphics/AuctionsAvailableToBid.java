package Graphics;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Auction;
import model.AuctionCentral;
import model.Bidder;

public class AuctionsAvailableToBid implements Initializable{
	
	private static final String ArrayList = null;
	private AuctionCentral myAuctionCentral;
	private Bidder myBidder;

	//These are for controlling the list of auction 
	@FXML private ListView<String> listOfAuctions; 
	
	public void construct(AuctionCentral ac, Bidder bidder) {
		this.myAuctionCentral = ac;
		this.myBidder = bidder;
		ArrayList<Auction> auctions = myAuctionCentral.getFutureAuctions();
		ArrayList<String> auctionNames = new ArrayList<String>();
		for(int i = 0; i < auctions.size(); i ++) {
			//auctionNames.add(auctions.get(i).getAuctionName());
			this.listOfAuctions.getItems().add(auctions.get(i).getAuctionName());
		}
		//listOfAuctions.getItems().addAll("auction1", "auction1", "auction1");
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
