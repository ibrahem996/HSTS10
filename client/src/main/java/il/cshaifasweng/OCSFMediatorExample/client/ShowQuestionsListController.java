package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class ShowQuestionsListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button qbackbtn;

    @FXML
    private Button signoutbtn;

    @FXML
    private MenuButton chosesubbtn;
    
    int subject_id;
    
    
    static private List<Subject> subjects = new ArrayList<Subject>();
    
    public  ShowQuestionsListController() {
    }
    
    public  ShowQuestionsListController( List<Subject> subjects) {
    	this.subjects = subjects;
	}

    @FXML
    void chosesubac(ActionEvent event) {

    }

    @FXML
    void qbackac(ActionEvent event) throws IOException {
    	App.getInstance().showwhatiamView();
    }

    @FXML
    void signoutac(ActionEvent event) throws IOException {
    	App.getInstance().LogOut();
    	App.getInstance().showBackToPrimaryView();
    }
    
    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent e) 
        { 
        	Subject chosen = null;
        	
        	String subjectString = (((MenuItem)e.getSource()).getText());
        	
        	for (Subject subject : subjects)
            {
            	if(subject.getName().equalsIgnoreCase(subjectString))
            	{
            		chosen = subject;
            	}


            }
        	 
            chosesubbtn.setText(((MenuItem)e.getSource()).getText());
           
            System.out.println(chosen.getId());
            System.out.println(chosen.getName());
            System.out.println(subjectString);

            try {
				App.getInstance().bringQuestions(chosen.getId());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
            
            
        } 
    }; 

    @FXML
    void initialize() {
    	
    	int i=0;
    	
        assert qbackbtn != null : "fx:id=\"qbackbtn\" was not injected: check your FXML file 'shoquestionlist.fxml'.";
        assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'shoquestionlist.fxml'.";
        assert chosesubbtn != null : "fx:id=\"chosesubbtn\" was not injected: check your FXML file 'shoquestionlist.fxml'.";
        
        for (Subject subject : subjects)
        {
        	MenuItem btnButton = new MenuItem();
        	btnButton.setId(String.valueOf(i));
        	btnButton.setText(subjects.get(i).getName());
        	chosesubbtn.getItems().add(btnButton);
        	i++;
            btnButton.setOnAction(event1);

        }


    }
}
