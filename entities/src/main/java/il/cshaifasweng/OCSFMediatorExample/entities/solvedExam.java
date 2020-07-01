package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.File;
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

import il.cshaifasweng.OCSFMediatorExample.databaseinitilize.InitlizeDataBase;

////we may need id for solvedexam
@Entity
@Table(name = "solvedexam")
public class solvedExam implements Serializable {

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
	
	private String generalCommentString;
	private String explainationForGradeChanging;

	private File file;
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
		this.chosenanswers = chosenanswers;
		checkedornot = false;
		this.shefinished = shefinished;
		Grade = 0;
		file = null;
	}

	public solvedExam() {
		file = null;
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

	public List<Integer> getChosenAnswers() {
		return chosenanswers;
	}

	public void setChosenAnswers(List<Integer> chosenanswers) {
		this.chosenanswers = chosenanswers;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public static List<solvedExam> getSolvedExamByStudentID(String student) {

		List<solvedExam> solvedExams = InitlizeDataBase.getSolvedExamByStudentID(student);
		return solvedExams;
	}

	///////////////////////////////////////////

	public solvedExam getsolvedExamBySolvedId(int solved) {
		System.out.println("bbbbbbbbbbbbbb");
		Exam exam = new Exam();
		List<Integer> chosenanswer = new ArrayList<Integer>();
		solvedExam chosenSolvedExam = new solvedExam();
		List<solvedExam> solvedexams = new ArrayList<solvedExam>();
		solvedexams = InitlizeDataBase.getallsolvedExam();
		for (solvedExam solvedexam : solvedexams) {

			if (solvedexam.getId() == solved) {
				System.out.println("bifbbbb");

				System.out.println(solvedexam.getGrade());
				System.out.println(solvedexam.getId());
				System.out.println(solvedexam.getshefinished());
				System.out.println(solvedexam.getcheckedornot());

				chosenSolvedExam = solvedexam;
				List<Question> allexamQuestions = new ArrayList<Question>();
				List<Double> graded = new ArrayList<Double>();

				exam = chosenSolvedExam.getExam();

				int choose_answer;
				String questionString;
				String answer0String;
				String answer1String;
				String answer2String;
				String answer3String;
				double grade;
				String teatchercomment;
				String studentStringcomment;
				chosenanswer = chosenSolvedExam.getChosenAnswers();
				allexamQuestions = exam.getQuestions();
				graded = exam.getGrades();
				for (int i = 0; i < allexamQuestions.size(); i++) {
					questionString = exam.getQuestions().get(i).getQuestion();
					answer0String = allexamQuestions.get(i).getAnswers().get(0).getAnswer();
					answer1String = allexamQuestions.get(i).getAnswers().get(1).getAnswer();
					answer2String = allexamQuestions.get(i).getAnswers().get(2).getAnswer();
					answer3String = allexamQuestions.get(i).getAnswers().get(3).getAnswer();

					teatchercomment = exam.getTeacherComment().get(i);
					studentStringcomment = exam.getStudentComment().get(i);
					grade = exam.getGrades().get(i);

					System.out.println(exam.getQuestions().get(i).getQuestion());
					System.out.println(allexamQuestions.get(i).getAnswers().get(0).getAnswer());
					System.out.println(allexamQuestions.get(i).getAnswers().get(1).getAnswer());
					System.out.println(allexamQuestions.get(i).getAnswers().get(2).getAnswer());
					System.out.println(allexamQuestions.get(i).getAnswers().get(3).getAnswer());
					System.out.println(exam.getTeacherComment().get(i));
					System.out.println(exam.getStudentComment().get(i));
					System.out.println(exam.getGrades().get(i));
					choose_answer = chosenanswer.get(i);

				}

			}

		}

		return chosenSolvedExam;

	}

//	public static List<solvedExam> getsolvedExamByStudentId(String studentid) {
//		System.out.println("bbbbbbbbbbbbbb");
//		List<solvedExam> chosenSolvedExam = new  ArrayList<solvedExam>();
//		System.out.println("cccc");
//		List<solvedExam> solvedexams = new ArrayList<solvedExam>();
//		System.out.println("dddd");
//		solvedexams = InitlizeDataBase.getallsolvedExam();
//		System.out.println("eeeeeee");
//		for (solvedExam solvedexam : solvedexams) {
//			if (Integer.toString(solvedexam.getStudent().getId()).equals(studentid) && solvedexam.getcheckedornot()==true) {
//				System.out.println("fffff");
//				chosenSolvedExam.add(solvedexam);
//			}
//
//		}
//		System.out.println("hhhhh");
//		return chosenSolvedExam;
//	
//	
//	
//	
//	}
	
	
	
	
	public static List<solvedExam> getsolvedExamByStudentId(String studentid) {
		System.out.println("bbbbbbbbbbbbbb");
		Exam exam = new Exam();
		List<Integer> chosenanswer = new ArrayList<Integer>();
		List<solvedExam> chosenSolvedExam = new  ArrayList<solvedExam>();
		List<solvedExam> solvedexams = new ArrayList<solvedExam>();
		System.out.println("cccccccccccc");
		File file;
		solvedexams = InitlizeDataBase.getallsolvedExam();
		
		System.out.println("ddddddddddddd");
		for (solvedExam solvedexam : solvedexams) {

			if (Integer.toString(solvedexam.getStudent().getId()).equals(studentid) && solvedexam.getcheckedornot()==true) {
				System.out.println("bifbbbb");

				System.out.println(solvedexam.getGrade());
				System.out.println(solvedexam.getId());
				System.out.println(solvedexam.getshefinished());
				System.out.println(solvedexam.getcheckedornot());

				chosenSolvedExam.add(solvedexam);
				List<Question> allexamQuestions = new ArrayList<Question>();
				List<Double> graded = new ArrayList<Double>();

				exam = solvedexam.getExam();

				int choose_answer;
				String questionString;
				String answer0String;
				String answer1String;
				String answer2String;
				String answer3String;
				double grade;
				String teatchercomment;
				String studentStringcomment;
				chosenanswer = solvedexam.getChosenAnswers();
				allexamQuestions = exam.getQuestions();
				graded = exam.getGrades();
				file=solvedexam.getFile();
				
				for (int i = 0; i < allexamQuestions.size(); i++) {
					questionString = exam.getQuestions().get(i).getQuestion();
					answer0String = allexamQuestions.get(i).getAnswers().get(0).getAnswer();
					answer1String = allexamQuestions.get(i).getAnswers().get(1).getAnswer();
					answer2String = allexamQuestions.get(i).getAnswers().get(2).getAnswer();
					answer3String = allexamQuestions.get(i).getAnswers().get(3).getAnswer();

					teatchercomment = exam.getTeacherComment().get(i);
					studentStringcomment = exam.getStudentComment().get(i);
					grade = exam.getGrades().get(i);

					System.out.println(exam.getQuestions().get(i).getQuestion());
					System.out.println(allexamQuestions.get(i).getAnswers().get(0).getAnswer());
					System.out.println(allexamQuestions.get(i).getAnswers().get(1).getAnswer());
					System.out.println(allexamQuestions.get(i).getAnswers().get(2).getAnswer());
					System.out.println(allexamQuestions.get(i).getAnswers().get(3).getAnswer());
					System.out.println(exam.getTeacherComment().get(i));
					System.out.println(exam.getStudentComment().get(i));
					System.out.println(exam.getGrades().get(i));
					choose_answer = chosenanswer.get(i);

				}
					
			}

		}

		return chosenSolvedExam;

	}

	public String getGeneralCommentString() {
		return generalCommentString;
	}

	public void setGeneralCommentString(String generalCommentString) {
		this.generalCommentString = generalCommentString;
	}

	public String getExplainationForGradeChanging() {
		return explainationForGradeChanging;
	}

	public void setExplainationForGradeChanging(String explainationForGradeChanging) {
		this.explainationForGradeChanging = explainationForGradeChanging;
	}
	
	
	
	
///////////////////////////////////////////////////////////////////////////////////////
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