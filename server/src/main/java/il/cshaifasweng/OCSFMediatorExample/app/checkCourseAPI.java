package il.cshaifasweng.OCSFMediatorExample.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.Commands.Command;
import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

public class checkCourseAPI {

	public static void checkSpecificCourses(Command command, ConnectionToClient client) {
		List <Course> specficCourses = new ArrayList<Course>();
		String username = (String) command.getCommand();
		Teacher teacher = new Teacher();
		teacher = teacher.getTeacherByuserName(username);
		System.out.println(teacher.getFirstName());
		
		specficCourses = teacher.getCourses();
		System.out.println(specficCourses.get(0).getCourseName());

		command.setCommand(specficCourses);
		 try {		

				client.sendToClient(command);
	
				System.out.format("Sent specific courses to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public static void bringallCoursesformanager(Command command, ConnectionToClient client)
	{
		
       List <Course> allCourses = new ArrayList<Course>();
		
		Course course = new Course();
		allCourses =course.getallCourses();
		

		command.setCommand(allCourses);
		
		System.out.println(allCourses.get(0).getCourseName());
		System.out.println(allCourses.get(1).getCourseName());
		System.out.println(allCourses.get(2).getCourseName());
		
		
		 try {		

				client.sendToClient(command);
	
				System.out.format("Sent specific subjects to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
	}
	
	
	
	
	
	
}
