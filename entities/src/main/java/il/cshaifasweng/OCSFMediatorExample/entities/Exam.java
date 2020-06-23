package il.cshaifasweng.OCSFMediatorExample.entities;

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

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

@Entity
@Table(name = "exam")
public class Exam {

	@Id
	private int id; // must be 6 digits

	char[] code = new char[4]; // must be 4 digits check in teacher controller

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int number;

	double duration;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course course;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private Teacher createdByteacher;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Question.class)
	@JoinTable(name = "exam_question", joinColumns = @JoinColumn(name = "exam_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	private List<Question> questions;

	@Column(name = "QuestionGrade")
	@ElementCollection(targetClass = Question.class)
	private List<Pair<Question, Double>> QuestionGrade;

	// constructor

	public Exam(double duration, Subject subject, Course course, Teacher createdByteacher, List<Question> questions) {
		int examNum = course.getNum();
		this.duration = duration;
		this.course = course;
		this.createdByteacher = createdByteacher;
		course.addExam(this);
		this.questions = questions;
		this.id = this.course.getId() * 100 + subject.getId() * 10000 + examNum;
		this.course.setNum();
		for (Question question : questions) {
			question.getExams().add(this);
		}

		this.course.addExam(this);

	}

	public Exam() {
	}

	// methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char[] getCode() {
		return code;
	}

	public void setCode(char[] code) {
		this.code = code;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
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

	public void addGradeToQuestion(List<Question> questions, List<Double> grades) {
		double graded;
		List<Double> gradestemp = grades;
		for (Question question : questions) {
			graded = gradestemp.remove(0);
			Pair<Question, Double> onePair = new Pair<>(question, graded);
			this.QuestionGrade.add(onePair);
		}
	}

}
