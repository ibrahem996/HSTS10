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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ShowingListQuestionSubjectController {

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
	private Button editbtn;

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

	Object[] qinfObjects = new Object[8];

	Subject currentSubject;

	int correctAnwer = 0;

	int chosenAnswer;

	int questionNum = 0;
	int questionseditid = 0;/////////////////// this is endicate how many questions we have add and them to
							/////////////////// the if in 160

	static private List<Object> allQuestions = new ArrayList<Object>();

	static private List<Subject> subjects = new ArrayList<Subject>();

	public ShowingListQuestionSubjectController() {

	}

	public ShowingListQuestionSubjectController(List<Object> allQuestions, List<Subject> subjects) {
		this.allQuestions = allQuestions;
		this.subjects = subjects;
	}

	@FXML
	void editac(ActionEvent event) throws IOException {
		Object[] newqinfObjects = new Object[8];

		Subject chosenSubject = null;
		int chosenid = (int) qinfObjects[7];
		for (Subject subject : subjects) {
			if (subject.getId() == chosenid) {
				chosenSubject = subject;
			}
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

		newqinfObjects[3] = answer1txt.getText();
		newqinfObjects[4] = answer2txt.getText();
		newqinfObjects[5] = answer3txt.getText();
		newqinfObjects[6] = answer4txt.getText();
		newqinfObjects[7] = chosenid;

		allQuestions.add(newqinfObjects);

		answer2.setAnswer(answer2txt.getText());
		answer3.setAnswer(answer3txt.getText());
		answer4.setAnswer(answer4txt.getText());
		newqinfObjects[1] = qusttext.getText();// pay attention that the id is null in the newqinfObjects[0]
		newqinfObjects[2] = chosenAnswer;
		Question newQuestion = new Question(qusttext.getText(), answer1, answer2, answer3, answer4, chosenAnswer,
				chosenSubject);
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

		App.getInstance().AddQuestion(newQuestion, 1);

	}

	@FXML
	void nextac(ActionEvent event) throws IOException {

		questionNum++;

		if (questionNum == allQuestions.size() + questionseditid - 1) {
			FillTheQuestions(questionNum);
			lastQuestionLabel.setVisible(true);
		}

		else if (questionNum == allQuestions.size()) {
			App.getInstance().showlastStageView();
		}

		else {

			FillTheQuestions(questionNum);
		}

	}

	@FXML
	void qbackac(ActionEvent event) throws IOException {

		questionNum--;

		if (questionNum < 0) {
			App.getInstance().showQuestionListnView();

		}

		else {
			lastQuestionLabel.setVisible(false);
			FillTheQuestions(questionNum);
		}

	}

	@FXML
	void select1ac(ActionEvent event) {
		chosenAnswer = 1;
		select2btn.setSelected(false);
		select3btn.setSelected(false);
		select4btn.setSelected(false);

	}

	@FXML
	void select2ac(ActionEvent event) {
		chosenAnswer = 2;
		select1btn.setSelected(false);
		select3btn.setSelected(false);
		select4btn.setSelected(false);
	}

	@FXML
	void select3ac(ActionEvent event) {
		chosenAnswer = 3;
		select1btn.setSelected(false);
		select2btn.setSelected(false);
		select4btn.setSelected(false);
	}

	@FXML
	void select4ac(ActionEvent event) {
		chosenAnswer = 4;
		select1btn.setSelected(false);
		select2btn.setSelected(false);
		select3btn.setSelected(false);
	}

	@FXML
	void signoutac(ActionEvent event) {

	}

	void FillTheQuestions(int i) {

		qinfObjects = (Object[]) allQuestions.get(i);

		qusttext.setText(qinfObjects[1].toString());
		answer1txt.setText(qinfObjects[3].toString());
		answer2txt.setText(qinfObjects[4].toString());
		answer3txt.setText(qinfObjects[5].toString());
		answer4txt.setText(qinfObjects[6].toString());
		correctAnwer = (int) qinfObjects[2];

		switch (correctAnwer) {

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

		questionNum = 0;

		lastQuestionLabel.setVisible(false);

		assert qbackbtn != null : "fx:id=\"qbackbtn\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";
		assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";
		assert nextbtn != null : "fx:id=\"nextbtn\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";
		assert editbtn != null : "fx:id=\"editbtn\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";
		assert qusttext != null : "fx:id=\"qusttext\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";
		assert answer1txt != null : "fx:id=\"answer1txt\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";
		assert answer2txt != null : "fx:id=\"answer2txt\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";
		assert answer3txt != null : "fx:id=\"answer3txt\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";
		assert answer4txt != null : "fx:id=\"answer4txt\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";
		assert select1btn != null : "fx:id=\"select1btn\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";
		assert select3btn != null : "fx:id=\"select3btn\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";
		assert select2btn != null : "fx:id=\"select2btn\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";
		assert select4btn != null : "fx:id=\"select4btn\" was not injected: check your FXML file 'shoquestionlistsubject.fxml'.";

		FillTheQuestions(questionNum);

	}

}