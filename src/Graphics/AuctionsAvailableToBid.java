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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Auction;
import model.AuctionCentral;
import model.Bidder;

public class AuctionsAvailableToBid implements Initializable{
	private static final int MAX_TOTAL_BIDS = 12;
	private AuctionCentral myAuctionCentral;
	private Bidder myBidder;
	@FXML private Label cannotBid;
	@FXML private ListView<String> listOfAuctions; 
	
	public void construct(AuctionCentral ac, Bidder bidder) {
		this.myAuctionCentral = ac;
		this.myBidder = bidder;
		//TODO TEST THIS. ASK JAKE WHICH BIDDER TO USE
		if(this.myBidder.getTotalNumberOfBids() == this.MAX_TOTAL_BIDS) {
			this.cannotBid.setText("You have reached the Maximum number"
					+ " of allowed bids per bidder: " + this.MAX_TOTAL_BIDS);
		} else {
			ArrayList<Auction> auctions = myAuctionCentral.getFutureAuctions();
			for(int i = 0; i < auctions.size(); i ++) {
				this.listOfAuctions.getItems().add(auctions.get(i).getAuctionName());
			}
		}
		
	}
	/**
	 * Precondition: user can bid
	 * Postcondition: user will be sent to 
	 * the bidding page for the auction selected
	 * @throws IOException 
	 */
	@FXML
	public void goToAuction(ActionEvent event) throws IOException {
		String selectedAuction = listOfAuctions.getSelectionModel().getSelectedItem();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/BiddingScene.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage bidingScene = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        bidingScene.setScene(scene);
        BiddingController controller = (BiddingController) loader.getController();
        controller.construct(myAuctionCentral, myBidder, selectedAuction); 
        bidingScene.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.cannotBid.setText("");
	}

}
