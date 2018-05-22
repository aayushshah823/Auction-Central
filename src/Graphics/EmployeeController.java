package Graphics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AuctionCentral;

public class EmployeeController implements Initializable{

	private AuctionCentral myAuctionCentral;
	
	Label myName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void changeMaxAuctions(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/ChangeMaxAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage changeMaxAuctionsWindow = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        changeMaxAuctionsWindow.setScene(scene);
        ChangeMaxLoginsController controller = (ChangeMaxLoginsController) loader.getController();
        controller.setAuctionCentral(myAuctionCentral);
        changeMaxAuctionsWindow.show();
	}
	
	public void inputDateRange(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/inputDateRange.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage inputDateRange = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        inputDateRange.setScene(scene);
	}
	
	public void viewAllAuctions(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/employeeAllAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage allAuctions = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        allAuctions.setScene(scene);
	}
	
	public void exit(ActionEvent theEvent) throws IOException {
		Platform.exit();
	}
	
	public void logout(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Login.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage login = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        login.setScene(scene);
        LoginController controller = (LoginController) loader.getController();
        controller.setAuctionCentral(myAuctionCentral);
        login.show();
	}
	
	
	public void setAuctionCentral(AuctionCentral ac) {
		myAuctionCentral = ac;
	}
	
	public void setName(String theText) {
		myName.setText(theText);
	}

}
