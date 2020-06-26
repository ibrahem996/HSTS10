package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.bytebuddy.asm.Advice.This;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;



/**
 * JavaFX App
 */
public class App extends Application {             ////remember update question
													
													//// REMEMBER CLOSE STAGE

	private static App app;
	private static Scene scene;
	private SimpleClient client;
	private static Stage stage;
	private String UserInfo;

	
	
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
	
	
	public static void showPrimaryView(Stage stage1) throws IOException
	{
		stage = stage1;
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
	
	public void checkSubject(int i) throws IOException
	{
		SimpleClient.getClient().handlecheckSubject(UserInfo,i);
	}
	
	public void AddQuestion(Question newQuestion,int editing) throws IOException {
		SimpleClient.getClient().handleAddQuestion(newQuestion,editing);
		
	}
	
	public void bringQuestions(int ChosencourseID) throws IOException {
		SimpleClient.getClient().handlebringQuestions(ChosencourseID);
		
	}
	public void bringquestionsforspecialcoursebysubject(Object[]examInfoObjects) throws IOException {
		
		examInfoObjects[2] = UserInfo;
		SimpleClient.getClient().handlebringquestionsforspecialcoursebysubject(examInfoObjects);
	}

	
	public void showTeacherView() throws IOException
	{
		scene = new Scene(loadFXML("teacher"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("Teacher Actions");
		stage.show();
	}
	
	public void showManagerView() throws IOException
	{
		this.stage = new Stage();
		try 
		{
			scene = new Scene(loadFXML("manager"), 600, 400);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.stage.setScene(scene);
		this.stage.setTitle("Manager Actions");
		this.stage.show(); 
	}
	
	public void showAddQuestionView() throws IOException
	{
		this.stage = new Stage();
		scene = new Scene(loadFXML("addquestion"), 600, 400);
		this.stage.setScene(scene);
		this.stage.setTitle("Adding Questions");
		this.stage.show();
	}

	public static void main(String[] args) {
		launch();
	}


	public void showlastStageView() throws IOException {
		this.stage = new Stage();
		scene = new Scene(loadFXML("laststage"), 600, 400);
		this.stage.setScene(scene);
		this.stage.setTitle("HSTS");
		this.stage.show();
		
	}


	public void checkCourses(int stage) throws IOException {
		

		SimpleClient.getClient().handlecheckCourses(stage,UserInfo);
	}


	public void showQuestionListnView() throws IOException {
		this.stage = new Stage();
		scene = new Scene(loadFXML("shoquestionlist"), 600, 400);
		this.stage.setScene(scene);
		this.stage.setTitle("Showing Questions");
		this.stage.show();
		
	}


	public void showingTheQuestions() throws IOException {
		this.stage = new Stage();
		scene = new Scene(loadFXML("shoquestionlistsubject"), 600, 400);
		this.stage.setScene(scene);
		this.stage.setTitle("Showing Questions");
		this.stage.show();
		
	}


	public void showCreateExamView() throws IOException {
		this.stage = new Stage();
		scene = new Scene(loadFXML("exams"), 600, 400);
		this.stage.setScene(scene);
		this.stage.setTitle("Creating Exam");
		this.stage.show();
		
	}


	public void startAgain() throws IOException {
		scene = new Scene(loadFXML("primary"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("HSTS");
		stage.show();
		
	}


	public void ShowingBuildingTheExam() throws IOException {
		scene = new Scene(loadFXML("choosingquestionstoexam"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("Building Exam");
		stage.show();
		
	}


	public void SavingTheExamWithQuestions(Object[] examInfoObjects1) throws IOException {
		
		SimpleClient.getClient().handleSavingTheExamWithQuestions(examInfoObjects1);
		
	}


	public void chooseCourseController() throws IOException {
		scene = new Scene(loadFXML("choosecourse"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("Choosing Course");
		stage.show();
		
	}


	public void bringExams(int courseId) throws IOException {
		
		SimpleClient.getClient().handleBringingExamsInfo(courseId);

		
	}


	public void displayExamView() throws IOException {
		scene = new Scene(loadFXML("displayexams"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("Displaying Exam");
		stage.show();
		
	}


	public void GivingCodeToExam(int chosenintger, char[] choseCode, int examExecutaion) throws IOException {
		
		SimpleClient.getClient().handleGivingCodeToExam(chosenintger,choseCode,examExecutaion);

	}


	


	

}