package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.bytebuddy.asm.Advice.This;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.criteria.CriteriaBuilder.Case;

import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
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
	private static String UserInfo;
	private String UserId;
    private String whatiam;//TeatcherORManger
    static Exam exam = new Exam();
 
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
	
	
	public static void showPrimaryView(Stage stage1) throws IOException
	{
		stage = stage1;
		scene = new Scene(loadFXML("primary"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("HSTS");
		stage.show();
		

	}

	
	
	

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
		whatiam=arr[2];
		SimpleClient.getClient().handleLoginIn(arr);

	}
	
    
	public void LogOut() throws IOException {
		String[] arr=new String[1];
		System.out.println("username =" + UserInfo);
		arr[0] = UserInfo;
		SimpleClient.getClient().handleLogOut(arr);

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
		else if (arr[2].equalsIgnoreCase("submitted")) {
			addingTextToCodeOrId("loginexam.fxml", "This exam already submitted.");
		}
		if (arr[0].equalsIgnoreCase("true")&&arr[2].equalsIgnoreCase("true")) {
			if(!(arr[1].equalsIgnoreCase(this.UserId))){
				addingTextToCodeOrId("loginexam.fxml", "Incompetable ID.");
			}else {
				//7sb alcode if mmo7szss
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
			case("This exam already submitted."):
				((LoginExamController) controller).getWrongId().setText("This exam already submitted.");
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

	

	public void showTeacherView() throws IOException {
		scene = new Scene(loadFXML("teacher"), 600, 400);
		stage.setScene(scene);
        stage.setTitle("Teacher Actions");
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
        stage.setTitle("Manager Actions");
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
        stage.setTitle("Adding Questions");
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
	public void StartExamAnswer() throws IOException {/////////////////sending exam to the controller to show it
		

//		if(msg.getExamExecutaion().equals("true")) {
//			System.out.println("bbbbbbbbbbb");
//			scene = new Scene(loadFXML("examexecutintg"), 600, 400);//mmo7shav
//			stage.setScene(scene);
//			stage.show();
//		}else {
			System.out.println("ccccccccccc");
			System.out.println("dddddddddd");
			scene = new Scene(loadFXML("examexecutintg"), 600, 400);//ydne
			System.out.println("eeeeeee");
			stage.setScene(scene);
			System.out.println("ffffffffffff");
			stage.show();
			System.out.println("hhhhhhhhhhhh");
//		}
		
		
	}
	

	

    
    public void startAgain() throws IOException {
		scene = new Scene(loadFXML("primary"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("HSTS");
		stage.show();
		
	}
    
  /*  public void showlastStageView() throws IOException {
		scene = new Scene(loadFXML("laststage"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("HSTS");
		stage.show();
		
	}
    */
    public void checkCourses(int stage) throws IOException {
		

		SimpleClient.getClient().handlecheckCourses(stage,UserInfo);
	}
    
    public void showQuestionListnView() throws IOException {
		scene = new Scene(loadFXML("shoquestionlist"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("Showing Questions");
		stage.show();
		
	}
    
    public void showingTheQuestions() throws IOException {
		scene = new Scene(loadFXML("shoquestionlistsubject"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("Showing Questions");
		stage.show();
		
	}


	public void showCreateExamView() throws IOException {
		scene = new Scene(loadFXML("exams"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("Creating Exam");
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
    
	public static void main(String[] args) throws IOException {
		launch();
		
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
    
    public void bringExams(int courseId) throws IOException {
		
		SimpleClient.getClient().handleBringingExamsInfo(courseId);

		
	}


	public void ShowingSelectedExamInfo() throws IOException {
		scene = new Scene(loadFXML("showingselectedexaminfo"), 600, 400);
		stage.setScene(scene);
		stage.setTitle("showing selected exam info");
		stage.show();

		
	}
   public void bringselectedexam(int chosenExamID) throws IOException {
		SimpleClient.getClient().handlebringselectedexam(chosenExamID);
		
	}


	public void showSelectedExamQuestion(Exam exam, Object[] examInfoObjects) throws IOException {

		this.exam = exam;
		
	  	
		ShowingSelectedExamQuestionController showingSelectedExamQuestionController = new ShowingSelectedExamQuestionController(this.exam,examInfoObjects,whatiam);

		scene = new Scene(loadFXML("showingselectedexamquestions"), 600, 400);
		stage.setScene(scene);

		stage.setTitle("showing selected exam info");

		stage.show();

		
	}

	public void savingtheeditedexam(Object[] examInfoObjects1) throws IOException {
		examInfoObjects1[2] = UserInfo;
		SimpleClient.getClient().handlesavingtheeditedexam(examInfoObjects1);
		
	}


	public void allsubjectforManager() throws IOException {

	
		SimpleClient.getClient().handleBringingallsubjectsformanager();

	}
  
	public void savingthesolvedexam(Exam exam, int[] choosenAswers, Boolean shefinished) throws IOException {
		SimpleClient.getClient().handlesavingthesolvedexam(exam,choosenAswers,shefinished,UserInfo);
		scene = new Scene(loadFXML("student"), 600, 400);
		stage.setScene(scene);
		stage.show();
	}

	public void showwhatiamView() throws IOException {

		switch (whatiam) {
		case ("Teacher"):
		
			showTeacherView();
			break;

		case ("Manager"):
			showManagerView();
			break;

		}
		
	}


	public void BringingallCoursedformanager() throws IOException {
		SimpleClient.getClient().handleBringingallCoursedformanager();
	}


}