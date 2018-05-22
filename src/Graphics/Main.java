package Graphics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 
 * @author Raisa
 *
 */
public class Main  extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			//Read the fxml and draw the interface
			Parent root = FXMLLoader.load(getClass()
					.getResource("/Graphics/Login.fxml"));
			
			primaryStage.setTitle("Auction Central");
			primaryStage.setScene(new Scene(root));
	        primaryStage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
