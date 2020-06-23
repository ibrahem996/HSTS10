package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ShowScannedController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView welcomesign;

	@FXML
	void initialize() {
		assert welcomesign != null : "fx:id=\"welcomesign\" was not injected: check your FXML file 'showscanned.fxml'.";

	}

}
