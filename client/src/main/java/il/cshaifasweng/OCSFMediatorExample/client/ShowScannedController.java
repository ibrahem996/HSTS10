package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.solvedExam;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ShowScannedController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button signoutbtn;

	@FXML
	private Button backbtn;

	@FXML
	private ListView<String> listViewid;// check if there is need to change it to SolvedExams

	@FXML
	private Button DisInfo;

	@FXML
	private TextField GradeChangeComment;

	@FXML
	private Button DisplaySelectedExambtn;

	@FXML
	private TextField GeneralComments;

	@FXML
	private Text Gradetxt;

	static List<solvedExam> solvedexams;

	public ShowScannedController(List<solvedExam> returnedsolvedexams) {
		solvedexams = returnedsolvedexams;
	}

	public ShowScannedController() {
		
	}
	@FXML
	void InDisInfo(ActionEvent event) {
		GeneralComments.setVisible(false);
		GradeChangeComment.setVisible(false);
		int index = listViewid.getSelectionModel().getSelectedIndex();
		solvedExam solved = solvedexams.get(index);
		if (solved.getGeneralCommentString() != null) {
			GeneralComments.setVisible(true);
			GeneralComments.setText("General comment: " + solved.getGeneralCommentString());
		}

		if (solved.getExplainationForGradeChanging() != null) {
			GradeChangeComment.setVisible(true);
			GradeChangeComment.setText("explanation: " + solved.getExplainationForGradeChanging());
		}
		Gradetxt.setVisible(true);
		Gradetxt.setText("Grade: " + Integer.toString(solved.getGrade()));
		DisplaySelectedExambtn.setVisible(true);

	}

	@FXML
	void OnBackBut(ActionEvent event) {
		try {
			App.getInstance().showBackToStudentView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void OnDisplaySelectedExam(ActionEvent event) {
		int index = listViewid.getSelectionModel().getSelectedIndex();
		solvedExam solved = solvedexams.get(index);
		try {
			App.getInstance().DisplaySelectedExams(solved);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	@FXML
	void initialize() {
		assert signoutbtn != null : "fx:id=\"signoutbtn\" was not injected: check your FXML file 'showscanned.fxml'.";
		assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'showscanned.fxml'.";
		assert listViewid != null : "fx:id=\"listViewid\" was not injected: check your FXML file 'showscanned.fxml'.";
		assert DisInfo != null : "fx:id=\"DisInfo\" was not injected: check your FXML file 'showscanned.fxml'.";
		assert GradeChangeComment != null : "fx:id=\"GradeChangeComment\" was not injected: check your FXML file 'showscanned.fxml'.";
		assert DisplaySelectedExambtn != null : "fx:id=\"DisplaySelectedExambtn\" was not injected: check your FXML file 'showscanned.fxml'.";
		assert GeneralComments != null : "fx:id=\"GeneralComments\" was not injected: check your FXML file 'showscanned.fxml'.";
		assert Gradetxt != null : "fx:id=\"Gradetxt\" was not injected: check your FXML file 'showscanned.fxml'.";

		String fullInfoString;
		for (solvedExam solved : solvedexams) {

			fullInfoString = "Course name: " + solved.getExam().getCourse().getCourseName();
			listViewid.getItems().addAll(fullInfoString);
		}

		GeneralComments.setVisible(false);
		GradeChangeComment.setVisible(false);
		Gradetxt.setVisible(false);
		DisplaySelectedExambtn.setVisible(false);
	}
}
