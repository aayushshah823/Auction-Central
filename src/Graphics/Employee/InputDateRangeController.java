package Graphics.Employee;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Graphics.LoginController;
import model.AuctionCentral;
import model.AuctionCentralEmployee;

public class InputDateRangeController implements Initializable {

	private AuctionCentral myAuctionCentral;
	
	private AuctionCentralEmployee myEmployee;
	
	@FXML
	DatePicker myStartDate;
	
	@FXML
	DatePicker myEndDate;
	
	@FXML
	Label myLabel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void construct(AuctionCentral theAuctionCentral, AuctionCentralEmployee theEmployee) {
		this.myAuctionCentral = theAuctionCentral;
		this.myEmployee = theEmployee;
		myLabel.setText("");
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
	
	@FXML
	public void submit(ActionEvent theEvent) throws IOException {
		if (myStartDate.getValue() == null || myEndDate.getValue() == null) {
			myLabel.setText("Please select a start date and an end date.");
			myLabel.setTextFill(Color.web("#ff0000"));
		} else {
			LocalDate startDate = myStartDate.getValue();
			LocalDate endDate = myEndDate.getValue();
			if (endDate.isAfter(startDate) || endDate.isEqual(startDate)) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/AuctionsBetweenTwoDates.fxml"));
		        AnchorPane anchorPane = loader.load();
		        Stage auctionsBetweenDates = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
		        Scene scene = new Scene(anchorPane);
		        auctionsBetweenDates.setScene(scene);
		        AuctionsBetweenDates controller = (AuctionsBetweenDates) loader.getController();
		        controller.construct(myAuctionCentral, myEmployee, startDate, endDate, "");
		        auctionsBetweenDates.show();
			} else {
				myLabel.setText("The end date is prior to the start date. Please change the end date to after the start date.");
				myLabel.setTextFill(Color.web("#ff0000"));
			}
		}
	}
}
