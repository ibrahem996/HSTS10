package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList; 
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import il.cshaifasweng.OCSFMediatorExample.databaseinitilize.InitlizeDataBase;


@Entity
@Table(name = "subject")
public class Subject implements Serializable{
	
	@Id
	private int id;
	
	int number = 0; 
		
	private String name;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "subject")
	private List<Course> courses;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "subject")
	private List<Question> questions;

	@ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE},targetEntity = Teacher.class)
	@JoinTable(name = "subject_teacher", joinColumns = @JoinColumn(name = "subject_id"),inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	private List<Teacher> teachers; 
	
	//contructor
	
	public Subject() {
	}

	public Subject(int id, String name) {
		this.id = id;
		this.name = name;
		courses = new  ArrayList<Course>();
		questions = new ArrayList<Question>();
		teachers = new ArrayList<Teacher>();
	}
	
	//methods
	
	public int getNum() 
	{
		return this.number;
	}
	
	public void setNum() 
	{
		this.number++;
	}
	
	public void addCoursestoSubject(Course... courses) 
	{
		for (Course course : courses)
		{
			this.courses.add(course);
			course.setSubject(this);
		}
	}
	
	public void addQuestionstoSubject(Question... questions) 
	{
		for (Question question : questions)
		{
			this.questions.add(question);
		}
	}
	
	public void addTeachertoSubject(Teacher... teachers) 
	{
		for (Teacher teacher : teachers)
		{
			this.teachers.add(teacher);
			teacher.addSubject(this);
		}
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public List<Subject> getAllSubjects()
	{
		
		List<Subject> subjects = new  ArrayList<Subject>();
		subjects = InitlizeDataBase.getAllSubects();
		
		return subjects;
	}
}
