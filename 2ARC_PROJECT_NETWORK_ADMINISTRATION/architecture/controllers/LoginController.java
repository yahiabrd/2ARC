package architecture.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import architecture.models.DatabaseConnector;
import architecture.models.ErrorMessages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	@FXML
	private TextField login;
	@FXML
	private PasswordField password;
	@FXML
	private Button validation;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void CheckAccount() throws Exception{
		String username = login.getText();
		String psw = password.getText();
		String profession = "network engineer";
		boolean isExist = false;
		Connection conn = DatabaseConnector.conn();
		PreparedStatement prep1 = conn.prepareStatement("SELECT * FROM employees WHERE emp_name = ? AND emp_profession = ?");
		prep1.setString(1, username);
		prep1.setString(2, profession);
		ResultSet result = prep1.executeQuery();
		while(result.next()) {
			isExist = true;
			if(result.getString("emp_psw").equals(psw)) {
				//System.out.println("ok bien connecté");
				validation.getScene().getWindow().hide();
				
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/architecture/views/AdministrationPage.fxml"));
			        Parent root1 = (Parent) fxmlLoader.load();
			        Stage stage = new Stage();
			        stage.setResizable(false);
			        stage.setTitle("Administration page");
			        stage.setScene(new Scene(root1));
			        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/adminIcon.png")));
			        stage.show();
			        
				}catch(Exception ex) {
					
				}
			}else {
				//System.out.println("mauvais password");
				ErrorMessages.error("Bad password", "try again");
			}
		}
		if(isExist==false) {
			ErrorMessages.error("Bad account", "this account doesn't exist");
		}
	}

	public void checkByClick(ActionEvent event) throws Exception{
		CheckAccount();
	}
	
	public void checkByKey(KeyEvent event) throws Exception{
		if(event.getCode() == KeyCode.ENTER) {
			CheckAccount();
		}
	}
}
