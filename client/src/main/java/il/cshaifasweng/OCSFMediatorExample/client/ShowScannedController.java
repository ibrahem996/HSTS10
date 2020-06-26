package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;

public class ShowScannedController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView welcomesign;

    @FXML
    private Button BackBut;

    @FXML
    private SplitMenuButton CoursMenu;

    @FXML
    void OnBackBut(ActionEvent event) throws IOException {
    	App.getInstance().showBackToStudentView();
    }

    @FXML
    void OnChoosed(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert welcomesign != null : "fx:id=\"welcomesign\" was not injected: check your FXML file 'showscanned.fxml'.";
        assert BackBut != null : "fx:id=\"BackBut\" was not injected: check your FXML file 'showscanned.fxml'.";
        assert CoursMenu != null : "fx:id=\"CoursMenu\" was not injected: check your FXML file 'showscanned.fxml'.";

    }
}
