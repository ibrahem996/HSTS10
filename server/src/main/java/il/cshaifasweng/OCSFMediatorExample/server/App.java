package il.cshaifasweng.OCSFMediatorExample.server;

import java.io.IOException;

import java.sql.SQLException;
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

