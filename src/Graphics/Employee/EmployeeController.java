package Graphics.Employee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;
import Graphics.LoginController;
import model.AuctionCentral;
import model.AuctionCentralEmployee;

public class EmployeeController implements Initializable{

	private AuctionCentral myAuctionCentral;
	
	private AuctionCentralEmployee myEmployee;
	
	@FXML
	TextField myStartDate;
	
	@FXML
	TextField myEndDate;
	
	@FXML 
	Label currMaxAuctionsLabel;
	
	@FXML
	TextField maxAuctions;
	
	@FXML
	Label myMainLabel;
	
	@FXML
	Label myChangedMaxAuctionsSuccessLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void changeMaxAuctions(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/ChangeMaxAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage changeMaxAuctionsWindow = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        changeMaxAuctionsWindow.setScene(scene);
        ChangeMaxAuctionsController controller = (ChangeMaxAuctionsController) loader.getController();
        controller.construct(myAuctionCentral, (AuctionCentralEmployee) myEmployee);
        changeMaxAuctionsWindow.show();
	}
	
	public void inputDateRange(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/InputDateRange.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage inputDateRange = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        InputDateRangeController controller = (InputDateRangeController) loader.getController();
        controller.construct(myAuctionCentral, (AuctionCentralEmployee) myEmployee);
        inputDateRange.setScene(scene);
	}
	
	public void viewAllAuctions(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/EmployeeViewAllAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage allAuctions = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        EmployeeViewAllAuctionsController controller = (EmployeeViewAllAuctionsController) loader.getController();
        controller.construct(myAuctionCentral, (AuctionCentralEmployee) myEmployee, "");
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
        controller.construct(myAuctionCentral);
        login.show();
	}
	
	public void construct(AuctionCentral ac, AuctionCentralEmployee employee, int theCode) {
		this.myAuctionCentral = ac;
		this.myEmployee = employee;
		if (theCode == 0) {
			myMainLabel.setText("Welcome " + employee.getName() + "! You are logged in as an Employee of Auction Central. What would you like to do?");
		} else if (theCode == 1) {
			myMainLabel.setText("The maximum number of auctions at a time has been changed! What would you like to do next?");
		}
	}

}
