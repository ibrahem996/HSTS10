package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class checkSubjectAPI {
	
	public static void checkSpecificSubjects(Command command,ConnectionToClient client)
	{
		List <Subject> specficSubjects = new ArrayList<Subject>();
		String username = (String) command.getCommand();
		Teacher teacher = new Teacher();
		teacher = teacher.getTeacherByuserName(username);
		System.out.println(teacher.getFirstName());
		
		specficSubjects = teacher.getSubject();
		System.out.print(specficSubjects.get(0).getName());

		command.setCommand(specficSubjects);
		 try {		

				client.sendToClient(command);
	
				System.out.format("Sent specific subjects to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
	
	}

	public static void bringallsubjectformanager(Command command,ConnectionToClient client) {
		List <Subject> allSubjects = new ArrayList<Subject>();
		
		Subject subject = new Subject();
		allSubjects =subject.getAllSubjects();
		

		command.setCommand(allSubjects);
		
		System.out.println(allSubjects.get(0).getName());
		System.out.println(allSubjects.get(1).getName());
		System.out.println(allSubjects.get(2).getName());
		
		
		 try {		

				client.sendToClient(command);
	
				System.out.format("Sent specific subjects to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

}
