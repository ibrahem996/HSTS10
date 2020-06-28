package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.IOException; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.entities.Answer;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class QuestionsAPI {																	//remember to add Update Question
	
	
	
	public static Statement connectionToDB() throws SQLException
	{
		String url = "jdbc:mysql://127.0.0.1/hstsdatabase";
		String name = "root";
		String pass = "lioncatc1";
		Connection myConnection = DriverManager.getConnection(url,name,pass);
		Statement stmt = (Statement) myConnection.createStatement();
		return stmt;
	}
	
	
	public static void AddSpecificQuestion(Command command,ConnectionToClient client) throws SQLException
	{
        System.out.println("here is the AddQuestionCommand");
		int answerid;
		Calendar firstAppearance;
		String questionString;
		String answer;
		int question_id;
		int answer_id;
		int subject_id;
		
		Statement stmt = connectionToDB();
		        
        Question newQuestion =  (Question) command.getCommand();

        System.out.println(newQuestion.getQuestion());
        List<Answer> theAnswers = new ArrayList<Answer>();
        
        String foundType = "";
        questionString = newQuestion.getQuestion();
        theAnswers = newQuestion.getAnswers();
        firstAppearance = Calendar.getInstance();
        System.out.println(theAnswers.get(0).getAnswer());

        
        
        System.out.println(newQuestion.getId());
    	
    	

        subject_id = newQuestion.getSubject().getId();		    
        answer_id = (newQuestion.getCorrectAnswer());;
		

        String sql1 = "SELECT number FROM subject WHERE id = '" + newQuestion.getSubject().getId() + "'";
	    ResultSet rs = stmt.executeQuery(sql1);
	    if(rs.next()){
	    	   foundType = rs.getString(1);
	           System.out.println( foundType);

	    	}
	    
	    int foundValue = Integer.parseInt(foundType);
        ++foundValue;
        System.out.println( foundValue);
        
        String sql2 = "UPDATE subject SET number = '" + foundValue + "' WHERE id ='" + newQuestion.getSubject().getId() + "'";
        stmt.executeUpdate(sql2);
        
        System.out.println(newQuestion.getSubject().getId() * 1000 + foundValue);
        
        newQuestion.setId(newQuestion.getSubject().getId() * 1000 + foundValue);

        question_id = newQuestion.getId();

        System.out.println( question_id);

        theAnswers.get(0).setId(question_id*10+1);
        theAnswers.get(1).setId(question_id*10+2);
        theAnswers.get(2).setId(question_id*10+3);
        theAnswers.get(3).setId(question_id*10+4);

        System.out.println( theAnswers.get(0).getId());


        
        String sql =  "INSERT INTO question (id, firstAppearance, questionString, TheAnswer, subject_id) VALUES ('"
      		  + question_id + "', " + "'" + new Timestamp(firstAppearance.getTimeInMillis()) + "', " + "'" + questionString + "', " +
      		  "'" + answer_id + "', " + "'" + subject_id + "')"; 
      		  stmt.executeUpdate(sql);
      		  
      		  

      		 answerid = theAnswers.get(0).getId();
             answer = theAnswers.get(0).getAnswer();
         sql =
      		  "INSERT INTO answer (id, answer, firstAppearance, question_id) VALUES ('" + answerid
      		 + "', " + "'" + answer + "', " + "'" + new Timestamp(firstAppearance.getTimeInMillis()) + "', " + "'" +
      		  question_id + "')"; stmt.executeUpdate(sql);
      		  

      		answerid = theAnswers.get(1).getId();
            answer = theAnswers.get(1).getAnswer();
      	 sql =
      	  	  "INSERT INTO answer (id, answer, firstAppearance, question_id) VALUES ('" + answerid
        		 + "', " + "'" + answer + "', " + "'" + new Timestamp(firstAppearance.getTimeInMillis()) + "', " + "'" +
   	      		  question_id + "')"; stmt.executeUpdate(sql);

   	      		answerid = theAnswers.get(2).getId();
   	         answer = theAnswers.get(2).getAnswer();		  
   	      sql =
   	      		  "INSERT INTO answer (id, answer, firstAppearance, question_id) VALUES ('" + answerid
   	      		 + "', " + "'" + answer + "', " + "'" + new Timestamp(firstAppearance.getTimeInMillis()) + "', " + "'" +
   	      		  question_id + "')"; stmt.executeUpdate(sql);
      	   
   	      		answerid = theAnswers.get(3).getId();
   	         answer = theAnswers.get(3).getAnswer();
   	     sql =
   	      		  "INSERT INTO answer (id, answer, firstAppearance, question_id) VALUES ('" + answerid
   	      		 + "', " + "'" + answer + "', " + "'" +  new Timestamp(firstAppearance.getTimeInMillis()) + "', " + "'" +
   	      		  question_id + "')"; stmt.executeUpdate(sql);
        
   	      		  
   	      		  
  				try {
					client.sendToClient(command);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


	}

	
	
	
	public static void bringQuestionsSubject(Command command, ConnectionToClient client) throws SQLException {

		int subjectId = (int) command.getCommand();
        System.out.println( subjectId);
        
		Statement stmt = connectionToDB();
        System.out.println( subjectId);


        ArrayList<Object> allquistions = new ArrayList<Object>();
		String sql1 = "SELECT * FROM question WHERE subject_id = '" + subjectId + "'";
	    ResultSet rs = stmt.executeQuery(sql1);

	    while (rs.next()){

	    	   Object[] qinfObjects = new Object [8];
	    	
	    	   int qid = rs.getInt(1);
	           System.out.println(qid);
	           qinfObjects[0]=qid;
	           
	           String qString =  rs.getString(4);
	           System.out.println(qString);
	           qinfObjects[1]=qString;
	      
	           int correct = rs.getInt(2);
	           System.out.println(correct);
	           qinfObjects[2] = correct;
	           
	           qinfObjects[7] = subjectId;
	        		   
	           Statement stmtanswer = connectionToDB();
		
	           String sqlAnswer = "SELECT answer FROM answer WHERE question_id = '" + qid + "'";
	           ResultSet rsAnswer = stmtanswer.executeQuery(sqlAnswer);
		  	   int c=3;
		  	   while (rsAnswer.next()){
		  		  
		  		   String answer=rsAnswer.getNString(1);
		  		   System.out.println(answer);
		  		   qinfObjects[c++]=answer;
		  	   		}
		  	   
		  	   	   allquistions.add(qinfObjects);
			  }
		
	    command.setCommand(allquistions);
			    
	    try {
	    	client.sendToClient(command);
			} catch (IOException e) {
					// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
	}
}




