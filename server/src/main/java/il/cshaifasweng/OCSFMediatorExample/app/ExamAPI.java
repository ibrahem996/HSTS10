package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.protobuf.StringValue;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.databaseinitilize.InitlizeDataBase;
import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import il.cshaifasweng.OCSFMediatorExample.entities.solvedExam;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class ExamAPI {

	public static Statement connectionToDB() throws SQLException {
		String url = "jdbc:mysql://127.0.0.1/hstsdatabase";
		String name = "root";
		String pass = "9064";
		Connection myConnection = DriverManager.getConnection(url, name, pass);
		Statement stmt = (Statement) myConnection.createStatement();
		return stmt;
	}

	public static List<Question> questionfromidtopbject(List<Integer> questionsId) {
		int questionid;
		List<Question> questionsobjects = new ArrayList<Question>();
		for (int i = 0; i < questionsId.size(); i++) {
			Question questionobjet = new Question();
			questionid = questionsId.get(i);
			questionobjet = questionobjet.getQuestionbyId(questionid);
			questionsobjects.add(questionobjet);
			System.out.println(questionobjet.getQuestion());
		}

		return questionsobjects;

	}

	public static void SavingTheExam(Command command, ConnectionToClient client) throws SQLException {

		double duration;

		Course course;
		String userInfo;
		String generalCommentTeacher;
		String generalCommentStudent = null;
		List<Double> gradesDoubles = new ArrayList<Double>();
		List<Question> questionsobjects = new ArrayList<Question>();

		List<Integer> questionsId = new ArrayList<Integer>();
		List<String> commentTeacher = new ArrayList<String>();
		List<String> commentStudent = new ArrayList<String>();

		Statement stmt = connectionToDB();

		Object[] examsInfo = new Object[9];

		examsInfo = (Object[]) command.getCommand();

		generalCommentTeacher = (String) examsInfo[3];

		userInfo = (String) examsInfo[2];

		course = (Course) examsInfo[1];

		String str = examsInfo[0].toString();
		double d = Double.valueOf(str).doubleValue();

		duration = d;

		generalCommentStudent = (String) examsInfo[4];
		gradesDoubles = (List<Double>) examsInfo[5];

		questionsId = (List<Integer>) examsInfo[6];
		commentStudent = (List<String>) examsInfo[7];
		commentTeacher = (List<String>) examsInfo[8];


		Teacher teacher = new Teacher();
		Exam exam = new Exam();
		teacher = teacher.getTeacherByuserName(userInfo);

		questionsobjects = questionfromidtopbject(questionsId);

		String foundType = null;

		String sql1 = "SELECT number FROM course WHERE id = '" + course.getId() + "'";
		ResultSet rs = stmt.executeQuery(sql1);
		if (rs.next()) {
			foundType = rs.getString(1);
			System.out.println(foundType);

		}

		int foundValue = Integer.parseInt(foundType);
		++foundValue;
		System.out.println(foundValue);

		String sql2 = "UPDATE course SET number = '" + foundValue + "' WHERE id ='" + course.getId() + "'";
		stmt.executeUpdate(sql2);

		System.out.println(course.getId() * 100 + course.getSubject().getId() * 10000 + foundValue);


		exam.setId(course.getId() * 100 + course.getSubject().getId() * 10000 + foundValue);


		System.out.println(exam.getId());

		exam.setDuration(duration);
		exam.setCourse(course);
		exam.setCreatedByteacher(teacher);
		exam.setGeneralCommentTeacher(generalCommentTeacher);
		exam.setGeneralCommentStudent(generalCommentStudent);
		exam.setGrades(gradesDoubles);
		exam.setQuestions(questionsobjects);
		exam.setStudentComment(commentStudent);
		exam.setTeacherComment(commentTeacher);

		System.out.println(course.getCourseName());

		System.out.println(teacher.getLastName());

		String sql = "INSERT INTO exam (id, code, duration, number, course_id,Onexecute,howMuchTimeToADD,timeRequest,teacher_id,examExecutaion,GeneralCommentStudent,GeneralCommentTeacher,executed ) VALUES ('"
				+ exam.getId() + "', " + "'" + null + "', " + "'" + duration + "', " + "'" + foundValue + "', " + "'"
				+ course.getId() + "', " + " 0, 0, 0 ,'" + teacher.getId() + "', " + " 1 , " + "'" + generalCommentStudent + "', "
				+ "'" + generalCommentTeacher + "', " + " 0 ) ";

		stmt.executeUpdate(sql);
		int i = 0;
		for (Question question : questionsobjects) {

			sql = "INSERT INTO exam_question (Exam_id,question_id ) VALUES ('" + exam.getId() + "', " + "'"
					+ question.getId() + "')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO exam_questiongrade (Exam_id,QuestionGrade ) VALUES ('" + exam.getId() + "', " + "'"
					+ gradesDoubles.get(i) + "')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO exam_studentcomment (Exam_id, StudentComment) VALUES ('" + exam.getId() + "', " + "'"
					+ commentStudent.get(i) + "')";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO exam_teachercomment (Exam_id, TeacherComment) VALUES ('" + exam.getId() + "', " + "'"
					+ commentTeacher.get(i) + "')";
			stmt.executeUpdate(sql);
			i++;

		}

		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void BringingExamInfo(Command command, ConnectionToClient client) throws SQLException {

		int id = (int) command.getCommand();

		System.out.println(id);

		Statement stmt = connectionToDB();

		String sql1 = "SELECT * FROM exam WHERE course_id = '" + id + "'";

		ResultSet rs = stmt.executeQuery(sql1);

		List<Object> examsInfoList = new ArrayList<Object>();

		while (rs.next()) {

			Object[] examInfoArray = new Object[3];

			int examID = rs.getInt(1);
			double duration = rs.getInt(5);
			int teacherID = rs.getInt(9);

			Teacher teacher = new Teacher();
			teacher = teacher.getTeacherByuserID(teacherID);
			String teacherFullName = teacher.getFirstName() + " " + teacher.getLastName();

			examInfoArray[0] = examID;
			examInfoArray[1] = duration;
			examInfoArray[2] = teacherFullName;

			examsInfoList.add(examInfoArray);

		}
		

		command.setCommand(examsInfoList);

		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void creatingCodeForExam(Command command, ConnectionToClient client) throws SQLException {

		Statement stmt = connectionToDB();

		List<Object> codeInfoObject = new ArrayList<Object>();

		char[] codeExam = new char[4];

		codeInfoObject = (List<Object>) command.getCommand();

		int examID = (int) codeInfoObject.get(0);

		codeExam = (char[]) codeInfoObject.get(1);

		int examExecutaion = (int) codeInfoObject.get(2);

		String codeEString = String.valueOf(codeExam);

		String sql2 = "UPDATE exam SET code = '" + codeEString + "' WHERE id ='" + examID + "'";
		stmt.executeUpdate(sql2);
		
		Statement stmt1 = connectionToDB();
		Statement stmt2 = connectionToDB();
		if (examExecutaion == 1)

		{
			System.out.println("comp");

			String sql3 = "UPDATE exam SET examExecutaion = 1 WHERE id ='" + examID + "'";
			stmt1.executeUpdate(sql3);
			

			String sql4 = "UPDATE exam SET executed = 1 WHERE id ='" + examID + "'";
			stmt2.executeUpdate(sql4);
			
			
		}

		else {
			System.out.println("manual");

			String sql3 = "UPDATE exam SET examExecutaion = 0 WHERE id ='" + examID + "'";
			stmt1.executeUpdate(sql3);
		}

		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void BringaSelectedExam(Command command, ConnectionToClient client) {
		List<Question> allexamQuestions = new ArrayList<Question>();
		List<Double> graded = new ArrayList<Double>();
		int id_examSelected = (int) command.getCommand();
		Exam exam = new Exam();
		exam = exam.getExamByID(id_examSelected);

		String questionString;
		String answer0String;
		String answer1String;
		String answer2String;
		String answer3String;
		double grade;
		String teatchercomment;
		String studentStringcomment;

		command.setCommand(exam);
		allexamQuestions = exam.getQuestions();
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

		// System.out.println(exam.getDuration());
		// System.out.println(exam.getGeneralCommentStudent());

		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void SavingTheSolvedExam(Command command, ConnectionToClient client) throws SQLException {
		// command[0]=exam;command[1]=answers;command[2]=finished;command[3]=studentusername

		Statement stmt = connectionToDB();
		List<Object> examsInfo = new ArrayList<Object>();
		examsInfo = (List<Object>) command.getCommand();
		Exam exam = null;
		exam = (Exam) examsInfo.get(0);
		int[] choosenAswers = new int[exam.getQuestions().size()];
		choosenAswers = (int[]) examsInfo.get(1);
		Boolean shefinished = (Boolean) examsInfo.get(2);
		String user = (String) examsInfo.get(3);
		Student student = new Student();
		System.out.println(user);
		student = student.getStudentByuserName(user);
		List<Integer> list = Arrays.stream(choosenAswers).boxed().collect(Collectors.toList());
		solvedExam solved = new solvedExam(exam, student, shefinished ,list);

		List<Question> questions = exam.getQuestions();
		
		int i = 0;
		
		int grade = 0;
		for (Question question : questions) {// calculating the grade
			if (choosenAswers[i] == question.getCorrectAnswer()) {
				grade += exam.getGrades().get(i);
			}
			i++;
		}
		solved.setGrade(grade);
		String sql;
		if (shefinished) {
			sql = "INSERT INTO solvedexam ( exam_id, student_id, checkedornot, shefinished, Grade,explainationForGradeChanging,generalCommentString) VALUES "
					+ "('" + exam.getId() + "', '" + student.getId() + "', 0 , 1, '" + grade + "', 0 , 0 )";
		} else {
			sql = "INSERT INTO solvedexam (exam_id, student_id, checkedornot, shefinished, Grade) VALUES ('"
					+ exam.getId() + "', '" + student.getId() + "', 0 , 0, '" + grade + "', 0 , 0 )";
		}

		stmt.executeUpdate(sql);
		sql = "SELECT * FROM solvedexam WHERE exam_id = '" + exam.getId() + "' AND student_id = '" + student.getId()
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int solved_id = rs.getInt("id");
		i=0;
		for (Question question : questions) {
//			sql = "INSERT INTO solvedexam_question (solvedExam_id, questionsSolved_id ) VALUES ('" + solved_id
//					+ "', '" + question.getId() + "')";
//			stmt.executeUpdate(sql);
							   
			sql = "INSERT INTO solvedexam_chosenanswers (solvedExam_id, chosenanswers ) VALUES ('" + solved_id + "', "
			+ "'" + choosenAswers[i] + "')";
			stmt.executeUpdate(sql);
			
//			sql = "INSERT INTO exam_questiongrade (Exam_id,QuestionGrade ) VALUES ('" + exam.getId() + "', " + "'"
//					+ gradesDoubles.get(i) + "')";
//			stmt.executeUpdate(sql);

			i++;
		}

		System.out.println("eeeeeeeeeeeee");
		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void AllExamstoShowResultsTeacher(Command command, ConnectionToClient client) {
	
		List <Exam> AllexamstoseeResults = new ArrayList<Exam>();
		String teacherusername = (String) command.getCommand();
		Exam exam = new Exam();
		AllexamstoseeResults = exam.getExamCreatedByTeacher(teacherusername);
		System.out.println(AllexamstoseeResults.get(0).getId());
		
		command.setCommand(AllexamstoseeResults);
		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void AllExamsforManagerResult(Command command, ConnectionToClient client)
	{
		List <Exam> AllexamstoseeResults = new ArrayList<Exam>();
		Exam exam = new Exam();
		AllexamstoseeResults = exam.getExamforManagerResult();
		//System.out.println(AllexamstoseeResults.get(2).getId());
		//System.out.println(AllexamstoseeResults.get(1).getId());
		System.out.println(AllexamstoseeResults.get(0).getId());
		
		command.setCommand(AllexamstoseeResults);
		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void displaySolvedExam(Command command, ConnectionToClient client) throws SQLException {        
		Statement stmt = connectionToDB();
		int examid = (int) command.getCommand();
		List <Object> solvedExamIinfo = new ArrayList<Object>();
		String sql1 = "SELECT * FROM solvedexam WHERE exam_id = '" + examid + "'";

		ResultSet rs = stmt.executeQuery(sql1);

		while (rs.next()) {
			int [] solvedexamInfoarray = new int [6];
			int id = rs.getInt(1);
			int studentID = rs.getInt(3);
			int Grade = rs.getInt(4);
			int checkornot = rs.getInt(5);
			int shefinished = rs.getInt(6);
			solvedexamInfoarray[0] = id;
			System.out.println(solvedexamInfoarray[0]);

			solvedexamInfoarray[1] = examid;
			System.out.println(solvedexamInfoarray[1]);

			solvedexamInfoarray[2] = studentID;
			System.out.println(solvedexamInfoarray[2]);

			solvedexamInfoarray[3] = Grade;
			System.out.println(solvedexamInfoarray[3]);

			solvedexamInfoarray[4] = checkornot;
			System.out.println(solvedexamInfoarray[4]);
			
			solvedexamInfoarray[5] = shefinished;
			System.out.println(solvedexamInfoarray[5]);

			solvedExamIinfo.add(solvedexamInfoarray);
		}
		
		command.setCommand(solvedExamIinfo);
		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		


		
	}
	
	public static void bringsSpecificSolvedExam(Command command, ConnectionToClient client) {
		System.out.println(" bringsSpecificSolvedExam function ");
		List<Question> allexamQuestions = new ArrayList<Question>();
		List<Double> graded = new ArrayList<Double>();
		Exam exam = new Exam();
		int solvedid = (int) command.getCommand();
		solvedExam solvedexam = new solvedExam();

		solvedexam = solvedexam.getsolvedExamBySolvedId(solvedid);
		
		exam=solvedexam.getExam();
   
		String questionString;
		String answer0String;
		String answer1String;
		String answer2String;
		String answer3String;
		double grade;
		String teatchercomment;
		String studentStringcomment;
		command.setCommand(solvedexam);
		
		allexamQuestions = solvedexam.getExam().getQuestions();
		grade=solvedexam.getGrade();
		
		System.out.println(grade);
		System.out.println(allexamQuestions.size());
		for (int i = 0; i < allexamQuestions.size(); i++) {
			System.out.println("nnnnnnnnnnnnnnnnnnnn");
			System.out.println(i);
			questionString = exam.getQuestions().get(i).getQuestion();
			System.out.println("nnnnnnnnnnnnnnnnnnnn");
			answer0String = allexamQuestions.get(i).getAnswers().get(0).getAnswer();
			System.out.println("nnnnnnnnnnnnnnnnnnnn");
			answer1String = allexamQuestions.get(i).getAnswers().get(1).getAnswer();
			answer2String = allexamQuestions.get(i).getAnswers().get(2).getAnswer();
			answer3String = allexamQuestions.get(i).getAnswers().get(3).getAnswer();
			System.out.println("nnnnnnnnnnnnnnnnnnnn");
		   
			
		//	teatchercomment = exam.getTeacherComment().get(i);
		//	studentStringcomment = exam.getStudentComment().get(i);
			// grade = exam.getGrades().get(i);

			  System.out.println(exam.getQuestions().get(i).getQuestion());
			  System.out.println(allexamQuestions.get(i).getAnswers().get(0).getAnswer());
			  System.out.println(allexamQuestions.get(i).getAnswers().get(1).getAnswer());
			  System.out.println(allexamQuestions.get(i).getAnswers().get(2).getAnswer());
			  System.out.println(allexamQuestions.get(i).getAnswers().get(3).getAnswer());
			//  System.out.println(allexamQuestions.get(i).getAnswers().get(3).getAnswer());


		}

		System.out.println(" bringsSpecificSolvedExam ffffffffffffffffunction ");
		
		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void confirmSolvedExam(Command command, ConnectionToClient client) throws SQLException {

		Statement stmt = connectionToDB();
		String [] solvedinfoStrings = (String[]) command.getCommand();
		String solvedidstring = solvedinfoStrings[0];
		int solvedID = Integer.parseInt(solvedidstring);	
		String gradedString = solvedinfoStrings[1];
		int graded = Integer.parseInt(gradedString);
		String generalcomentString = solvedinfoStrings[2];
		String explainedString = solvedinfoStrings[3];
		
		String sql1 = "UPDATE solvedexam SET explainationForGradeChanging = '" + explainedString + "' WHERE id ='" + solvedID + "'";
		stmt.executeUpdate(sql1);
		
		String sql2 = "UPDATE solvedexam SET Grade = '" + graded + "' WHERE id ='" + solvedID + "'";
		stmt.executeUpdate(sql2);
		
		String sql3 = "UPDATE solvedexam SET generalCommentString = '" + generalcomentString + "' WHERE id ='" + solvedID + "'";
		stmt.executeUpdate(sql3);
		
		String sql4 = "UPDATE solvedexam SET checkedornot = 1 WHERE id ='" + solvedID + "'";
		stmt.executeUpdate(sql4);
		
		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	public static void BringExamOnExecute(Command command, ConnectionToClient client) throws SQLException {
		Teacher teacher = new Teacher();
		String userinfoString = (String) command.getCommand();
		teacher = teacher.getTeacherByuserName(userinfoString);
		int teacherid = teacher.getId();
		String fullnameString = teacher.getFirstName() + " " + teacher.getLastName();
		Statement stmt = connectionToDB();

		String sql1 = "SELECT * FROM exam WHERE teacher_id = '" + teacherid + "' AND Onexecute = 1";

		ResultSet rs = stmt.executeQuery(sql1);
		
		List<Object[]> examsInfoList = new ArrayList<Object[]>();

		while (rs.next()) {

			Object[] examInfoArray = new Object[5];

			int examID = rs.getInt(1);
			double duration = rs.getInt(5);
			int executaion = rs.getInt(6);
			int courseid = rs.getInt(8);


			Course course = new Course();
			course = course.getCourseByID(courseid);
			String coursenameString = course.getCourseName();

			examInfoArray[0] = examID;
			examInfoArray[1] = duration;
			examInfoArray[2] = executaion;
			examInfoArray[3] = coursenameString;
			examInfoArray[4] = fullnameString;


			examsInfoList.add(examInfoArray);
		
		}
		

		command.setCommand(examsInfoList);

			try {
				client.sendToClient(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		

	}

	public static void addTimeForExam(Command command, ConnectionToClient client) throws SQLException {
		
		Statement stmt = connectionToDB();
		String [] examOnExecuteInfo = (String[]) command.getCommand();
		
		String examidString = examOnExecuteInfo[0];
		int examidID = Integer.parseInt(examidString);
		
		String timeString = examOnExecuteInfo[1];
		double time = Double.parseDouble(timeString);
		
		String sql1 = "UPDATE exam SET howMuchTimeToADD = '" + time + "' WHERE id ='" + examidID + "'";
		stmt.executeUpdate(sql1);
		
		String sql2 = "UPDATE exam SET timeRequest = 1 WHERE id ='" + examidID + "'";
		stmt.executeUpdate(sql2);
			
		command.setCommand(null);

		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public static void checkingAddtimeRequestManager(Command command, ConnectionToClient client) throws SQLException {
		Statement stmt = connectionToDB();

		String sql1 = "SELECT * FROM exam WHERE timeRequest = 1";
		ResultSet rs = stmt.executeQuery(sql1);

		Boolean resultcheckingAddtime = rs.next();
		
		System.out.println(resultcheckingAddtime);
		
		command.setCommand(resultcheckingAddtime);
		
		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void bringTimeRequestExamManager(Command command, ConnectionToClient client) throws SQLException {
		Statement stmt = connectionToDB();
		String sql1 = "SELECT * FROM exam WHERE timeRequest = 1";
		ResultSet rs = stmt.executeQuery(sql1);
		
		List<Object[]> examsInfoList = new ArrayList<Object[]>();


		while (rs.next()) {

			Object[] examInfoArray = new Object[5];

			int examID = rs.getInt(1);
			double duration = rs.getInt(5);
			int executaion = rs.getInt(6);
			int courseid = rs.getInt(8);
			int teachid = rs.getInt(9);

			Teacher teacher = new Teacher();
			teacher = teacher.getTeacherByuserID(teachid);
			String fullnameString = teacher.getFirstName() + " " + teacher.getLastName();

			Course course = new Course();
			course = course.getCourseByID(courseid);
			String coursenameString = course.getCourseName();

			examInfoArray[0] = examID;
			examInfoArray[1] = duration;
			examInfoArray[2] = executaion;
			examInfoArray[3] = coursenameString;
			examInfoArray[4] = fullnameString;


			examsInfoList.add(examInfoArray);
		
		}
		

		command.setCommand(examsInfoList);

			try {
				client.sendToClient(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void handleConfirmingtheaddingTimeByManager(Command command, ConnectionToClient client) throws SQLException {
		
				Statement stmt = connectionToDB();
				String examidstring = (String) command.getCommand();
				
				int examid = Integer.parseInt(examidstring);
				System.out.println(examid);
				
				String sql1 = "UPDATE exam SET timeRequest = 2 WHERE id ='" + examid + "'";
				stmt.executeUpdate(sql1);
				
				command.setCommand(null);

				try {
					client.sendToClient(command);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
}
	
