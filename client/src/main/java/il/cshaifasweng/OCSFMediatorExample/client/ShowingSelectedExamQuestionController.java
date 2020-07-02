
package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ShowingSelectedExamQuestionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signoutbtn;

    @FXML
    private Button backbtn;

    @FXML
    private TextField answer1txt;

    @FXML
    private Button nextbtn;

    @FXML
    private TextArea questiontxt;

    @FXML
    private TextField answer2txt;

    @FXML
    private TextField answer3txt;
    
    @FXML
    private TextField answer4txt;

    @FXML
    private Button removebtn;

    @FXML
    private TextArea teachercmnttxt;

    @FXML
    private TextArea studntcmnttxt;

    @FXML
    private TextField gradetxt;
    
    @FXML
    private Button submiteditingbtn;
    
    @FXML
    private Label lastquestionlabel;

    static String whatiam; 
    static Exam exam = new Exam();
    int questionNum = 0;
    static Object[] examInfoObjects1 = new Object [9];
    
    static List <Question> questions = new ArrayList<Question>();
    static List <Double> gradesDoubles = new ArrayList<Double>();

    static List <Double> newGradesDoubles = new ArrayList<Double>();
    
    static List <String> commentPerStudentList = new ArrayList<String>();  
    static List <String> newCommentPerStudentList = new ArrayList<String>();

    static List <String> commentPerTeacherList = new ArrayList<String>();
    static List <String> newCommentPerTeacherList = new ArrayList<String>();

    static List<Integer>questionsID = new ArrayList<Integer>();

    

    public ShowingSelectedExamQuestionController(Exam exam, Object[] examInfoObjects,String whatiam) {
    	this.exam = exam;
    	examInfoObjects1 = examInfoObjects;
    	this.whatiam=whatiam;
    }
    
    public ShowingSelectedExamQuestionController() {
    }

	@FXML
    void backac(ActionEvent event) throws IOException {

		    questionNum--;
		if (questionNum < 0) {

        	App.getInstance().ShowingSelectedExamInfo();
    	}
		else {
     		submiteditingbtn.setVisible(false);
     		lastquestionlabel.setVisible(false);
			FillTheQuestions(questionNum);

		}
		
		
    }

    @FXML
    void nextac(ActionEvent event) throws IOException {
    	questionNum++;
    
    	
     	if(questionNum == questions.size()-1)
     	{
       	    FillTheQuestions(questionNum);
     		lastquestionlabel.setVisible(true);
     		
     		 if  (whatiam.equalsIgnoreCase("Teacher"))
     	       {
     			submiteditingbtn.setVisible(true);
     		   }
     	    	
     		

     	}
     	
     	else if (questionNum == questions.size()) {
        	App.getInstance().showwhatiamView(); 
     	}
     	
     	else {
       	    FillTheQuestions(questionNum);

		}


    }

    @FXML
    void removeac(ActionEvent event) {

    	questions .remove(questionNum);
    	gradesDoubles .remove(questionNum);
    	commentPerStudentList  .remove(questionNum);
    	commentPerTeacherList .remove(questionNum);
    	  FillTheQuestions(questionNum);
    	  if (questionNum==questions.size()-1)
    	  {
    		  lastquestionlabel.setVisible(true);
    		  submiteditingbtn.setVisible(true);
    	  }
  
    }

    @FXML
    void signoutac(ActionEvent event) {

    }
    
    @FXML
    void submiteditingac(ActionEvent event) throws IOException {

    	for (int i=0;i<questions.size();i++)
    	{
    		questionsID.add(questions.get(i).getId());
  
    	}
    	System.out.println(examInfoObjects1[0]);
    	Course course = (Course) examInfoObjects1[1];
    	System.out.println(course.getCourseName());
    	System.out.println(commentPerStudentList.size());
    	examInfoObjects1[5]=newGradesDoubles;
    	examInfoObjects1[6]=questionsID;
    	examInfoObjects1[7]=newCommentPerStudentList;
    	examInfoObjects1[8]=newCommentPerTeacherList;
    	App.getInstance().savingtheeditedexam(examInfoObjects1);
    	System.out.println(commentPerStudentList.size());
    }
    
    
    
    void FillTheQuestions(int i)
    {

    	questiontxt.setText(questions.get(i).getQuestion());
    	answer1txt.setText(questions.get(i).getAnswers().get(0).getAnswer());
    	answer2txt.setText(questions.get(i).getAnswers().get(1).getAnswer());
    	answer3txt.setText(questions.get(i).getAnswers().get(2).getAnswer());
    	answer4txt.setText(questions.get(i).getAnswers().get(3).getAnswer());
      	teachercmnttxt.setText(commentPerTeacherList.get(i));
      	studntcmnttxt.setText(commentPerStudentList.get(i));
	  	
	  	String gradesString = Double.toString(gradesDoubles.get(i));

     	gradetxt.setText(gradesString);
     	fillthenewlists(i);

    }
	 private void fillthenewlists(int qnumber)
	 {
		 Double grDouble = Double.parseDouble(gradetxt.getText());
		 newGradesDoubles.add(grDouble) ;
		 newCommentPerStudentList.add(studntcmnttxt.getText());
		 newCommentPerTeacherList.add(teachercmnttxt.getText());
		 
    }

    @FXML
    void initialize() {

    	questionNum = 0;
    	questions = exam.getQuestions();
    	gradesDoubles = exam.getGrades();
    	commentPerStudentList = exam.getStudentComment();
    	commentPerTeacherList = exam.getTeacherComment();

    	
   	 if  (whatiam.equalsIgnoreCase("Manager"))
       {
   		questiontxt.setEditable(false);
   		answer1txt.setEditable(false);
   		answer2txt.setEditable(false);
   		answer3txt.setEditable(false);
   		answer4txt.setEditable(false);
   		teachercmnttxt.setEditable(false);
   		studntcmnttxt.setEditable(false);
   		gradetxt.setEditable(false);
   		removebtn.setVisible(false);


	   }
    
    	
    	
    	lastquestionlabel.setVisible(false);
    	submiteditingbtn.setVisible(false);
    	
        assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'showingselectedexamquestions.fxml'.";
        assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'showingselectedexamquestions.fxml'.";
        assert answer1txt != null : "fx:id=\"answer1txt\" was not injected: check your FXML file 'showingselectedexamquestions.fxml'.";
        assert nextbtn != null : "fx:id=\"nextbtn\" was not injected: check your FXML file 'showingselectedexamquestions.fxml'.";
        assert questiontxt != null : "fx:id=\"questiontxt\" was not injected: check your FXML file 'showingselectedexamquestions.fxml'.";
        assert answer2txt != null : "fx:id=\"answer2txt\" was not injected: check your FXML file 'showingselectedexamquestions.fxml'.";
        assert answer3txt != null : "fx:id=\"answer3txt\" was not injected: check your FXML file 'showingselectedexamquestions.fxml'.";
        assert removebtn != null : "fx:id=\"removebtn\" was not injected: check your FXML file 'showingselectedexamquestions.fxml'.";
        assert teachercmnttxt != null : "fx:id=\"teachercmnttxt\" was not injected: check your FXML file 'showingselectedexamquestions.fxml'.";
        assert studntcmnttxt != null : "fx:id=\"studntcmnttxt\" was not injected: check your FXML file 'showingselectedexamquestions.fxml'.";
        assert gradetxt != null : "fx:id=\"gradetxt\" was not injected: check your FXML file 'showingselectedexamquestions.fxml'.";

        FillTheQuestions(questionNum);
    }
}
