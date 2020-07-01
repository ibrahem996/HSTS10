package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ManualExamController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button open;

	@FXML
	private Text timertxt;

	@FXML
	private Button Homebtn;

	@FXML
	private Button submittxt;

	public static Exam exam = new Exam();
	static int duration;
	static Boolean submitted;
	static File file;

	public ManualExamController(Exam exam1) {

		System.out.println("controllll");
		exam = exam1;
		duration = (int) exam.getDuration();
		submitted = false;
		System.out.println("enddddddddddcontrollll");
		file = new File("exam" + "_" + exam.getCourse().getCourseName() + ".doc");
	}

	public ManualExamController() {

	}

	@FXML
	void Onsubmit(ActionEvent event) {
		submitted = true;
		File file = new File("exam" + "_" + exam.getCourse().getCourseName() + ".doc");
//		try {
//			App.getInstance().savingtheuploadedexam(exam,file);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete the file");
		}

//		Frame frame = new Frame();
//		FileDialog dialog = new FileDialog(frame, "Select File to Open");
//		dialog.setMode(FileDialog.LOAD);
//		dialog.setVisible(true);
//		String file = dialog.getFile();
//		File[] file2 = dialog.getFiles(); 
//		File file3 = file2[0];
//		System.out.println(file + " chosen.");
//		try {
//			App.getInstance().savingtheuploadedexam(exam, file3);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		Homebtn.setVisible(true);
		submittxt.setVisible(false);
		timertxt.setText("Good Luck!!!");
	}

	@FXML
	void OnHome(ActionEvent event) {
		try {
			App.getInstance().showBackToStudentView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void OnOpen(ActionEvent event) {
		try {

			new Thread() {
				public void run() {
					try {

						if (!Desktop.isDesktopSupported())// check if Desktop is supported by Platform or not
						{
							System.out.println("not supported");
							return;
						}
						Desktop.getDesktop().open(new File("exam" + "_" + exam.getCourse().getCourseName() + ".doc"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	void initialize() {

		Homebtn.setVisible(false);

		assert open != null : "fx:id=\"open\" was not injected: check your FXML file 'manualexam.fxml'.";
		assert timertxt != null : "fx:id=\"timertxt\" was not injected: check your FXML file 'manualexam.fxml'.";
		assert Homebtn != null : "fx:id=\"Homebtn\" was not injected: check your FXML file 'manualexam.fxml'.";

		Task<Void> sleeper = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try {
					while (duration > 0) {
						timertxt.setText(Integer.toString(duration--) + " minutes left");
						Thread.sleep(60000);
					}
				} catch (InterruptedException e) {
				}
				return null;
			}
		};
		sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				try {
					if (!submitted) {
						App.getInstance().savingtheuploadedexam(exam, null);// activate this method & upload file to
																			// solvedexam entity
					}
					// App.getInstance().Onexec(exam.getId());
					Homebtn.setVisible(true);
					submittxt.setVisible(false);
					timertxt.setText("The time has ended!");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		new Thread(sleeper).start();

	}
}
