package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
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
public class solvedExam implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) ////////// ******** cascade may be wrong
	@JoinColumn(name = "exam_id")
	private Exam exam;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@Column(name = "chosenanswers")
	@ElementCollection 
	private List<Integer> chosenanswers;

	private Boolean checkedornot;
	private Boolean shefinished;
	private int Grade;

	// constructor
	public solvedExam(Exam exam, Student student, Boolean shefinished, List<Integer> chosenanswers) {
		this.exam = exam;
		this.student = student;
//		questionsSolved = new ArrayList<Integer>();
//		questionsSolved = chosenanswers;
		// choseanswerforquestion(this.exam.getQuestions(),chosenanswers);
		this.chosenanswers=chosenanswers;
		checkedornot = false;
		this.shefinished = shefinished;
		Grade = 0;
	}

	public solvedExam() {

	}

	// methods
	public int getId() {

		return this.id;
	}

	public int getGrade() {

		return Grade;
	}

	public void setGrade(int Grade) {
		this.Grade = Grade;

	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Boolean getcheckedornot() {
		return checkedornot;
	}

	public void setcheckedornot(Boolean checkedornot) {
		this.checkedornot = checkedornot;
	}

	public Boolean getshefinished() {
		return shefinished;
	}

	public void setshefinished(Boolean shefinished) {
		this.shefinished = shefinished;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	public List<Integer> getChosenAnswers(){
		return chosenanswers;
	}
	
	public void setChosenAnswers(List<Integer> chosenanswers) {
		this.chosenanswers = chosenanswers;
	}

//	public List<Integer> getQuestionsSolved() {
//		return questionsSolved;
//	}
//
//	public void setQuestionsSolved(List<Integer> questionsSolved) {
//		this.questionsSolved = questionsSolved;
//	}

//	public void choseanswerforquestion(List<Question> questions, List<Integer> chosenanswers)
//	{
//		int chosen;
//		List<Integer> answerstemp = chosenanswers;
//		for (Question question : questions)
//		{
//			chosen = answerstemp.remove(0);
//			Pair<Question, Integer> onePair = new Pair<>(question, chosen);
//			this.questionsSolved.add(onePair);
//		}
//	
//	}

}