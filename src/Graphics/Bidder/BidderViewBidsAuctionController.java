package Graphics.Bidder;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.Auction;
import model.Bidder;
import model.Item;

public class BidderViewBidsAuctionController implements Initializable{

	@FXML 
	private ListView<String> listOfBids; 
	@FXML
	private Label auctionInfo;
	
	public void construct(Bidder bidder, Auction auction) {
		Map<Item, Double> bids = bidder.getBidsInOneAuction(auction);
		for (Item item : bids.keySet()) {
			this.listOfBids.getItems().add(item.getItemName() + " - " + item.getItemDesciption() + " | " + "Minimum bid: $" 
					+ item.getStartingBid() + " My bid: $"+ bids.get(item) + " | " + auction.getAuctionName());
		}
		auctionInfo.setText(auction.getAuctionName() + " | " + auction.getStartDate() + " " + auction.getStartTime() + "-" + auction.getEndTime());
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}


}