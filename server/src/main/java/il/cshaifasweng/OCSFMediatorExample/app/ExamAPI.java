package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.StringValue;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class ExamAPI {
	
	public static Statement connectionToDB() throws SQLException
	{
		String url = "jdbc:mysql://127.0.0.1/hsts";
		String name = "root";
		String pass = "t12345";
		Connection myConnection = DriverManager.getConnection(url,name,pass);
		Statement stmt = (Statement) myConnection.createStatement();
		return stmt;
	}
	
	
	public static List<Question> questionfromidtopbject(List<Integer>questionsId )
	{
		int questionid;
		List <Question> questionsobjects= new ArrayList<Question>();
		for (int i=0;i<questionsId.size();i++)
		{
			Question questionobjet = new Question();
			questionid = questionsId.get(i);
			questionobjet = questionobjet.getQuestionbyId(questionid);
			questionsobjects.add(questionobjet);
			System.out.println(questionobjet.getQuestion());
		}
		
		return questionsobjects;
			
	}
	
	
	
	public static void SavingTheExam(Command command,ConnectionToClient client) throws SQLException 
	{
      

		double duration;
           

		Course course;       
											///////////attention if it doesnt work
		String userInfo;
		String generalCommentTeacher;
		String generalCommentStudent = null;
		List <Double> gradesDoubles = new ArrayList<Double>();
		List <Question> questionsobjects= new ArrayList<Question>();

		List <Integer> questionsId = new ArrayList<Integer>();
		List <String> commentTeacher = new ArrayList<String>();
		List <String> commentStudent = new ArrayList<String>();


		Statement stmt = connectionToDB();
       
        Object[] examsInfo = new Object[9];
        
        examsInfo =  (Object[]) command.getCommand();
        
        generalCommentTeacher = (String) examsInfo[3];
		
        userInfo = (String) examsInfo[2];
	     
        course = (Course) examsInfo[1];
		 
		String str =  examsInfo[0].toString();
		double d = Double.valueOf(str).doubleValue();
		 
        duration =  d;
        Boolean examExecution ;
        generalCommentStudent = (String) examsInfo[4];
        gradesDoubles = (List<Double>) examsInfo[5];

        questionsId = (List<Integer>) examsInfo[6];
        commentStudent = (List<String>) examsInfo[7];
        commentTeacher = (List<String>) examsInfo[8];
        
        /////////// Everything Works ^^^
        
        Teacher teacher = new Teacher();
        Exam exam = new Exam();
		teacher = teacher.getTeacherByuserName(userInfo);
				
		questionsobjects = questionfromidtopbject(questionsId );		
		
		String foundType = null;
		

        String sql1 = "SELECT number FROM course WHERE id = '" + course.getId() + "'";
	    ResultSet rs = stmt.executeQuery(sql1);
	    if(rs.next()){
	    	   foundType = rs.getString(1);
	           System.out.println( foundType);

	    	}
	    
	    int foundValue = Integer.parseInt(foundType);
        ++foundValue;
        System.out.println( foundValue);
        
        String sql2 = "UPDATE course SET number = '" + foundValue + "' WHERE id ='" + course.getId() + "'";
        stmt.executeUpdate(sql2);
        
        System.out.println( "HEHEHEHE");

        System.out.println(course.getId()* 100 + course.getSubject().getId()*10000 + foundValue);
        
        System.out.println( "HEHEHEHE1");

        exam.setId(course.getId()* 100 + course.getSubject().getId()*10000 + foundValue);
		
        System.out.println( "HEHEHEHE2");

        System.out.println( exam.getId());
        
        
        exam.setDuration(duration);
        exam.setCourse(course);
        exam.setCreatedByteacher(teacher);
        exam.setGeneralCommentTeacher(generalCommentTeacher);
        exam.setGeneralCommentStudent(generalCommentStudent);
        exam.setGrades(gradesDoubles);
        exam.setQuestions(questionsobjects);
        exam.setStudentComment(commentStudent);
        exam.setTeacherComment(commentTeacher);
        examExecution = true;
        System.out.println( "HEHEHEHE3");

        System.out.println( course.getCourseName());
        System.out.println( "HEHEHEHE44");

        System.out.println( teacher.getLastName());

		//course.addExam(exam);
		//teacher.addExamstoTeacher(exam);
        
       
		
				
		
		String sql =  "INSERT INTO exam (id, code, duration, number, course_id,teacher_id,examExecutaion,GeneralCommentStudent,GeneralCommentTeacher) VALUES ('"
	      		  + exam.getId() + "', " + "'" + null + "', " + "'" + duration + "', " +
	      		  "'" + foundValue + "', " + "'" + course.getId() + "', " + "'" + teacher.getId() + "', " + " 1 , " + "'" + generalCommentStudent +
	      		"', " + "'" + generalCommentTeacher + "') "; 
		
	      		  stmt.executeUpdate(sql);
	    int i = 0; 		  
	    for (Question question : questionsobjects)
	    {
	    	
	    sql = "INSERT INTO exam_question (Exam_id,question_id ) VALUES ('" + exam.getId() +  "', " + "'" +  question.getId() + "')";
	    stmt.executeUpdate(sql);
	    
	    sql = "INSERT INTO exam_questiongrade (Exam_id,QuestionGrade ) VALUES ('" + exam.getId() +  "', " + "'" + gradesDoubles.get(i) + "')";
	    stmt.executeUpdate(sql);
	    
	    sql = "INSERT INTO exam_studentcomment (Exam_id, StudentComment) VALUES ('" + exam.getId() +  "', " + "'" + commentStudent.get(i) + "')";
	    stmt.executeUpdate(sql);
	     
	    
	    sql = "INSERT INTO exam_teachercomment (Exam_id, TeacherComment) VALUES ('" + exam.getId() +  "', " + "'" + commentTeacher.get(i) + "')";
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

		int id  = (int) command.getCommand();
		System.out.println("GDDFD");
		
		System.out.println(id);
		
		
		Statement stmt = connectionToDB();	
		
		String sql1 = "SELECT * FROM exam WHERE course_id = '" + id + "'";

	    ResultSet rs = stmt.executeQuery(sql1);

	    List<Object> examsInfoList = new ArrayList<Object>();
	    
	    
	    
	    while (rs.next()){
	    	
	       Object[] examInfoArray = new Object [3];

	  	   int examID = rs.getInt(1);
	  	   int duration = rs.getInt(5);
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
		
        codeInfoObject =  (List<Object>) command.getCommand();
        
        int examID = (int) codeInfoObject.get(0);
        
        codeExam = (char[]) codeInfoObject.get(1);
        
        int examExecutaion = (int) codeInfoObject.get(2);
        
        System.out.println("LLLLLLL");
        String codeEString = String.valueOf(codeExam); 
        
        String sql2 = "UPDATE exam SET code = '" + codeEString + "' WHERE id ='" + examID + "'";
        stmt.executeUpdate(sql2);
        
        Statement stmt1 = connectionToDB();

        if (examExecutaion == 1)
        	
        {
            System.out.println("comp");

        String sql3 = "UPDATE exam SET examExecutaion = 1 WHERE id ='" + examID + "'";
        stmt1.executeUpdate(sql3);
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
		
		
		
	}


