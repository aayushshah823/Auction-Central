package Graphics.Bidder;

import java.io.IOException;
import java.net.URL;
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
public class BidderViewItemsController implements Initializable {

	private AuctionCentral myAuctionCentral;
	
	@FXML
	Label name;
	
	@FXML
	ListView<String> listOfItems;
	
	public void construct(AuctionCentral ac, Auction auction) {
		this.myAuctionCentral = ac;
		for (Item item : auction.getItems()) {
			this.listOfItems.getItems().add(item.getItemName() + " - " + item.getItemDesciption() + " | " + " | " + "Minimum bid: $" + item.getStartingBid());
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Bidder/BidderViewAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        BidderViewAuctionsController controller = (BidderViewAuctionsController) loader.getController();
        controller.construct(myAuctionCentral);
        back.setScene(scene);
        back.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
	}

}