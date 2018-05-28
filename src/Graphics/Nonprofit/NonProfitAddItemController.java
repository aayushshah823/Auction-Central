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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Auction;
import model.AuctionCentral;
import model.Item;
import model.NonProfit;

/**
 * 
 * @author Benjamin Yuen
 *
 */
public class NonProfitAddItemController implements Initializable {
	
	@FXML
	private TextField myItemNameField;
	@FXML
	private TextArea myDescripArea;
	@FXML
	private TextField myStartBidField;
	@FXML
	private Label myItemNameLabel; 
	@FXML
	private Label myDescripLabel;
	@FXML
	private Label myStartBidLabel;
	@FXML
	private Label myErrorLabel, myErrorLabel2;
	@FXML
	private Button mySubmitButton;

	private AuctionCentral myAuctionCentral;
	private NonProfit myNonProfit;
	private Auction myCurrAuction;
	private Item myItem;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myErrorLabel.setVisible(false);
	}
	
	public void construct(AuctionCentral ac, NonProfit nonProfit, Auction auc) {
		this.myAuctionCentral = ac;
		this.myNonProfit = nonProfit;
		this.myCurrAuction = auc;
	}
	
	@FXML
	public void submit(ActionEvent event) throws IOException {
		int errorCount = 0;		
		errorCount =checkEmpty(myStartBidField, myDescripLabel, errorCount);
		errorCount =checkEmpty(myItemNameField, myItemNameLabel, errorCount);
		errorCount = checkArea(errorCount);
		
		double startBid = -1;			
		try {		
			myStartBidLabel.setTextFill(Color.BLACK);
			myStartBidLabel.setText("What is the starting bid?");
			startBid = Integer.parseInt(myStartBidField.getText());
		} catch (NumberFormatException e) {
			myStartBidLabel.setTextFill(Color.RED);
			myStartBidLabel.setText("What is the starting bid? Please enter a number.");
		}
		
		
		if (checkAllEntriesFilled() && startBid != -1) {	
			myErrorLabel.setVisible(false);

			myItem = new Item(myItemNameField.getText(), startBid, myDescripArea.getText(), 0);
			myCurrAuction.addItem(myItem);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Nonprofit/NonprofitComfirmItem.fxml"));
	        AnchorPane anchorPane = loader.load();
	        Stage back = (Stage)((Node)event.getSource()).getScene().getWindow();
	        Scene scene = new Scene(anchorPane);
	        back.setScene(scene);
	        NonprofitComfirmItem controller = (NonprofitComfirmItem) loader.getController();
	        controller.construct(myAuctionCentral, myNonProfit, 
	        		myItemNameField.getText(), startBid, myDescripArea.getText(),
	        		myCurrAuction);
	        back.show();
		} else {
			myErrorLabel.setVisible(true);
			myErrorLabel.setText("Please fill in required fields");
		}
	}
	
	private boolean checkAllEntriesFilled() {		
		return !myDescripArea.getText().equals("") && !myItemNameField.getText().equals("")
				&& !myStartBidField.getText().equals("");
	}
	
	private int checkEmpty(TextField field, Label label, int count) {
		if (field.getText().equals("")) {			
			label.setTextFill(Color.RED);
			count++;
		} else {
			label.setTextFill(Color.BLACK);
			count--;
		}
		return count;
	}
	
	private int checkArea(int count) {
		if (myDescripArea.getText().equals("")) {
			myDescripLabel.setTextFill(Color.RED);
			count++;
		} else {
			myDescripLabel.setTextFill(Color.BLACK);
			count--;
		}
		return count;
	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Nonprofit/ViewItemList.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        NonprofitViewAllAuctions controller = (NonprofitViewAllAuctions) loader.getController();
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
