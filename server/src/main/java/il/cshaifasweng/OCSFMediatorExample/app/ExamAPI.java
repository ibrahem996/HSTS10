package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.File;
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
		String url = "jdbc:mysql://127.0.0.1/hsts";
		String name = "root";
		String pass = "t12345";
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
		/////////// attention if it doesnt work
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

		/////////// Everything Works ^^^

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

		System.out.println("HEHEHEHE");

		System.out.println(course.getId() * 100 + course.getSubject().getId() * 10000 + foundValue);

		System.out.println("HEHEHEHE1");

		exam.setId(course.getId() * 100 + course.getSubject().getId() * 10000 + foundValue);

		System.out.println("HEHEHEHE2");

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

		// course.addExam(exam);
		// teacher.addExamstoTeacher(exam);

		String sql = "INSERT INTO exam (id, code, duration, number, course_id,teacher_id,examExecutaion,GeneralCommentStudent,GeneralCommentTeacher,executed ) VALUES ('"
				+ exam.getId() + "', " + "'" + null + "', " + "'" + duration + "', " + "'" + foundValue + "', " + "'"
				+ course.getId() + "', " + "'" + teacher.getId() + "', " + " 1 , " + "'" + generalCommentStudent + "', "
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
		System.out.println("GDDFD");

		System.out.println(id);

		Statement stmt = connectionToDB();

		String sql1 = "SELECT * FROM exam WHERE course_id = '" + id + "'";

		ResultSet rs = stmt.executeQuery(sql1);

		List<Object> examsInfoList = new ArrayList<Object>();

		while (rs.next()) {

			Object[] examInfoArray = new Object[3];

			int examID = rs.getInt("id");
			int duration = rs.getInt("duration");
			int teacherID = rs.getInt("teacher_id");

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
		System.out.println("hhhhhhhhhhhhh");
		List<Object> examsInfo = new ArrayList<Object>();
		examsInfo = (List<Object>) command.getCommand();
		System.out.println("aaaaaaaaaaaa");
		Exam exam = null;
		exam = (Exam) examsInfo.get(0);
		int[] choosenAswers = new int[exam.getQuestions().size()];
		choosenAswers = (int[]) examsInfo.get(1);
		Boolean shefinished = (Boolean) examsInfo.get(2);
		String user = (String) examsInfo.get(3);
		Student student = new Student();
		System.out.println("bbbbbbbbbbbbbbbbb");
		System.out.println(user);
		student = student.getStudentByuserName(user);
		System.out.println("kkkkkkkkkkk");
		List<Integer> list = Arrays.stream(choosenAswers).boxed().collect(Collectors.toList());
		solvedExam solved = new solvedExam(exam, student, shefinished, list);

		List<Question> questions = exam.getQuestions();

		int i = 0;

		int grade = 0;
		for (Question question : questions) {// calculating the grade
			if (choosenAswers[i] == question.getCorrectAnswer()) {
				grade += exam.getGrades().get(i);
			}
			i++;
		}
		System.out.println("cccccccccccccccc");
		solved.setGrade(grade);
		String sql;
		System.out.println("iddddddddd" + solved.getId());
		if (shefinished) {
			sql = "INSERT INTO solvedexam ( exam_id, student_id, checkedornot, shefinished, Grade) VALUES ('"
					+ exam.getId() + "', '" + student.getId() + "', 0 , 1, '" + grade + "')";
			System.out.println("iddddddddd" + solved.getId());
		} else {
			sql = "INSERT INTO solvedexam (exam_id, student_id, checkedornot, shefinished, Grade) VALUES ('"
					+ exam.getId() + "', '" + student.getId() + "', 0 , 0, '" + grade + "')";
		}

		stmt.executeUpdate(sql);
		sql = "SELECT * FROM solvedexam WHERE exam_id = '" + exam.getId() + "' AND student_id = '" + student.getId()
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int solved_id = rs.getInt("id");
		System.out.println("solved_idddddddd = " + solved_id);
		i = 0;
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

		List<Exam> AllexamstoseeResults = new ArrayList<Exam>();
		String teacherusername = (String) command.getCommand();
		Exam exam = new Exam();
		AllexamstoseeResults = exam.getExamCreatedByTeacher(teacherusername);
		// System.out.println(AllexamstoseeResults.get(2).getId());
		// System.out.println(AllexamstoseeResults.get(1).getId());
		System.out.println(AllexamstoseeResults.get(0).getId());

		System.out.println("eeegggggggggeeeee");
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
		List<Object> solvedExamIinfo = new ArrayList<Object>();
		String sql1 = "SELECT * FROM solvedexam WHERE exam_id = '" + examid + "'";

		ResultSet rs = stmt.executeQuery(sql1);

		while (rs.next()) {
			int[] solvedexamInfoarray = new int[6];
			int id = rs.getInt(1);
			int studentID = rs.getInt(3);
			int Grade = rs.getInt(4);
			int checkornot = rs.getInt(5);
			int shefinished = rs.getInt(6);
			solvedexamInfoarray[0] = id;
			solvedexamInfoarray[1] = examid;
			solvedexamInfoarray[2] = studentID;
			solvedexamInfoarray[3] = Grade;
			solvedexamInfoarray[4] = checkornot;
			solvedexamInfoarray[5] = shefinished;

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

	public static void onexecute(Command command, ConnectionToClient client) throws SQLException {
		Statement stmt = connectionToDB();
		int exam_id = (int) command.getCommand();
		String sql = "UPDATE exam SET Onexecute = 0 WHERE id = '" + exam_id + "'";
		stmt.executeUpdate(sql);
		sql = "UPDATE exam SET code = -1 WHERE id = '" + exam_id + "'";
		stmt.executeUpdate(sql);
//		Exam exam = new Exam();
//		char[] nullarr = null;
//		exam = exam.getExamByID(exam_id);
//		exam.setOnexecute(false);
//		exam.setCode(nullarr);
	}

	public static void savingManualExam(Command command, ConnectionToClient client) throws SQLException {// exam file
																											// user
		// TODO Auto-generated method stub
		Statement stmt = connectionToDB();
		List<Object> codeInfoObject = new ArrayList<Object>();
		codeInfoObject = (List<Object>) command.getCommand();

		Exam exam = (Exam) codeInfoObject.get(0);
		int examid = exam.getId();
		File file = (File) codeInfoObject.get(1);
		String student_id = (String) codeInfoObject.get(2);
		String sql;

		System.out.println("111111111111111111");
		if (file != null) {
			System.out.println("222222222222222222222222");
			sql = "INSERT INTO solvedexam (exam_id, student_id, checkedornot, shefinished, Grade, file) VALUES ('"
					+ examid + "'" + ", '" + student_id + "', 0, 1, 0, '" + file + "')";
		} else {
			System.out.println("33333333333333");

			sql = "INSERT INTO solvedexam (exam_id, student_id, checkedornot, shefinished, Grade) VALUES ('" + examid
					+ "'" + ", '" + student_id + "', 0, 0, 0)";
		}
		stmt.executeUpdate(sql);
		List<Question> questions = exam.getQuestions();

		sql = "SELECT * FROM solvedexam WHERE exam_id = '" + exam.getId() + "' AND student_id = '" + student_id + "'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int solved_id = rs.getInt("id");
		for (Question question : questions) {

			sql = "INSERT INTO solvedexam_chosenanswers (solvedExam_id, chosenanswers ) VALUES ('" + solved_id + "', '"
					+ 0 + "')";
			stmt.executeUpdate(sql);

		}

//		System.out.println("44444444444444444444444");
//		stmt.executeUpdate(sql);
//		System.out.println("555555555555555555");

	}

	public static void getsolved(Command command, ConnectionToClient client) throws SQLException {
		Statement stmt = connectionToDB();
		Object[] examsInfo = new Object[2];
		examsInfo = (Object[]) command.getCommand();
		String student_id = (String) examsInfo[0];
		solvedExam solve = new solvedExam();
		List<solvedExam> solved = solvedExam.getsolvedExamByStudentId(student_id);

		examsInfo[0] = solved;

		List<Question> allexamQuestions = new ArrayList<Question>();
		List<Double> graded = new ArrayList<Double>();
		Exam exam = new Exam();
		File file;
		String comment1, comment2;

		for (solvedExam solvedexam : solved) {
			exam = solvedexam.getExam();
			file = solvedexam.getFile();
			String questionString;
			String answer0String;
			String answer1String;
			String answer2String;
			String answer3String;
			double grade;
			String teatchercomment;
			String studentStringcomment;
			comment1=solvedexam.getGeneralCommentString();
			comment2=solvedexam.getExplainationForGradeChanging();

			allexamQuestions = solvedexam.getExam().getQuestions();
			grade = solvedexam.getGrade();

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

				// teatchercomment = exam.getTeacherComment().get(i);
				// studentStringcomment = exam.getStudentComment().get(i);
				// grade = exam.getGrades().get(i);

				System.out.println(exam.getQuestions().get(i).getQuestion());
				System.out.println(allexamQuestions.get(i).getAnswers().get(0).getAnswer());
				System.out.println(allexamQuestions.get(i).getAnswers().get(1).getAnswer());
				System.out.println(allexamQuestions.get(i).getAnswers().get(2).getAnswer());
				System.out.println(allexamQuestions.get(i).getAnswers().get(3).getAnswer());
				// System.out.println(allexamQuestions.get(i).getAnswers().get(3).getAnswer());

			}
		}

		command.setCommand(examsInfo);
		try {
			client.sendToClient(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ccccccccccccccccccccccccccccccccccc");
	}

}
