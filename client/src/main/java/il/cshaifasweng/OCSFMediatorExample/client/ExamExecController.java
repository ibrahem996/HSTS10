package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public class ExamExecController {

	public Text getWrongCode() {
		return WrongCode;
	}

	public void setWrongCode(String wrongCode) {
		WrongCode.setText(wrongCode);
	}

	public Text getWrongId() {
		return WrongId;
	}

	public static void setWrongId(String string) {
		WrongId.setText(string);
	}

	@FXML
	private TextField IDText;

	@FXML
	private TextField ExamCode;

	@FXML
	private Text WrongCode;

	@FXML
	private static Text WrongId;

	@FXML
	private Button StartButton;

	@FXML
	private Button BackID;

	@FXML
	void OnBack(ActionEvent event) {
		try {
			App.getInstance().showBackToStrudentView();
		} catch (IOException e) {
			// TOD Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void OnStart(ActionEvent event) {

		if (ExamCode.getText().length() != 4) {
			WrongCode.setText("The code should be 4 digits/letters.");
		}

		String[] arr = { IDText.getText(), ExamCode.getText() };

		try {
			App.getInstance().startExam(arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
