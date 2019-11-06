package architecture.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AdministrationController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void showIpConfiguration(ActionEvent event)  throws Exception{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/architecture/views/ConfigIp.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();
	        Stage stage = new Stage();
	        stage.setResizable(false);
	        stage.setTitle("IP config");
	        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/adminIcon.png")));
	        stage.setScene(new Scene(root1));
	        stage.show();
	        
		}catch(Exception ex) {
			
		}
	}
	
	public void showDatabaseManagement(ActionEvent event) throws Exception{
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/architecture/views/DatabaseManagement.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();
	        Stage stage = new Stage();
	        stage.setResizable(false);
	        stage.setTitle("Database Management");
	        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/adminIcon.png")));
	        stage.setScene(new Scene(root1));
	        stage.show();
	        
		}catch(Exception ex) {
			
		}
	}
	
	
	public void showRoomsManagement(ActionEvent event) throws Exception{
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/architecture/views/RoomsManagement.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();
	        Stage stage = new Stage();
	        stage.setResizable(false);
	        stage.setTitle("Rooms Management");
	        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/adminIcon.png")));
	        stage.setScene(new Scene(root1));
	        stage.show();
	        
		}catch(Exception ex) {
			
		}
	}
	
	public void showServer(ActionEvent event) throws Exception{
		String command = "cmd.exe /c start cmd.exe /k "+"java ServerPrinter";
		Process child = Runtime.getRuntime().exec(command);
	}
}
