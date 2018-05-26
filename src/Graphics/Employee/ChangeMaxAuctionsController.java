package Graphics.Employee;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import Graphics.LoginController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.AuctionCentral;
import model.AuctionCentralEmployee;

public class ChangeMaxAuctionsController implements Initializable{

	private AuctionCentral myAuctionCentral;
	
	private AuctionCentralEmployee myEmployee;
	
	@FXML
	Label myLabel;
	
	@FXML
	Label errorLabel;
	
	@FXML
	TextField myTextField;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	public void construct(AuctionCentral theAuctionCentral, AuctionCentralEmployee theEmployee) {
		myAuctionCentral = theAuctionCentral;
		myEmployee = theEmployee;
		myLabel.setText(Integer.toString(myAuctionCentral.getMaxUpcomingAuctions()));
		errorLabel.setText("");
	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/EmployeeMainMenu.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        EmployeeController controller = (EmployeeController) loader.getController();
        controller.construct(myAuctionCentral, (AuctionCentralEmployee) myEmployee, 0);
        back.show();
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
        controller.construct(myAuctionCentral);
        login.show();
	}
	
	public void submit(ActionEvent theEvent) throws IOException {
		HashMap<Integer, String> errorMap = submitNewMaxAuctions();
		if (errorMap.isEmpty()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/EmployeeMainMenu.fxml"));
	        AnchorPane anchorPane = loader.load();
	        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
	        Scene scene = new Scene(anchorPane);
	        back.setScene(scene);
	        EmployeeController controller = (EmployeeController) loader.getController();
	        controller.construct(myAuctionCentral, (AuctionCentralEmployee) myEmployee, 1);
	        back.show();
		} else {
			if (errorMap.containsKey(1)) {
				errorLabel.setTextFill(Color.web("#ff0000"));
				errorLabel.setText(errorMap.get(1));
			} else if (errorMap.containsKey(0)) {
				errorLabel.setTextFill(Color.web("#ff0000"));
				errorLabel.setText(errorMap.get(0));
			}
			
		}
	}
	
	public HashMap<Integer, String> submitNewMaxAuctions() {
		String theNewMaxString = myTextField.getText();
		int theNewMax = Integer.parseInt(theNewMaxString);
		HashMap<Integer, String> errorMap = myAuctionCentral.updateAuctionLimit(theNewMax);
		return errorMap;
	}
}
