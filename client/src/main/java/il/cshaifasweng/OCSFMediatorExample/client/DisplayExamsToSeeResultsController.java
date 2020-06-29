package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;





public class DisplayExamsToSeeResultsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signoutbtn;

    @FXML
    private Button backbtn;

    @FXML
    private ListView<String> listViewid;

    @FXML
    private Button DisplaySelectedExambtn;

    static String whatiam; 
    static private List<Exam> AllExamToSeeResults = new ArrayList<Exam>();
    
    
    public DisplayExamsToSeeResultsController(List<Exam> AllExamToSeeResults,String whatiam) {  //////// what the fk is whatiam for???????

    	this.AllExamToSeeResults = AllExamToSeeResults;
    	this.whatiam = whatiam;
	}
    
    public DisplayExamsToSeeResultsController() {
   	}
    

    
    
    @FXML
    void DisplaySelectedExamac(ActionEvent event) throws IOException {
    	
    	String selectedString = "";
    	ObservableList<String> exam;
    	exam = listViewid.getSelectionModel().getSelectedItems();
    	for (String mString : exam) 
    	{
    		selectedString += mString;
    	}

    	String chosen = selectedString.substring(9, 15);
    	
    	int chosenintger =Integer.parseInt(chosen);
    	System.out.println(chosenintger);
    	App.getInstance().bringallsolvedExams(chosenintger);

    }

    @FXML
    void backac(ActionEvent event) {

    }

    @FXML
    void signoutac(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	 int i = 0 ;
    	ObservableList<String> itemStrings;
		listViewid.setOrientation(Orientation.VERTICAL);
    	
    	
    	
    	
        assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'displayexamstoseeResults.fxml'.";
        assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'displayexamstoseeResults.fxml'.";
        assert listViewid != null : "fx:id=\"listViewid\" was not injected: check your FXML file 'displayexamstoseeResults.fxml'.";
        assert DisplaySelectedExambtn != null : "fx:id=\"DisplaySelectedExambtn\" was not injected: check your FXML file 'displayexamstoseeResults.fxml'.";

        for (Exam exam : AllExamToSeeResults)
		  { 

			  
			  int examid = exam.getId();
			  String examIDString = Integer.toString(examid);
			  double duration = exam.getDuration();
			  String durationString =String.valueOf(duration) ;
			  String fullnameString = exam.getCreatedByteacher().getFirstName()+"  " + exam.getCreatedByteacher().getLastName();
			  char [] code = new char [4];
			  code = exam.getCode();
			  String codeString = String.copyValueOf(code);
			  
			  String fullInfoString = "Exam ID: " + examIDString + "\n" + "Duration: " + durationString +"\n" + "Created By: " + fullnameString
					  +"\n" + "Code: " + codeString ;
			  System.out.println(fullInfoString);
			  
			  listViewid.getItems().addAll(fullInfoString);
			  i++;
			  
			  
		  }
        
        
        
    }
}
