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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "course")

public class Course implements Serializable {

	@Id
	private int id;
	
	@ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;
	
	String courseName;
	
	int number = 0; 
	
	@ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE},targetEntity = Student.class)
	@JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "course_id"),inverseJoinColumns = @JoinColumn(name = "student_id"))
	private List<Student> students;
	
	@ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE},targetEntity = Teacher.class)
	@JoinTable(name = "course_teacher", joinColumns = @JoinColumn(name = "course_id"),inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	private List<Teacher> teachers;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "course")
	private List<Exam> exams;

	//constructor
	
	public Course(int id, Subject subject, String courseName) {
		super();
		this.id = id;
		this.subject = subject;
		this.courseName = courseName;
		teachers = new ArrayList<Teacher>();
		exams = new ArrayList<Exam>();
		students = new ArrayList<Student>();
	}
	
	public Course() {
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	
	public void addExam(Exam exam) {
		exams.add(exam);
	}
	
	
	
}
