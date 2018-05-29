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

public class AuctionsAvailableToBid implements Initializable{
	public AuctionCentral myAuctionCentral;
	public Bidder myBidder;
	public Auction myCurrentAuction;
	public Item myCurrentItem;
	@FXML 
	private Label cannotBid;
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
		                	String toDisplay = auction.getAuctionName() + " | " 
		                + getNonProfitName(auction) + " | " + auction.getStartDate() + " " 
									+ auction.getStartTime() + "-" + auction.getEndTime() 
									+ " | " + auction.getItems().size();
							if (auction.getItems().size() == 1) {
								toDisplay += " item";
							} else {
								toDisplay += " items";
							}
							setText(toDisplay);
		                }
		            }
		         };
		         cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
		        	 @Override
		        	 public void handle(MouseEvent event) {
		        		 if(event.getButton().equals(MouseButton.PRIMARY)){
		        			 myCurrentAuction = cell.getItem();
		        			 displayCurrentAuction();
		        		 }
		        	 }
		         });
		         return cell;
		    }
		});
		for (Auction auction : myAuctionCentral.getFutureAuctions()) {
			if (auction.getItems().size() > 0) {
				this.listOfAuctions.getItems().add(auction);
			}
		}
		DecimalFormat df = new DecimalFormat("0.00"); 
		listOfItems.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {
			@Override
			public ListCell<Item> call(ListView<Item> arg0) {
				ListCell<Item> cell = new ListCell<Item>() {
					@Override
					protected void updateItem(Item item, boolean empty) {
						super.updateItem(item, empty);
						if(item != null) {
							String toDisplay = item.getItemName() 
									+ "\n\tMinimum bid: $" 

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
							myCurrentItem = cell.getItem();
						}
					}

				});
				return cell;
			}
		});

	}	
	
	public void displayCurrentAuction() {
		this.listOfItems.getItems().clear();
		for (Item item : myCurrentAuction.getItems()) {
			this.listOfItems.getItems().add(item);
		}
	}
	
	private String getNonProfitName(Auction theAuction) {
		for (NonProfit nonProfit : myAuctionCentral.getAllAuctions().keySet()) {
			for (Auction auction : myAuctionCentral.
					getAllAuctions().get(nonProfit)) {
				if (auction.equals(theAuction)) {
					return nonProfit.getOrg();
				}
			}
		}
		return null;
	}
	
	/**
	 * Precondition: user can bid
	 * Postcondition: user will be sent to 
	 * the bidding page for the auction selected
	 * @throws IOException 
	 */
	@FXML
	public void goToBiddingScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().
				getResource("/Graphics/Bidder/BiddingScene.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage bidingScene = (Stage)((Node)event.
        		getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        bidingScene.setScene(scene);
        BiddingController controller = (BiddingController) loader.getController();
        controller.construct(myAuctionCentral, myBidder, this.myCurrentAuction, this.myCurrentItem); 
        bidingScene.show();
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
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().
				getResource("/Graphics/Bidder/BidderMainMenu.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        BidderController controller = (BidderController) loader.getController();
        controller.construct(myAuctionCentral, myBidder);
        back.setScene(scene);
        back.show();
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.cannotBid.setText("");
	}

}
