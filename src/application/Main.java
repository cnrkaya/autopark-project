package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


/**Starts the application ,performs menu scene
 * 
 * @author kaya
 *@version 1.0
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {			
			AnchorPane root = (AnchorPane)FXMLLoader.load((getClass().getResource("/view/Menu.fxml")));
			Scene menu = new Scene(root,800,450);
			primaryStage.setScene(menu);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
