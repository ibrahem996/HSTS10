package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StudentController {


	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView welcomesign;

	@FXML
	private Button scanned;

	@FXML
	private Button grades;

	@FXML
	private Button execution;

	@FXML
	private Button logOut;

	@FXML
	void OnExecution(ActionEvent event) {
		try {
			App.getInstance().showExecutelogView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void OnGrades(ActionEvent event) {
		try {
			App.getInstance().getSolvedExams(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void OnLogOut(ActionEvent event) {
		try {
			App.getInstance().LogOut();
			App.getInstance().showBackToPrimaryView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void OnScanned(ActionEvent event) {
		try {
			App.getInstance().getSolvedExams(2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {
		assert welcomesign != null : "fx:id=\"welcomesign\" was not injected: check your FXML file 'student.fxml'.";
		assert scanned != null : "fx:id=\"scanned\" was not injected: check your FXML file 'student.fxml'.";
		assert grades != null : "fx:id=\"grades\" was not injected: check your FXML file 'student.fxml'.";
		assert execution != null : "fx:id=\"execution\" was not injected: check your FXML file 'student.fxml'.";
		assert logOut != null : "fx:id=\"logOut\" was not injected: check your FXML file 'student.fxml'.";

	}
}
