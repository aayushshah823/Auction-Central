package Graphics.Bidder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Auction;
import model.AuctionCentral;
import model.Bidder;
import model.Item;
/**
 * 
 * @author Jake
 *
 */
public class BidderViewBidsAllController implements Initializable {

	private AuctionCentral myAuctionCentral;
	private Bidder myBidder;
	
	@FXML
	Label name;
	
	@FXML
	ListView<String> listOfBids;
	
	public void construct(AuctionCentral ac, Bidder bidder) {
		this.myAuctionCentral = ac;
		this.myBidder = bidder;
		Map<Auction, Map<Item, Double>> bids = myBidder.getAllItemsInAllAuctions();
		for (Auction auction : bids.keySet()) {
			Map<Item, Double> auctionBids = bids.get(auction);
			for (Item item : auctionBids.keySet()) {
				this.listOfBids.getItems().add(item.getItemName() + " - " + item.getItemDesciption() + " | " + auction.getAuctionName() + " | " + "Minimum bid: $" 
			+ item.getStartingBid() + " My bid: $" + auctionBids.get(item));
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
	}

}
