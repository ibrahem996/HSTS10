package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import il.cshaifasweng.OCSFMediatorExample.databaseinitilize.InitlizeDataBase;

@Entity
@Table(name = "student")
public class Student implements Serializable {

	@Id
	private int id;
	
	String userName;
	
	String password;

	String firstName;
	
	String lastName;
	
	boolean isConnected;
	
	@ManyToMany (mappedBy = "students",cascade = {CascadeType.PERSIST,CascadeType.MERGE},targetEntity = Course.class)
	private List<Course> courses;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "student")
	private List<checkedExam> theexams;
	
	///constructor
	public Student() {
	}
	
	public Student(int id, String userName, String password, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		courses = new ArrayList<Course>();
		theexams = new ArrayList<checkedExam>();
		
	}

	////methods

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<checkedExam> getTheexams() {
		return theexams;
	}

	public void setTheexams(List<checkedExam> theexams) {
		this.theexams = theexams;
	}
	
	public void addCoursestoPerson(Course... courses)
	{
		for (Course course : courses)
		{
			this.courses.add(course);
			course.getStudents().add(this); 
		}
	}
	
	public void addCheckedExamtoPerson(checkedExam... checkedexams)
	{
		for (checkedExam checkedexam : checkedexams)
		{
			this.theexams.add(checkedexam);
		}
	}
	
	public Student getStudentByuserName(String userName1)
	{
		Student chosenStudent = null;
		List<Student> students = new  ArrayList<Student>();
		students = InitlizeDataBase.getAllstudents();
		for(Student student : students)
		{
			if (student.userName.equalsIgnoreCase(userName1))
			{
				chosenStudent = student;
			}
		}
		
		return chosenStudent;
		
	}
}


