package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ShowingSelectedExamInfo {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signoutbtn;

    @FXML
    private Button backbtn;

    @FXML
    private TextArea comenttxt;

    @FXML
    private TextArea cmntformetxt;

    @FXML
    private TextField durationtxt;

    @FXML
    private Button nextbtn;
    
    @FXML
    private TextField coursenametxt;

    @FXML
    private TextField createdBytxt;
    
    static Exam exam = new Exam();
    static String whatiam; 
    static Object[] examInfoObjects = new Object [9];

    
    
    
    public ShowingSelectedExamInfo(Exam exam, String whatiam) {
    	this.exam = exam;
		this.whatiam=whatiam;

    	  
		  
	}
    
    public ShowingSelectedExamInfo() {
		
   	}

	@FXML
    void backac(ActionEvent event) throws IOException {
		App.getInstance().displayExamView();
    }

    @FXML
    void nextac(ActionEvent event) throws IOException {

    	examInfoObjects[0] = durationtxt.getText();
    	examInfoObjects[1] = exam.getCourse();
    	examInfoObjects[2] = null;
    	examInfoObjects[3] = comenttxt.getText();
    	examInfoObjects[4] = cmntformetxt.getText();
    	
		


    	App.getInstance().showSelectedExamQuestion(exam,examInfoObjects);


    }

    @FXML
    void signoutac(ActionEvent event) throws IOException {

    	App.getInstance().LogOut();
    	App.getInstance().showBackToPrimaryView();
    	
    }
   

    
    @FXML
    void initialize() {
    	
    	
    		 if  (whatiam.equalsIgnoreCase("Manager"))
  	       {
    			 comenttxt.setEditable(false);
    			 cmntformetxt.setEditable(false);
    			 durationtxt.setEditable(false);
    			 coursenametxt.setEditable(false);
  		   }
			 

    		 createdBytxt.setEditable(false);
    		 
        assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'showingselectedexaminfo.fxml'.";
        assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'showingselectedexaminfo.fxml'.";
        assert comenttxt != null : "fx:id=\"comenttxt\" was not injected: check your FXML file 'showingselectedexaminfo.fxml'.";
        assert cmntformetxt != null : "fx:id=\"cmntformetxt\" was not injected: check your FXML file 'showingselectedexaminfo.fxml'.";
        assert durationtxt != null : "fx:id=\"durationtxt\" was not injected: check your FXML file 'showingselectedexaminfo.fxml'.";
        assert nextbtn != null : "fx:id=\"nextbtn\" was not injected: check your FXML file 'showingselectedexaminfo.fxml'.";
        assert coursenametxt != null : "fx:id=\"durationtxt1\" was not injected: check your FXML file 'showingselectedexaminfo.fxml'.";
        assert createdBytxt != null : "fx:id=\"createdBytxt\" was not injected: check your FXML file 'showingselectedexaminfo.fxml'.";
        
        String exam_duration=String.valueOf(exam.getDuration());
        String courseNameString = exam.getCourse().getCourseName();
	    String generalTeachercomment=exam.getGeneralCommentTeacher();
	    String generalstudentcomment=exam.getGeneralCommentStudent();
	    String teachercreated= exam.getCreatedByteacher().getFirstName() +' ' + exam.getCreatedByteacher().getLastName() ; 
	    
	    durationtxt.setText(exam_duration); 
	    coursenametxt.setText(courseNameString); 
 	   cmntformetxt.setText(generalTeachercomment);
 	   comenttxt.setText(generalstudentcomment);
 	   createdBytxt.setText(teachercreated);

    }
}
