package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import il.cshaifasweng.OCSFMediatorExample.client.App;

public class PrimaryController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField usertxt;

	@FXML
	private MenuButton interfaceid;

	@FXML
	private MenuItem studentid;

	@FXML
	private MenuItem teacherid;

	@FXML
	private MenuItem managerid;

	@FXML
	private PasswordField passtxt;

	@FXML
	private Button loginbtn;

	@FXML
	private Text usernametext;

	String userType;

	public Text getUserNameText() {
		return usernametext;
	}

	@FXML
	void interfaceac(ActionEvent event) {

	}

	@FXML
	void loginac(ActionEvent event) throws IOException {

		String[] arr = { usertxt.getText(), passtxt.getText(), userType };

		App.getInstance().LoginIn(arr);

		System.out.println(usertxt.getText());
		System.out.println(passtxt.getText());
		System.out.println(userType);

	}

	@FXML
	void managerac(ActionEvent event) {
		userType = "Manager";
		interfaceid.setText("Manager");
	}

	@FXML
	void studentac(ActionEvent event) {

		userType = "Student";
		interfaceid.setText("Student");
	}

	@FXML
	void teacherac(ActionEvent event) {
		userType = "Teacher";
		interfaceid.setText("Teacher");
	}

	@FXML
	void initialize() {
		assert usertxt != null : "fx:id=\"usertxt\" was not injected: check your FXML file 'primary.fxml'.";
		assert interfaceid != null : "fx:id=\"interfaceid\" was not injected: check your FXML file 'primary.fxml'.";
		assert studentid != null : "fx:id=\"studentid\" was not injected: check your FXML file 'primary.fxml'.";
		assert teacherid != null : "fx:id=\"teacherid\" was not injected: check your FXML file 'primary.fxml'.";
		assert managerid != null : "fx:id=\"managerid\" was not injected: check your FXML file 'primary.fxml'.";
		assert passtxt != null : "fx:id=\"passtxt\" was not injected: check your FXML file 'primary.fxml'.";
		assert loginbtn != null : "fx:id=\"loginbtn\" was not injected: check your FXML file 'primary.fxml'.";

	}
}