package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class StartExamAPI {


	public static void ReturnExam(Command command, ConnectionToClient client) {
		String[] arr = new String[4];
		arr = (String[]) command.getCommand();
		String ID = arr[1];
		String Code = arr[3];
		String name, pass, url;
		url = "jdbc:mysql://127.0.0.1/hstsdatabase";
		name = "root";
		pass = "t12345";
		Exam exam = new Exam();
		Connection myConnection;
		try {
			myConnection = DriverManager.getConnection(url, name, pass);
			Statement stmt = (Statement) myConnection.createStatement();
			String sql = "SELECT * FROM exam WHERE code = '" + Code + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int exam_id = rs.getInt("id");
			Boolean onexec = rs.getBoolean("Onexecute");
			int duration = rs.getInt("duration");
			if(!onexec) {
				sql = "UPDATE exam SET Onexecute = 1 WHERE id = '" + exam_id + "'";
				stmt.executeUpdate(sql);
				Thread thread = new Thread() {
					public void run() {
						try {
							sleep(duration * 60000);
							String sql1 = "UPDATE exam SET Onexecute = 0 WHERE id = '" + exam_id + "'";
							String sql2 = "UPDATE exam SET code = -1 WHERE id = '" + exam_id + "'";//yes3ed rabak
							try {
								stmt.executeUpdate(sql1);
								
								stmt.executeUpdate(sql2);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				thread.start();
				
			}

			System.out.println("examidddddd = " + exam_id);
			exam = exam.getExamByID(exam_id);
			System.out.println("examidddddd22222222 = " + exam.getId());
			command.setCommand(exam);
			System.out.println("22222222222222222222");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("kkkkkkkkkkkkkkkk");
		System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
		List<Question> allexamQuestions = new ArrayList<Question>();
		List<Double> graded = new ArrayList<Double>();
		String questionString;
		String answer0String;
		String answer1String;
		String answer2String;
		String answer3String;
		double grade;
		String teatchercomment;
		String studentStringcomment;

		allexamQuestions = exam.getQuestions();
		int duration = (int) exam.getDuration();
		graded = exam.getGrades();
		for (int i = 0; i < allexamQuestions.size(); i++) {
			questionString = exam.getQuestions().get(i).getQuestion();
			answer0String = allexamQuestions.get(i).getAnswers().get(0).getAnswer();
			answer1String = allexamQuestions.get(i).getAnswers().get(1).getAnswer();
			answer2String = allexamQuestions.get(i).getAnswers().get(2).getAnswer();
			answer3String = allexamQuestions.get(i).getAnswers().get(3).getAnswer();

			teatchercomment = exam.getTeacherComment().get(i);
			studentStringcomment = exam.getStudentComment().get(i);
			grade = exam.getGrades().get(i);
			/*
			 * System.out.println(exam.getQuestions().get(i).getQuestion());
			 * System.out.println(allexamQuestions.get(i).getAnswers().get(0).getAnswer());
			 * System.out.println(allexamQuestions.get(i).getAnswers().get(1).getAnswer());
			 * System.out.println(allexamQuestions.get(i).getAnswers().get(2).getAnswer());
			 * System.out.println(allexamQuestions.get(i).getAnswers().get(3).getAnswer());
			 * System.out.println(exam.getTeacherComment().get(i));
			 * System.out.println(exam.getStudentComment().get(i));
			 * System.out.println(exam.getGrades().get(i));
			 */

		}

		

		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void checkifextra(Command command, ConnectionToClient client) throws SQLException {
		
		String name, pass, url;
		url = "jdbc:mysql://127.0.0.1/hstsdatabase";
		name = "root";
		pass = "t12345";
		Connection myConnection;
		myConnection = DriverManager.getConnection(url, name, pass);
		Statement stmt = (Statement) myConnection.createStatement();
		int exam_id= (int) command.getCommand();
		System.out.println("8744444444444444");
		String sql="SELECT * FROM exam WHERE id = '" + exam_id + "' AND timeRequest = 2";
		ResultSet rs = stmt.executeQuery(sql);
		int extra;
		if(rs.next()) {
			 extra = (int) rs.getDouble("howMuchTimeToADD");
		}else {
			extra=0;
		}
		
		command.setCommand(extra);

		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
