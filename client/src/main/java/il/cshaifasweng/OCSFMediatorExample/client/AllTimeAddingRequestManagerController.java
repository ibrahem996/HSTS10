package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class AllTimeAddingRequestManagerController {

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
    private Button submitbtn;
    
    static Object [] alladdingtimerequestexamsarray = new Object[5];

    static List<Object[]> alladdingtimerequestexams = new ArrayList<Object[]>();

    public AllTimeAddingRequestManagerController(List<Object[]> alladdingtimerequestexams) {
    	
    	this.alladdingtimerequestexams = alladdingtimerequestexams;		      
		}
    
    public AllTimeAddingRequestManagerController() {
	}

	@FXML
    void backac(ActionEvent event) {

    }

    @FXML
    void signoutac(ActionEvent event) {

    }

    @FXML
    void submitac(ActionEvent event) throws IOException {
    	String selectedString = "";
    	ObservableList<String> exam;
    	exam = listViewid.getSelectionModel().getSelectedItems();
    	for (String mString : exam) 
    	{
    		selectedString += mString;
    	}

    	String chosen = selectedString.substring(10, 16);
    	
    	System.out.println("VAAAAAAMOS");

    	System.out.println(chosen);
    	App.getInstance().confirmingtheaddingTimeByManager(chosen);
    	
    }

    @FXML
    void initialize() {

    	ObservableList<String> itemStrings;
		listViewid.setOrientation(Orientation.VERTICAL);
		
        assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'alltimeaddingrequestmanager.fxml'.";
        assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'alltimeaddingrequestmanager.fxml'.";
        assert listViewid != null : "fx:id=\"listViewid\" was not injected: check your FXML file 'alltimeaddingrequestmanager.fxml'.";
        assert submitbtn != null : "fx:id=\"submitbtn\" was not injected: check your FXML file 'alltimeaddingrequestmanager.fxml'.";
        
        for (int i=0 ; i<alladdingtimerequestexams.size() ; i++)
		  { 

	
      	System.out.println(i);
      	
      	alladdingtimerequestexamsarray = alladdingtimerequestexams.get(i);
			  
			  int examid = (int) alladdingtimerequestexamsarray[0];
			  String examidString = Integer.toString(examid);
			  
			  Object obj = alladdingtimerequestexamsarray[1];
			  String str = obj.toString();
			  
			  double d = Double.valueOf(str).doubleValue();
			  double duration = d;
			  String durationString = Double.toString(duration);
			  
			  String coursename = (String) alladdingtimerequestexamsarray[3];			  
			  
			  int executaion = (int) alladdingtimerequestexamsarray[2];
			  String executaionString;
			  if (executaion == 1)
			  {
				  executaionString = "Computerized";
			  }
			  
			  else {
				  executaionString ="Manual";
	
			}	  
			  
			  String fullNameString = (String) alladdingtimerequestexamsarray[4];
			 			  
			  String fullInfoString = "Exam ID : " + examidString + "\n" +"Teacher Name: " + fullNameString +"\n" + "Duration : " + durationString +"\n" +"Course : " + coursename + "\n" + "Executaion Type : " + executaionString;
			  System.out.println(fullInfoString);
			  listViewid.getItems().addAll(fullInfoString);

		  }

    }
}