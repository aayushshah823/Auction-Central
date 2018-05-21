package Graphics;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.AuctionCentral;
/**
 * 
 * @author Raisa
 *
 */
public class BidderController implements Initializable {

	private AuctionCentral ac;
	
	@FXML
	Label name;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	public void setAuctionCentral(AuctionCentral ac) {
		this.ac = ac;
	}

	public void setName(String theText) {
		name.setText(theText);
	}

}
