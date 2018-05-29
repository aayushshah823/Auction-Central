package Graphics.Nonprofit;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.ResourceBundle;

import Graphics.LoginController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import model.AuctionCentral;
import model.NonProfit;

/**
 * 
 * @author Benjamin Yuen
 *
 */
public class NonprofitAuctionRequest implements Initializable{

	@FXML
	private TextField durationField, auctionNameTxtField,  
						hourField, minField, endHourField, endMinField;
	@FXML
	private Label startDateLabel, startTimeLabel,  durationLabel, aucNameLabel,
					startDateErrorLabel, startTimeErrorLabel, durationErrorLabel, maxAuctionsErrorLabel, 
					nameErrorLabel;
	@FXML
	private ComboBox startTimeMinCombo, startTimeHrCombo,endTimeHrCombo, endTimeMinCombo;
	@FXML
	private DatePicker startDatePicker;
	@FXML
	private Button submitButton;
	
	private AuctionCentral myAuctionCentral;
	private NonProfit myNonProfit;
	private int myDuration;
	private LocalDate myStartDate;
	private LocalTime myStartTime;
	
	private ObservableList<String> minutes = FXCollections.observableArrayList();
	private ObservableList<String> hour = FXCollections.observableArrayList();
	
	public void construct(AuctionCentral ac, NonProfit nonProfit) {
		this.myAuctionCentral = ac;
		this.myNonProfit = nonProfit;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startDateErrorLabel.setVisible(false);
		startTimeErrorLabel.setVisible(false);
		durationErrorLabel.setVisible(false);
		maxAuctionsErrorLabel.setVisible(false);
		nameErrorLabel.setVisible(false);
		
		startDateErrorLabel.setTextFill(Color.RED);
		startTimeErrorLabel.setTextFill(Color.RED);
		durationErrorLabel.setTextFill(Color.RED);

		makeMinutes();
		makeHours();		
	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Nonprofit/NonProfitWelcomeScreen.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        NonProfitController controller = (NonProfitController) loader.getController();
        controller.construct(myAuctionCentral, myNonProfit);
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
	
	private void makeMinutes() {
		minutes.add("00");
		minutes.add("01");
		minutes.add("02");
		minutes.add("03");
		minutes.add("04");
		minutes.add("05");
		minutes.add("06");
		minutes.add("07");
		minutes.add("08");
		minutes.add("09");
		for (int i = 10; i <= 59; i++) {
			String num = "" + i;
			minutes.add(num);
		}
		startTimeMinCombo.setValue("00");
		startTimeMinCombo.setItems(minutes);
	}
	
	private void makeHours() {
		for (int i = 1; i <= 23; i++) {
			String num = "" + i;
			hour.add(num);
		}
		startTimeHrCombo.setValue("12");
		startTimeHrCombo.setItems(hour);
	}
	
	@FXML
	public void submit(ActionEvent event) throws IOException {	
		makeAuctionName();
		makeStartDate();
		makeStartTime();
		makeDuration();
		HashMap<Integer, String> aucReqMap = new HashMap<>();
		try {
			aucReqMap = myAuctionCentral.auctionRequest(myNonProfit, myStartDate, 
					myStartTime, myDuration, auctionNameTxtField.getText());
			//Error checking for auction
			if (aucReqMap.isEmpty()) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Nonprofit/NonProfitComfirmAucReq.fxml"));
		        AnchorPane anchorPane = loader.load();
		        Stage back = (Stage)((Node)event.getSource()).getScene().getWindow();
		        Scene scene = new Scene(anchorPane);
		        back.setScene(scene);
		        NonProfitComfirmAucReq controller = (NonProfitComfirmAucReq) loader.getController();
		        controller.construct(myAuctionCentral, myNonProfit, auctionNameTxtField.getText(), 
		        						myStartDate, myStartTime, myDuration);
		        back.show();
			} else {
				// 1, 2, 3, 5: start date
				// start time: 0
				String errorText = "Error! ";
				if (aucReqMap.containsKey(0)) {
					startTimeErrorLabel.setTextFill(Color.RED);
					errorText += aucReqMap.get(0) + " ";
				} 
				if (aucReqMap.containsKey(1)) {
					startDateErrorLabel.setTextFill(Color.RED);
					errorText += aucReqMap.get(1) + " ";
				} 
				if (aucReqMap.containsKey(2)) {
					startDateErrorLabel.setTextFill(Color.RED);
					errorText += aucReqMap.get(2) + " ";
				} 
				if (aucReqMap.containsKey(3)) {
					startDateErrorLabel.setTextFill(Color.RED);
					errorText += aucReqMap.get(3) + " ";
				} 
				if (aucReqMap.containsKey(4)) {					
					errorText += aucReqMap.get(4) + " ";
				} 
				if (aucReqMap.containsKey(5)) {
					startDateErrorLabel.setTextFill(Color.RED);
					errorText += aucReqMap.get(5) + " ";
				}	
				//WRAP TEXT
				maxAuctionsErrorLabel.setWrapText(true);
				maxAuctionsErrorLabel.setTextAlignment(TextAlignment.JUSTIFY);
				maxAuctionsErrorLabel.setVisible(true);
				maxAuctionsErrorLabel.setText(errorText);
			}	
		} catch (NullPointerException e) {
			
		}
	}
	
	private void makeAuctionName() {
		if (auctionNameTxtField.getText().equals("")) {
			nameErrorLabel.setVisible(true);
			nameErrorLabel.setText("Please enter an auction name");
			aucNameLabel.setTextFill(Color.RED);
		} else {
			nameErrorLabel.setVisible(false);
			aucNameLabel.setTextFill(Color.BLACK);
		}
	}
	
	private void makeStartDate() {
		if (startDatePicker.getValue() == null) {
			startDateErrorLabel.setVisible(true);
			startDateErrorLabel.setText("Please enter a date");
			startDateLabel.setTextFill(Color.RED);
		} else {
			startDateErrorLabel.setVisible(false);
			startDateLabel.setTextFill(Color.BLACK);
		}
		myStartDate = startDatePicker.getValue();
	}
	
	private void makeStartTime() {
		int startHr = Integer.parseInt((String) startTimeHrCombo.getValue());
		int startMin = Integer.parseInt((String) startTimeMinCombo.getValue()); 		
		myStartTime = LocalTime.of(startHr, startMin);
	}
	
	private void makeDuration() {
		myDuration=0;
		try {				
			durationErrorLabel.setVisible(false);
			durationLabel.setTextFill(Color.BLACK);
			myDuration = Integer.parseInt(durationField.getText());
		} catch (NumberFormatException e) {
			durationLabel.setTextFill(Color.RED);
			durationErrorLabel.setVisible(true);
			durationErrorLabel.setText("Please enter a number");
		}
	}


}
