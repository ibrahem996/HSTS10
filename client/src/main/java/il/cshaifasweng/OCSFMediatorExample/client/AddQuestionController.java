package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Answer;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddQuestionController {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button qbackbtn;

    @FXML
    private MenuButton chosesubbtn;

    @FXML
    private TextArea qusttxt;

    @FXML
    private TextField answer1txt;

    @FXML
    private TextField answertxt2;

    @FXML
    private TextField answertxt3;

    @FXML
    private TextField answertxt4;

    @FXML
    private RadioButton circle2btn;

    @FXML
    private RadioButton circle3btn;

    @FXML
    private RadioButton circle1btn;

    @FXML
    private RadioButton circle4btn;

    @FXML
    private Button qsubmtbtn;
    
    int chosenAnswer;

    static private List<Subject> subjects = new ArrayList<Subject>();;
    
    public  AddQuestionController() {
    }
    
    public  AddQuestionController( List<Subject> subjects) {
    	this.subjects = subjects;
	}
    
	
    
    
    @FXML
    void chosesubac(ActionEvent event) throws IOException {
		
    }

    @FXML
    void circle1ac(ActionEvent event) {
    	chosenAnswer = 1;
    	circle2btn.setSelected(false);
    	circle3btn.setSelected(false);
    	circle4btn.setSelected(false);
  
    }

    @FXML
    void circle2ac(ActionEvent event) {
    	chosenAnswer = 2;
    	circle1btn.setSelected(false);
    	circle3btn.setSelected(false);
    	circle4btn.setSelected(false);
    }

    @FXML
    void circle3ac(ActionEvent event) {
    	chosenAnswer = 3;
    	circle2btn.setSelected(false);
    	circle1btn.setSelected(false);
    	circle4btn.setSelected(false);
    }

    @FXML
    void circle4ac(ActionEvent event) {
    	chosenAnswer = 4;
    	circle2btn.setSelected(false);
    	circle3btn.setSelected(false);
    	circle1btn.setSelected(false);
    }

    @FXML
    void qbackac(ActionEvent event) {

    }
    @FXML
    void qsubmitac(ActionEvent event) throws IOException {

    	   	Subject chosenSubject = null;
    	   	for(Subject subject : subjects) {
    			  if(subject.getName().equalsIgnoreCase(chosesubbtn.getText())) 
    			  { 
    				  chosenSubject	= subject; }
    			  }

    	  	Answer answer1 = new Answer();
    	   	Answer answer2 = new Answer();
    	 	Answer answer3 = new Answer();
    	  	Answer answer4 = new Answer();
    	  	System.out.println(answer1.getId());
        	System.out.println(answer2.getId());
        	System.out.println(answer3.getId());
        	System.out.println(answer4.getId());
    		answer1.setAnswer(answer1txt.getText());       
    	  	answer2.setAnswer(answertxt2.getText());
    	  	answer3.setAnswer(answertxt3.getText());
    	  	answer4.setAnswer(answertxt4.getText());
    	  	System.out.println(chosenSubject.getNum());
    	  	Question newQuestion = new Question(qusttxt.getText(),answer1,answer2,answer3,answer4,chosenAnswer, chosenSubject); 
    	 	System.out.println(chosenSubject.getNum());
    	 	System.out.println(chosenSubject.getNum());
        	System.out.println(answer1.getAnswer());
        	System.out.println(answer2.getAnswer());
        	System.out.println(answer3.getAnswer());
        	System.out.println(answer4.getAnswer());
        	
        	System.out.println(answer1.getId());
        	System.out.println(answer2.getId());
        	System.out.println(answer3.getId());
        	System.out.println(answer4.getId());

        	System.out.println(newQuestion.getCorrectAnswer());
    	  	App.getInstance().AddQuestion(newQuestion,0);

			  

    }
    
    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
        public void handle(ActionEvent e) 
        { 
            chosesubbtn.setText(((MenuItem)e.getSource()).getText());
        } 
    }; 
    
    

    

    @FXML
    void initialize() {
    	
    	int i=0;
    	
        assert qbackbtn != null : "fx:id=\"qbackbtn\" was not injected: check your FXML file 'addquestion.fxml'.";
        assert chosesubbtn != null : "fx:id=\"chosesubbtn\" was not injected: check your FXML file 'addquestion.fxml'.";
        assert qusttxt != null : "fx:id=\"qusttxt\" was not injected: check your FXML file 'addquestion.fxml'.";
        assert answer1txt != null : "fx:id=\"answer1txt\" was not injected: check your FXML file 'addquestion.fxml'.";
        assert answertxt2 != null : "fx:id=\"answertxt2\" was not injected: check your FXML file 'addquestion.fxml'.";
        assert answertxt3 != null : "fx:id=\"answertxt3\" was not injected: check your FXML file 'addquestion.fxml'.";
        assert answertxt4 != null : "fx:id=\"answertxt4\" was not injected: check your FXML file 'addquestion.fxml'.";
        assert circle2btn != null : "fx:id=\"circle2btn\" was not injected: check your FXML file 'addquestion.fxml'.";
        assert circle3btn != null : "fx:id=\"circle3btn\" was not injected: check your FXML file 'addquestion.fxml'.";
        assert circle1btn != null : "fx:id=\"circle1btn\" was not injected: check your FXML file 'addquestion.fxml'.";
        assert circle4btn != null : "fx:id=\"circle4btn\" was not injected: check your FXML file 'addquestion.fxml'.";
        assert qsubmtbtn != null : "fx:id=\"qsubmtbtn\" was not injected: check your FXML file 'addquestion.fxml'.";
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
