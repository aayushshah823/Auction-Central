package Graphics.Bidder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
		name.setText("Welcome, " + myBidder.getName());
	}

	
	@FXML
	public void viewAuctionsAvailableToBid(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Bidder/BidderSeeAllAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage bidderAuction = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        bidderAuction.setScene(scene);
        AuctionsAvailableToBid controller = (AuctionsAvailableToBid) loader.getController();
        controller.construct(myAuctionCentral, myBidder);
        bidderAuction.show();
	}
	
    public void viewItemsInOneAuction(ActionEvent event) throws IOException{
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Bidder/BidderViewAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage bidderAuction = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        bidderAuction.setScene(scene);
        BidderViewAuctionsController controller = (BidderViewAuctionsController) loader.getController();
        controller.construct(myAuctionCentral); 
        bidderAuction.show();
	}
    
    public void viewBids(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Bidder/BidderViewBids.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage bidderAuction = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        bidderAuction.setScene(scene);
        BidderViewBidsController controller = (BidderViewBidsController) loader.getController();
        controller.construct(myAuctionCentral, myBidder); 
        bidderAuction.show();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
	}

}
