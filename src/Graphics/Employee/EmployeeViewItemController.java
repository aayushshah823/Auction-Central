package Graphics.Employee;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Graphics.LoginController;
import model.Auction;
import model.AuctionCentral;
import model.AuctionCentralEmployee;
import model.Item;

public class EmployeeViewItemController implements Initializable {

	private AuctionCentral myAuctionCentral;
	private AuctionCentralEmployee myEmployee;
	private Item myItem;
	private LocalDate myStartDate;
	private LocalDate myEndDate;
	
	@FXML Label itemDetails;
	
	public void construct(AuctionCentral theAuctionCentral, AuctionCentralEmployee theEmployee, Auction theAuction, Item theItem,
							LocalDate theStartTime, LocalDate theEndTime) {
		myAuctionCentral = theAuctionCentral;
		myEmployee = theEmployee;
		myItem = theItem;
		myStartDate = theStartTime;
		myEndDate = theEndTime;
		String details = this.myItem.getItemName() + "\n" 
				+ "Item Description: " +myItem.getItemDesciption()
				+ "\nStarting Bid: $" + this.myItem.getStartingBid();
		this.itemDetails.setText(details);
	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/AuctionsBetweenTwoDates.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        AuctionsBetweenDates controller = (AuctionsBetweenDates) loader.getController();
        controller.construct(myAuctionCentral, (AuctionCentralEmployee) myEmployee, myStartDate, myEndDate, "");
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
