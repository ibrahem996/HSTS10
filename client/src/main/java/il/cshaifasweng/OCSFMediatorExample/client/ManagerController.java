package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mdsplyexamsbtn;

    @FXML
    private Button mdsplyquestionbtn;

    @FXML
    private Button mcnfrmaddtimebtn;

    @FXML
    private Button mdsplyexamrsltbtn;

    @FXML
    private Button msignoutbtn;

    @FXML
    private Text managerintr;

    @FXML
    void mcnfrmaddtimeac(ActionEvent event) {

    }

    @FXML
    void mdsplyexamrsltac(ActionEvent event) {

    }

    @FXML
    void mdsplyexamsac(ActionEvent event) {

    }

    @FXML
    void mdsplyquestionac(ActionEvent event) {

    }

    @FXML
    void msignoutac(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert mdsplyexamsbtn != null : "fx:id=\"mdsplyexamsbtn\" was not injected: check your FXML file 'manager.fxml'.";
        assert mdsplyquestionbtn != null : "fx:id=\"mdsplyquestionbtn\" was not injected: check your FXML file 'manager.fxml'.";
        assert mcnfrmaddtimebtn != null : "fx:id=\"mcnfrmaddtimebtn\" was not injected: check your FXML file 'manager.fxml'.";
        assert mdsplyexamrsltbtn != null : "fx:id=\"mdsplyexamrsltbtn\" was not injected: check your FXML file 'manager.fxml'.";
        assert msignoutbtn != null : "fx:id=\"msignoutbtn\" was not injected: check your FXML file 'manager.fxml'.";
        assert managerintr != null : "fx:id=\"managerintr\" was not injected: check your FXML file 'manager.fxml'.";

    }
}
