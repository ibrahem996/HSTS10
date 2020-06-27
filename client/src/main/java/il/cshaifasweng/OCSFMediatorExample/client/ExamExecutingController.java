package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ExamExecutingController {

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
	private TextArea studntcmnttxt;

	@FXML
	private Label lastquestionlabel;

	@FXML
	private Button submiteditingbtn;

	@FXML
	private RadioButton Rad1;

	@FXML
	private RadioButton Rad2;

	@FXML
	private RadioButton Rad3;

	@FXML
	private RadioButton Rad4;

	@FXML
	private Text quesnum;

	@FXML
	private Text timertxt;

	public static Exam exam = new Exam();
	static int questionNum = 0;
	static int numofques;

	static List<Question> questions = new ArrayList<Question>();
	static List<String> commentPerStudentList = new ArrayList<String>();
	static List<String> newCommentPerStudentList = new ArrayList<String>();
	static int[] ChoosenAswers;

	static int choosenAnswer;
	static int duration;

	public ExamExecutingController(Exam exam1) {
		System.out.println("controllll");
		exam = exam1;
		System.out.println(exam.getQuestions().get(2).getQuestion());
		System.out.println(exam.getQuestions().get(2).getAnswers().get(0).getAnswer());
		System.out.println(exam.getGrades().get(1));
		ChoosenAswers = new int[exam.getQuestions().size()];
		numofques = exam.getQuestions().size();
		duration = (int) exam.getDuration();
		System.out.println("enddddddddddcontrollll");
	}

	public ExamExecutingController() {

	}

	@FXML
	void OnRad1(ActionEvent event) {
		ChoosenAswers[questionNum] = 1;
		Rad1.setSelected(true);
		Rad2.setSelected(false);
		Rad3.setSelected(false);
		Rad4.setSelected(false);
	}

	@FXML
	void OnRad2(ActionEvent event) {
		ChoosenAswers[questionNum] = 2;
		Rad2.setSelected(true);
		Rad1.setSelected(false);
		Rad3.setSelected(false);
		Rad4.setSelected(false);
	}

	@FXML
	void OnRad3(ActionEvent event) {
		ChoosenAswers[questionNum] = 3;
		Rad3.setSelected(true);
		Rad1.setSelected(false);
		Rad2.setSelected(false);
		Rad4.setSelected(false);
	}

	@FXML
	void OnRad4(ActionEvent event) {
		ChoosenAswers[questionNum] = 4;
		Rad4.setSelected(true);
		Rad1.setSelected(false);
		Rad2.setSelected(false);
		Rad3.setSelected(false);
	}

	@FXML
	void backac(ActionEvent event) {
		questionNum--;
		quesnum.setText(questionNum + 1 + " / " + numofques);
		if (questionNum == 1) {
			backbtn.setVisible(false);
		}
		if(questionNum == questions.size()-2) {
			nextbtn.setVisible(true);
			submiteditingbtn.setVisible(false);
			backbtn.setVisible(true);
			lastquestionlabel.setVisible(false);
		}

		FillTheQuestions(questionNum);

	}

	@FXML
	void nextac(ActionEvent event) {
		questionNum++;
		quesnum.setText(questionNum + 1 + " / " + numofques);
		if (questionNum == 1) {
			backbtn.setVisible(true);
			
		}

		if (questionNum == questions.size() - 1) {
			FillTheQuestions(questionNum);
			lastquestionlabel.setVisible(true);
			submiteditingbtn.setVisible(true);
			nextbtn.setVisible(false);

		}

		else {
			FillTheQuestions(questionNum);

		}
	}

	@FXML
	void signoutac(ActionEvent event) throws IOException {
		App.getInstance().LogOut();
	}

	@FXML
	void submiteditingac(ActionEvent event) throws IOException {

		App.getInstance().savingthesolvedexam(exam, ChoosenAswers, true);
		System.out.println(commentPerStudentList.size());
	}

	void FillTheQuestions(int i) {

		questiontxt.setText(questions.get(i).getQuestion());
		answer1txt.setText(questions.get(i).getAnswers().get(0).getAnswer());
		answer2txt.setText(questions.get(i).getAnswers().get(1).getAnswer());
		answer3txt.setText(questions.get(i).getAnswers().get(2).getAnswer());
		answer4txt.setText(questions.get(i).getAnswers().get(3).getAnswer());
		studntcmnttxt.setText(commentPerStudentList.get(i));

		fillthenewlists(i);

	}

	private void fillthenewlists(int qnumber) {
		newCommentPerStudentList.add(studntcmnttxt.getText());

	}

	@FXML
	void initialize() {

		System.out.println("inittttttttttt");
		questionNum = 0;
		questions = exam.getQuestions();
		commentPerStudentList = exam.getStudentComment();
		int i = 0;
		while (i < questions.size()) {
			ChoosenAswers[i] = 0;
			i++;
		}

		quesnum.setText(questionNum + 1 + " / " + numofques);
		lastquestionlabel.setVisible(false);
		submiteditingbtn.setVisible(false);

		backbtn.setVisible(false);

		assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert answer1txt != null : "fx:id=\"answer1txt\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert nextbtn != null : "fx:id=\"nextbtn\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert questiontxt != null : "fx:id=\"questiontxt\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert answer2txt != null : "fx:id=\"answer2txt\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert answer3txt != null : "fx:id=\"answer3txt\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert answer4txt != null : "fx:id=\"answer4txt\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert studntcmnttxt != null : "fx:id=\"studntcmnttxt\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert lastquestionlabel != null : "fx:id=\"lastquestionlabel\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert submiteditingbtn != null : "fx:id=\"submiteditingbtn\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert Rad1 != null : "fx:id=\"Rad1\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert Rad2 != null : "fx:id=\"Rad2\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert Rad3 != null : "fx:id=\"Rad3\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert Rad4 != null : "fx:id=\"Rad4\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert quesnum != null : "fx:id=\"quesnum\" was not injected: check your FXML file 'examexecutintg.fxml'.";
		assert timertxt != null : "fx:id=\"timertxt\" was not injected: check your FXML file 'examexecutintg.fxml'.";

		FillTheQuestions(questionNum);
		Timer myTimer = new Timer();
		duration++;
		myTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				timertxt.setText(Integer.toString(--duration) + " minutes left");
				if (duration <= 0) {
					try {
						App.getInstance().savingthesolvedexam(exam, ChoosenAswers, false);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}, 60000);

		System.out.println("enddddddddinittttttttttt");

	}
}
