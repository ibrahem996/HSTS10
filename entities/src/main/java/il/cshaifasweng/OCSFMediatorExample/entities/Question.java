package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;   
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mysql.cj.xdevapi.Statement;

import il.cshaifasweng.OCSFMediatorExample.databaseinitilize.InitlizeDataBase;


@Entity
@Table(name = "question")
public class Question implements Serializable{
	

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		

		int studentAnswer = 0;
	
		String questionString;
		
		@OneToMany (cascade = CascadeType.ALL, mappedBy = "question")
		private List<Answer> answers ;
		
	
		@Column(name="TheAnswer")
		private int correctAnswer;

		@ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
		@JoinColumn(name = "subject_id")
		private Subject subject;
		
		@ManyToMany (mappedBy = "questions",cascade = {CascadeType.PERSIST,CascadeType.MERGE},targetEntity = Exam.class)
		private List<Exam> exams;
		
	
		
		
		private Calendar firstAppearance;
		
		
		//constructor
		public Question(String question,Answer answer1,Answer answer2,Answer answer3, Answer answer4,int flagCorrect,Subject subject)
		{
			int questionNum = subject.getNum(); 
			answers = new  ArrayList<Answer>();
		
			this.correctAnswer = flagCorrect;
			
			this.subject = subject;
			
			this.questionString = question;
			
			answers.add(answer1);
			answers.add(answer2);
			answers.add(answer3);
			answers.add(answer4);
			
			firstAppearance = Calendar.getInstance();
			
			answer1.setQuestion(this);
			answer2.setQuestion(this);
			answer3.setQuestion(this);
			answer4.setQuestion(this);	
			
			this.id = this.subject.getId() * 1000 + questionNum; 
			this.subject.setNum(); 
			studentAnswer= 0;
		}

		public Question() {
			studentAnswer= 0;
		}
		
		//Methods

		public int getId()
		{
		return id;
		}
				
				
		public String getQuestion() 
		{	
		return questionString;
		}
				
		
		public void setId(int id)
		{
			this.id = id;
		}
				
		public void setQuestion(String questionStr) 
		{
			this.questionString = questionStr;
		}
		
		public void setCorrectAnswer(int correct) 
		{
			this.correctAnswer = correct;
		}
		
		public int getCorrectAnswer() 
		{	
		return this.correctAnswer;
		}
				
				
		public List<Answer> getAnswers() 
		{
			return answers;
		}
				
				

				
		public List<Exam> getExams() {
			return exams;
		}

		public Subject getSubject()
		{
			return this.subject;
		}

		public void setExams(List<Exam> exams) {
			this.exams = exams;
		}
		public Calendar getFirstAppearance() 
		{
			return firstAppearance;
		}
		public int getStudentAnswer() {
			return studentAnswer;
		}

		public void setStudentAnswer(int studentAnswer) {
			this.studentAnswer = studentAnswer;
		}


		
		
		
		
		public Question getQuestionbyId(int QuestionId)
		{
			Question chosenQuestion = null;
			List<Question> Questions = new  ArrayList<Question>();
			Questions = InitlizeDataBase.getAllQuestions();
			for(Question question : Questions)
			{
				if (QuestionId == question.getId())
				{
					chosenQuestion = question;
				}
			}	
			return chosenQuestion;
			
		}

}
		
		
		
		
		
		

