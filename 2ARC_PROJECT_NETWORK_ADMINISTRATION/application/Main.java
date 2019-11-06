package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			 Parent root = FXMLLoader.load(getClass().getResource("/architecture/views/LoginPage.fxml"));
			 
			 primaryStage.setTitle("Login Page");
			 primaryStage.setScene(new Scene(root));
			 primaryStage.setResizable(false);
			 primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/adminIcon.png")));
			 primaryStage.show();
			 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
