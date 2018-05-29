package Graphics.Employee;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;



import Graphics.LoginController;
import Graphics.Bidder.BidderController;
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
import model.NonProfit;

public class AuctionsBetweenDates implements Initializable{
	
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
	
	public void construct(AuctionCentral ac, AuctionCentralEmployee employee, LocalDate theStartDate, 
															LocalDate theEndDate, String theMessage) {
		this.myAuctionCentral = ac;
		this.myEmployee = employee;
		myStartDate = theStartDate;
		myEndDate = theEndDate;
		myLabel.setText("Auctions between " + theStartDate + " : " + theEndDate);
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
		                	System.out.println("asgasg");
		                	String toDisplay = auction.getAuctionName() + " | " + getNonProfitName(auction) + " | " + auction.getStartDate() + " " 
									+ auction.getStartTime() + "-" + auction.getEndTime() + " | " + auction.getItems().size();
							if (auction.getItems().size() == 1) {
								toDisplay += " item";
							} else {
								toDisplay += " items";
							}
							if (auction.getStartDate().isBefore(LocalDate.now()) || auction.getStartDate().isEqual(LocalDate.now())) {
								toDisplay += " | CLOSED";
							}
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
		
		for (Auction auction : myAuctionCentral.getAuctionsBetweenDates(theStartDate, theEndDate)) {
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
								DecimalFormat df = new DecimalFormat("0.00"); 
								String toDisplay = item.getItemName() + " | Minimum bid: $" 
										+ df.format(item.getStartingBid());          	
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
	
	private String getNonProfitName(Auction theAuction) {
		for (NonProfit nonProfit : myAuctionCentral.getAllAuctions().keySet()) {
			for (Auction auction : myAuctionCentral.getAllAuctions().get(nonProfit)) {
				if (auction.equals(theAuction)) {
					return nonProfit.getOrg();
				}
			}
		}
		return null;
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/EmployeeViewItemControllerTwoDates.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        EmployeeViewItemControllerTwoDates controller = (EmployeeViewItemControllerTwoDates) loader.getController();
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/AuctionsBetweenTwoDates.fxml"));
	        AnchorPane anchorPane = loader.load();
	        Stage auctionsBetweenDates = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
	        Scene scene = new Scene(anchorPane);
	        auctionsBetweenDates.setScene(scene);
	        AuctionsBetweenDates controller = (AuctionsBetweenDates) loader.getController();
	        controller.construct(myAuctionCentral, myEmployee, myStartDate, myEndDate, cancelMessage);
	        auctionsBetweenDates.show();
		} else {
			myAuctionCancelledLabel.setTextFill(Color.web("#ff0000"));
			myAuctionCancelledLabel.setText("The auction '" + myCurrentAuction.getAuctionName() + "' cannot be cancelled.\n" +
											"The auction currently has bids submitted.");
		}
	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/InputDateRange.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        InputDateRangeController controller = (InputDateRangeController) loader.getController();
        controller.construct(myAuctionCentral, (AuctionCentralEmployee) myEmployee);
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

	public void home(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/EmployeeMainMenu.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage login = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        login.setScene(scene);
        EmployeeController controller = (EmployeeController) loader.getController();
        controller.construct(myAuctionCentral, myEmployee, 0);
        login.show();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myAuctionCancelledLabel.setText("");
	}

}
