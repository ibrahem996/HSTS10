package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList; 
import java.util.List; 

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import il.cshaifasweng.OCSFMediatorExample.databaseinitilize.InitlizeDataBase;

@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

	@Id
	private int id;

	private String userName;

	private String password;

	private String firstName;

	private String lastName;
	
	boolean isConnected;

	@ManyToMany(mappedBy = "teachers", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Course.class)
	private List<Course> courses;
	
	@ManyToMany(mappedBy = "teachers", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Subject.class)
	private List<Subject> subjects;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "createdByteacher")
	private List<Exam> exams;
	
	
	
	//constructor

	public Teacher(){
	}

	public Teacher(int id, String userName, String password, String firstName, String lastName) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		courses = new  ArrayList<Course>();
		subjects = new  ArrayList<Subject>();
		exams = new ArrayList<Exam>();
	}
	
	//methods

	public void addCoursestoTeacher(Course... courses) 
	{
		for (Course course : courses)
		{
			this.courses.add(course);
			course.getTeachers().add(this); 
		}
	}
	
	public void addExamstoTeacher(Exam... exams) 
	{
		for (Exam exam : exams)
		{
			this.exams.add(exam);
		}
	}
	
	public Teacher getTeacherByuserName(String userName1)
	{
		Teacher chosenTeacher = null;
		List<Teacher> teachers = new  ArrayList<Teacher>();
		teachers = InitlizeDataBase.getAllteachers();
		for(Teacher teacher : teachers)
		{
			if (teacher.userName.equalsIgnoreCase(userName1))
			{
				chosenTeacher = teacher;
			}
		}
		
		return chosenTeacher;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	
	public List<Subject> getSubject() {
		return subjects;
	}
	
	public void addSubject(Subject subject) {
		subjects.add(subject);
	}
	

}
