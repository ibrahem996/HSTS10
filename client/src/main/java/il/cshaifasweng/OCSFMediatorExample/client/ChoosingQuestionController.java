package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ChoosingQuestionController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button signoutbtn;

	@FXML
	private Button backbtn;

	@FXML
	private Button selectbtn;

	@FXML
	private TextArea commentstxt;

	@FXML
	private TextField gradetxt;

	@FXML
	private TextArea cmntformetxt;

	@FXML
	private TextArea questiontxt;

	@FXML
	private TextField answer1txt;

	@FXML
	private TextField answer2txt;

	@FXML
	private TextField answer3txt;

	@FXML
	private TextField answer4txt;

	@FXML
	private Button nextbtn1;

	@FXML
	private Label lastqustionlabel;

	@FXML
	private Text gradelabel;

	@FXML
	private Text comentlabel;

	@FXML
	private Text comentformelabel;

	@FXML
	private RadioButton chose1btn;

	@FXML
	private RadioButton chose4btn;

	@FXML
	private RadioButton chose3btn;

	@FXML
	private RadioButton chose2btn;

	@FXML
	private Button submitbtn;

	int questionNum = 0;

	int correctAnwer = 0;
	boolean pressedselect;

	static private List<Object> allQuestions = new ArrayList<Object>();
	private List<String> commentstudent = new ArrayList<String>();
	private List<String> commentteacher = new ArrayList<String>();
	private List<Double> Grades = new ArrayList<Double>();
	List<Integer> allQuestionselected = new ArrayList<Integer>();
	Object[] qinfObjects = new Object[8];
	static Object[] examInfoObjects1 = new Object[9];
	Boolean[] selectedOrnot = new Boolean[allQuestions.size()];

	public ChoosingQuestionController(List<Object> allQuestions, Object[] examInfoObjects) {

		this.allQuestions = allQuestions;
		this.examInfoObjects1 = examInfoObjects;

	}

	public ChoosingQuestionController() {
	}

	@FXML
	void backac(ActionEvent event) throws IOException {

		listscontrolling();
		gradetxt.setText("");
		commentstxt.setText("");
		cmntformetxt.setText("");
		questionNum--;

		if (questionNum < 0) {

			App.getInstance().showCreateExamView();

		}

		else {
			if (selectedOrnot[questionNum]) {
				selectbtn.setText("Unselect");
			}

			else {
				selectbtn.setText("Select");
			}

			setvisibleselected(false);
			lastqustionlabel.setVisible(false);
			submitbtn.setVisible(false);
			nextbtn1.setVisible(true);

			FillTheQuestions(questionNum);

		}

	}

	@FXML
	void chose1ac(ActionEvent event) {

	}

	@FXML
	void chose2ac(ActionEvent event) {

	}

	@FXML
	void chose3ac(ActionEvent event) {

	}

	@FXML
	void chose4ac(ActionEvent event) {

	}

	@FXML
	void nextac(ActionEvent event) throws IOException {

		listscontrolling();
		gradetxt.setText("");
		commentstxt.setText("");
		cmntformetxt.setText("");
		questionNum++;
		if (selectedOrnot[questionNum]) {
			selectbtn.setText("Unselect");
		} else {
			selectbtn.setText("Select");
		}
		setvisibleselected(false);
		if (questionNum == allQuestions.size() - 1) {
			FillTheQuestions(questionNum);
			lastqustionlabel.setVisible(true);
			submitbtn.setVisible(true);
			nextbtn1.setVisible(false);

		}

		else if (questionNum == allQuestions.size()) {
			App.getInstance().showlastStageView();
		}

		else {

			FillTheQuestions(questionNum);
		}

	}

	@FXML
	void selectac(ActionEvent event) {

		if (!selectedOrnot[questionNum]) {
			pressedselect = true;
			selectedOrnot[questionNum] = true;
			selectbtn.setText("Unselect");

			setvisibleselected(true);
			selectedOrnot[questionNum] = true;
		} else {
			setvisibleselected(false);
			selectbtn.setText("Select");

			selectedOrnot[questionNum] = false;

		}

	}

	@FXML
	void submitac(ActionEvent event) throws IOException {
		listscontrolling();
		examInfoObjects1[5] = Grades;
		examInfoObjects1[6] = allQuestionselected;
		examInfoObjects1[7] = commentstudent;
		examInfoObjects1[8] = commentteacher;
		App.getInstance().SavingTheExamWithQuestions(examInfoObjects1);

	}

	@FXML
	void signoutac(ActionEvent event) {

	}

	private void listscontrolling() {

		if (selectedOrnot[questionNum] && pressedselect) {

			Grades.add(Double.parseDouble(gradetxt.getText()));
			commentstudent.add(commentstxt.getText());
			commentteacher.add(cmntformetxt.getText());
			System.out.println((int) qinfObjects[0]);
			allQuestionselected.add((int) qinfObjects[0]);

		} else {
			if (!selectedOrnot[questionNum]) {
				for (int i = 0; i < allQuestionselected.size(); i++) {
					if (allQuestionselected.get(i) == (int) qinfObjects[0]) {
						Grades.remove(i);
						commentstudent.remove(i);
						commentteacher.remove(i);
						allQuestionselected.remove(i);
					}

				}
			}
		}
		pressedselect = false;
	}

	void FillTheQuestions(int i) {

		qinfObjects = (Object[]) allQuestions.get(i);

		questiontxt.setText(qinfObjects[1].toString());
		answer1txt.setText(qinfObjects[3].toString());
		answer2txt.setText(qinfObjects[4].toString());
		answer3txt.setText(qinfObjects[5].toString());
		answer4txt.setText(qinfObjects[6].toString());
		correctAnwer = (int) qinfObjects[2];

		switch (correctAnwer) {

		case 1:
			chose1btn.setSelected(true);
			chose2btn.setSelected(false);
			chose3btn.setSelected(false);
			chose4btn.setSelected(false);

			break;

		case 2:
			chose2btn.setSelected(true);
			chose1btn.setSelected(false);
			chose3btn.setSelected(false);
			chose4btn.setSelected(false);

			break;

		case 3:
			chose3btn.setSelected(true);
			chose1btn.setSelected(false);
			chose2btn.setSelected(false);
			chose4btn.setSelected(false);

			break;

		case 4:
			chose4btn.setSelected(true);
			chose1btn.setSelected(false);
			chose2btn.setSelected(false);
			chose3btn.setSelected(false);

			break;

		}

	}

	private void setvisibleselected(Boolean flag) {
		gradetxt.setVisible(flag);
		commentstxt.setVisible(flag);
		cmntformetxt.setVisible(flag);
		gradelabel.setVisible(flag);
		comentformelabel.setVisible(flag);
		comentlabel.setVisible(flag);

	}

	@FXML
	void initialize() {
		pressedselect = false;
		submitbtn.setVisible(false);

		lastqustionlabel.setVisible(false);

		questionNum = 0;

		setvisibleselected(false);

		System.out.println(selectedOrnot.length);
		for (int i = 0; i < selectedOrnot.length; i++) {
			selectedOrnot[i] = false;
		}

		assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert selectbtn != null : "fx:id=\"selectbtn\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert commentstxt != null : "fx:id=\"commentstxt\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert gradetxt != null : "fx:id=\"gradetxt\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert cmntformetxt != null : "fx:id=\"cmntformetxt\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert questiontxt != null : "fx:id=\"questiontxt\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert answer1txt != null : "fx:id=\"answer1txt\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert answer2txt != null : "fx:id=\"answer2txt\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert answer3txt != null : "fx:id=\"answer3txt\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert answer4txt != null : "fx:id=\"answer4txt\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert nextbtn1 != null : "fx:id=\"nextbtn1\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert chose1btn != null : "fx:id=\"chose1btn\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert chose4btn != null : "fx:id=\"chose4btn\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert chose3btn != null : "fx:id=\"chose3btn\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";
		assert chose2btn != null : "fx:id=\"chose2btn\" was not injected: check your FXML file 'choosingquestionstoexam.fxml'.";

		FillTheQuestions(questionNum);

	}
}
