/**
 * 
 */
package Graphics;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import model.AuctionCentral;
import model.NonProfit;

/**
 * @author aayushshah
 * @author Benjamin Yuen
 *
 */
public class NonProfitController implements Initializable {

	@FXML
	Button button2;
	
	@FXML
	private TextField startDateTxtField;
	@FXML
	private TextField startTimeTxtField;
	@FXML
	private TextField endTimeTxtField;
	@FXML
	private Label startDateLabel;
	@FXML
	private Label startTimeLabel;
	@FXML
	private Label endTimeLabel;
	@FXML
	private Button submitButton;
	
	
	private AuctionCentral myAuctionCentral;
	private NonProfit myNonProfit;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void construct(AuctionCentral ac, NonProfit nonProfit) {
		this.myAuctionCentral = ac;
		this.myNonProfit = nonProfit;
	}
	
	@FXML
	public void viewAuctionRequestScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/nonprofitAuctionRequest.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage auctionRequest = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(anchorPane);
        auctionRequest.setScene(scene);
        auctionRequest.show();
	}
	
	@FXML
	public void submit(ActionEvent event) throws IOException {
		//LocalDate inputDate = LocalDate.of(year,month,dayOfMonth);
//		LocalDate startDate = LocalDate.of(year,month,dayOfMonth);
//		myAuctionCentral.auctionRequest(myNonProfit, startDate, startTime, duration, theName);
	}


}
