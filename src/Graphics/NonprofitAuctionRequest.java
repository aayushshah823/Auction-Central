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
	private TextField durationField, auctionNameTxtField, startDateField, 
						hourField, minField, endHourField, endMinField;
	@FXML
	private Label startDateLabel, startDateLabel2, startTimeLabel, startTimeLabel2, durationLabel,
					startDateErrorLabel, startTimeErrorLabel, durationErrorLabel, maxAuctionsErrorLabel;
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
		startDateErrorLabel.setTextFill(Color.RED);
		startTimeErrorLabel.setTextFill(Color.RED);
		durationErrorLabel.setTextFill(Color.RED);
		
		startDatePicker = new DatePicker(LocalDate.now());
		startDatePicker.setValue(LocalDate.MIN);		
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
		for (int i = 10; i <= 12; i++) {
			String num = "" + i;
			hour.add(num);
		}
		startTimeHrCombo.setValue("12");
		startTimeHrCombo.setItems(hour);
	}
	
	@FXML
	public void submit(ActionEvent event) throws IOException {				
		int startHr=0, startMin=0;
//		LocalDate startDate = LocalDate.parse(startDatePicker.getEditor().getText(), 
//												DateTimeFormatter.BASIC_ISO_DATE);
		LocalDate startDate = null;
		try {
			String date = startDateField.getText();
			startDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
			System.out.println(startDate.toString());
		} catch( DateTimeParseException e) {
			startDateLabel.setTextFill(Color.RED);
			startDateErrorLabel.setVisible(true);
			startDateErrorLabel.setText("Please use format 'year-month-day' or '2011-05-03'");
		}
		
		startTimeMinCombo.getUserData();
		int duration=0;
		try {
			durationLabel.setTextFill(Color.BLACK);
			durationErrorLabel.setVisible(false);
			duration = Integer.parseInt(durationField.getText());
		} catch (NumberFormatException e) {
			durationLabel.setTextFill(Color.RED);
			durationErrorLabel.setVisible(true);
			durationErrorLabel.setText("Please enter a number");
		}
		
		startHr = Integer.parseInt((String) startTimeHrCombo.getValue());
		startMin = Integer.parseInt((String) startTimeMinCombo.getValue()); 
		
		LocalTime startTime = LocalTime.of(startHr, startMin);
		HashMap<Integer, String> aucReqMap = null;
		try {
			aucReqMap = myAuctionCentral.auctionRequest(myNonProfit, startDate, 
					startTime, duration, auctionNameTxtField.getText());
			//Error checking for auction
			if (aucReqMap.isEmpty()) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/ViewAllAuctionSubmittd.fxml"));
		        AnchorPane anchorPane = loader.load();
		        Stage back = (Stage)((Node)event.getSource()).getScene().getWindow();
		        Scene scene = new Scene(anchorPane);
		        back.setScene(scene);
		        back.show();
			} else {
				// 1, 2, 3, 5: start date
				// start time: 0
				String startDateErrorTxt = "Error! ";
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
				maxAuctionsErrorLabel.setVisible(true);
				maxAuctionsErrorLabel.setText(startDateErrorTxt);
			}	
		} catch (NullPointerException e) {
			
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
