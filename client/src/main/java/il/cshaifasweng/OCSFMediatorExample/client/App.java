package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;

/**
 * JavaFX App
 */
public class App extends Application { //// ask le2el how to make an column dissapear from database sql
										//// REMEMBER CLOSE STAGE

	private static App app;
	private static Scene scene;
	private SimpleClient client;
	private static Stage stage;
	private String UserInfo;
	private String UserId;

	public static App getInstance() {
		if (app == null)
			app = new App();
		return app;
	}

	@Override
	public void start(Stage stage) throws IOException {
		
		client = SimpleClient.getClient();
		client.openConnection();
		showPrimaryView(stage);

	}

	public static void  showPrimaryView(Stage stage1) throws IOException {
		stage=stage1;
		scene = new Scene(loadFXML("primary"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("HSTS");
		stage.show();
		

	}

	/*
	 * public void closeStage(Stage stage) { stage.close(); }
	 */

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
	}

	public void LoginIn(String[] arr) throws IOException {
		this.UserInfo = arr[0];
		SimpleClient.getClient().handleLoginIn(arr);

	}

	public void startExam(String[] arr) throws IOException {
		this.UserInfo = arr[0];
		SimpleClient.getClient().handleLoginToExam(arr);
	}

	public void checkStartExamAnswer(String[] arr) throws IOException {

		if (arr[0].equalsIgnoreCase("false")) {
			
			addingTextToCodeOrId("examexec.fxml", "Wrong ID.");
		

		}
		else if (arr[2].equalsIgnoreCase("false")) {
			addingTextToCodeOrId("examexec.fxml", "Wrong Code.");
		}
		if (arr[0].equalsIgnoreCase("true")&&arr[2].equalsIgnoreCase("true")) {
			if(!(arr[1].equalsIgnoreCase(this.UserId))){
				addingTextToCodeOrId("examexec.fxml", "Incompetable ID.");
			}else {
				//7sb alcode if mmo7shav of ydne
				SimpleClient.getClient().handleStartExam(arr);
			}
			
		}

	}
	
	public void addingTextToCodeOrId(String fxm,String putthis) {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(Main.class.getResource(fxm));
		AnchorPane mainAnchor = null;
		try {
			mainAnchor = (AnchorPane) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExamExecController  controller = fxmlLoader.getController();
		switch(putthis){
			case("Wrong ID."):
				controller.getWrongId().setText("Wrong ID.");
			break;
			case("Wrong Code."):
				controller.getWrongCode().setText("Wrong Code.");
			break;
			case("Incompetable ID."):
				controller.getWrongId().setText("Incompetable ID.");
		}
		
		System.out.println(controller.getWrongId().getText());
		scene = new Scene(mainAnchor, 600, 400);
		stage.setScene(scene);
		stage.show();
	}

	public void checkSubject() throws IOException {
		SimpleClient.getClient().handlecheckSubject(UserInfo);
	}

	public void AddQuestion(Question question) throws IOException {
		SimpleClient.getClient().handleAddQuestion(question);

	}

	public void showTeacherView() throws IOException {
		//this.stage = new Stage();
		scene = new Scene(loadFXML("teacher"), 600, 400);
		stage.setScene(scene);
		stage.show();
	}

	public void showStudentView(String [] msg) throws IOException {
		this.UserId=msg[2];
		scene = new Scene(loadFXML("student"), 600, 400);
		stage.setScene(scene);
		stage.show();
		
	}

	public void showManagerView() throws IOException {
		scene = new Scene(loadFXML("manager"), 600, 400);
		stage.setScene(scene);
		stage.show();
	}

	public void showExecutelogView() throws IOException {
		scene = new Scene(loadFXML("examexec"), 600, 400);
		stage.setScene(scene);
		stage.show();
	}

	public void showGradesView() throws IOException {
		scene = new Scene(loadFXML("showgrades"), 600, 400);
		stage.setScene(scene);
		stage.show();
	}

	public void showScannedExam() throws IOException {
		scene = new Scene(loadFXML("showscanned"), 600, 400);
		stage.setScene(scene);
		stage.show();
	}
	
	public void showAddQuestionView() throws IOException {
		scene = new Scene(loadFXML("addquestion"), 600, 400);
		stage.setScene(scene);
		stage.show();
	}

	public void showBackToStudentView() throws IOException {
		scene = new Scene(loadFXML("student"), 600, 400);
		stage.setScene(scene);
		stage.show();
	}

	public void showBackToPrimaryView() throws IOException {
		scene = new Scene(loadFXML("primary"), 600, 400);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}