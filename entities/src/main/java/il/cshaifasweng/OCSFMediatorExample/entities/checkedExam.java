package il.cshaifasweng.OCSFMediatorExample.entities;

import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;


@Entity
@Table(name = "checkedexam")
public class checkedExam {             
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne 
	@JoinColumn(name = "solvedexam_id",referencedColumnName = "id")
	private solvedExam solvedexam;
	
	@ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;
	
	private String comment;
	
	private double grade;
	
	@Column(name = "wrongAnswers")
	@ElementCollection(targetClass=Question.class)
	private List<Question> wrongAnswers;
	
	@Column(name = "commentedQuestion")
	@ElementCollection(targetClass=Question.class)
	private List<Pair<Question, String>> commentedQuestion;
	
	@Column(name = "gradedQuestion")
	@ElementCollection(targetClass=Question.class)
	private List<Pair<Question, Double>> gradedQuestion;

	public checkedExam(solvedExam solvedexam, List<Question> wrongAnswers,List<String> comments,List<Double> grades)
	{
		wrongAnswers = new ArrayList<Question>();
		commentedQuestion = new ArrayList<Pair<Question, String>>();
		gradedQuestion = new ArrayList<Pair<Question, Double>>();
		List<Question> tempList = this.solvedexam.getExam().getQuestions();
		this.solvedexam = solvedexam;
		this.wrongAnswers = wrongAnswers;
		commentToQuestion(tempList,comments);
		gradeToQuestion(tempList, grades);
	}

	public solvedExam getSolvedexam() {
		return solvedexam;
	}

	public void setSolvedexam(solvedExam solvedexam) {
		this.solvedexam = solvedexam;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public List<Question> getWrongAnswers() {
		return wrongAnswers;
	}

	public List<Pair<Question, String>> getCommentedQuestion() {
		return commentedQuestion;
	}

	public void setCommentedQuestion(List<Pair<Question, String>> commentedQuestion) {
		this.commentedQuestion = commentedQuestion;
	}

	public List<Pair<Question, Double>> getGradedQuestion() {
		return gradedQuestion;
	}

	public void setGradedQuestion(List<Pair<Question, Double>> gradedQuestion) {
		this.gradedQuestion = gradedQuestion;
	}
	
	public void commentToQuestion(List<Question> questions, List<String> comments)
	{
		String commented;
		List<String> commentstemp = comments;
		for (Question question : questions)
		{
			commented = commentstemp.remove(0);
			Pair<Question, String> onePair = new Pair<>(question, commented); 
			this.commentedQuestion.add(onePair);
		}
	
	}
	
	public void gradeToQuestion(List<Question> questions, List<Double> grades)
	{
		double graded;
		List<Double> gradestemp = grades;
		for (Question question : questions)
		{
			graded = gradestemp.remove(0);
			Pair<Question, Double> onePair = new Pair<>(question, graded);
			this.gradedQuestion.add(onePair);
		}
	
	}
	
}


