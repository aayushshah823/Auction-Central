package Graphics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AuctionCentral;
/**
 * 
 * @author Raisa
 *
 */
public class LoginController implements Initializable{
	
	/**
	 * Precondition: User attempts to login
	 * Postcondition: The correct scene is shown 
	 * depending on the user type and if
	 * login was successful 
	 * @throws IOException 
	 */
	
	@FXML
	Button button;
	
	private AuctionCentral ac;
	
	public void login(ActionEvent event) throws IOException {
//		Parent bidderParent = FXMLLoader.load(getClass()
//				.getResource("/Graphics/Bidder.fxml"));
//		Scene bidderScene = new Scene(bidderParent);
//		
//		//This line gets the stage information
//		//We need to have a way to connect this with our model to get 
//		//the user information.
//		Stage bidderWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
//		bidderWindow.setScene(bidderScene);
//		bidderWindow.show();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Bidder.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage bidderWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        bidderWindow.setScene(scene);
        
        BidderController controller = (BidderController) loader.getController();
        controller.setAuctionCentral(ac);
        controller.setName("Welcome " + ac.getAuctionsSortedByDate().get(4).getAuctionName());
        
        bidderWindow.show();
        
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	public void setAuctionCentral(AuctionCentral ac) {
		this.ac = ac;
	}

	public void setButtonText() {
		button.setText(ac.getAuctionsSortedByDate().get(1).getAuctionName());
	}
	

}
