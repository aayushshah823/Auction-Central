package Graphics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

	
	public void construct(AuctionCentral ac, Bidder bidder) {
		this.myAuctionCentral = ac;
		this.myBidder = bidder;
		name.setText("Welcome, " + myBidder.getName());
	}
	
	public void viewAuctionsAvailableToBid(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/BidderSeeAllAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage bidderAuction = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        bidderAuction.setScene(scene);
       //BidderController controller = (BidderController) loader.getController();
        //controller.construct(myAuctionCentral, (Bidder) user);
        bidderAuction.show();
	}
	public void showAuctionsWhereICanBid() {
		
	}
    public void viewItemsInOneAuction(ActionEvent event) throws IOException{
		
	}
    
    public void viewItemsIHaveBidOnInOneAuction(ActionEvent event) throws IOException{
		
   	}
    
    public void viewAllItemsIHaveBidOnAllAuctions(ActionEvent event) throws IOException{
		
   	}    
	
	@Override
	public void initialize(URL location, ResourceBundle resources){
		// TODO Auto-generated method stub
	}

}
