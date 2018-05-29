package Graphics.Bidder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
/**
 * 
 * @author Raisa
 *
 */
public class BidderController implements Initializable {

	private AuctionCentral myAuctionCentral;
	private Bidder myBidder;
	
	@FXML
	Label name;
	
	@FXML
	ListView<String> listOfAuctions;
	
	public void construct(AuctionCentral ac, Bidder bidder) {
		this.myAuctionCentral = ac;
		this.myBidder = bidder;
		name.setText("Welcome " + myBidder.getName() + 
				"! You are logged in as a bidder. What would you like to do?");
	}

	
	@FXML
	public void viewAuctionsAvailableToBid(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().
				getResource("/Graphics/Bidder/BidderSeeAllAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage bidderAuction = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        bidderAuction.setScene(scene);
        AuctionsAvailableToBid controller = (AuctionsAvailableToBid) loader.getController();
        controller.construct(myAuctionCentral, myBidder);
        bidderAuction.show();
	}
	
    public void viewItemsInOneAuction(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().
    			getResource("/Graphics/Bidder/BidderViewAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage bidderAuction = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        bidderAuction.setScene(scene);
        BidderViewAuctionsController controller = 
        		(BidderViewAuctionsController) loader.getController();
        controller.construct(myAuctionCentral, myBidder); 
        bidderAuction.show();
	}
    
    public void viewBids(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().
    			getResource("/Graphics/Bidder/BidderViewBids.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage bidderAuction = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        bidderAuction.setScene(scene);
        BidderViewBidsController controller = 
        		(BidderViewBidsController) loader.getController();
        controller.construct(myAuctionCentral, myBidder); 
        bidderAuction.show();
    }
    
	public void exit(ActionEvent theEvent) throws IOException {
		Platform.exit();
	}
	
	public void logout(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().
				getResource("/Graphics/Login.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage login = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        login.setScene(scene);
        LoginController controller = (LoginController) loader.getController();
        controller.construct(myAuctionCentral);
        login.show();
	}
	@FXML
	public void BackToBidderMainMenu(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().
				getResource("/Graphics/bidder/BidderMainMenu.fxml"));
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
