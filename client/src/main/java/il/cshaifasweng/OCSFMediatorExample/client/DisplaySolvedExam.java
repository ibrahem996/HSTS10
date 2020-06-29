package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.databaseinitilize.InitlizeDataBase;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class DisplaySolvedExam {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signoutbtn;

    @FXML
    private Button backbtn;

    @FXML
    private ListView<?> listViewid;

    @FXML
    private Button DisplaySelectedExambtn;
    
    static Exam exam;
    
    static private List<Object> chosenStudentsbyExamIDIntegers = new ArrayList<Object>();


    public DisplaySolvedExam(int chosenintger, List<Object> solvedExamIinfo) {
    	List<Exam> exams = new  ArrayList<Exam>();
    	this.chosenStudentsbyExamIDIntegers = solvedExamIinfo;
		exams = InitlizeDataBase.getAllexams();
		for(Exam exam : exams)
		{
			if (exam.getId()==chosenintger)
			{
			this.exam = exam;
		}
			}
		
	}
    
    
    public DisplaySolvedExam() {
   	}
    
    

	@FXML
    void DisplaySelectedExamac(ActionEvent event) {

    }

    @FXML
    void backac(ActionEvent event) {

    }

    @FXML
    void signoutac(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'displaysolvedexam.fxml'.";
        assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'displaysolvedexam.fxml'.";
        assert listViewid != null : "fx:id=\"listViewid\" was not injected: check your FXML file 'displaysolvedexam.fxml'.";
        assert DisplaySelectedExambtn != null : "fx:id=\"DisplaySelectedExambtn\" was not injected: check your FXML file 'displaysolvedexam.fxml'.";

    }
}
