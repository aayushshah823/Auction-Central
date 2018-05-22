package Graphics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AuctionCentral;
import model.AuctionCentralEmployee;
import model.Bidder;
import model.NonProfit;
import model.User;
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
	TextField username;
	
	private AuctionCentral myAuctionCentral;
	
	public void login(ActionEvent event) throws IOException {
		User user = myAuctionCentral.login(username.getText());
		if (username.getText().equals("")) {
			// if empty - needs work
		} else if (user == null) {
			// if nonexistent - needs work
		} else if (user.getUserType().equals("bidder")) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Bidder.fxml"));
	        AnchorPane anchorPane = loader.load();
	        Stage bidderWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
	        Scene scene = new Scene(anchorPane);
	        bidderWindow.setScene(scene);
	        BidderController controller = (BidderController) loader.getController();
	        controller.construct(myAuctionCentral, (Bidder) user);
	        controller.setName("Welcome " + myAuctionCentral.getAuctionsSortedByDate().get(4).getAuctionName());
	        bidderWindow.show();
		} else if (user.getUserType().equals("nonprofit")) {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/NonProfit.fxml"));
	        AnchorPane anchorPane = loader.load();
	        Stage nonProfitWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
	        Scene scene = new Scene(anchorPane);
	        nonProfitWindow.setScene(scene);
	        NonProfitController controller = (NonProfitController) loader.getController();
	        controller.construct(myAuctionCentral, (NonProfit) user);
	        nonProfitWindow.show();
		} else {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/EmployeeMainMenu.fxml"));
	        AnchorPane anchorPane = loader.load();
	        Stage employeeWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
	        Scene scene = new Scene(anchorPane);
	        employeeWindow.setScene(scene);
	        EmployeeController controller = (EmployeeController) loader.getController();
	        controller.construct(myAuctionCentral, (AuctionCentralEmployee) user);
	        controller.setName("Welcome " + myAuctionCentral.getAuctionsSortedByDate().get(4).getAuctionName());
	        employeeWindow.show();
		}
        
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	public void construct(AuctionCentral ac) {
		this.myAuctionCentral = ac;
	}
	

}
