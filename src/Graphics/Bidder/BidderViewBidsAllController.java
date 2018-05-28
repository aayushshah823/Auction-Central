package Graphics.Bidder;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

import Graphics.LoginController;
import javafx.fxml.Initializable;
import javafx.application.Platform;
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
	ListView<String> listOfBids;
	
	public void construct(AuctionCentral ac, Bidder bidder) {
		DecimalFormat df = new DecimalFormat("0.00"); 
		this.myAuctionCentral = ac;
		this.myBidder = bidder;
		Map<Auction, Map<Item, Double>> bids = myBidder.getAllItemsInAllAuctions();
		for (Auction auction : bids.keySet()) {
			Map<Item, Double> auctionBids = bids.get(auction);
			for (Item item : auctionBids.keySet()) {
				this.listOfBids.getItems().add("Item: " + item.getItemName() + "\nDescription: " + item.getItemDesciption() + "\nMinimum bid: $" 
			+ df.format(item.getStartingBid()) + " | My bid: $" + df.format(auctionBids.get(item)) + " | " + auction.getAuctionName());
			}
		}
	}
	
	public void exit(ActionEvent theEvent) throws IOException {
		Platform.exit();
	}
	
	public void logout(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Login.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage login = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        login.setScene(scene);
        LoginController controller = (LoginController) loader.getController();
        controller.construct(myAuctionCentral);
        login.show();
	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Bidder/BidderViewBids.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        BidderViewBidsController controller = (BidderViewBidsController) loader.getController();
        controller.construct(myAuctionCentral, myBidder);
        back.setScene(scene);
        back.show();
	}
	
	public void BackToBidderMainMenu(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/bidder/BidderMainMenu.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage login = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        login.setScene(scene);
        BidderController controller = (BidderController) loader.getController();
        controller.construct(myAuctionCentral, this.myBidder);
        login.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
	}

}
