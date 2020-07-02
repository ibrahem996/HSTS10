
package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import il.cshaifasweng.OCSFMediatorExample.databaseinitilize.InitlizeDataBase;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static SimpleServer server;
    public static void main( String[] args ) throws IOException, SQLException
    {
    	String name,pass,url;
		
		  url = "jdbc:mysql://127.0.0.1/hstsdatabase"; 
		  name = "root"; 
		  pass = "t12345";
		  
		  Connection myConnection = DriverManager.getConnection(url,name,pass);
		  Statement stmt = (Statement) myConnection.createStatement();
		  String details = "SELECT * FROM `subject` ";
		  ResultSet rs = stmt.executeQuery(details);
		
		  
		  
		  if (rs.next()) {
			  System.out.println("not Init");

		  InitlizeDataBase.LoadData(args, 0);
		  } 
		  
		  else {
			  System.out.println("init");

			  InitlizeDataBase.LoadData(args,1);
		  }
		
        server = new SimpleServer(3000);
        server.listen();
    
        }
}

