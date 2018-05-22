package Graphics;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.AuctionCentral;
import model.Bidder;
/**
 * 
 * @author Raisa
 *
 */
public class BidderController implements Initializable {

	private AuctionCentral myAuctionCentral;
	private Bidder myBidder;
	
	@FXML
	Label name;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	public void construct(AuctionCentral ac, Bidder bidder) {
		this.myAuctionCentral = ac;
		this.myBidder = bidder;
		name.setText("Welcome, " + myBidder.getName());
	}
	
	public void viewAuctionsAvailableToBid(AuctionCentral ac, Bidder bidder) {
		
	}
	
    public void viewItemsInOneAuction(AuctionCentral ac, Bidder bidder) {
		
	}
    
    public void viewItemsIHaveBidOnInOneAuction(AuctionCentral ac, Bidder bidder) {
		
   	}
    
    public void viewAllItemsIHaveBidOnAllAuctions(AuctionCentral ac, Bidder bidder) {
		
   	}

}
