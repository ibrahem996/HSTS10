package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class StartExamAPI {
	
	public static void ReturnExam(Command command, ConnectionToClient client) {
		String[] arr=new String[2];
		arr = (String[]) command.getCommand();
		String ID = arr[0];
		String Code=arr[1];
		String name, pass, url;
		url = "jdbc:mysql://127.0.0.1/hstsdatabase";
		name = "root";
		pass = "lioncatc1";
		Exam exam = new Exam();
		Connection myConnection;
		try {
			myConnection = DriverManager.getConnection(url, name, pass);
			Statement stmt = (Statement) myConnection.createStatement();
			String sql = "SELECT * FROM exam WHERE code = '" + Code + "'";
			ResultSet rs = stmt.executeQuery(sql);
			int exam_id = rs.getInt(1);
			exam = exam.getExamByuserID(exam_id);
			command.setCommand(exam);
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
