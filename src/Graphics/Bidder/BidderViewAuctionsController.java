package Graphics.Bidder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

import Graphics.LoginController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;
import model.Auction;
import model.AuctionCentral;
import model.Bidder;
import model.Item;
import model.NonProfit;

public class BidderViewAuctionsController implements Initializable{
	
	private AuctionCentral myAuctionCentral;
	private Bidder myBidder;

	@FXML 
	private ListView<Auction> listOfAuctions; 
	@FXML
	private ListView<String> listOfItems;

	
	public void construct(AuctionCentral ac, Bidder bidder) {
		this.myAuctionCentral = ac;
		this.myBidder = bidder;
		Map<Auction, Map<Item, Double>> bids = myBidder.getAllItemsInAllAuctions();
		listOfAuctions.setCellFactory(new Callback<ListView<Auction>, ListCell<Auction>>() {
			@Override
			public ListCell<Auction> call(ListView<Auction> param) {
				ListCell<Auction> cell = new ListCell<Auction>() {
					@Override
					protected void updateItem(Auction auction, boolean empty) {
						super.updateItem(auction, empty);
						if(auction != null) {
							String toDisplay = auction.getAuctionName() + " | " + getNonProfitName(auction) + " | " + auction.getStartDate() + " " 
									+ auction.getStartTime() + "-" + auction.getEndTime() + " | " + auction.getItems().size();
							if (auction.getItems().size() == 1) {
								toDisplay += " item";
							} else {
								toDisplay += " items";
							}
							if (bids.containsKey(auction)) {
								toDisplay += " - " + bids.get(auction).size();
								if (bids.get(auction).size() > 1) {
									toDisplay += " bids";
								} else {
									toDisplay += " bid";
								}
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
							displayItems(cell.getItem());
						}
					}
				});
				return cell;
			}
		});
		for (Auction auction : myAuctionCentral.getAuctionsSortedByDate()) {
			this.listOfAuctions.getItems().add(auction);
		}
	}
	
	private void displayItems(Auction auction) {
		DecimalFormat df = new DecimalFormat("0.00"); 
		this.listOfItems.getItems().clear();
		Map<Auction, Map<Item, Double>> bids = myBidder.getAllItemsInAllAuctions();
		Map<Item, Double> auctionBids = bids.get(auction);
		for (Item item : auction.getItems()) {
			String toDisplay = "Item: " + item.getItemName() + "\nDescription: " + item.getItemDesciption() + "\nMinimum bid: $" 
		+ df.format(item.getStartingBid());
			if (auctionBids != null && auctionBids.containsKey(item)) {
				toDisplay += " | My bid: $" + df.format(auctionBids.get(item));
			}
			this.listOfItems.getItems().add(toDisplay);
		}
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Bidder/BidderMainMenu.fxml"));
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

}