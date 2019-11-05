package architecture.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import architecture.models.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ConfigurationIpController implements Initializable{

	@FXML
	private Label defaultgateway;
	@FXML
	private Label ipv4;
	@FXML
	private Label subnetmask;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DatabaseConnector.conn();
			PreparedStatement prep1 = conn.prepareStatement("SELECT * FROM ipconfig");
			ResultSet result = prep1.executeQuery();
			
			while(result.next()) {
				defaultgateway.setText(result.getString("default_gateway"));
				subnetmask.setText(result.getString("subnet_mask"));
				ipv4.setText("192.168.1.18");
			}
			
			//result.close();

		}catch(Exception e) {
			
		}
	}
}














