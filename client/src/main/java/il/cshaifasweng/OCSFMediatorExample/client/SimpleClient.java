package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;

import il.cshaifasweng.OCSFMediatorExample.Commands.CommandType;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;

public class SimpleClient extends AbstractClient {

	private static SimpleClient client = null;
	private boolean commandRequest;
	private CommandType type;
	private Command command;

	private SimpleClient(String host, int port) {
		super(host, port);
		commandRequest = false;
	}

	public void handleLoginIn(String[] arr) throws IOException {
		commandRequest = true;
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
		handleStartExamCommandFromServer();

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

	@SuppressWarnings("unchecked")
	private void handlecheckSubjectCommandFromServer() throws IOException {
		List<Subject> subjects = new ArrayList<Subject>();
		subjects = (List<Subject>) command.getCommand();
		System.out.print(subjects.get(0).getName());
		AddQuestionController addQuestionController = new AddQuestionController(subjects);
		App.getInstance().showAddQuestionView();

	}

	public void handleAddQuestion(Question question) throws IOException {
		commandRequest = true;
		command = new Command(question, CommandType.addQuestionCommand);
		SimpleClient.getClient().sendToServer(command);
		waitForServerResponse();
		handlecheckSubjectCommandFromServer();
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

}
