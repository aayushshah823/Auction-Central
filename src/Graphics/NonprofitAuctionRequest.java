package Graphics;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.AuctionCentral;
import model.NonProfit;

/**
 * 
 * @author Benjamin Yuen
 *
 */
public class NonprofitAuctionRequest implements Initializable{

	@FXML
	private TextField auctionNameTxtField, monthField, dayField, yearField, 
						hourField, minField, endHourField, endMinField;
	@FXML
	private Label startDateLabel, startDateLabel2, startTimeLabel, startTimeLabel2, endTimeLabel, endTimeLabel2, 
					startDateErrorLabel, startTimeErrorLabel, endTimeErrorLabel, maxAuctionsErrorLabel;
	@FXML
	private Button submitButton;
	
	private AuctionCentral myAuctionCentral;
	private NonProfit myNonProfit;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startDateErrorLabel.setVisible(false);
		startTimeErrorLabel.setVisible(false);
		endTimeErrorLabel.setVisible(false);
		maxAuctionsErrorLabel.setVisible(false);
		startDateErrorLabel.setTextFill(Color.RED);
		startTimeErrorLabel.setTextFill(Color.RED);
		endTimeErrorLabel.setTextFill(Color.RED);
	}
	
	public void construct(AuctionCentral ac, NonProfit nonProfit) {
		this.myAuctionCentral = ac;
		this.myNonProfit = nonProfit;
	}
	
	@FXML
	public void submit(ActionEvent event) throws IOException {
		maxAuctionsErrorLabel.setVisible(true);
		String startDateErrorTxt = "Error! ";
		startDateErrorTxt += "stuff" + "\n";
		startDateErrorTxt += "stuff" + "\n";
		startDateErrorTxt += "stuff" + "\n";
		maxAuctionsErrorLabel.setText(startDateErrorTxt);
		
		yearField.textProperty().addListener(new ChangeListener<String>() {
			@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	yearField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		
		
		int year = Integer.parseInt(yearField.getText());
		int month = Integer.parseInt(monthField.getText());
		int day = Integer.parseInt(dayField.getText());
		
		int startHr = Integer.parseInt(hourField.getText());
		int startMin = Integer.parseInt(minField.getText());		
		
		int endHr = Integer.parseInt(endHourField.getText());
		int endMin = Integer.parseInt(endMinField.getText());
		
		// Error check: Months only 1-12, Hour: 1-12, Min: 0-59
		if(!(month >= 1 && month <= 12) || !(day >= 1 && day <= 32)) {
			startDateLabel.setTextFill(Color.RED);
			startDateLabel2.setTextFill(Color.RED);				
			startDateErrorLabel.setVisible(true);
			startDateErrorLabel.setText("Error! Months must be between 1 - 12");
		}
		if (!(startHr >= 1 && startHr <= 12) || !(startMin >= 0 && startMin <= 59) ) {
			startTimeLabel.setTextFill(Color.RED);
			startTimeLabel2.setTextFill(Color.RED);			
			startTimeErrorLabel.setVisible(true);
			startTimeErrorLabel.setText("Error! Hours must be between 1 - 12, "
											+ "and Minutes must be between 00 - 59");
		}
		if (!(endHr >= 1 && endHr <= 12) || !(endMin >= 0 && endMin <= 59) ) {
			endTimeLabel.setTextFill(Color.RED);
			endTimeLabel2.setTextFill(Color.RED);			
			endTimeErrorLabel.setVisible(true);
			endTimeErrorLabel.setText("Error! Hours must be between 1 - 12, "
											+ "and Minutes must be between 00 - 59");
		}
		
		LocalDate startDate = LocalDate.of(year, month, day);
		LocalTime startTime = LocalTime.of(startHr, startMin);
		LocalTime endTime = LocalTime.of(endHr, endMin);
		int duration = (int) startTime.until(endTime, ChronoUnit.HOURS);		
		HashMap<Integer, String> aucReqMap = myAuctionCentral.auctionRequest(myNonProfit, startDate, 
											startTime, duration, auctionNameTxtField.getText());
		
		//Error checking for auction
		if (aucReqMap.isEmpty()) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/NonProfitWelcomeScreen.fxml"));
	        AnchorPane anchorPane = loader.load();
	        Stage back = (Stage)((Node)event.getSource()).getScene().getWindow();
	        Scene scene = new Scene(anchorPane);
	        back.setScene(scene);
	        back.show();
		} else {
			// 1, 2, 3, 5: start date
			// start time: 0, 
//			String startDateErrorTxt = "Error! ";
			if (aucReqMap.containsKey(0)) {
				startTimeErrorLabel.setText(aucReqMap.get(0));
			} 
			if (aucReqMap.containsKey(1)) {
				startDateErrorTxt += aucReqMap.get(1) + "\n";
			} 
			if (aucReqMap.containsKey(2)) {
				startDateErrorTxt += aucReqMap.get(2) + "\n";
			} 
			if (aucReqMap.containsKey(3)) {
				startDateErrorTxt += aucReqMap.get(3) + "\n";
			} 
			if (aucReqMap.containsKey(4)) {
				maxAuctionsErrorLabel.setText(aucReqMap.get(4));
			} 
			if (aucReqMap.containsKey(5)) {
				startDateErrorTxt += aucReqMap.get(5) + "\n";
			}			
			if (startDateErrorTxt != "") {
				startDateErrorLabel.setText(startDateErrorTxt);
			}			
		}	

	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/NonProfitWelcomeScreen.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
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

}
