/**
 * 
 */
package Graphics;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.AuctionCentral;
import model.NonProfit;

/**
 * @author aayushshah
 * @author Benjamin Yuen
 *
 */
public class NonProfitController implements Initializable {

	@FXML
	Button button2;
	
	private AuctionCentral myAuctionCentral;
	private NonProfit myNonProfit;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void construct(AuctionCentral ac, NonProfit nonProfit) {
		this.myAuctionCentral = ac;
		this.myNonProfit = nonProfit;
	}


}
