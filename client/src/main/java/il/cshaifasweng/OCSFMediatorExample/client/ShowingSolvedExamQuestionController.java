package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.solvedExam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ShowingSolvedExamQuestionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button qbackbtn;

    @FXML
    private Button signoutbtn;

    @FXML
    private Button nextbtn;

    @FXML
    private Button confirmbtn;

    @FXML
    private TextArea qusttext;

    @FXML
    private TextField answer1txt;

    @FXML
    private TextField answer2txt;

    @FXML
    private TextField answer3txt;

    @FXML
    private TextField answer4txt;

    @FXML
    private RadioButton select1btn;

    @FXML
    private RadioButton select3btn;

    @FXML
    private RadioButton select2btn;

    @FXML
    private RadioButton select4btn;

    @FXML
    private Label lastQuestionLabel;

    @FXML
    private Button changegradebtn;

    @FXML
    private TextField finalgradetxt;

    @FXML
    private TextArea explainationtxt;
    
    @FXML
    private TextArea generalcomnttxt;

    
    static solvedExam solvedexam ;
    static String whatiam; 
    static Exam exam = new Exam();
    int questionNum = 0;
    int chosenanswer=0;
    static List <Question> questions = new ArrayList<Question>();
    static List <Double> gradesDoubles = new ArrayList<Double>();
    static List <Integer> chosenanswers = new ArrayList<Integer>();
	static String [] solvedInfo = new String[4];

    
    
    
    public ShowingSolvedExamQuestionController(solvedExam solvedexam,String whatiam) {

    	this.solvedexam = solvedexam;
    	this.exam=solvedexam.getExam();
    	this.whatiam = whatiam;
    	
		System.out.println(solvedexam.getExam().getId());
		System.out.println(solvedexam.getExam().getQuestions().get(1).getQuestion());
		System.out.println(solvedexam.getExam().getQuestions().get(0).getAnswers().get(0).getAnswer());
		System.out.println(solvedexam.getChosenAnswers().get(0));
		System.out.println(solvedexam.getExam().getQuestions().get(0).getCorrectAnswer());

    	
	}
    
    public ShowingSolvedExamQuestionController() {
	}

	@FXML
    void changegradeac(ActionEvent event) {
    	finalgradetxt.setEditable(true);
    	explainationtxt.setVisible(true);

    }

    @FXML
    void confirmac(ActionEvent event) throws IOException {
    	String gradedString = finalgradetxt.getText();
    	String generalcomntdtxt = generalcomnttxt.getText();
    	String explainedString = explainationtxt.getText();
    	String solvedidString = String.valueOf(solvedexam.getId());
    	solvedInfo[1] = gradedString;
    	solvedInfo[2] = generalcomntdtxt;
    	solvedInfo[3] = explainedString;
    	solvedInfo[0] = solvedidString;    	
    	System.out.println("AAAAA");
    	System.out.println(gradedString);
    	System.out.println(generalcomntdtxt);
    	System.out.println(explainedString);
    	App.getInstance().confirmsolvedexam(solvedInfo);

    }

    @FXML
    void nextac(ActionEvent event) throws IOException {
    	
         questionNum++;
    
    	
     	if(questionNum == questions.size()-1)
     	{
     		if (whatiam.equalsIgnoreCase("Teacher"))
     		{
    	    	changegradebtn.setVisible(true);
    	    	confirmbtn.setVisible(true);
    	    	nextbtn.setVisible(false);


     		}
     		else if(whatiam.equalsIgnoreCase("Manager")) {
     			confirmbtn.setVisible(false);
     			
     		}
       	    FillTheQuestions(questionNum);
         	 lastQuestionLabel.setVisible(true);
	    	finalgradetxt.setVisible(true);
	    	
     		
     	}
     	
     	else if (questionNum == questions.size()) {
        	App.getInstance().showwhatiamView(); 
     	}
     	
     	else {
       	    FillTheQuestions(questionNum);

		}
    }

    @FXML
    void qbackac(ActionEvent event) throws IOException {

            questionNum--;
    		if (questionNum < 0) {

            	App.getInstance().showDisplaySolvedExam();
        	}
    		else {
    			confirmbtn.setVisible(false);
    			lastQuestionLabel.setVisible(false);
    	    	changegradebtn.setVisible(false);
    	    	finalgradetxt.setVisible(false);
    	    	explainationtxt.setVisible(false);
    		nextbtn.setVisible(true);
    	    	FillTheQuestions(questionNum);

    		}
    }

    @FXML
    void select1ac(ActionEvent event) {

    }

    @FXML
    void select2ac(ActionEvent event) {

    }

    @FXML
    void select3ac(ActionEvent event) {

    }

    @FXML
    void select4ac(ActionEvent event) {

    }

    @FXML
    void signoutac(ActionEvent event) {

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    void FillTheQuestions(int i)
    {

    	qusttext.setText(questions.get(i).getQuestion());
    	answer1txt.setText(questions.get(i).getAnswers().get(0).getAnswer());
    	answer2txt.setText(questions.get(i).getAnswers().get(1).getAnswer());
    	answer3txt.setText(questions.get(i).getAnswers().get(2).getAnswer());
    	answer4txt.setText(questions.get(i).getAnswers().get(3).getAnswer());
    	chosenanswer=solvedexam.getChosenAnswers().get(i);
 
    	

    	switch(chosenanswer) {
        
        case 1:
        	select1btn.setSelected(true);
        	select2btn.setSelected(false);
        	select3btn.setSelected(false);
        	select4btn.setSelected(false);

        	break;
        
        case 2:
        	select2btn.setSelected(true);
        	select1btn.setSelected(false);
        	select3btn.setSelected(false);
        	select4btn.setSelected(false);

        	break;

        case 3:
        	select3btn.setSelected(true);
        	select1btn.setSelected(false);
        	select2btn.setSelected(false);
        	select4btn.setSelected(false);

        	break;

        case 4:
        	select4btn.setSelected(true);
        	select1btn.setSelected(false);
        	select2btn.setSelected(false);
        	select3btn.setSelected(false);

        	break;


      }
    	
	  

    }

    @FXML
    void initialize() {
    	
    	int finalgrade =solvedexam.getGrade();
    	String finalgradeString = String.valueOf(finalgrade);
    	finalgradetxt.setText(finalgradeString);
    	questionNum = 0;
    	questions = exam.getQuestions();
    	gradesDoubles = exam.getGrades();
    	chosenanswers = solvedexam.getChosenAnswers();

    	lastQuestionLabel.setVisible(false);
    	changegradebtn.setVisible(false);
    	finalgradetxt.setVisible(false);
    	explainationtxt.setVisible(false);
    	confirmbtn.setVisible(false);
    	
    	finalgradetxt.setEditable(false);

        assert qbackbtn != null : "fx:id=\"qbackbtn\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert nextbtn != null : "fx:id=\"nextbtn\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert confirmbtn != null : "fx:id=\"confirmbtn\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert qusttext != null : "fx:id=\"qusttext\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert answer1txt != null : "fx:id=\"answer1txt\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert answer2txt != null : "fx:id=\"answer2txt\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert answer3txt != null : "fx:id=\"answer3txt\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert answer4txt != null : "fx:id=\"answer4txt\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert select1btn != null : "fx:id=\"select1btn\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert select3btn != null : "fx:id=\"select3btn\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert select2btn != null : "fx:id=\"select2btn\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert select4btn != null : "fx:id=\"select4btn\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert lastQuestionLabel != null : "fx:id=\"lastQuestionLabel\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert changegradebtn != null : "fx:id=\"changegradebtn\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert finalgradetxt != null : "fx:id=\"finalgradetxt\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert explainationtxt != null : "fx:id=\"explainationtxt\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";
        assert generalcomnttxt != null : "fx:id=\"generalcomnttxt\" was not injected: check your FXML file 'showingsolvedexamquestion.fxml'.";

        if(whatiam.equalsIgnoreCase("Manager"))
        {
        	generalcomnttxt.setVisible(false);
        }
        FillTheQuestions(questionNum);
    }
}