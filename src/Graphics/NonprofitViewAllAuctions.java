package Graphics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Graphics.Bidder.BiddingController;
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
import model.NonProfit;

public class NonprofitViewAllAuctions implements Initializable {

	
	private AuctionCentral myAuctionCentral;
	private NonProfit myNonProfit;
	
	public Auction myCurrentAuction ;
	public Item myCurrentItem;
	

	@FXML 
	private ListView<Auction> listOfAuctions; 
	@FXML 
	private ListView<Item> listOfItems; 
	@FXML
	private Button add;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void construct(AuctionCentral ac, NonProfit nonProfit) {
		this.myAuctionCentral = ac;
		this.myNonProfit = nonProfit;
		
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
						          myCurrentAuction = cell.getItem(); 	
						          displayCurrentAuction();
					         }
					    }
					}
            	});
		        return cell;
		    }
		});
		
		for (Auction auction : myAuctionCentral.getAuctionsSortedByDate()) {
			   listOfAuctions.getItems().add(auction);
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
		this.listOfItems.getItems().clear();
		if(myCurrentAuction.getItems()  != null) {
			for (Item item : myCurrentAuction.getItems()) {
				this.listOfItems.getItems().add(item);
			}
		}
	}
	
	public void add(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/AddItemToAuction.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        back.show();
	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/NonProfitWelcomeScreen.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        back.show();
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

}
