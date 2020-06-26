package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ExamsController {

    static private List<Course> courses = new ArrayList<Course>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signoutbtn;

    @FXML
    private Button backbtn;

    @FXML
    private MenuButton chosecoursebtn;

    @FXML
    private TextArea comenttxt;

    @FXML
    private TextArea cmntformetxt;

    @FXML
    private TextField durationtxt;
    
    @FXML
    private Button nextbtn;
    
    Object[] examInfoObjects = new Object [9];
    


    
    public ExamsController(List<Course> courses) {
		
    	this.courses = courses;
	}
    
    public ExamsController() {
		
   	}

	@FXML
    void backac(ActionEvent event) {

    }

    @FXML
    void chosecourseac(ActionEvent event) {

    }

    
    @FXML
    void nextac(ActionEvent event) throws IOException {
    	Course chosenCourse = null;
    	examInfoObjects[0] = durationtxt.getText();
        
        for(Course course : courses) {
			  if(course.getCourseName().equalsIgnoreCase(chosecoursebtn.getText()) ) 
			  { 
				  chosenCourse = course; 
				  }
			  }
        
        examInfoObjects[1] = chosenCourse;
        
        examInfoObjects[2] = null; 
        
        examInfoObjects[3] = comenttxt.getText();
        examInfoObjects[4] = cmntformetxt.getText();
        
        
        App.getInstance().bringquestionsforspecialcoursebysubject(examInfoObjects);



    }

    private void bringquestionsforspecialcoursebysubject(Object[] examInfoObjects2) {
		// TODO Auto-generated method stub
		
	}

	@FXML
    void signoutac(ActionEvent event) {

    }
    
    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent e) 
        { 
        	chosecoursebtn.setText(((MenuItem)e.getSource()).getText());
        } 
    }; 
     
    
    
    
    @FXML
    void initialize() {
    	int i = 0;
        assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'exams.fxml'.";
        assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'exams.fxml'.";
        assert chosecoursebtn != null : "fx:id=\"chosecoursebtn\" was not injected: check your FXML file 'exams.fxml'.";
        assert comenttxt != null : "fx:id=\"comenttxt\" was not injected: check your FXML file 'exams.fxml'.";
        assert cmntformetxt != null : "fx:id=\"cmntformetxt\" was not injected: check your FXML file 'exams.fxml'.";
        assert durationtxt != null : "fx:id=\"durationtxt\" was not injected: check your FXML file 'exams.fxml'.";
        
        
        for (Course course : courses)
        {
        	MenuItem btnButton = new MenuItem();
        	btnButton.setId(String.valueOf(i));
        	btnButton.setText(courses.get(i).getCourseName());
        	chosecoursebtn.getItems().add(btnButton);
        	i++;
            btnButton.setOnAction(event1);

        }

    }
}
