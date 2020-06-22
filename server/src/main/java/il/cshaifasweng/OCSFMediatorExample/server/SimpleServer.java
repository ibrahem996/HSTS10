package il.cshaifasweng.OCSFMediatorExample.server;

import java.sql.SQLException;


import javax.persistence.criteria.CriteriaBuilder.Case; 

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.Commands.CommandType;
import il.cshaifasweng.OCSFMediatorExample.app.QuestionsAPI;
import il.cshaifasweng.OCSFMediatorExample.app.checkSubjectAPI;
import il.cshaifasweng.OCSFMediatorExample.app.loginAPI;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;  
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;



public class SimpleServer extends AbstractServer {                        ////////remember change from create to update in hibernate

	public SimpleServer(int port) {
		super(port);
		
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
			
			System.out.println("Handling Message:");
		
	        Command command = (Command) msg;
	        CommandType type = command.getType();
	        
	        switch(type) {
	        
            case loginCommand:
            	

                System.out.println("loginCommand");
                //client.setInfo("username", msg);
                loginAPI.checkSpecificUser(command,client);
    			System.out.println("Handling Message:");

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
    			
    		}	        
	}

}
	
	


