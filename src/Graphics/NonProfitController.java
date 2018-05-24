/**
 * 
 */
package Graphics;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import model.AuctionCentral;
import model.NonProfit;

/**
 * @author aayush shah
 * @author Benjamin Yuen
 *
 */
public class NonProfitController implements Initializable {

	@FXML
	Button button2;
	
	@FXML
	private TextField auctionNameTxtField, monthField, dayField, yearField, 
						hourField, minField, endHourField, endMinField;
	@FXML
	private Label startDateLabel, startDateLabel2, startTimeLabel, startTimeLabel2, endTimeLabel, endTimeLabel2;
	@FXML
	private Button submitButton;
	
	@FXML
	private Label userName;
	
	
	private AuctionCentral myAuctionCentral;
	private NonProfit myNonProfit;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void construct(AuctionCentral ac, NonProfit nonProfit) {
		this.myAuctionCentral = ac;
		this.myNonProfit = nonProfit;
		userName.setText("Welcome " + myNonProfit.getUsername() + ". What would you like to do today?");
	}
	
	@FXML
	public void viewAllAuction(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/ViewAllAuctionSubmittd.fxml"));
		AnchorPane ap = loader.load();
        Stage auctionRequest = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
		Scene scene = new Scene(ap);
		auctionRequest.setScene(scene);
        auctionRequest.show();
		
	}
	
	@FXML
	public void viewAuctionRequestScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/nonprofitAuctionRequest.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage auctionRequest = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        auctionRequest.setScene(scene);
        auctionRequest.show();
	}
	
	@FXML
	public void submit(ActionEvent event) throws IOException {
		int year = Integer.parseInt(yearField.getText());
		int month = Integer.parseInt(monthField.getText());
		int day = Integer.parseInt(dayField.getText());
		LocalDate startDate = LocalDate.of(year, month, day);
		
		int startHr = Integer.parseInt(hourField.getText());
		int startMin = Integer.parseInt(minField.getText());
		LocalTime startTime = LocalTime.of(startHr, startMin);
		
		int endHr = Integer.parseInt(endHourField.getText());
		int endMin = Integer.parseInt(endMinField.getText());
		LocalTime endTime = LocalTime.of(endHr, endMin);
		
		int duration = (int) startTime.until(endTime, ChronoUnit.HOURS);
		
		HashMap<Integer, String> aucReqMap = myAuctionCentral.auctionRequest(myNonProfit, startDate, 
											startTime, duration, auctionNameTxtField.getText());
		
		//Error checking for auction
		// Error check: Months only 1-12, Hour: 1-12, Min: 0-59
		if (aucReqMap.isEmpty()) {
			
		} else {
			// 2, 3, 5: start date
			// 
		}
		
		
	
	}


}
