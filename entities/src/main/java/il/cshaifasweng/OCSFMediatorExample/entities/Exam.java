package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List; 

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import il.cshaifasweng.OCSFMediatorExample.databaseinitilize.InitlizeDataBase;


@Entity
@Table(name = "exam")
public class Exam implements Serializable
{																		     
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	char[] code = new char[4];  //must be 4 digits check in teacher controller
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int number;
	
	Boolean Onexecute = false;
	
	


	double duration;
	
	int executed;///exit to execute
	
	
	String GeneralCommentTeacher;
	
	String GeneralCommentStudent;
	
    Boolean examExecutaion;
    
	@ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id")
	private Course course;
	 
	@ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private Teacher createdByteacher;
	
	
	@ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE},targetEntity = Question.class)
	@JoinTable(name = "exam_question", joinColumns = @JoinColumn(name = "exam_id"),inverseJoinColumns = @JoinColumn(name = "question_id"))
	private List<Question> questions;
	
	@Column(name = "QuestionGrade")
	@ElementCollection //(targetClass=Double.class)
	private List<Double> QuestionGrade;
	
	@Column(name = "StudentComment")
	@ElementCollection// (targetClass=String.class)
	private List<String> studentComment;
	
	@Column(name = "TeacherComment")
    @ElementCollection //(targetClass = String.class)
	private List<String> teacherComment;
	
	


	

	//constructor
	
	public Exam(double duration, Course course, Teacher createdByteacher,String generalCommentTeacher,String generalCommentStudent, List<Double> grades,List<Question> questions,List<String> commentsStudent,List<String> commentsTeacher) {
		int examNum = course.getNum(); 
		examExecutaion = true;
		int subject_id = course.getSubject().getId();
		this.duration = duration;
		this.course = course;
		this.createdByteacher = createdByteacher;           
		course.addExam(this);								 
		this.questions = questions;
		this.GeneralCommentStudent = generalCommentStudent;
		this.GeneralCommentTeacher = generalCommentTeacher;
		this.id = this.course.getId() * 100 + subject_id * 10000 +  examNum; 
		this.course.setNum(); 
		this.executed=0;
		for (Question question : questions)   
		{
			question.getExams().add(this); 
		}
		 
		this.course.addExam(this);  
		

	}
	
	

	public Exam() {
		this.executed=0;
	}

	
	//methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Boolean getOnexecute() {
		return Onexecute;
	}



	public void setOnexecute(Boolean onexecute) {
		Onexecute = onexecute;
	}


	public char[] getCode() {
		return code;
	}

	public void setCode(char[] code) {
		this.code = code;
	}
	
	public void setTeacher(Teacher teacher) {
		this.createdByteacher = teacher;
	}
	
	public Teacher getTeacher()
	{
		return createdByteacher;
	}
	
	public Boolean getExamExecutaion() {
		return examExecutaion;
	}
	
	public void setExamExecutaion(Boolean executaion) {
		this.examExecutaion = executaion;
	}
	
	public String getGeneralCommentTeacher() {
		return GeneralCommentTeacher;
	}

	public void setGeneralCommentTeacher(String generalCommentTeacher) {
		GeneralCommentTeacher = generalCommentTeacher;
	}

	public String getGeneralCommentStudent() {
		return GeneralCommentStudent;
	}

	public void setGeneralCommentStudent(String generalCommentStudent) {
		GeneralCommentStudent = generalCommentStudent;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}
	public int getExecuted() {
		return executed;
	}
	public void setDuration() {
		this.executed=1;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Teacher getCreatedByteacher() {
		return createdByteacher;
	}

	public void setCreatedByteacher(Teacher createdByteacher) {
		this.createdByteacher = createdByteacher;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void setGrades(List<Double> gradesDoubles)
	{
		this.QuestionGrade = gradesDoubles;
	}
	
	public List<Double> getGrades()
	{
		return QuestionGrade;
	}



	public List<String> getStudentComment() {
		return studentComment;
	}



	public void setStudentComment(List<String> studentComment) {
		this.studentComment = studentComment;
	}



	public List<String> getTeacherComment() {
		return teacherComment;
	}



	public void setTeacherComment(List<String> teacherComment) {
		this.teacherComment = teacherComment;
	}
	
	public Exam getExamByID(int id)
	{
		Exam chosenExam = null;
		List<Exam> exams = new  ArrayList<Exam>();
		exams = InitlizeDataBase.getAllexams();
		for(Exam exam : exams)
		{
			if (exam.getId() == id)
			{
				chosenExam = exam;
			}
		}
		
		return chosenExam;
	}
	
	
	
	public List<Exam> getExamCreatedByTeacher(String createdByteacher)
	{
		
		List<Exam> chosenExams = new  ArrayList<Exam>();
		List<Exam> exams = new  ArrayList<Exam>();
		exams = InitlizeDataBase.getAllexams();
		String createdbyString;
		for(Exam exam : exams)
		{
			createdbyString = exam.getCreatedByteacher().getUserName();
			if ((createdbyString.equals(createdByteacher))&& exam.getExecuted()==1)
			{
				System.out.println(	exam.getId());
				chosenExams.add(exam);
		}
			}

		return chosenExams;
	}
	
	
	

}
