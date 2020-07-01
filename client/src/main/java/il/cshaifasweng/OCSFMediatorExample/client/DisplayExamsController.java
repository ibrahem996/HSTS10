package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;


import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class DisplayExamsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signoutbtn;

    @FXML
    private Button backbtn;

    @FXML
    private ListView <String> listViewid;
    

    @FXML
    private Button submitbtn;
    
    @FXML
    private Button examextractionbtn;
    
    @FXML
    private TextField codebtn;
    
    @FXML
    private CheckBox manualbtn;

    @FXML
    private CheckBox compbtn;
    
    @FXML
    private Button DisplaySelectedExambtn;

   

    static private List<Object> examsInfo = new ArrayList<Object>();
    
    Object[] examobjects = new Object [3];
    
    int examExecutaion;
    static String whatiam; 


    
    public DisplayExamsController(List<Object> examsInfoList,String whatiam) {
    	this.examsInfo = examsInfoList;

    	this.whatiam = whatiam;
    	


	}
    
    public DisplayExamsController() {
   	}
    
    @FXML
    void DisplaySelectedExamac(ActionEvent event) throws IOException {

    	char[] choseCode = new char[4];
    	String selectedString = "";
    	ObservableList<String> exam;
    	exam = listViewid.getSelectionModel().getSelectedItems();
    	for (String mString : exam) 
    	{
    		selectedString += mString;
    	}

    	String chosen = selectedString.substring(9, 15);
    	
    	int chosenintger =Integer.parseInt(chosen);
    	App.getInstance().bringselectedexam(chosenintger);
    }
    

	@FXML
    void backac(ActionEvent event) throws IOException {
		
		App.getInstance().chooseCourseController();
	       
    }

    @FXML
    void signoutac(ActionEvent event) throws IOException {
    	
    	App.getInstance().LogOut();
    	App.getInstance().showBackToPrimaryView();

    }
    
    @FXML
    void submitac(ActionEvent event) throws IOException {
    	char[] choseCode = new char[4];
    	String selectedString = "";
    	ObservableList<String> exam;
    	exam = listViewid.getSelectionModel().getSelectedItems();
    	for (String mString : exam) 
    	{
    		selectedString += mString;
    	}

    	String chosen = selectedString.substring(9, 15);
    	
    	int chosenintger =Integer.parseInt(chosen);
    	choseCode = codebtn.getText().toCharArray();
    	
    	App.getInstance().GivingCodeToExam(chosenintger,choseCode,examExecutaion);

    	
    }
    
    @FXML
    void manualac(ActionEvent event) {
    	compbtn.setSelected(false);
    	examExecutaion = 0;

    }
    
    @FXML
    void compac(ActionEvent event) {
    	manualbtn.setSelected(false);
    	examExecutaion = 1;
    }

    
    
    
    @FXML
    void examextractionac(ActionEvent event) {
    	codebtn.setVisible(true);
    	submitbtn.setVisible(true);
    	manualbtn.setVisible(true);
    	compbtn.setVisible(true);


    }
    


    @FXML
    void initialize() {
    	System.out.println("QQQQ5");

    	int i = 0 ;
    	manualbtn.setVisible(false);
    	compbtn.setVisible(false);
    	codebtn.setVisible(false);
    	submitbtn.setVisible(false);
    	ObservableList<String> itemStrings;
		listViewid.setOrientation(Orientation.VERTICAL);
		
		
		
		
		

   	 if  (whatiam.equalsIgnoreCase("Manager"))
	       {
			  examextractionbtn.setVisible(false);
			  
		   }

        assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'displayexams.fxml'.";
        assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'displayexams.fxml'.";
        assert listViewid != null : "fx:id=\"listViewid\" was not injected: check your FXML file 'displayexams.fxml'.";
        assert submitbtn != null : "fx:id=\"submitbtn\" was not injected: check your FXML file 'displayexams.fxml'.";
        assert examextractionbtn != null : "fx:id=\"examextractionbtn\" was not injected: check your FXML file 'displayexams.fxml'.";
        assert codebtn != null : "fx:id=\"codebtn\" was not injected: check your FXML file 'displayexams.fxml'.";
        assert manualbtn != null : "fx:id=\"manualbtn\" was not injected: check your FXML file 'displayexams.fxml'.";
        assert compbtn != null : "fx:id=\"compbtn\" was not injected: check your FXML file 'displayexams.fxml'.";

        

		  for (Object examInfo : examsInfo)
		  { 

			  List<Object> tempList = examsInfo;
			  examobjects = (Object[]) tempList.get(i);
			  
			  int examid = (int) examobjects[0];
			  String examIDString = Integer.toString(examid);
			  
			  String str = examobjects[1].toString();
			  double d = Double.valueOf(str).doubleValue();
			  double duration = d;
			  
			  String durationString = Double. toString(duration);
			  String fullnameString = (String) examobjects[2];
			  
			  String fullInfoString = "Exam ID: " + examIDString + "\n" + "Duration: " + durationString +"\n" + "Created By: " + fullnameString;
			  System.out.println(fullInfoString);
			  
			  listViewid.getItems().addAll(fullInfoString);
			  i++;
			  
			  
		  }
		 

    }
}
