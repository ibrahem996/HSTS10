package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LastStageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button anotheracbtn;

    @FXML
    private Button disconnectbtn;

    @FXML
    void anotheracac(ActionEvent event) throws IOException {
    																////remember to fix this we need to send person interface 
    	App.getInstance().showTeacherView();
    }

    @FXML
    void disconnectac(ActionEvent event) throws IOException {
    	
    	App.getInstance().startAgain();

    }

    @FXML
    void initialize() {
        assert anotheracbtn != null : "fx:id=\"anotheracbtn\" was not injected: check your FXML file 'laststage.fxml'.";
        assert disconnectbtn != null : "fx:id=\"disconnectbtn\" was not injected: check your FXML file 'laststage.fxml'.";

    }
}
