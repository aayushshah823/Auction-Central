package Graphics;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.ResourceBundle;

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
	private Label startDateLabel, startDateLabel2, startTimeLabel, startTimeLabel2, durationLabel,
					startDateErrorLabel, startTimeErrorLabel, durationErrorLabel, maxAuctionsErrorLabel, 
					nameErrorLabel,aucNameLabel;
	@FXML
	private ComboBox startTimeMinCombo, startTimeHrCombo,endTimeHrCombo, endTimeMinCombo;
	@FXML
	private DatePicker startDatePicker;
	@FXML
	private Button submitButton;
	
	private AuctionCentral myAuctionCentral;
	private NonProfit myNonProfit;
	
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

//		submitButton.setDisable(true);
//		if (!durationField.getText().equals("") && !auctionNameTxtField.getText().equals("")) {
//			submitButton.setDisable(false);
//		}
		makeMinutes();
		makeHours();		
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
		hour.add("00");
		hour.add("01");
		hour.add("02");
		hour.add("03");
		hour.add("04");
		hour.add("05");
		hour.add("06");
		hour.add("07");
		hour.add("08");
		hour.add("09");
		for (int i = 10; i <= 23; i++) {
			String num = "" + i;
			hour.add(num);
		}
		startTimeHrCombo.setValue("12");
		startTimeHrCombo.setItems(hour);
	}
	
	@FXML
	public void submit(ActionEvent event) throws IOException {	
		if (auctionNameTxtField.getText().equals("")) {
			nameErrorLabel.setVisible(true);
			nameErrorLabel.setText("Please enter an auction name");
			aucNameLabel.setTextFill(Color.RED);
		} else {
			nameErrorLabel.setVisible(false);
			aucNameLabel.setTextFill(Color.BLACK);
		}

		//start date
		LocalDate startDate = startDatePicker.getValue();
		System.out.println(startDate.toString());		
		//duration
		int duration=0;
		try {				
			durationErrorLabel.setVisible(false);
			durationLabel.setTextFill(Color.BLACK);
			duration = Integer.parseInt(durationField.getText());
		} catch (NumberFormatException e) {
			durationLabel.setTextFill(Color.RED);
			durationErrorLabel.setVisible(true);
			durationErrorLabel.setText("Please enter a number");
		} 
		
		//Start time
		int startHr = Integer.parseInt((String) startTimeHrCombo.getValue());
		int startMin = Integer.parseInt((String) startTimeMinCombo.getValue()); 		
		LocalTime startTime = LocalTime.of(startHr, startMin);		
		
//		HashMap<Integer, String> aucReqMap = null;
		HashMap<Integer, String> aucReqMap = new HashMap<>();
		aucReqMap = myAuctionCentral.auctionRequest(
				myNonProfit, 
				startDate, 
				startTime, 
				duration, 
				auctionNameTxtField.getText());
		try {
//			aucReqMap = myAuctionCentral.auctionRequest(myNonProfit, startDate, 
//					startTime, duration, auctionNameTxtField.getText());
			//Error checking for auction
			if (aucReqMap.isEmpty()) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/NonProfitComfirmAucReq.fxml"));
		        AnchorPane anchorPane = loader.load();
		        Stage back = (Stage)((Node)event.getSource()).getScene().getWindow();
		        Scene scene = new Scene(anchorPane);
		        back.setScene(scene);
		        NonProfitComfirmAucReq controller = (NonProfitComfirmAucReq) loader.getController();
		        controller.construct(myAuctionCentral, myNonProfit);
		        back.show();
			} else {
				// 1, 2, 3, 5: start date
				// start time: 0
				String startDateErrorTxt = "Error! ";
				if (aucReqMap.containsKey(0)) {
					startDateErrorTxt += aucReqMap.get(0);
				} 
				if (aucReqMap.containsKey(1)) {
					// error check 
					startDateErrorTxt += aucReqMap.get(1);
				} 
				if (aucReqMap.containsKey(2)) {
					startDateErrorTxt += aucReqMap.get(2);
				} 
				if (aucReqMap.containsKey(3)) {
					startDateErrorTxt += aucReqMap.get(3);
				} 
				if (aucReqMap.containsKey(4)) {
					startDateErrorTxt += aucReqMap.get(4);
				} 
				if (aucReqMap.containsKey(5)) {
					startDateErrorTxt += aucReqMap.get(5);
				}	
				//WRAP TEXT
				maxAuctionsErrorLabel.setWrapText(true);
				maxAuctionsErrorLabel.setTextAlignment(TextAlignment.JUSTIFY);
				maxAuctionsErrorLabel.setVisible(true);
				maxAuctionsErrorLabel.setText(startDateErrorTxt);
			}	
		} catch (NullPointerException e) {
			System.out.println("im null");
		}
	}
		
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/NonProfitWelcomeScreen.fxml"));
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

}
