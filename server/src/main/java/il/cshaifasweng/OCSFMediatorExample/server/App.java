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
<<<<<<< HEAD
		url = "jdbc:mysql://127.0.0.1/hsts";
		name = "root";
		pass = "t12345";
=======
		url = "jdbc:mysql://127.0.0.1/hstsdatabase";
		name = "root";
		pass = "Lilyan$4";
>>>>>>> a9484d2740d5481d40aa5799c1af01e8804f4d9b
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
