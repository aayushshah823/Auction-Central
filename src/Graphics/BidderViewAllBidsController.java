package Graphics;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Auction;
import model.AuctionCentral;
import model.Bidder;
import model.Item;

public class BidderViewAllBidsController implements Initializable{
	
	private AuctionCentral myAuctionCentral;
	private Bidder myBidder;

	//These are for controlling the list of auction 
	@FXML private ListView<String> listOfBids; 
	
	public void construct(AuctionCentral ac, Bidder bidder) {
		this.myAuctionCentral = ac;
		this.myBidder = bidder;
		Map<Auction, Map<Item, Double>> bids = myBidder.getAllItemsInAllAuctions();
		for (Auction auction : bids.keySet()) {
			System.out.println("asgasgasg");
			Map<Item, Double> auctionBids = bids.get(auction);
			for (Item item : auctionBids.keySet()) {
				this.listOfBids.getItems().add(item.getItemName() + " " + auctionBids.get(item) + " " + auction.getAuctionName());
			}
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}