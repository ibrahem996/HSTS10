package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.solvedExam;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class ShowGradesController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView welcomesign;

	@FXML
	private ListView<String> list;

	@FXML
	private Button backbtn;
	static List<solvedExam> solvedexams;

	public ShowGradesController(List<solvedExam> solvedexams) {
		this.solvedexams = solvedexams;
	}

	public ShowGradesController() {

	}

	@FXML
	void OnBack(ActionEvent event) {
		try {
			App.getInstance().showBackToStudentView();
		} catch (IOException e) {
			// TOD Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void initialize() {
		assert welcomesign != null : "fx:id=\"welcomesign\" was not injected: check your FXML file 'showgrades.fxml'.";
		assert list != null : "fx:id=\"list\" was not injected: check your FXML file 'showgrades.fxml'.";
		assert backbtn != null : "fx:id=\"backbtn\" was not injected: check your FXML file 'showgrades.fxml'.";
		String fullInfoString;
		for (solvedExam solved : solvedexams) {

			fullInfoString = "Course name: " + solved.getExam().getCourse().getCourseName() + ", Grade: "
					+ Integer.toString(solved.getGrade());
			list.getItems().addAll(fullInfoString);
		}

	}
}
