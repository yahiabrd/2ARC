package architecture.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class RoomsManagementController implements Initializable{

	@FXML
	private Label labelRoom;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void showAvailableRooms(ActionEvent event) throws Exception{
		labelRoom.getScene().getWindow().hide();
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/architecture/views/AvailableRooms.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();
	        Stage stage = new Stage();
	        stage.setResizable(false);
	        stage.setTitle("Available Rooms");
	        stage.setScene(new Scene(root1));
	        stage.show();
	        
		}catch(Exception ex) {
			
		}
	}
	
	public void showBookedRooms(ActionEvent event) throws Exception{
		labelRoom.getScene().getWindow().hide();
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/architecture/views/BookedRooms.fxml"));
	        Parent root1 = (Parent) fxmlLoader.load();
	        Stage stage = new Stage();
	        stage.setResizable(false);
	        stage.setTitle("Booked Rooms");
	        stage.setScene(new Scene(root1));
	        stage.show();
	        
		}catch(Exception ex) {
			
		}
	}
}
