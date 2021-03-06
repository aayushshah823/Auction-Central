package Graphics.Bidder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import Graphics.LoginController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Auction;
import model.AuctionCentral;
import model.Bidder;
import model.Item;

public class BiddingController  implements Initializable {
	
	private AuctionCentral myAuctionCentral;
	private Bidder myBidder;
	private Auction currentAuction;
	private Item item;
	@FXML
	Label feedBack;
	@FXML
	Label itemDetails;	
	@FXML
	TextField bidAmount;
	@FXML
	Button submitBidAmount;
	
	
	public void construct(AuctionCentral ac, Bidder bidder, Auction auction, Item item) {
		this.myAuctionCentral = ac;
		this.myBidder = bidder; 
		this.currentAuction = auction;  
		this.item = item;
		feedBack.setText("");
		if(currentAuction.isMaxBidsPerAuction(myBidder)) {
			this.feedBack.setTextFill(Color.web("#ff0000"));
			this.feedBack.setText("You have achieved the maximum number of "
					+ "allowed bids per auction: " +  currentAuction.MAX_BIDS_ALLOWED_PER_BIDDER);
			this.submitBidAmount.setDisable(true);
		}
		DecimalFormat df = new DecimalFormat("0.00"); 
		String details = "Item: "+
				this.item.getItemName() + "\n\nDescription: " + item.getItemDesciption() 
				+ "\n\nStarting Bid: " + "$" + df.format(this.item.getStartingBid());
		this.itemDetails.setText(details);
	}
	

	public void exit(ActionEvent theEvent) throws IOException {
	try {
		FileOutputStream file = new FileOutputStream("auctionCentralDefault.ser");
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(myAuctionCentral);
		out.close();
		file.close();
	} catch (IOException exception) {
		System.out.println("IOException");
	}
		Platform.exit();
	}
	
	@FXML
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
	
	@FXML
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/bidder/BidderSeeAllAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage login = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        login.setScene(scene);
        AuctionsAvailableToBid controller = (AuctionsAvailableToBid) loader.getController();
        controller.construct(myAuctionCentral, this.myBidder);
        login.show();
	}
	@FXML
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

	@FXML
	public void enterBidAmount(ActionEvent event) throws IOException { 		
		String bid = this.bidAmount.getText();
		Double bidAmount = Double.parseDouble(bid);
		int attemptedBid = this.currentAuction.makeBid(this.item, bidAmount, this.myBidder);
		String fb = "";
		if(attemptedBid == 2) {
		   fb = "Bid Amount is lower" + "\n" + "han starting bid";
		} else {
		   fb = "Congratulations!" + "\n" + "Your Bid has been placed!";
		}
		

		this.feedBack.setText(fb);
	
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
