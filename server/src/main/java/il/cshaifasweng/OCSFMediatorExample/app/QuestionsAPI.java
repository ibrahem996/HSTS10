package il.cshaifasweng.OCSFMediatorExample.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.databaseinitilize.InitlizeDataBase;
import il.cshaifasweng.OCSFMediatorExample.entities.Answer;
import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Manager;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import il.cshaifasweng.OCSFMediatorExample.entities.checkedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.solvedExam;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class QuestionsAPI {																	//remember to add Update Question
	
	private static Session session;
	/*
	 * private static SessionFactory getSessionFactory() throws HibernateException {
	 * 
	 * Configuration configuration = new Configuration();
	 * configuration.addAnnotatedClass(Exam.class);
	 * configuration.addAnnotatedClass(Question.class);
	 * configuration.addAnnotatedClass(Answer.class);
	 * configuration.addAnnotatedClass(Subject.class);
	 * configuration.addAnnotatedClass(Course.class);
	 * configuration.addAnnotatedClass(Teacher.class);
	 * configuration.addAnnotatedClass(Student.class);
	 * configuration.addAnnotatedClass(Manager.class);
	 * configuration.addAnnotatedClass(checkedExam.class);
	 * configuration.addAnnotatedClass(solvedExam.class);
	 * 
	 * 
	 * 
	 * ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	 * .applySettings(configuration.getProperties()).build(); return
	 * configuration.buildSessionFactory(serviceRegistry);
	 * 
	 * }
	 */
	
	
	public static void AddSpecificQuestion(Command command,ConnectionToClient client) throws SQLException
	{
		//session = InitlizeDataBase.getSession();
        System.out.println("here is the AddQuestionCommand");
		int id;
		Calendar firstAppearance;
		String questionString;
		String answer;
		int question_id;
		int answer_id;
		int subject_id;
		String name,pass,url;
		
		
		/*
		 * url = "jdbc:mysql://127.0.0.1/hstsdatabase"; name = "root"; pass = "9064";
		 * Connection myConnection = DriverManager.getConnection(url,name,pass);
		 * Statement stmt = (Statement) myConnection.createStatement();
		 */
		 
        System.out.println("beginTransaction2");
	//	session.beginTransaction();  ///////////////
        
        Question newQuestion=new Question();
        newQuestion = (Question) command.getCommand();
        System.out.println(newQuestion.getQuestion());
        List<Answer> theAnswers = new ArrayList<Answer>();
        theAnswers = newQuestion.getAnswers();
        Answer answer1 = new Answer();
        answer1 = theAnswers.get(0);
        System.out.println(answer1.getAnswer());
        InitlizeDataBase.AddSpecificquestion(answer1);
        System.out.println(answer1.getAnswer());
        id = answer1.getId();
        answer = answer1.getAnswer();
        firstAppearance = Calendar.getInstance();
        question_id = newQuestion.getId();
		
		/*
		 * session.save(answer1); session.flush(); session.getTransaction().commit()
		 */;
        System.out.println("beginTransaction3");

        
        
        
		
		/*
		 * String sql =
		 * "INSERT INTO answer (id, answer, firstAppearance, question_id) VALUES ('" +id
		 * + "', " + "'" + answer + "', " + "'" + firstAppearance + "', " + "'" +
		 * question_id + "')"; stmt.executeUpdate(sql);
		 */
		 
        
		/*
		 * id = newQuestion.getId(); firstAppearnce = Calendar.getInstance();
		 * questionString = newQuestion.getQuestion(); answer_id =
		 * newQuestion.getCorrectAnswer().getId(); subject_id =
		 * newQuestion.getSubject().getId(); String sql =
		 * "INSERT INTO question (id, firstAppearance, questionString, answer_id, subject_id) VALUES ('"
		 * + id + "', " + "'" + firstAppearance + "', " + "'" + questionString + "', " +
		 * "'" + answer_id + "', " + "'" + subject_id + "')"; stmt.executeUpdate(sql);
		 */
        

	}
}
