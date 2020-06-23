package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import il.cshaifasweng.OCSFMediatorExample.entities.Question;

/**
 * JavaFX App
 */
public class App extends Application { //// ask le2el how to make an column dissapear from database sql
										//// REMEMBER CLOSE STAGE

	private static App app;
	private static Scene scene;
	private SimpleClient client;
	private Stage stage;
	private String UserInfo;

	public static App getInstance() {
		if (app == null)
			app = new App();
		return app;
	}

	@Override
	public void start(Stage stage) throws IOException {
		this.stage = stage;
		client = SimpleClient.getClient();
		client.openConnection();
		showPrimaryView();

	}

	public void showPrimaryView() throws IOException {
		scene = new Scene(loadFXML("primary"), 600, 400);
		this.stage.setScene(scene);
		this.stage.setTitle("HSTS");
		this.stage.show();

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

	public void checkSubject() throws IOException {
		SimpleClient.getClient().handlecheckSubject(UserInfo);
	}

	public void AddQuestion(Question question) throws IOException {
		SimpleClient.getClient().handleAddQuestion(question);

	}

	public void showTeacherView() throws IOException {
		this.stage = new Stage();
		scene = new Scene(loadFXML("teacher"), 600, 400);
		this.stage.setScene(scene);
		this.stage.show();
	}

	public void showStudentView() throws IOException {
		this.stage = new Stage();
		scene = new Scene(loadFXML("student"), 600, 400);
		this.stage.setScene(scene);
		this.stage.show();
	}

	public void showManagerView() throws IOException {
		this.stage = new Stage();
		try {
			scene = new Scene(loadFXML("manager"), 600, 400);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.stage.setScene(scene);
		this.stage.show();
	}

	public void showExecutelogView() throws IOException {
		this.stage = new Stage();
		scene = new Scene(loadFXML("examexec"), 600, 400);
		this.stage.setScene(scene);
		this.stage.show();
	}

	public void showAddQuestionView() throws IOException {
		this.stage = new Stage();
		scene = new Scene(loadFXML("addquestion"), 600, 400);
		this.stage.setScene(scene);
		this.stage.show();
	}

	public static void main(String[] args) {
		launch();
	}


}