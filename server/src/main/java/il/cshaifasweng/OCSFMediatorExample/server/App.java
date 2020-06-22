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
	    	InitlizeDataBase.LoadData(args, 1);
		
        server = new SimpleServer(3000);
        server.listen();
    
        }
}
