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
		url = "jdbc:mysql://127.0.0.1/project";
		name = "root";
		pass = "9064";
		Connection myConnection = DriverManager.getConnection(url,name,pass);
        Statement stmt = (Statement) myConnection.createStatement();
        String details = "SELECT * FROM `question` WHERE `id`=2500";
        ResultSet rs= stmt.executeQuery(details);
        if (rs.next()) {
			
        	InitlizeDataBase.LoadData(args, 1);
		}
		else {
	    	InitlizeDataBase.LoadData(args, 1);
		}
        server = new SimpleServer(3000);
        server.listen();
    
        }
}
