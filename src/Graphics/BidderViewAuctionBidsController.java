package Graphics;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Auction;
import model.Bidder;
import model.Item;

public class BidderViewAuctionBidsController implements Initializable{

	//These are for controlling the list of auction 
	@FXML private ListView<String> listOfBids; 
	
	public void construct(Bidder bidder, Auction auction) {
		Map<Item, Double> bids = bidder.getBidsInOneAuction(auction);
		for (Item item : bids.keySet()) {
			this.listOfBids.getItems().add(item.getItemName());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}


}