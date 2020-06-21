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
import javax.persistence.Table;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

																				////we may need id for solvedexam
@Entity
@Table(name = "solvedexam")
public class solvedExam {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.LAZY)  //////////******** cascade may be wrong
	@JoinColumn(name = "exam_id")
	private Exam exam;
	
	@ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;
	
	@Column(name = "chosenanswers")
	@ElementCollection(targetClass=Question.class)
	private List<Pair<Question, Integer>> questionsSolved;
	

	//constructor
	public solvedExam(Exam exam, Student student,List<Integer> chosenanswers) {
		this.exam = exam;
		this.student = student;
		questionsSolved = new ArrayList<Pair<Question, Integer>>();
		choseanswerforquestion(this.exam.getQuestions(),chosenanswers);

	}
	
	public solvedExam()
	{
		
	}

	//methods
	
	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Pair<Question, Integer>> getQuestionsSolved() {
		return questionsSolved;
	}

	public void setQuestionsSolved(List<Pair<Question, Integer>> questionsSolved) 
	{
		this.questionsSolved = questionsSolved;
	}
	
	public void choseanswerforquestion(List<Question> questions, List<Integer> chosenanswers)
	{
		int chosen;
		List<Integer> answerstemp = chosenanswers;
		for (Question question : questions)
		{
			chosen = answerstemp.remove(0);
			Pair<Question, Integer> onePair = new Pair<>(question, chosen);
			this.questionsSolved.add(onePair);
		}
	
	}
	

}