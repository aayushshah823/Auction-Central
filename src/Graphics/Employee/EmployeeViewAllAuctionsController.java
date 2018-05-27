package Graphics.Employee;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Auction;
import model.AuctionCentral;
import model.AuctionCentralEmployee;
import model.Item;

public class EmployeeViewAllAuctionsController implements Initializable{
	
	private AuctionCentral myAuctionCentral;
	private AuctionCentralEmployee myEmployee;
	private Auction myCurrentAuction;
	private Item myCurrentItem;
	private LocalDate myStartDate;
	private LocalDate myEndDate;
	
	@FXML
	private Label myLabel;
	
	@FXML
	private Label myAuctionCancelledLabel;

	//These are for controlling the list of auction 
	@FXML private ListView<Auction> listOfAuctions; 
	
	@FXML private ListView<Item> listOfItems;
	
	public void construct(AuctionCentral ac, AuctionCentralEmployee employee, String theMessage) {
		this.myAuctionCentral = ac;
		this.myEmployee = employee;
		myLabel.setText("Viewing All Auctions");
		myCurrentAuction = null;
		myAuctionCancelledLabel.setText(theMessage);		
		
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
					            if(event.getClickCount() == 1){
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
			                } else {
			                	setText("");
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
	
	public void viewItem(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/EmployeeViewItem.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        EmployeeViewItemController controller = (EmployeeViewItemController) loader.getController();
        controller.construct(myAuctionCentral, (AuctionCentralEmployee) myEmployee, myCurrentAuction, myCurrentItem, myStartDate, myEndDate);
        back.show();
	}
	
	@FXML
	public void cancelAuction(ActionEvent theEvent) throws IOException {
		if (myCurrentAuction.getStartDate().isBefore(LocalDate.now())) {
			myAuctionCancelledLabel.setTextFill(Color.web("#ff0000"));
			myAuctionCancelledLabel.setText("The auction '" + myCurrentAuction.getAuctionName() + "' cannot be cancelled.\n" +
											"The auction has already passed.");
		} else if (myAuctionCentral.cancelAuction(myCurrentAuction) == 1) {
			String cancelMessage = "The auction '" + myCurrentAuction.getAuctionName() + "' has been cancelled.";
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/EmployeeViewAllAuctions.fxml"));
	        AnchorPane anchorPane = loader.load();
	        Stage auctionsBetweenDates = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
	        Scene scene = new Scene(anchorPane);
	        auctionsBetweenDates.setScene(scene);
	        EmployeeViewAllAuctionsController controller = (EmployeeViewAllAuctionsController) loader.getController();
	        controller.construct(myAuctionCentral, myEmployee, cancelMessage);
	        auctionsBetweenDates.show();
		} else {
			myAuctionCancelledLabel.setTextFill(Color.web("#ff0000"));
			myAuctionCancelledLabel.setText("The auction '" + myCurrentAuction.getAuctionName() + "' cannot be cancelled.\n" +
											"The auction currently has bids submitted.");
		}
	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/EmployeeMainMenu.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        EmployeeController controller = (EmployeeController) loader.getController();
        controller.construct(myAuctionCentral, (AuctionCentralEmployee) myEmployee, 0);
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myAuctionCancelledLabel.setText("");
	}

}
