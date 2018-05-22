package Graphics;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AuctionCentral;

public class ChangeMaxAuctionsController implements Initializable{

	private AuctionCentral myAuctionCentral;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void submit(ActionEvent theEvent, int theNewMax) throws IOException {
		HashMap<Integer, String> errorMap = myAuctionCentral.updateAuctionLimit(theNewMax);
		if (errorMap.isEmpty()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/EmployeeMainMenu.fxml"));
	        AnchorPane anchorPane = loader.load();
	        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
	        Scene scene = new Scene(anchorPane);
	        back.setScene(scene);
	        EmployeeController controller = (EmployeeController) loader.getController();
	        controller.setAuctionCentral(myAuctionCentral);
	        controller.setName("The maximum number of auctions at a time has been changed! What would you like to do next?");
	        back.show();
		} else {
			//figure out how to display errors.
		}
	}

	public void exit(ActionEvent theEvent) throws IOException {
		Platform.exit();
	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/EmployeeMainMenu.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        EmployeeController controller = (EmployeeController) loader.getController();
        controller.setAuctionCentral(myAuctionCentral);
        controller.setName("Welcome ");
        back.show();
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
	
	
	public void setAuctionCentral(AuctionCentral ac) {
		myAuctionCentral = ac;
	}
}
