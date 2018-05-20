package Graphics;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 
 * @author Raisa
 *
 */
public class LoginController implements Initializable{
	
	/**
	 * Precondition: User attempts to login
	 * Postcondition: The correct scene is shown 
	 * depending on the user type and if
	 * login was successful 
	 * @throws IOException 
	 */
	
	public void login(ActionEvent event) throws IOException {
		Parent bidderParent = FXMLLoader.load(getClass()
				.getResource("/Graphics/Bidder.fxml"));
		Scene bidderScene = new Scene(bidderParent);
		
		//This line gets the stage information
		//We need to have a way to connect this with our model to get 
		//the user information.
		Stage bidderWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		bidderWindow.setScene(bidderScene);
		bidderWindow.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
