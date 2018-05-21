package Graphics;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.AuctionCentral;
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
//			Parent root = FXMLLoader.load(getClass()
//					.getResource("/Graphics/Login.fxml"));
//			
//			primaryStage.setTitle("Auction Central");
//			primaryStage.setScene(new Scene(root));
//	        primaryStage.show();
			AuctionCentral auctionCentral = null;
			try {   
		        FileInputStream file = new FileInputStream("auctionCentralDefault.ser");
		        ObjectInputStream in = new ObjectInputStream(file);        
		        auctionCentral = (AuctionCentral)in.readObject();
		        in.close();
		        file.close();
		    } catch(IOException exception) {
		        System.out.println("IOException");
		    } catch(ClassNotFoundException exception) {
		        System.out.println("ClassNotFoundException");
		    }
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Graphics/Login.fxml"));
	        AnchorPane anchorPane = loader.load();
	        
	        LoginController controller = (LoginController) loader.getController();
	        controller.setAuctionCentral(auctionCentral);
	        controller.setButtonText(auctionCentral.getAuctionsSortedByDate().get(1).getAuctionName());
	        
	        Scene scene = new Scene(anchorPane);
	        primaryStage.setScene(scene);
	        primaryStage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
