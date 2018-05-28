package Graphics.Nonprofit;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Auction;
import model.AuctionCentral;
import model.NonProfit;
/**
 * 
 * @author Benjamin Yuen
 *
 */
public class NonprofitComfirmItem implements Initializable {	
	
	@FXML
	private Label congratLabel, itemInforLabel;
	
	private AuctionCentral myAuctionCentral;
	private NonProfit myNonProfit;
	private String myName, myDesc;
	private double myStartBid;
	private Auction myAuc;
	
	public void construct(AuctionCentral ac, NonProfit nonProfit, String name, double startBid, String desc, Auction auc) {
		this.myAuctionCentral = ac;
		this.myNonProfit = nonProfit;
		myName = name;
		myDesc = desc;
		myStartBid = startBid;
		myAuc = auc;
		
		congratLabel.setWrapText(true);
		congratLabel.setText("Congratulations! You have added an Item to the "
								+ myAuc.getAuctionName() + " Auction.");
		itemInforLabel.setWrapText(true);
		itemInforLabel.setText("Item Name: " + myName + "\n" + 
							"Starting Bid: " + myStartBid + "\n" + 
							"Item Description: " + myDesc + "\n");
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
//	@FXML
//	public void back(ActionEvent theEvent) throws IOException {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Nonprofit/AddItemToAuctionList.fxml"));
//        AnchorPane anchorPane = loader.load();
//        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
//        Scene scene = new Scene(anchorPane);
//        back.setScene(scene);
//        NonProfitAddItemController controller = (NonProfitAddItemController) loader.getController();
//        controller.construct(myAuctionCentral, myNonProfit);
//        back.show();
//	}
	@FXML
	public void exit(ActionEvent theEvent) throws IOException {
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
