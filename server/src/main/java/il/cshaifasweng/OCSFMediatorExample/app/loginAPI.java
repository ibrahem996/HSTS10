package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class loginAPI { ///// remember to check if connected

	public static boolean IsConnected = false;

	public static void checkSpecificUser(Command command, ConnectionToClient client) {

		String messageArrayList[] = new String[3];
		messageArrayList = (String[]) command.getCommand();
		String userName = messageArrayList[0];
		String password = messageArrayList[1];
		String type = messageArrayList[2];
		System.out.print("type = " + type + "\n");
		String temp[] = new String[2];
		String temp2[] = new String[3];

		try {
			String name, pass, url;
			url = "jdbc:mysql://127.0.0.1/hstsdatabase";
			name = "root";
			pass = "lioncatc1";
			Connection myConnection = DriverManager.getConnection(url, name, pass);
			Statement stmt = (Statement) myConnection.createStatement();

			switch (type) {

			case ("Student"):
				System.out.println("studenttttttttt1111111111111");
				String sql = "SELECT * FROM student WHERE userName = '" + userName + "' AND password = '" + password
						+ "'";
				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next()) {
					System.out.println("studenttttttttt");
					if (rs.getBoolean("isConnected")) {
						temp2[0] = "isconnected";
					} else {
						temp2[0] = "true";
						String sql5 = "UPDATE student SET isConnected = 1 WHERE userName = " + "'" + userName + "'";
						stmt.executeUpdate(sql5);
					}
					sql = "SELECT * FROM student WHERE userName = '" + userName + "' AND password = '" + password + "'";
					rs = stmt.executeQuery(sql);
					if (rs.next()) {
						temp2[2] = Integer.toString(rs.getInt("id"));
						System.out.println("temp2[2]=" + temp2[2]);

						System.out.println("trueeeeeeeeeeeee= " + rs.getBoolean("isConnected"));
						

					}
				} else {
					temp2[0] = "false";
				}
				temp2[1] = "student";
				command.setCommand(temp2);
				System.out.println("temp2[0]========"+temp2[0]);
				break;

			case ("Teacher"):
				System.out.println("teacheeeeeeerrrr");
				String sql2 = "SELECT userName FROM teacher WHERE userName = '" + userName + "' AND password = '"
						+ password + "'";
				ResultSet rs2 = stmt.executeQuery(sql2);
				if (rs2.next()) {
					temp[0] = "true";
					temp[1] = "teacher";
					command.setCommand(temp);
				}
				break;

			case ("Manager"):
				String sql3 = "SELECT userName FROM manager WHERE userName = '" + userName + "' AND password = '"
						+ password + "'";
				ResultSet rs3 = stmt.executeQuery(sql3);
				if (rs3.next()) {
					temp[0] = "true";
					temp[1] = "manager";
					command.setCommand(temp);
				}
				break;

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

	private static String toString(int int1) {
		// TODO Auto-generated method stub
		return null;
	}

}