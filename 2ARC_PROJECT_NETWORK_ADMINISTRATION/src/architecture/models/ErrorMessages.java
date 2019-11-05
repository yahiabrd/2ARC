package architecture.models;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Window;

public abstract class ErrorMessages {
	
	public static void error(String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static void confirmation(String title, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static void information(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		    	Dialog<?> dialog = new Dialog<>();
		    	Window    window = dialog.getDialogPane().getScene().getWindow();
		    	window.hide();
		    	System.out.println("ok");
		    }
		});
	}
}
