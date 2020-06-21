package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.ArrayList;   
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mysql.cj.xdevapi.Statement;


@Entity
@Table(name = "question")
public class Question implements Serializable{
	

		@Id
		private int id;
		
		String questionString;
		
		@OneToMany (cascade = CascadeType.ALL, mappedBy = "question")
		private List<Answer> answers ;
		
		@OneToOne 
		@JoinColumn(name = "answer_id",referencedColumnName = "id")
		private Answer correctAnswer;

		@ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
		@JoinColumn(name = "subject_id")
		private Subject subject;
		
		@ManyToMany (mappedBy = "questions",cascade = {CascadeType.PERSIST,CascadeType.MERGE},targetEntity = Exam.class)
		private List<Exam> exams;
		
	/*
	 * private String studentComment;
	 *                                        maybe those not here?
	 * private String teacherComment;
	 */
		
		
		private Calendar firstAppearance;
		
		
		//constructor
		public Question(String question,Answer answer1,Answer answer2,Answer answer3, Answer answer4,int flagCorrect,Subject subject)
		{
			int questionNum = subject.getNum(); 
			answers = new  ArrayList<Answer>();
			switch(flagCorrect)
			{
			case 1:
				correctAnswer = answer1;
				break;
			case 2:
				correctAnswer = answer2;
				break;
			case 3:
				correctAnswer = answer3;
				break;
			case 4:
				correctAnswer = answer4;
				break;
			default:
				throw new NullPointerException("demo");
			}
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
		}

		public Question() {
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
				
				
		public void setQuestion(String questionStr) 
		{
			this.questionString = questionStr;
		}
				
				
		public List<Answer> getAnswers() 
		{
			return answers;
		}
				
				/*
				public void setAnswers(List<Answer> answers) {        ////////remember to add this to the final project
					if (answers.size())
					this.answers = answers;
				}
				*/
				
				
		public Answer getCorrectAnswer() 
		{
		return correctAnswer;
		}
				
				
		public void setCorrectAnswer(int id) 
		{ 
			for (Answer answer : answers) 
			{
				if(answer.getId() == id)
			 	{
						  		this.correctAnswer = answer;
				}
			}
				
		}
		
	/*
	 * public String getStudentComment() { return studentComment; }
	 * 
	 * public void setStudentComment(String studentComment) { this.studentComment =
	 * studentComment; }
	 * 
	 * public String getTeacherComment() { return teacherComment; }
	 * 
	 * public void setTeacherComment(String teacherComment) { this.teacherComment =
	 * teacherComment; }
	 */
				
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


}
		
		
		
		
		
		

