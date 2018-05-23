package Graphics;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Auction;
import model.AuctionCentral;
import model.Bidder;
import model.Item;

public class BiddingController  implements Initializable {
	
	private AuctionCentral myAuctionCentral;
	private Bidder myBidder;
	private Auction currentAuction;
	@FXML
	Label feedBack;
	@FXML
	Button bid;
	@FXML
	ListView<String> listOfItems;
	@FXML
	TextArea ItemDetails;
	@FXML
	TextField bidAmount;
	@FXML
	Button submitBidAmount;
	@FXML
	Label biddingFeedback;
	
	public void construct(AuctionCentral ac, Bidder bidder, String auctionName) {
		this.myAuctionCentral = ac;
		this.myBidder = bidder; 
		currentAuction = null; 
		ArrayList<Auction> auctions = myAuctionCentral.getFutureAuctions();
		for(int i = 0; i < auctions.size(); i ++) { 
			if(auctions.get(i).getAuctionName().equals(auctionName)) {
				currentAuction = auctions.get(i);
				break;
			}
		}
		//showing the list of items
		if(currentAuction.isMaxBidsPerAuction(myBidder)) {
			this.feedBack.setText("You have achived the maximum number of "
					+ "allowed bids per auction: " +  currentAuction.MAX_BIDS_ALLOWED_PER_BIDDER);
			this.bid.setDisable(true);
		}else {
			ArrayList<Item> items = currentAuction.getItems();				
			for(int i = 0; i < items.size(); i ++) {
				this.listOfItems.getItems().add(items.get(i).getItemName());
			}
		}
		
	}
	
	//TODO Maybe I should have a new controller for this.. 
	@FXML
	public void bidOnItem(ActionEvent event) throws IOException { 		
		String selectedItem = listOfItems.getSelectionModel().getSelectedItem();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/BiddingConditionsClear.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage biddingCleared = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        biddingCleared.setScene(scene);
        biddingCleared.show();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		feedBack.setText("");
		
	}

}
