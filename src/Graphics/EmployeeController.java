package Graphics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import model.AuctionCentralEmployee;

public class EmployeeController implements Initializable{

	private AuctionCentral myAuctionCentral;
	private AuctionCentralEmployee myEmployee;
	
	@FXML
	Label myName;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	public void changeMaxAuctions(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/ChangeMaxAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage changeMaxAuctionsWindow = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        changeMaxAuctionsWindow.setScene(scene);
	}
	
	public void inputDateRange(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/inputDateRange.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage inputDateRange = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        inputDateRange.setScene(scene);
	}
	
	public void viewAllAuctions(ActionEvent theEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/employeeAllAuctions.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage allAuctions = (Stage)((Node)theEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        allAuctions.setScene(scene);
	}
	
	public void construct(AuctionCentral ac, AuctionCentralEmployee employee) {
		//myAuctionCentral = ac;
		//myEmployee = employee;
		myName.setText("Welcome" + employee.getName() + " .You are logged in as an Employee of Auction Central. What would you like to do?");
	}

}
