package il.cshaifasweng.OCSFMediatorExample.server;

import java.sql.SQLException;

import javax.persistence.criteria.CriteriaBuilder.Case;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.Commands.CommandType;
import il.cshaifasweng.OCSFMediatorExample.app.ExamAPI;
import il.cshaifasweng.OCSFMediatorExample.app.QuestionsAPI;
import il.cshaifasweng.OCSFMediatorExample.app.StartExamAPI;
import il.cshaifasweng.OCSFMediatorExample.app.checkCourseAPI;

import il.cshaifasweng.OCSFMediatorExample.app.checkSubjectAPI;
import il.cshaifasweng.OCSFMediatorExample.app.loginAPI;
import il.cshaifasweng.OCSFMediatorExample.app.loginExamAPI;
import il.cshaifasweng.OCSFMediatorExample.app.logoutAPI;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class SimpleServer extends AbstractServer { //////// remember change from create to update in hibernate

	public SimpleServer(int port) {
		super(port);

	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {

		System.out.println("Handling Message:");

		Command command = (Command) msg;
		CommandType type = command.getType();

		switch (type) {

		case loginCommand:

			System.out.println("loginCommand44444444444444444");
			// client.setInfo("username", msg);
			loginAPI.checkSpecificUser(command, client);
			System.out.println("Handling Message:");

			break;

		case loginExamCommand:
			System.out.println("start exam 1111111111111");
			loginExamAPI.checkSpecificUser(command, client);
			break;
			
		case checkSubjectCommand:

			System.out.println("checkCommand");
			checkSubjectAPI.checkSpecificSubjects(command, client);
			break;


		case addQuestionCommand:
			try {
				System.out.println("AddQuestionCommand");
				QuestionsAPI.AddSpecificQuestion(command, client);
				System.out.println("AddQuestionCommand2");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
          case checkCourseCommand:
				
            	System.out.println("checkCourseCommand");
            	checkCourseAPI.checkSpecificCourses(command, client);

				break;
          case bringQuestionbySubjectCommand:
            	
            	System.out.println("bringQuestionbySubjectCommand");
            	try {
					QuestionsAPI.bringQuestionsSubject(command, client);

					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            break;
            
            case bringquestionsforspecialcoursebysubjectCommand:
				
            	System.out.println("bringquestionsforspecialcoursebysubjectCommand");
            	
            	try {
					QuestionsAPI.bringQuestionsSubject(command, client);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
                
                	case savingTheExamCommand:
            	System.out.println("savingTheExamCommand");
            	
            	try {
					ExamAPI.SavingTheExam(command, client);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
		
		case startExamCommand:
			
			StartExamAPI.ReturnExam(command, client);
			break;
			
		case logoutCommand:
			logoutAPI.logout(command, client);
            break;
            	case bringExamInfoCommand:
            	System.out.println("bringExamInfoCommand");

				try {
					ExamAPI.BringingExamInfo(command,client);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
				
			case CreatingCodeCommand:
            	System.out.println("CreatingCodeCommand");

				try {
					ExamAPI.creatingCodeForExam(command,client);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;

	      case bringselectedexamCommand:
				
				System.out.println("bringselectedexamCommand");
				ExamAPI.BringaSelectedExam(command,client);
				break;
			case savingtheeditedexamCommand:
				
				try {
					System.out.println("savingtheeditedexamCommand");
					ExamAPI.SavingTheExam(command, client);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case bringingallsubjectformanagerCommand:
				
				System.out.println("bringingallsubjectformanager");
				checkSubjectAPI.bringallsubjectformanager(command, client);
				break;
			case bringingallCoursesformanagerCommand:
				System.out.println("bringingallCoursesformanagerCommand");
				checkCourseAPI.bringallCoursesformanager(command, client);
				break;
		}
	}

}