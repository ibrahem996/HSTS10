package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class loginExamAPI {
	public static boolean IsConnected = false;

	public static void checkSpecificUser(Command command, ConnectionToClient client) {

		String messageArrayList[] = new String[2];
		messageArrayList = (String[]) command.getCommand();
		String ID = messageArrayList[0];
		String codeId = messageArrayList[1];
		String temp[] = new String[4];
		try {
			String name, pass, url;
			url = "jdbc:mysql://127.0.0.1/hstsdatabase";
			name = "root";
			pass = "lioncatc1";
			Connection myConnection = DriverManager.getConnection(url, name, pass);
			Statement stmt = (Statement) myConnection.createStatement();

			String sql = "SELECT id FROM student WHERE id = '" + ID + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				System.out.println("ID = " + ID);
				temp[0] = "true"; // checks if the id is existed
				temp[1] = ID; // stores the id

				sql = "SELECT code FROM exam WHERE code = '" + codeId + "'";
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					temp[2] = "true"; // checks if the code is exists, and if the student have the same code
					temp[3] = codeId; // stores the code
				} else {
					temp[2] = "false";
					temp[3] = "false";
				}
			}

			else {
				temp[0] = "false";
				temp[1] = "false";
				temp[2] = "false";
				temp[3] = "false";
			}

			command.setCommand(temp);

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
