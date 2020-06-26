package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.Calendar;  
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name = "answer")
public class Answer implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;
	
	
	private String answer;
		
	private Calendar firstAppearance;
	
	///Constructors
		public Answer(String answer)
		{
			this.answer = answer;
			firstAppearance = Calendar.getInstance();
		}
		
		public Answer() {
			
			firstAppearance = Calendar.getInstance();
			
		}

		
		///Methods
		
		public String getAnswer() {
			return answer;
		}

		
		public void setAnswer(String answer) {   
			
			this.answer = answer;
		}
		
		public void setQuestion(Question q) {
			this.question = q;
		}
		
		
		public Question getQuestion() {
			return question;
		}
		
		
		public int getId() {
			return id;
		}
		public void setId(int id)
		{
			this.id = id;
		}


		public Calendar getFirstAppearance() {
			return firstAppearance;
		}

		

}
