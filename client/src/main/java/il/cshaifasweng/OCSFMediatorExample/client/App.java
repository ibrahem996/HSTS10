package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
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
	private static String UserInfo;
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
		stage.setOnCloseRequest(event -> {
		    try {
				App.getInstance().LogOut();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

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
		UserInfo = arr[0];
		SimpleClient.getClient().handleLoginIn(arr);

	}
	
	public void LogOut() throws IOException {
		String[] arr=new String[1];
		System.out.println("username =" + UserInfo);
		arr[0] = UserInfo;
		SimpleClient.getClient().handleLogOut(arr);

	}

	public void startExam(String[] arr) throws IOException {
		this.UserId = arr[0];
		SimpleClient.getClient().handleLoginToExam(arr);
	}

	public void checkStartExamAnswer(String[] arr) throws IOException {

		if (arr[0].equalsIgnoreCase("false")) {
			
			addingTextToCodeOrId("loginexam.fxml", "Wrong ID.");
		

		}
		else if (arr[2].equalsIgnoreCase("false")) {
			addingTextToCodeOrId("loginexam.fxml", "Wrong Code.");
		}
		if (arr[0].equalsIgnoreCase("true")&&arr[2].equalsIgnoreCase("true")) {
			if(!(arr[1].equalsIgnoreCase(this.UserId))){
				addingTextToCodeOrId("loginexam.fxml", "Incompetable ID.");
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
		Object controller = null ;
		switch(fxm) {
		case("primary.fxml"):
			controller = (PrimaryController) fxmlLoader.getController();
			break;
		
		case("loginexam.fxml"):
			controller = (LoginExamController) fxmlLoader.getController();
			break;
		}
		
		switch(putthis){
			case("Wrong ID."):
				((LoginExamController) controller).getWrongId().setText("Wrong ID.");
			break;
			case("Wrong Code."):
				((LoginExamController) controller).getWrongCode().setText("Wrong Code.");
			break;
			case("Incompetable ID."):
				((LoginExamController) controller).getWrongId().setText("Incompetable ID.");
			break;
			case("false"):
				((PrimaryController) controller).getUserNameText().setText("Invalid input!");
			break;
			case("isconnected"):
				((PrimaryController) controller).getUserNameText().setText("Your account is already cnnected!");
			break;
		}
		
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
		if(msg[0].equalsIgnoreCase("false")) {
			addingTextToCodeOrId("primary.fxml","false");
		}
		else if  (msg[0].equalsIgnoreCase("isconnected")) {
			addingTextToCodeOrId("primary.fxml","isconnected");
		}else {
			scene = new Scene(loadFXML("student"), 600, 400);
			stage.setScene(scene);
			stage.show();
		}
		
	}

	public void showManagerView() throws IOException {
		scene = new Scene(loadFXML("manager"), 600, 400);
		stage.setScene(scene);
		stage.show();
	}

	public void showExecutelogView() throws IOException {
		scene = new Scene(loadFXML("loginexam"), 600, 400);
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
	public void StartExamAnswer(Exam msg) throws IOException {/////////////////sending exam to the controller to show it
		scene = new Scene(loadFXML("examexecuting"), 600, 400);
		stage.setScene(scene);
		stage.show();
		
	}

	public static void main(String[] args) throws IOException {
		launch();
		
	}
	

	

}