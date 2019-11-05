package architecture.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class DatabaseManagementController implements Initializable{

	@FXML
	private WebView web;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		WebEngine webEngine = web.getEngine();
		webEngine.load("http://localhost/phpmyadmin/db_structure.php?server=1&db=cap2019");
	}

}
