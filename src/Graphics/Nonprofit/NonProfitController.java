/**
 * 
 */
package Graphics.Nonprofit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.ResourceBundle;

import Graphics.LoginController;
import javafx.application.Platform;
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
import javafx.scene.paint.Color;
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
	private Label name;	
	
	private AuctionCentral myAuctionCentral;
	private NonProfit myNonProfit;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}
	
	public void construct(AuctionCentral ac, NonProfit nonProfit) {
		this.myAuctionCentral = ac;
		this.myNonProfit = nonProfit;
		name.setText("Welcome " + myNonProfit.getName() + "! You are logged in as a member of " + myNonProfit.getOrg() + ". What would you like to do?");
	}
	
	@FXML
	public void viewAllAuction(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Nonprofit/ViewItemList.fxml"));
		AnchorPane ap = loader.load();
        Stage auctionRequest = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
		Scene scene = new Scene(ap);
		auctionRequest.setScene(scene);
		NonprofitViewAllAuctions viewAllAuctionController = (NonprofitViewAllAuctions) loader.getController();
		viewAllAuctionController.construct(myAuctionCentral, myNonProfit);
        auctionRequest.show();		
	}
	
	@FXML
	public void viewAuctionRequestScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Nonprofit/nonprofitAuctionRequest.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage auctionRequest = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        auctionRequest.setScene(scene);
        NonprofitAuctionRequest aucReqController = (NonprofitAuctionRequest) loader.getController();
        aucReqController.construct(myAuctionCentral, myNonProfit);
        auctionRequest.show();
	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Nonprofit/NonProfitWelcomeScreen.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
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
