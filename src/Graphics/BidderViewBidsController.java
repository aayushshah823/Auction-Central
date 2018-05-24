package Graphics;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.runners.ParentRunner;

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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;
import model.Auction;
import model.AuctionCentral;
import model.Bidder;
import model.Item;

public class BidderViewBidsController implements Initializable{
	
	private AuctionCentral myAuctionCentral;
	
	private Bidder myBidder;

	//These are for controlling the list of auction 
	@FXML private ListView<Auction> listOfAuctions; 
	
	public void construct(AuctionCentral ac, Bidder bidder) {
		this.myAuctionCentral = ac;
		this.myBidder = bidder;
		Map<Auction, Map<Item, Double>> bids = myBidder.getAllItemsInAllAuctions();
		
		listOfAuctions.setCellFactory(new Callback<ListView<Auction>, ListCell<Auction>>() {
		    @Override
		    public ListCell<Auction> call(ListView<Auction> param) {
		         ListCell<Auction> cell = new ListCell<Auction>() {
		             @Override
		            protected void updateItem(Auction item, boolean empty) {
		                super.updateItem(item, empty);
		                if(item != null) {
		                	int count = bids.get(item).size();
		                	if (count > 1) {
		                		setText(item.getAuctionName() + " - " + count + " bids");
		                	} else {
		                		setText(item.getAuctionName() + " - 1 bid");
		                	}
		                }
		            }
		         };
		         cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							if(event.getButton().equals(MouseButton.PRIMARY)){
					            if(event.getClickCount() == 2){
					            	FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/BidderViewAuctionBids.fxml"));
					                AnchorPane anchorPane = null;
									try {
										anchorPane = loader.load();
									} catch (IOException e) {
										e.printStackTrace();
									}
					                Stage bidderAuction = (Stage)((Node)event.getSource()).getScene().getWindow();
					                Scene scene = new Scene(anchorPane);
					                bidderAuction.setScene(scene);
					                BidderViewAuctionBidsController controller = (BidderViewAuctionBidsController) loader.getController();
					                controller.construct(myBidder, cell.getItem()); 
					                bidderAuction.show();
					            }
					        }
						}
             	});
		        return cell;
		    }
		});
		
		for (Auction auction : bids.keySet()) {
			this.listOfAuctions.getItems().add(auction);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}