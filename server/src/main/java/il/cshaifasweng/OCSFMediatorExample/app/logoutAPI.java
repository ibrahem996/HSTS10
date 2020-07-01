package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class logoutAPI {

	
	public static void logout(Command command, ConnectionToClient client) {
		String[] arr=new String[1];
		arr = (String[]) command.getCommand();
		String userName = arr[0];
		String name, pass, url;
		url = "jdbc:mysql://127.0.0.1/hstsdatabase";
		name = "root";
		pass = "t12345";
		Connection myConnection;
		try {
			myConnection = DriverManager.getConnection(url, name, pass);
			Statement stmt = (Statement) myConnection.createStatement();
			String sql = "UPDATE student SET isConnected = 0 WHERE userName = '" + userName + "'";
			stmt.executeUpdate(sql);
			arr[0] = "logout";
			command.setCommand(arr);
			client.sendToClient(command);
			System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
}
