package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.databaseinitilize.InitlizeDataBase;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
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
    private ListView<String> listViewid;

    @FXML
    private Button DisplaySelectedExambtn;
    
    static Exam exam;
    
    static int [] solvedexaminfoarray = new int[6];
    
    static private List<Object> solvedExamInfo = new ArrayList<Object>();


    public DisplaySolvedExam(List<Object> solvedExamIinfo) {
    	this.solvedExamInfo = solvedExamIinfo;
		
	}
		
    
    
    public DisplaySolvedExam() {
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

    //	System.out.println(selectedString);

    	String chosen = selectedString.substring(17,18);
    	int chosenintger =Integer.parseInt(chosen);
    	System.out.println(chosenintger);
    	App.getInstance().bringsspecificsolvedexam(chosenintger);
    }

    @FXML
    void backac(ActionEvent event) throws IOException {
    	
    	App.getInstance().displayExamtoseeResultsView();

    }

    @FXML
    void signoutac(ActionEvent event) throws IOException {

    	App.getInstance().LogOut();
    	App.getInstance().showBackToPrimaryView();
    	
    }

    @FXML
    void initialize() {
    	
    	ObservableList<String> itemStrings;
		listViewid.setOrientation(Orientation.VERTICAL);
		
        assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'displaysolvedexam.fxml'.";
        assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'displaysolvedexam.fxml'.";
        assert listViewid != null : "fx:id=\"listViewid\" was not injected: check your FXML file 'displaysolvedexam.fxml'.";
        assert DisplaySelectedExambtn != null : "fx:id=\"DisplaySelectedExambtn\" was not injected: check your FXML file 'displaysolvedexam.fxml'.";
        
        for (Object solvedexaminfo : solvedExamInfo)
		  { 

        		
			  solvedexaminfoarray = (int[]) solvedexaminfo;
			  
			  int solvedexamid = (int) solvedexaminfoarray[0];
			  String solvedexamidString = Integer.toString(solvedexamid);
			  
			  int examid = (int) solvedexaminfoarray[1];
			  String examidString = Integer.toString(examid);
			  
			  int studentid = (int) solvedexaminfoarray[2];
			  String studentidsString = Integer.toString(studentid);
			  
			  int grade = (int) solvedexaminfoarray[3];
			  String gradeString = Integer.toString(grade);
			  
			  int checkornot = (int) solvedexaminfoarray[4];
			  String checkornotString;
			  if (checkornot == 1)
			  {
				  checkornotString = "True";
			  }
			  else {
				 checkornotString ="False";
	
			}
			  
			  int shefinished = (int) solvedexaminfoarray[5];
			  String shefinishedString;
			  if (shefinished == 1)
			  {
				  shefinishedString = "True";
			  }
			  else {
				  shefinishedString ="False";
			}
			 			  
			 
			  String fullInfoString = "Solved Exam ID : " + solvedexamidString + "\n" + "Exam ID : " + examidString +"\n" +"Student ID: " + studentidsString + "\n" + "Grade : " + gradeString
					  + "\n" + "Checked : " + checkornotString + "\n" + "Finished : " + shefinishedString;
			  System.out.println(fullInfoString);
			  
			  listViewid.getItems().addAll(fullInfoString);
			  
			  
			  
		  }

    }
}
