package Graphics.Employee;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
import Graphics.LoginController;
import model.Auction;
import model.AuctionCentral;
import model.AuctionCentralEmployee;
import model.Item;

public class EmployeeViewItemController implements Initializable {

	private AuctionCentral myAuctionCentral;
	private AuctionCentralEmployee myEmployee;
	private Item myItem;
	
	@FXML Label itemDetails;
	
	public void construct(AuctionCentral theAuctionCentral, AuctionCentralEmployee theEmployee, Auction theAuction, Item theItem,
							LocalDate theStartTime, LocalDate theEndTime) {
		myAuctionCentral = theAuctionCentral;
		myEmployee = theEmployee;
		myItem = theItem;
		DecimalFormat df = new DecimalFormat("0.00"); 
		String details = "Item: "+
				this.myItem.getItemName() + "\n\nDescription: " + myItem.getItemDesciption() 
				+ "\n\nStarting Bid: " + "$" + df.format(this.myItem.getStartingBid());
		this.itemDetails.setText(details);
	}
	
	public void back(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/EmployeeViewAllAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage back = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        back.setScene(scene);
        EmployeeViewAllAuctionsController controller = (EmployeeViewAllAuctionsController) loader.getController();
        controller.construct(myAuctionCentral, (AuctionCentralEmployee) myEmployee, "");
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
	
	public void home(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Employee/EmployeeMainMenu.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage login = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        login.setScene(scene);
        EmployeeController controller = (EmployeeController) loader.getController();
        controller.construct(myAuctionCentral, myEmployee, 0);
        login.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
