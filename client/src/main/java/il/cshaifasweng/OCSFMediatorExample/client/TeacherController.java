package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TeacherController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button taddqustbtn;

    @FXML
    private Button tcreateexbtn;

    @FXML
    private Button tdsplyexamrsltbtn;

    @FXML
    private Button tsignoutbtn;
    
    @FXML
    private Button displayexamtbtn;

    @FXML
    private Button showqustlistbtn;
    
    @FXML
    private Button addtimebtn;

    
    @FXML
    void addtimeac(ActionEvent event) throws IOException {
    	
    	App.getInstance().bringExamOnExecute();

    }
    
    
    @FXML
    void shoqustlistac(ActionEvent event) throws IOException {
    	
    	App.getInstance().checkSubject(1);
    	

    }

    @FXML
    void taddqustionbtn(ActionEvent event) throws IOException {

    	App.getInstance().checkSubject(2);
    }
    

    @FXML
    void tcreateexac(ActionEvent event) throws IOException {
    	
    	App.getInstance().checkCourses(0);
    }

    @FXML
    void tdsplyexamrsltac(ActionEvent event) throws IOException {

    	App.getInstance().AllExamstoShowResultsTeacher();
    }

    @FXML
    void tsignoutac(ActionEvent event) throws IOException {
    	App.getInstance().LogOut();
    	App.getInstance().showBackToPrimaryView();

    }
    
    @FXML
    void displayexamacac(ActionEvent event) throws IOException {

    	App.getInstance().checkCourses(1);
    }

    @FXML
    void initialize() {
        assert taddqustbtn != null : "fx:id=\"taddqustbtn\" was not injected: check your FXML file 'teacher.fxml'.";
        assert tcreateexbtn != null : "fx:id=\"tcreateexbtn\" was not injected: check your FXML file 'teacher.fxml'.";
        assert tdsplyexamrsltbtn != null : "fx:id=\"tdsplyexamrsltbtn\" was not injected: check your FXML file 'teacher.fxml'.";
        assert tsignoutbtn != null : "fx:id=\"tsignoutbtn\" was not injected: check your FXML file 'teacher.fxml'.";
        assert displayexamtbtn != null : "fx:id=\"displayexamtbtn\" was not injected: check your FXML file 'teacher.fxml'.";
        assert showqustlistbtn != null : "fx:id=\"showqustlistbtn\" was not injected: check your FXML file 'teacher.fxml'.";
        assert addtimebtn != null : "fx:id=\"addtimebtn\" was not injected: check your FXML file 'teacher.fxml'.";


    }
}
