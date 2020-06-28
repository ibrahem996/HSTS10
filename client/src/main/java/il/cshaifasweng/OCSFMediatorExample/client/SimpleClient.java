package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;

import il.cshaifasweng.OCSFMediatorExample.Commands.CommandType;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
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
	private List<Subject> subjects = new ArrayList<Subject>();
	private List<Course> courses = new ArrayList<Course>();
	private Object[] examInfoObjects = new Object[9];
	private String  whatiam;//TeatcherORManger

	private SimpleClient(String host, int port) {
		super(host, port);
		commandRequest = false;
	}

	public void handleLoginIn(String[] arr) throws IOException {
		commandRequest = true;
		whatiam=arr[2];//TeatcherORManger
		command = new Command(arr, CommandType.loginCommand);
		SimpleClient.getClient().sendToServer(command);
		waitForServerResponse();
		handleLogininCommandFromServer();

	}

	public void handleLogOut(String[] arr) throws IOException {
		commandRequest = true;
		command = new Command(arr, CommandType.logoutCommand);
		SimpleClient.getClient().sendToServer(command);
		waitForServerResponse();
		handleLogoutCommandFromServer();

	}

	public void handleLoginToExam(String[] arr) throws IOException {
		commandRequest = true;
		command = new Command(arr, CommandType.loginExamCommand);
		SimpleClient.getClient().sendToServer(command);
		waitForServerResponse();
		handleLoginToExamCommandFromServer();

	}

	public void handleStartExam(String[] arr) throws IOException {
		commandRequest = true;
		command = new Command(arr, CommandType.startExamCommand);
		SimpleClient.getClient().sendToServer(command);
		waitForServerResponse();
		handleStartExamCommandFromServer();

	}

	private void handleStartExamCommandFromServer() throws IOException {
		System.out.println("handleStartExam111111111");
		Exam msg = (Exam) command.getCommand();
		App.getInstance().StartExamAnswer(msg);

	}

	private void handleLoginToExamCommandFromServer() throws IOException {
		System.out.println("handleStartExam111111111");
		String[] msg = (String[]) command.getCommand();
		System.out.println(msg[0]);
		System.out.println(msg[1]);

		App.getInstance().checkStartExamAnswer(msg);

	}

	private void handleLogoutCommandFromServer() throws IOException {
		App.getInstance().showBackToPrimaryView();
	}

	private void handleLogininCommandFromServer() throws IOException {
		System.out.println("handleSignin10");
		String[] msg = (String[]) command.getCommand();
		
		System.out.println(msg[0]);
		System.out.println(msg[1]);
		System.out.println("msg[1]= " + msg[1]);
		switch (msg[1]) {

		case ("student"):
			System.out.println("tjrebeeee333333");
			App.getInstance().showStudentView(msg);
			break;

		case ("teacher"):
			App.getInstance().showTeacherView();
			break;

		case ("manager"):
			App.getInstance().showManagerView();
			break;

		}

		/*
		 * if(msg[0].equalsIgnoreCase("true")) {
		 * System.out.print("HEREEEEEEEEEEs the 7mar");
		 * if(msg[1].equalsIgnoreCase("student")) { //app.showStudentInterface(); } else
		 * if(msg[1].equalsIgnoreCase("teacher")) {
		 * System.out.print("HEREEEEEEEEEEs the teacher1"); app.showTeacherInterface();
		 * } else if(msg[1].equalsIgnoreCase("manager")) { app.showManagerInterface(); }
		 * 
		 * } else { }
		 */

		System.out.println("loginCommand");
		// client.setInfo("username", msg);

	}

	public void handlecheckSubject(String userInfo) throws IOException {
		commandRequest = true;
		command = new Command(userInfo, CommandType.checkSubjectCommand);
		SimpleClient.getClient().sendToServer(command);
		waitForServerResponse();
		handlecheckSubjectCommandFromServer();

	}

	public void handlecheckSubject(String userInfo, int i) throws IOException {
		commandRequest = true;
		command = new Command(userInfo, CommandType.checkSubjectCommand);
		SimpleClient.getClient().sendToServer(command);
		waitForServerResponse();
		handlecheckSubjectCommandFromServer(i);

	}

	@SuppressWarnings("unchecked")
	private void handlecheckSubjectCommandFromServer(int i) throws IOException {
		List<Subject> subjects = new ArrayList<Subject>();
		subjects = (List<Subject>) command.getCommand();
		this.subjects = subjects;
		System.out.print(subjects.get(0).getName());
		if (i == 2) {
			AddQuestionController addQuestionController = new AddQuestionController(subjects);
			App.getInstance().showAddQuestionView();
		} else if (i == 1) {

			ShowQuestionsListController showQuestionsListController = new ShowQuestionsListController(subjects);
			App.getInstance().showQuestionListnView();

		}

	}

	public void handlecheckCourses(int stage, String userInfo) throws IOException {
		commandRequest = true;
		command = new Command(userInfo, CommandType.checkCourseCommand);
		SimpleClient.getClient().sendToServer(command);
		this.stage = stage;
		waitForServerResponse();
		handlecheckCoursesCommandFromServer();

	}

	private void handlecheckCoursesCommandFromServer() throws IOException {

		List<Course> courses = new ArrayList<Course>();
		courses = (List<Course>) command.getCommand();
		this.courses = courses;
		System.out.print(courses.get(0).getCourseName());
		if (stage == 0) {
			ExamsController examsController = new ExamsController(courses);

			App.getInstance().showCreateExamView();

		} else {

			ChooseCourseController chooseCourseController = new ChooseCourseController(courses);
			App.getInstance().chooseCourseController();
		}
	}

	@SuppressWarnings("unchecked")
	private void handlecheckSubjectCommandFromServer() throws IOException {
		List<Subject> subjects = new ArrayList<Subject>();
		subjects = (List<Subject>) command.getCommand();
		System.out.print(subjects.get(0).getName());
		AddQuestionController addQuestionController = new AddQuestionController(subjects);//TODO this var is not used!!
		App.getInstance().showAddQuestionView();

	}

	public void handleAddQuestion(Question newQuestion, int editing) throws IOException {
		commandRequest = true;
		command = new Command(newQuestion, CommandType.addQuestionCommand);
		SimpleClient.getClient().sendToServer(command);
		if (editing == 0) {
			waitForServerResponse();
			handleAddQuestionFromServer();
		}

	}

	public void handleAddQuestionFromServer() throws IOException {
		App.getInstance().showTeacherView();

	}

	public void handlebringQuestions(int i) throws IOException {
		commandRequest = true;
		command = new Command(i, CommandType.bringQuestionbySubjectCommand);
		SimpleClient.getClient().sendToServer(command);
		waitForServerResponse();
		handlebringQuestionsFromServer();

	}

	private void handlebringQuestionsFromServer() throws IOException {
		List<Object> allQuestions = new ArrayList<Object>();
		System.out.println("handlingbringingquestion from server");//ُTODO why the flowing variable is not used!!
		allQuestions = (List<Object>) command.getCommand();
		System.out.println (this.whatiam+"nhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

		ShowingListQuestionSubjectController showingListQuestionSubjectController = new ShowingListQuestionSubjectController(
				allQuestions, subjects,whatiam);// whatiam Manager or Teatcher 

		App.getInstance().showingTheQuestions();

	}

	@Override
	protected void handleMessageFromServer(Object msg) {
//		if (msg.getClass().equals(Warning.class)) {
//			EventBus.getDefault().post(new WarningEvent((Warning) msg));
//		}		
//		EventBus.getDefault().post("HHH");

		System.out.println("Handling message from server");
		command = (Command) msg;
		type = command.getType();
		System.out.println("Handling message from server");
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

	private void waitForServerResponse() {
		System.out.println(commandRequest);
		while (commandRequest) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void handlebringquestionsforspecialcoursebysubject(Object[] examInfoObjects) throws IOException {

		this.examInfoObjects = examInfoObjects;
		Course course = (Course) examInfoObjects[1];
		commandRequest = true;
		int idsubjectofspecialcourse = course.getSubject().getId();
		command = new Command(idsubjectofspecialcourse, CommandType.bringquestionsforspecialcoursebysubjectCommand);
		SimpleClient.getClient().sendToServer(command);
		waitForServerResponse();
		handlebringQuestionsbycourseFromServer();
	}

	private void handlebringQuestionsbycourseFromServer() throws IOException {

		List<Object> allQuestions = new ArrayList<Object>();
		System.out.println("handlingbringingquestionbycourse from server");
		allQuestions = (List<Object>) command.getCommand();

		ChoosingQuestionController choosingQuestionController = new ChoosingQuestionController(allQuestions,
				examInfoObjects);

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

		App.getInstance().showTeacherView();
	}

	public void handleBringingExamsInfo(int courseId) throws IOException {
		commandRequest = true;
		command = new Command(courseId, CommandType.bringExamInfoCommand);
		SimpleClient.getClient().sendToServer(command);
		waitForServerResponse();
		handleBringingExamsInfoFromServer();

	}

	private void handleBringingExamsInfoFromServer() throws IOException {

		List<Object> examsInfoList = new ArrayList<Object>();
		System.out.println("handleBringingExamsInfoFromServer from server");
		examsInfoList = (List<Object>) command.getCommand();

		DisplayExamsController displayExamsController = new DisplayExamsController(examsInfoList,whatiam);

		App.getInstance().displayExamView();

	}

	public void handleGivingCodeToExam(int chosenintger, char[] choseCode, int examExecutaion) throws IOException {
		commandRequest = true;
		List<Object> CodeInfoExam = new ArrayList<Object>();
		CodeInfoExam.add(chosenintger);
		CodeInfoExam.add(choseCode);
		CodeInfoExam.add(examExecutaion);
		command = new Command(CodeInfoExam, CommandType.CreatingCodeCommand);
		SimpleClient.getClient().sendToServer(command);
		waitForServerResponse();
		handleGivingCodeToExamFromServer();
	}

	private void handleGivingCodeToExamFromServer() throws IOException {
		App.getInstance().showTeacherView();
	}
	
	
	public void handlebringselectedexam(int chosenExamID) throws IOException {
		
		commandRequest = true;
        command = new Command(chosenExamID, CommandType.bringselectedexamCommand);
        SimpleClient.getClient().sendToServer(command);
        waitForServerResponse();
        handlebringselectedexamFromServer();
		
	}
	
	
	private void handlebringselectedexamFromServer() throws IOException {
		Exam exam = new Exam();
		 System.out.println("handlebringselectedexam from server");
				 exam =  (Exam) command.getCommand();
				 ShowingSelectedExamInfo showingSelectedExamInfo = new ShowingSelectedExamInfo(exam,whatiam);

				 
		App.getInstance().ShowingSelectedExamInfo();
		
	}
	public void handlesavingtheeditedexam(Object[] examInfoObjects1) throws IOException {
		commandRequest = true;
        command = new Command(examInfoObjects1, CommandType.savingtheeditedexamCommand);
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhh");
        SimpleClient.getClient().sendToServer(command);
        waitForServerResponse();
        handlesavingtheeditedexamFromServer();
		
	}
	private void handlesavingtheeditedexamFromServer() throws IOException {
		
		App.getInstance().showTeacherView();
	}

	public void handleBringingallsubjectsformanager() throws IOException {
		commandRequest = true;
        command = new Command(null, CommandType.bringingallsubjectformanagerCommand);
        SimpleClient.getClient().sendToServer(command);
        waitForServerResponse();
        handleBringingallsubjectsformanagerFromServer();
		
	}

	private void handleBringingallsubjectsformanagerFromServer() throws IOException {
		List<Subject> subjects = new ArrayList<Subject>();
		subjects = (List<Subject>) command.getCommand();
		this.subjects = subjects;
		System.out.print(subjects.get(0).getName());
		

			ShowQuestionsListController showQuestionsListController = new ShowQuestionsListController(subjects);
			App.getInstance().showQuestionListnView();

		}
		
	
	public void handleBringingallCoursedformanager() throws IOException {
		commandRequest = true;
        command = new Command(null, CommandType.bringingallCoursesformanagerCommand);
        SimpleClient.getClient().sendToServer(command);
        waitForServerResponse();
        handleBringingallCoursesformanagerFromServer();
		
	}

	private void handleBringingallCoursesformanagerFromServer() throws IOException {
		List<Course> courses = new ArrayList<Course>();
		courses = (List<Course>) command.getCommand();
		this.courses = courses;
		
		ChooseCourseController chooseCourseController = new ChooseCourseController(courses);
			App.getInstance().chooseCourseController();

	}
	
	
}
