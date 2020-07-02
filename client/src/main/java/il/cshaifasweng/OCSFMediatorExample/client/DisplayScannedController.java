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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DisplayScannedController {

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
	private RadioButton Rad1;

	@FXML
	private RadioButton Rad3;

	@FXML
	private Text quesnum;

	@FXML
	private Text timertxt;

	@FXML
	private RadioButton Rad2;

	@FXML
	private RadioButton Rad4;

	@FXML
	private Button homebtn;

    @FXML
    private Text thecorrecttxt;

	public static Exam exam = new Exam();
	public static solvedExam solvedexam = new solvedExam();
	static int questionNum = 0;
	static int numofques;

	static List<Question> questions = new ArrayList<Question>();
	static List<Integer> ChoosenAswers;
	static List<Integer> CorrectAswers;

	public DisplayScannedController(solvedExam solved) {
		solvedexam = solved;
		exam = solved.getExam();
		questions = exam.getQuestions();
		ChoosenAswers = solved.getChosenAnswers();
		numofques = questions.size();

	}

	public DisplayScannedController() {

	}

	@FXML
	void Onhomebtn(ActionEvent event) {
		try {
			App.getInstance().showBackToStudentView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void OnRad1(ActionEvent event) {

	}

	@FXML
	void OnRad2(ActionEvent event) {

	}

	@FXML
	void OnRad3(ActionEvent event) {

	}

	@FXML
	void OnRad4(ActionEvent event) {

	}

	@FXML
	void backac(ActionEvent event) {
		Rad1.setSelected(false);
		Rad1.setText("");
		Rad2.setSelected(false);
		Rad2.setText("");
		Rad3.setSelected(false);
		Rad3.setText("");
		Rad4.setSelected(false);
		Rad4.setText("");
		questionNum-=1;
		quesnum.setText(questionNum + 1 + " / " + numofques);
		
		if (questionNum == 0) {
			backbtn.setVisible(false);
		}
		if (questionNum == questions.size() - 2) {
			nextbtn.setVisible(true);
			backbtn.setVisible(true);
		}

		FillTheQuestions(questionNum);
	}

	@FXML
	void nextac(ActionEvent event) {
		Rad1.setSelected(false);
		Rad1.setText("");
		Rad2.setSelected(false);
		Rad2.setText("");
		Rad3.setSelected(false);
		Rad3.setText("");
		Rad4.setSelected(false);
		Rad4.setText("");
		questionNum+=1;
		quesnum.setText(questionNum + 1 + " / " + numofques);
		if (questionNum == 1) {
			backbtn.setVisible(true);

		}
		if (questionNum == questions.size() - 1) {
			FillTheQuestions(questionNum);
			nextbtn.setVisible(false);
		} else {
			
			FillTheQuestions(questionNum);
		}
	}

	@FXML
	void signoutac(ActionEvent event) {
		try {
			App.getInstance().LogOut();
			App.getInstance().showBackToPrimaryView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void FillTheQuestions(int i) {

		questiontxt.setText(questions.get(i).getQuestion());
		answer1txt.setText(questions.get(i).getAnswers().get(0).getAnswer());
		answer2txt.setText(questions.get(i).getAnswers().get(1).getAnswer());
		answer3txt.setText(questions.get(i).getAnswers().get(2).getAnswer());
		answer4txt.setText(questions.get(i).getAnswers().get(3).getAnswer());
		thecorrecttxt.setVisible(false);
		fillthenewlists(i);

	}

	private void fillthenewlists(int qnumber) {
		int correct = questions.get(qnumber).getCorrectAnswer();
		Boolean flag=false;
		switch (correct) {
		case 1:
			if (ChoosenAswers.get(qnumber) == 1) {
				Rad1.setSelected(true);
				Rad1.setText("Correct!");
				flag=true;
			} 
			break;
		case 2:
			if (ChoosenAswers.get(qnumber) == 2) {
				Rad2.setSelected(true);
				Rad2.setText("Correct!");
				flag=true;

			} 
			break;
		case 3:
			if (ChoosenAswers.get(qnumber) == 3) {
				Rad3.setSelected(true);
				Rad3.setText("Correct!");
				flag=true;

			} 
			break;
		case 4:
			if (ChoosenAswers.get(qnumber) == 4) {
				Rad4.setSelected(true);
				Rad4.setText("Correct!");
				flag=true;

			} 
			break;
		}
		
		
		if(!flag) {
			switch(ChoosenAswers.get(qnumber)) {
			case 1:
				Rad1.setSelected(true);
				break;
			case 2:
				Rad2.setSelected(true);
				break;
			case 3:
				Rad3.setSelected(true);
				break;
			case 4:
				Rad4.setSelected(true);
				break;
			}
			thecorrecttxt.setVisible(true);
	        thecorrecttxt.setText("The correct answer is: "+ correct);
		}
	

	}

	@FXML
	void initialize() {

		Rad1.setSelected(false);
		Rad2.setSelected(false);
		Rad3.setSelected(false);
		Rad4.setSelected(false);
		Rad1.setDisable(true);
		Rad2.setDisable(true);
		Rad3.setDisable(true);
		Rad4.setDisable(true);
		
		
		assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert answer1txt != null : "fx:id=\"answer1txt\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert nextbtn != null : "fx:id=\"nextbtn\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert questiontxt != null : "fx:id=\"questiontxt\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert answer2txt != null : "fx:id=\"answer2txt\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert answer3txt != null : "fx:id=\"answer3txt\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert answer4txt != null : "fx:id=\"answer4txt\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert Rad1 != null : "fx:id=\"Rad1\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert Rad3 != null : "fx:id=\"Rad3\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert quesnum != null : "fx:id=\"quesnum\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert timertxt != null : "fx:id=\"timertxt\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert Rad2 != null : "fx:id=\"Rad2\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
		assert Rad4 != null : "fx:id=\"Rad4\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
        assert homebtn != null : "fx:id=\"homebtn\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";
        assert thecorrecttxt != null : "fx:id=\"thecorrecttxt\" was not injected: check your FXML file 'displaysacannedexam.fxml'.";

		questionNum = 0;
		quesnum.setText(questionNum + 1 + " / " + numofques);
		backbtn.setVisible(false);
		FillTheQuestions(questionNum);
	}
}
