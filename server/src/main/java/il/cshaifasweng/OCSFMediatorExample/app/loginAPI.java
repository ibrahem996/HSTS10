package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.IOException;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;


public class loginAPI {													///// remember to check if connected
	
	public static boolean IsConnected = false;
	
	public static void checkSpecificUser(Command command,ConnectionToClient client)
	{
		String messageArrayList [] = new String[3];
	  	messageArrayList =  (String[]) command.getCommand();
		String userName = messageArrayList[0];
		String password = messageArrayList[1];
		String type = messageArrayList[2];
		
		String temp [] = new String[2];
		
		try {
			String name,pass,url;
			url = "jdbc:mysql://127.0.0.1/hsts";
			name = "root";
			pass = "t12345";
			Connection myConnection = DriverManager.getConnection(url,name,pass);
	        Statement stmt = (Statement) myConnection.createStatement();
	        if (type.equalsIgnoreCase("Student"))
	        {
		        String sql = "SELECT userName FROM student WHERE userName = '" + userName + "' AND password = '" + password + "' AND isConnected = '" + IsConnected + "'";
		 	    ResultSet rs = stmt.executeQuery(sql);
		 	    System.out.print( rs.next());
		 	    if(rs.next())
		 	    {
		 	    	temp[0] = "true";
		 	    	temp[1] = "student";
		 	    	command.setCommand(temp);
		 	    }
		 	     
				
	        }
	        else if (type.equalsIgnoreCase("Teacher"))
	        {
		        String sql = "SELECT userName FROM teacher WHERE userName = '" + userName + "' AND password = '" + password + "'";
		 	    ResultSet rs = stmt.executeQuery(sql);
		 	    if(rs.next())
		 	    {
		 	    	temp[0] = "true";
		 	    	temp[1] = "teacher";
		 	    	command.setCommand(temp);
		 	    }
	        }
	        
	        else if (type.equalsIgnoreCase("Manager"))
	        {
		        String sql = "SELECT userName FROM manager WHERE userName = '" + userName + "' AND password = '" + password + "'";
		 	    ResultSet rs = stmt.executeQuery(sql);
		 	    if(rs.next())
		 	    {
		 	    	temp[0] = "true";
		 	    	temp[1] = "manager";
		 	    	command.setCommand(temp);
		 	    }
	        }
	        
	        try {		
				client.sendToClient(command);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
		} catch (Exception e) {
			// TODO: handle exception
		}
			
	}

}

