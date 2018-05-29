package Graphics.Nonprofit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import Graphics.LoginController;
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
import model.AuctionCentral;
import model.NonProfit;
/**
 * 
 * @author Benjamin Yuen
 *
 */
public class NonProfitComfirmAucReq implements Initializable {	
	
	@FXML
	private Label infoLabel;
	
	private AuctionCentral myAuctionCentral;
	private NonProfit myNonProfit;
	private String myName;
	private LocalDate myStartDate;
	private LocalTime myStartTime;
	private int myDuration;
	
	public void construct(AuctionCentral ac, NonProfit nonProfit, String name, 
								LocalDate date, LocalTime time, int duration) {
		this.myAuctionCentral = ac;
		this.myNonProfit = nonProfit;
		this.myName = name;
		this.myStartDate = date;
		this.myStartTime = time;
		this.myDuration = duration;
		
		infoLabel.setWrapText(true);
		infoLabel.setText("Auction Name: " + myName + "\n" + 
							"Start Date: " + myStartDate + "\n" + 
							"Start Time: " + myStartTime+ "\n" +
							"Duration: " + myDuration + "\n");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	@FXML
	public void home(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Nonprofit/NonProfitWelcomeScreen.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        NonProfitController controller = (NonProfitController) loader.getController();
        controller.construct(myAuctionCentral, myNonProfit);
        back.show();
	} 
	@FXML
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Nonprofit/nonprofitAuctionRequest.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        NonprofitAuctionRequest controller = (NonprofitAuctionRequest) loader.getController();
        controller.construct(myAuctionCentral, myNonProfit);
        back.show();
	}

	public void exit(ActionEvent theEvent) throws IOException {
	try {
		FileOutputStream file = new FileOutputStream("auctionCentralDefault.ser");
		ObjectOutputStream out = new ObjectOutputStream(file);
		out.writeObject(myAuctionCentral);
		out.close();
		file.close();
	} catch (IOException exception) {
		System.out.println("IOException");
	}
		Platform.exit();
	}
	
	@FXML
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
