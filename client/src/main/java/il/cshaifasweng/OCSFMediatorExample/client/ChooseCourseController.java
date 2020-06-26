package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class ChooseCourseController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signoutbtn;

    @FXML
    private Button backbtn;

    @FXML
    private MenuButton chosecoursemenu;
    
	static private List<Course> courses = new ArrayList<Course>();

    
    public ChooseCourseController(List<Course> courses) {

    	this.courses = courses;
	}
    
    public ChooseCourseController() {
		
   	}

    @FXML
    void backac(ActionEvent event) {

    }

    @FXML
    void chosecourseac(ActionEvent event) throws IOException {
    }

    @FXML
    void signoutac(ActionEvent event) {

    }
    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent e) 
        { 
        	Course chosen = null;
        	
        	String courseString = (((MenuItem)e.getSource()).getText());
        	
        	for (Course course : courses)
            {
            	if(course.getCourseName().equalsIgnoreCase(courseString))
            	{
            		chosen = course;
            	}


            }
        	 
        	chosecoursemenu.setText(((MenuItem)e.getSource()).getText());
            
			try {
				App.getInstance().bringExams(chosen.getId());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


            
            
        } 
    }; 

    @FXML
    void initialize() {
    	int i=0;
        assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'displayexams.fxml'.";
        assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'displayexams.fxml'.";
        assert chosecoursemenu != null : "fx:id=\"chosecoursemenu\" was not injected: check your FXML file 'displayexams.fxml'.";
        
        for (Course course : courses)
        {

        	MenuItem btnButton = new MenuItem();
        	btnButton.setId(String.valueOf(i));
        	btnButton.setText(courses.get(i).getCourseName());
        	chosecoursemenu.getItems().add(btnButton);
        	i++;
            btnButton.setOnAction(event1);

        }
    }
}
