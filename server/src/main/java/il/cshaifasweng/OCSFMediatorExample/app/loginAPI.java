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

		try {
			String name, pass, url;
			url = "jdbc:mysql://127.0.0.1/hstsdatabase";
			name = "root";
			pass = "Lilyan$4";
			Connection myConnection = DriverManager.getConnection(url, name, pass);
			Statement stmt = (Statement) myConnection.createStatement();

			switch (type) {

			case ("Student"):
				System.out.println("studenttttttttt1111111111111");
				String sql = "SELECT userName FROM student WHERE userName = '" + userName + "' AND password = '"
						+ password + "'";
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					System.out.println("studenttttttttt");
					temp[0] = "true";
					temp[1] = "student";
					command.setCommand(temp);
				}
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

//			if (type.equalsIgnoreCase("Student")) {
//				System.out.println("studenttttttttt1111111111111");
//				String sql = "SELECT userName FROM student WHERE userName = '" + userName + "' AND password = '"
//						+ password + "' AND isConnected = '" + IsConnected + "'";
//				ResultSet rs = stmt.executeQuery(sql);
//				System.out.print(rs.next());
//				if (rs.next()) {
//					System.out.println("studenttttttttt");
//					temp[0] = "true";
//					temp[1] = "student";
//					command.setCommand(temp);
//				}
//
//			} else if (type.equalsIgnoreCase("Teacher")) {
//				System.out.println("teacheeeeeeerrrr");
//				String sql = "SELECT userName FROM teacher WHERE userName = '" + userName + "' AND password = '"
//						+ password + "'";
//				ResultSet rs = stmt.executeQuery(sql);
//				if (rs.next()) {
//					temp[0] = "true";
//					temp[1] = "teacher";
//					command.setCommand(temp);
//				}
//			}
//
//			else if (type.equalsIgnoreCase("Manager")) {
//				String sql = "SELECT userName FROM manager WHERE userName = '" + userName + "' AND password = '"
//						+ password + "'";
//				ResultSet rs = stmt.executeQuery(sql);
//				if (rs.next()) {
//					temp[0] = "true";
//					temp[1] = "manager";
//					command.setCommand(temp);
//				}
//			}

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
