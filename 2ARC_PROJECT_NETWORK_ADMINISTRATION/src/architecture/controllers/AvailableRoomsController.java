package architecture.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import architecture.models.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AvailableRoomsController implements Initializable{

	@FXML
	private Label labelRooms;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		String text = "";
		try {
			Connection conn = DatabaseConnector.conn();
			PreparedStatement prep1 = conn.prepareStatement("SELECT * FROM rooms WHERE available = 1");
			ResultSet result = prep1.executeQuery();
			
			while(result.next()) {
				text += result.getString("room_type") + "\n";
			}
			
			labelRooms.setText(text);
		
		}catch(Exception e) {
			
		}
	}

}
