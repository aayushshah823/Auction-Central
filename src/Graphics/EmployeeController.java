package Graphics;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
import model.Auction;
import model.AuctionCentral;
import model.AuctionCentralEmployee;

public class EmployeeController implements Initializable{

	private AuctionCentral myAuctionCentral;
	
	private AuctionCentralEmployee myEmployee;
	
	private ArrayList<Auction> myAuctionsBetweenDates;
	
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/ChangeMaxAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage changeMaxAuctionsWindow = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        //currMaxAuctions.setText(Integer.toString(myAuctionCentral.getMaxUpcomingAuctions()));
        changeMaxAuctionsWindow.setScene(scene);
        changeMaxAuctionsWindow.show();
	}
	
	public void inputDateRange(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/InputDateRange.fxml"));
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
        controller.construct(myAuctionCentral);
        login.show();
	}
	
	public void submit(ActionEvent theEvent) throws IOException {
		String theNewMaxString = maxAuctions.getText();
		int theNewMax = Integer.parseInt(theNewMaxString);
		System.out.println(theNewMaxString);
		System.out.println(theNewMax);
//		System.out.println(myAuctionCentral.getNumCurrentAuctions());
//		HashMap<Integer, String> errorMap = myAuctionCentral.updateAuctionLimit(theNewMax);
//		if (errorMap.isEmpty()) {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/EmployeeMainMenu.fxml"));
	        AnchorPane anchorPane = loader.load();
	        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
	        Scene scene = new Scene(anchorPane);
	        back.setScene(scene);
//	        myMainLabel.setText("The maximum number of auctions at a time has been changed! What would you like to do next?");
	        back.show();
//		} else {
//			//figure out how to display errors.
//		}
	}
	
	public void submitDates(ActionEvent theEvent) throws IOException {
		String start = myStartDate.getText();
//		String end = myEndDate.getText();
//		LocalDate startDate= LocalDate.parse(start, DateTimeFormatter.BASIC_ISO_DATE);
//		LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.BASIC_ISO_DATE);
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/AuctionsBetweenDates.fxml"));
//        AnchorPane anchorPane = loader.load();
//        Stage auctionsBetweenDates = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
//        Scene scene = new Scene(anchorPane);
//        AuctionsBetweenDates controller = (AuctionsBetweenDates) loader.getController();
//        controller.construct(myAuctionCentral, myEmployee, startDate, endDate);
//        auctionsBetweenDates.setScene(scene);
//        auctionsBetweenDates.show();		
	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/EmployeeMainMenu.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        back.show();
	}
	
	public void construct(AuctionCentral ac, AuctionCentralEmployee employee) {
		this.myAuctionCentral = ac;
		this.myEmployee = employee;
		myMainLabel.setText("Welcome " + employee.getName() + "! You are logged in as an Employee of Auction Central. What would you like to do?");
	}

}
