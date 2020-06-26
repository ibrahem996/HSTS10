package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.greenrobot.eventbus.EventBus; 


import il.cshaifasweng.OCSFMediatorExample.Commands.Command;

import il.cshaifasweng.OCSFMediatorExample.Commands.CommandType;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;
	private boolean commandRequest;
	private int stage;
    private CommandType type;
	private Command command;
	private List<Subject> subjects = new  ArrayList<Subject>();
	private List<Course> courses = new  ArrayList<Course>();
	private Object[] examInfoObjects = new Object [9];


	private SimpleClient(String host, int port) {
		super(host, port);
		commandRequest = false;
	}	
	public  void handleLoginIn(String [] arr) throws IOException
	{
			commandRequest = true;
	        command = new Command(arr, CommandType.loginCommand);
	        SimpleClient.getClient().sendToServer(command);
	        waitForServerResponse();
	        handleLogininCommandFromServer();

	}
	
	 private void handleLogininCommandFromServer() throws IOException
	{
	        System.out.println("handleLogin");
	     	String[] msg = (String[]) command.getCommand();
			System.out.println(msg[0]);
			System.out.println(msg[1]);
		
		  if(msg[0].equalsIgnoreCase("true")) 
		  {
		  if(msg[1].equalsIgnoreCase("student")) {
			  //app.showStudentInterface(); 
			  } 
			  
		  else if(msg[1].equalsIgnoreCase("teacher")) {
	 			App.getInstance().showTeacherView();
		  }
		  
		  else if(msg[1].equalsIgnoreCase("manager"))
		  { 
			  App.getInstance().showManagerView();
		  }
		   else {   
			  }
		  
		  }
	}
		 
	    
             //client.setInfo("username", msg);
       
	
	 public void handlecheckSubject(String userInfo, int i) throws IOException {
			commandRequest = true;
	        command = new Command(userInfo, CommandType.checkSubjectCommand);
	        SimpleClient.getClient().sendToServer(command);
	        waitForServerResponse();
	        handlecheckSubjectCommandFromServer(i);

		}
	
	
	@SuppressWarnings("unchecked")
	private void handlecheckSubjectCommandFromServer(int i) throws IOException
	 {
			List<Subject> subjects = new  ArrayList<Subject>();
			subjects = (List<Subject>) command.getCommand();
			this.subjects = subjects;
			System.out.print(subjects.get(0).getName());
			if(i==2) 
			{
		 	AddQuestionController addQuestionController = new AddQuestionController(subjects);
 			App.getInstance().showAddQuestionView();
			}
			else if(i==1) {
				
				ShowQuestionsListController showQuestionsListController = new ShowQuestionsListController(subjects);
	 			App.getInstance().showQuestionListnView();
				
			}

	 }
	
	public void handlecheckCourses(int stage,String userInfo) throws IOException {
		commandRequest = true;
        command = new Command(userInfo, CommandType.checkCourseCommand);
        SimpleClient.getClient().sendToServer(command);
        this.stage=stage;
        waitForServerResponse();
        handlecheckCoursesCommandFromServer();
     
        
	}
	
	private void handlecheckCoursesCommandFromServer() throws IOException
	{			
		

		List<Course> courses = new  ArrayList<Course>();
		courses = (List<Course>) command.getCommand();
		this.courses = courses;
		System.out.print(courses.get(0).getCourseName());
		if (stage==0)
		{
		ExamsController examsController = new ExamsController(courses);

		App.getInstance().showCreateExamView();

		}
		else {


			ChooseCourseController chooseCourseController = new ChooseCourseController(courses);
			App.getInstance().chooseCourseController();
		}
	}
	
	
	
	
	public void handleAddQuestion(Question newQuestion,int editing) throws IOException {
		commandRequest = true;
        command = new Command(newQuestion, CommandType.addQuestionCommand);
        SimpleClient.getClient().sendToServer(command);
        if (editing == 0)
        {
        	waitForServerResponse();
        	handleAddQuestionFromServer();
        }
        
		}
	
	public void handleAddQuestionFromServer() throws IOException {
			App.getInstance().showlastStageView();

		}
	
	public void handlebringQuestions(int i) throws IOException {
		commandRequest = true;
        command = new Command(i, CommandType.bringQuestionbySubjectCommand);
        SimpleClient.getClient().sendToServer(command);
        waitForServerResponse();
        handlebringQuestionsFromServer();
		
	}
	
	private void handlebringQuestionsFromServer() throws IOException {
		List<Object> allQuestions = new  ArrayList<Object>();
		 System.out.println("handlingbringingquestion from server");
		 allQuestions = (List<Object>) command.getCommand();

		 ShowingListQuestionSubjectController showingListQuestionSubjectController = new ShowingListQuestionSubjectController(allQuestions,subjects);

		 App.getInstance().showingTheQuestions();


		
	}
	@Override
	protected void handleMessageFromServer(Object msg) {

		 System.out.println("Handling message from server");
	     command = (Command) msg;
	     type = command.getType();
	     System.out.println("Command type: " + type.toString());
	     System.out.println(commandRequest);
	     commandRequest = false;
	     System.out.println(commandRequest);

	}
	
	
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}
	
	
	private void waitForServerResponse()
    {
        while (commandRequest)
        {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	public void handlebringquestionsforspecialcoursebysubject(Object[] examInfoObjects) throws IOException {
		
		this.examInfoObjects=examInfoObjects;
		Course course = (Course) examInfoObjects[1];
		commandRequest = true;
		int idsubjectofspecialcourse =  course.getSubject().getId();
        command = new Command(idsubjectofspecialcourse, CommandType.bringquestionsforspecialcoursebysubjectCommand);
        SimpleClient.getClient().sendToServer(command);
        waitForServerResponse();
        handlebringQuestionsbycourseFromServer();
	}
	private void handlebringQuestionsbycourseFromServer() throws IOException {
		
		List<Object> allQuestions = new  ArrayList<Object>();
		 System.out.println("handlingbringingquestionbycourse from server");
		 allQuestions = (List<Object>) command.getCommand();

		 ChoosingQuestionController choosingQuestionController = new ChoosingQuestionController(allQuestions,examInfoObjects);

		 App.getInstance().ShowingBuildingTheExam();
		
	}
	public void handleSavingTheExamWithQuestions(Object[] examInfoObjects1) throws IOException {
		commandRequest = true;
        command = new Command(examInfoObjects1, CommandType.savingTheExamCommand);
        SimpleClient.getClient().sendToServer(command);
        waitForServerResponse();
        handleSavingTheExamWithQuestionsFromServer();
		
	}
	
	private void handleSavingTheExamWithQuestionsFromServer() throws IOException {
		
		App.getInstance().showlastStageView();
	}
	
	public void handleBringingExamsInfo(int courseId) throws IOException {
		commandRequest = true;
        command = new Command(courseId, CommandType.bringExamInfoCommand);
        SimpleClient.getClient().sendToServer(command);
        waitForServerResponse();
        handleBringingExamsInfoFromServer();
		
	}
	private void handleBringingExamsInfoFromServer() throws IOException {

		 List<Object> examsInfoList = new  ArrayList<Object>();
		 System.out.println("handleBringingExamsInfoFromServer from server");
		 examsInfoList = (List<Object>) command.getCommand();

		 DisplayExamsController displayExamsController = new DisplayExamsController(examsInfoList);

		App.getInstance().displayExamView();
		
	}
	public void handleGivingCodeToExam(int chosenintger, char[] choseCode, int examExecutaion) throws IOException {
		commandRequest = true;
		List<Object> CodeInfoExam = new  ArrayList<Object>();
		CodeInfoExam.add(chosenintger);
	    CodeInfoExam.add(choseCode);
	    CodeInfoExam.add(examExecutaion);
        command = new Command(CodeInfoExam, CommandType.CreatingCodeCommand);
        SimpleClient.getClient().sendToServer(command);
        waitForServerResponse();
        handleGivingCodeToExamFromServer();
	}
	private void handleGivingCodeToExamFromServer() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	

}
