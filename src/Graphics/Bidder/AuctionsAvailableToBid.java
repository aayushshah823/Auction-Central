package Graphics.Bidder;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Graphics.LoginController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Auction;
import model.AuctionCentral;
import model.Bidder;
import model.Item;

public class AuctionsAvailableToBid implements Initializable{
	private static final int MAX_TOTAL_BIDS = 12;
	public AuctionCentral myAuctionCentral;
	public Bidder myBidder;
	public Auction myCurrentAuction ;
	public Item myCurrentItem;
	@FXML 
	private Label cannotBid;
	@FXML
	private Label itemTitle; 
	@FXML 
	private ListView<Auction> listOfAuctions; 
	@FXML 
	private ListView<Item> listOfItems; 
	@FXML
	private Button bid; 
	
	public void construct(AuctionCentral ac, Bidder bidder) {
		this.myAuctionCentral = ac;
		this.myBidder = bidder;
			
		listOfAuctions.setCellFactory(new Callback<ListView<Auction>, ListCell<Auction>>() {
		    @Override
		    public ListCell<Auction> call(ListView<Auction> param) {
		         ListCell<Auction> cell = new ListCell<Auction>() {
		             @Override
		            protected void updateItem(Auction auction, boolean empty) {
		                super.updateItem(auction, empty);
		                if(auction != null) {
		                	String toDisplay = auction.getAuctionName() + " | " + auction.getStartDate() + " " 
				                	+ auction.getStartTime() + "-" + auction.getEndTime() + " | ";		                	
		                	setText(toDisplay);
		                }
		            }
		         };
		         cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							if(event.getButton().equals(MouseButton.PRIMARY)){
					            if(event.getClickCount() == 2){
					              listOfItems.getItems().clear();
						          myCurrentAuction = cell.getItem(); 	
						          displayCurrentAuction();
					         }
					    }
					}
            	});
		        return cell;
		    }
		});
		for (Auction auction : myAuctionCentral.getFutureAuctions()) {
			this.listOfAuctions.getItems().add(auction);
		}
		
		listOfItems.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {
			@Override
			public ListCell<Item> call(ListView<Item> arg0) {
				 ListCell<Item> cell = new ListCell<Item>() {
				       @Override
			            protected void updateItem(Item item, boolean empty) {
			                super.updateItem(item, empty);
			                if(item != null) {
			                	String toDisplay = "Item Name: " + item.getItemName() + "\n" +
			                      "Item Description: " + item.getItemDesciption() + "\n" 
					                + "Starting Bid: "	+ item.getStartingBid() + "\n";		                	
			                	setText(toDisplay);
			                }
			            }
				 };
				 cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							if(event.getButton().equals(MouseButton.PRIMARY)){
					            if(event.getClickCount() == 1){
					            	myCurrentItem = cell.getItem();
					         }
					    }
					}
         	});
				return cell;
			}
		});
		
	}	
	
	public void displayCurrentAuction() {
		this.itemTitle.setText("Available Items");
		this.listOfItems.getItems().clear();
		if(myCurrentAuction.getItems()  != null) {
			for (Item item : myCurrentAuction.getItems()) {
				this.listOfItems.getItems().add(item);
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
	public void goToBiddingScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Bidder/BiddingScene.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage bidingScene = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        bidingScene.setScene(scene);
        BiddingController controller = (BiddingController) loader.getController();
        controller.construct(myAuctionCentral, myBidder, this.myCurrentAuction, this.myCurrentItem); 
        bidingScene.show();
	}
	
	@FXML
	public void exit(ActionEvent theEvent) throws IOException {
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.cannotBid.setText("");
		this.itemTitle.setText("");
	}

}
