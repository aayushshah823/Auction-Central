package Graphics.Bidder;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.runners.ParentRunner;

import Graphics.LoginController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

	@FXML 
	private ListView<Auction> listOfAuctions; 

	
	public void construct(AuctionCentral ac) {
		this.myAuctionCentral = ac;
		listOfAuctions.setCellFactory(new Callback<ListView<Auction>, ListCell<Auction>>() {
		    @Override
		    public ListCell<Auction> call(ListView<Auction> param) {
		         ListCell<Auction> cell = new ListCell<Auction>() {
		             @Override
		            protected void updateItem(Auction auction, boolean empty) {
		                super.updateItem(auction, empty);
		                if(auction != null) {
		                	String toDisplay = auction.getAuctionName() + " | " + getNonProfitName(auction) + " | " + auction.getStartDate() + " " 
				                	+ auction.getStartTime() + "-" + auction.getEndTime() + " | ";
		                	if (auction.getStartDate().isBefore(LocalDate.now()) || auction.getStartDate().isEqual(LocalDate.now())) {
		                		toDisplay += " - ENDED";
		                	}
		                	setText(toDisplay);
		                }
		            }
		         };
		         cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							if(event.getButton().equals(MouseButton.PRIMARY)){
					            if(event.getClickCount() == 2){
					            	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Bidder/BidderViewItems.fxml"));
					                AnchorPane anchorPane = null;
									try {
										anchorPane = loader.load();
									} catch (IOException e) {
										e.printStackTrace();
									}
					                Stage bidderAuction = (Stage)((Node)event.getSource()).getScene().getWindow();
					                Scene scene = new Scene(anchorPane);
					                bidderAuction.setScene(scene);
					                BidderViewItemsController controller = (BidderViewItemsController) loader.getController();
					                controller.construct(myAuctionCentral, cell.getItem()); 
					                bidderAuction.show();
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
	}
	
	public String getNonProfitName(Auction theAuction) {
		for (NonProfit nonProfit : myAuctionCentral.getAllAuctions().keySet()) {
			for (Auction auction : myAuctionCentral.getAllAuctions().get(nonProfit)) {
				if (auction.equals(theAuction)) {
					return nonProfit.getOrg();
				}
			}
		}
		return null;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}