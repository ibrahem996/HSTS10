package il.cshaifasweng.OCSFMediatorExample.databaseinitilize;


import java.util.ArrayList;   
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import il.cshaifasweng.OCSFMediatorExample.entities.Answer;
import il.cshaifasweng.OCSFMediatorExample.entities.Course;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Manager;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import il.cshaifasweng.OCSFMediatorExample.entities.checkedExam;
import il.cshaifasweng.OCSFMediatorExample.entities.solvedExam;



public class InitlizeDataBase {
	
	private static Question theChosenQuestiontoEdit;
	private static Answer theChosenAnswertoEdit;
	private static Session session;
	private static int i=0;

	
	
	
	
	private static SessionFactory getSessionFactory() throws HibernateException  {

		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Exam.class);
		configuration.addAnnotatedClass(Question.class);
		configuration.addAnnotatedClass(Answer.class);
		configuration.addAnnotatedClass(Subject.class);
		configuration.addAnnotatedClass(Course.class);
		configuration.addAnnotatedClass(Teacher.class);
		configuration.addAnnotatedClass(Student.class);
		configuration.addAnnotatedClass(Manager.class);
		configuration.addAnnotatedClass(checkedExam.class);
		configuration.addAnnotatedClass(solvedExam.class);



		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
		
	}

	public static void initializeData() {										

		//Subjects 
		
		Subject math = new Subject(15,"Math");
		Subject physics = new Subject(75,"Physics");
		Subject history = new Subject(85,"History");
		Subject sport = new Subject(95,"Sport");
		
		session.save(math);
		session.save(physics);
		session.save(history);
		session.save(sport);

		session.flush();

		
		
		
		//Courses
		
		Course algebra = new Course(60,math,"Alegbra");
		Course calculos = new Course(61,math,"calculos");
		Course historyafterhumans = new Course(62,history,"HistoryAfterHumans");
		Course football = new Course(63,sport,"Football");
		Course calesthinics = new Course(64,sport,"Calesthinics");
		Course wresting = new Course(65,sport,"Wrestling");
		Course mechanics = new Course(66,physics,"Mechanics");
		Course space = new Course(67,physics,"Space");
		
		session.save(algebra);
		session.save(calculos);
		session.save(historyafterhumans);
		session.save(football);
		session.save(calesthinics);
		session.save(wresting);
		session.save(mechanics);
		session.save(space);

		session.flush();
		
		
		//Students
		
		Student shadi = new Student(7,"shadicr7","rm4ever","shadi", "halloun");
		Student noor = new Student(9,"noorking","loveknafe","noor", "khmaiasy");
		Student ibrahim = new Student(17,"penguinLover","elNasMsale7","ibrahim", "hmad");
		Student tareq = new Student(19,"tareq123","1234","tareq", "saleh");
		Student lilyana = new Student(20,"lilyana321","4321","lilyana", "khateeb");
		Student malek = new Student(2,"malek2","2222","malik", "egbariye");

		session.save(shadi);
		session.save(noor);
		session.save(ibrahim);
		session.save(tareq);
		session.save(lilyana);
		session.save(malek);

		session.flush();
		
		
		
		
		//Teachers
		
		Teacher malki = new Teacher(1,"malki123","123","malki", "grossman");
		Teacher oren = new Teacher(2,"oren","12","oren", "wayman");
		Teacher erez = new Teacher(3,"erezloveibrahim","555","erez", "hmad");
		Teacher ronen = new Teacher(4,"ronenhestbrot","100","ronen", "shaltel");
		
		session.save(malki);
		session.save(oren);
		session.save(erez);
		session.save(ronen);

		session.flush();

		history.addTeachertoSubject(malki);
		physics.addTeachertoSubject(oren);
		sport.addTeachertoSubject(erez);
		math.addTeachertoSubject(ronen,oren);
		
		session.save(history);
		session.save(physics);
		session.save(sport);
		session.save(math);
		
		session.flush();

		
		malki.addCoursestoTeacher(historyafterhumans);
		oren.addCoursestoTeacher(space,mechanics);
		erez.addCoursestoTeacher(football,calesthinics);
		ronen.addCoursestoTeacher(algebra,calculos);
		
		session.save(malki);
		session.save(oren);
		session.save(erez);
		session.save(ronen);
		
		session.flush();
		
		
		//manager
		
		Manager zidane = new Manager(10, "zizou", "matartzi", "zinadine", "zidane");
		session.save(zidane);
		
		session.flush();

		
		shadi.addCoursestoPerson(space,mechanics,football,calesthinics);
		tareq.addCoursestoPerson(algebra,space,football);
		ibrahim.addCoursestoPerson(football,calculos);
		malek.addCoursestoPerson(algebra,calculos);
		lilyana.addCoursestoPerson(historyafterhumans,calculos);
		noor.addCoursestoPerson(historyafterhumans,algebra);
		
		session.save(shadi);
		session.save(tareq);
		session.save(ibrahim);
		session.save(malek);
		session.save(lilyana);
		session.save(noor);

		
		session.flush();

/*
		try {
			i++;
			Answer answer1_1 = new Answer();
			Answer answer2_1= new Answer();
			Answer answer3_1 = new Answer();
			Answer answer4_1 = new Answer();
			
			Question q1 = new Question("What is the capital of Italy ?",answer1_1,answer2_1,answer3_1,answer4_1,1,history);
			answer1_1.setAnswer("rome");
			answer2_1.setAnswer("Prague");
			answer3_1.setAnswer("Tel-Aviv");
			answer4_1.setAnswer("Kfr-Kna");
			q1.setId(q1.getSubject().getId() * 1000 + q1.getSubject().getNum());
			
			answer1_1.setId(q1.getId()*10+1);
			answer2_1.setId(q1.getId()*10+2);
			answer3_1.setId(q1.getId()*10+3);
			answer4_1.setId(q1.getId()*10+4);
			session.save(q1);
			
			session.save(answer1_1);
			session.save(answer2_1);
			session.save(answer3_1);
			session.save(answer4_1);

			session.flush();
			
		} catch (NullPointerException e) 
		{
			
			System.out.println("\nCorrect answer must be from 1 to 4 at question:" + i + " , please try to edit the correct answer and reconnect to the server manually !!!\n");				
		}
	
		try {
		i++;
		Answer answer1_2 = new Answer();
		Answer answer2_2= new Answer();
		Answer answer3_2 = new Answer();
		Answer answer4_2 = new Answer();

		
		Question q2 = new Question("which of the following numbers are primary ?",answer1_2,answer2_2,answer3_2,answer4_2,3,math);
		answer1_2.setAnswer("39");
		answer2_2.setAnswer("21");
		answer3_2.setAnswer("2");
		answer4_2.setAnswer("145");
		q2.setId(q2.getSubject().getId() * 1000 + q2.getSubject().getNum());

		answer1_2.setId(q2.getId()*10+1);
		answer2_2.setId(q2.getId()*10+2);
		answer3_2.setId(q2.getId()*10+3);
		answer4_2.setId(q2.getId()*10+4);
		session.save(q2);
		
		session.save(answer1_2);
		session.save(answer2_2);
		session.save(answer3_2);
		session.save(answer4_2);

		session.flush();
		} catch (NullPointerException e) 
		{
			
			System.out.println("\nCorrect answer must be from 1 to 4 at question:" + i + " , please try to edit the correct answer and reconnect to the server manually !!!\n");				
		}

		try {
		i++;
		Answer answer1_3 = new Answer();
		Answer answer2_3= new Answer();
		Answer answer3_3 = new Answer();
		Answer answer4_3 = new Answer();

		
		Question q3 = new Question("Which year the War World 2 ended ?",answer1_3,answer2_3,answer3_3,answer4_3,3,history);
		answer1_3.setAnswer("1943");
		answer2_3.setAnswer("1944");
		answer3_3.setAnswer("1945");
		answer4_3.setAnswer("1946");
		q3.setId(q3.getSubject().getId() * 1000 + q3.getSubject().getNum());

		answer1_3.setId(q3.getId()*10+1);
		answer2_3.setId(q3.getId()*10+2);
		answer3_3.setId(q3.getId()*10+3);
		answer4_3.setId(q3.getId()*10+4);
		session.save(q3);
		
		session.save(answer1_3);
		session.save(answer2_3);
		session.save(answer3_3);
		session.save(answer4_3);

		session.flush();
		} 
		catch (NullPointerException e) 
			{
			System.out.println("\nCorrect answer must be from 1 to 4 at question:" + i + " , please try to edit the correct answer and reconnect to the server manually !!!\n");				
			}
		try {
		i++;
		Answer answer1_4 = new Answer();
		Answer answer2_4= new Answer();
		Answer answer3_4 = new Answer();
		Answer answer4_4 = new Answer();

		
		Question q4 = new Question("Which team has the most Champion Leagues titles ?",answer1_4,answer2_4,answer3_4,answer4_4,4,sport);
		answer1_4.setAnswer("Man-Utd");
		answer2_4.setAnswer("AC-Milan");
		answer3_4.setAnswer("Liverpool");
		answer4_4.setAnswer("Real Madrid");
		q4.setId(q4.getSubject().getId() * 1000 + q4.getSubject().getNum());
		answer1_4.setId(q4.getId()*10+1);
		answer2_4.setId(q4.getId()*10+2);
		answer3_4.setId(q4.getId()*10+3);
		answer4_4.setId(q4.getId()*10+4);
		session.save(q4);
		
		session.save(answer1_4);
		session.save(answer2_4);
		session.save(answer3_4);
		session.save(answer4_4);

		session.flush();
		
		} catch (NullPointerException e) 
		{
			System.out.println("\nCorrect answer must be from 1 to 4 at question:" + i + " , please try to edit the correct answer and reconnect to the server manually !!!\n");				
		}

		try {
		i++;
		Answer answer1_5 = new Answer();
		Answer answer2_5= new Answer();
		Answer answer3_5 = new Answer();
		Answer answer4_5 = new Answer();

		
		Question q5 = new Question("Which of these movies has the most Oscar awards ?",answer1_5,answer2_5,answer3_5,answer4_5,2,history);
		answer1_5.setAnswer("Avengers EndGame");
		answer2_5.setAnswer("Titanic");
		answer3_5.setAnswer("Interstellar");
		answer4_5.setAnswer("Harry Potter and the Chamber of secrets");
		q5.setId(q5.getSubject().getId() * 1000 + q5.getSubject().getNum());
		answer1_5.setId(q5.getId()*10+1);
		answer2_5.setId(q5.getId()*10+2);
		answer3_5.setId(q5.getId()*10+3);
		answer4_5.setId(q5.getId()*10+4);

		
		session.save(q5);
		
		session.save(answer1_5);
		session.save(answer2_5);
		session.save(answer3_5);
		session.save(answer4_5);

		session.flush();

		} catch (NullPointerException e) 
		{
			System.out.println("\nCorrect answer must be from 1 to 4 at question:" + i + " , please try to edit the correct answer and reconnect to the server manually !!!\n");				
		}
		
		try {
		i++;
		Answer answer1_6 = new Answer();
		Answer answer2_6= new Answer();
		Answer answer3_6 = new Answer();
		Answer answer4_6 = new Answer();

		
		Question q6 = new Question("Which of these actors has never won the oscar ?",answer1_6,answer2_6,answer3_6,answer4_6,3,history);
		answer1_6.setAnswer("Leonardo Di Caprio");
		answer2_6.setAnswer("Johny Depp");
		answer3_6.setAnswer("Al pacino");
		answer4_6.setAnswer("Brad Pitt");
		q6.setId(q6.getSubject().getId() * 1000 + q6.getSubject().getNum());

		answer1_6.setId(q6.getId()*10+1);
		answer2_6.setId(q6.getId()*10+2);
		answer3_6.setId(q6.getId()*10+3);
		answer4_6.setId(q6.getId()*10+4);

		session.save(q6);
		
		session.save(answer1_6);
		session.save(answer2_6);
		session.save(answer3_6);
		session.save(answer4_6);

		session.flush();
		} catch (NullPointerException e) 
		{
			System.out.println("\nCorrect answer must be from 1 to 4 at question:" + i + " , please try to edit the correct answer and reconnect to the server manually !!!\n");				
		}
		
		*/
		session.getTransaction().commit();

	}

	
	public static <T> List<T> getAll(Class<T> object) 
	{
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
		Root<T> rootEntry = criteriaQuery.from(object);
		CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);
		TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
		return allQuery.getResultList();
	}
	
	public static List<Teacher> getAllteachers()
	{
		List<Teacher> teachers = new  ArrayList<Teacher>();
		teachers = getAll(Teacher.class);
		return teachers;
	}
	
	public static List<Question> getAllQuestions()
	{
		List<Question> questions = new  ArrayList<Question>();
		questions = getAll(Question.class);
		return questions;
	}

	
	
	public static String getAllQuestionsAnswers() throws Exception 
	{
		
		int answerNum=0;
		List<Question> questions = new  ArrayList<Question>();
		List<Answer> answers = new  ArrayList<Answer>();
		questions = getAll(Question.class);
		String answersStrings = "";
		String allQuestionString = "";
		for (Question question : questions) 
		{
			answers = question.getAnswers();																	      
			for (Answer answer : answers)
			{	answerNum++;
				answersStrings = answersStrings + "	- " + answerNum + ") " + answer.getAnswer() +"    -id:(" + answer.getId() + ")"  + "\n";
			}
			answerNum = 0;
			allQuestionString  = allQuestionString + "<"+question.getId()+">"+question.getQuestion()+ "\n" ;
			allQuestionString = allQuestionString + answersStrings + "\n";
			answersStrings ="";
		}
		
		return allQuestionString;
	}
	
	public static boolean checkValidId(int id)
	{
		List<Question> questions1 = new  ArrayList<Question>();
		questions1 = getAll(Question.class);
		for (Question question1 : questions1) 
		{
			  	if(question1.getId() == id)
			  	{
			  		theChosenQuestiontoEdit = question1;
			  		return true;
			  	}
		}
		return false;
	}
	
	public static boolean checkValidIdAnswer(int id,int question_num)
	{
		List<Question> questions1 = new  ArrayList<Question>();
		questions1 = getAll(Question.class);
		List<Answer> answers = new  ArrayList<Answer>();
		for (Question question1 : questions1) 
		{
			  	if(question1.getId() == question_num)
			  	{
			  		theChosenQuestiontoEdit = question1;
			  	}
		}
		answers = theChosenQuestiontoEdit.getAnswers();
		for (Answer answer : answers) 
		{
			  	if(answer.getId() == id)
			  	{
			  		theChosenAnswertoEdit = answer;
			  		return true;
			  	}
		}
		return false;
	}
	
	
	public static void editingTheQuestion(int id, String newQuestion)
	{
		if(theChosenQuestiontoEdit.getId() == id)
		{
			session.beginTransaction();
			theChosenQuestiontoEdit.setQuestion(newQuestion);
	     	session.save(theChosenQuestiontoEdit);
			session.flush();
			session.getTransaction().commit();
		}
	}
	
	public static void editingTheAnswer(int id, String newAnswer)
	{
		if(theChosenAnswertoEdit.getId() == id)
		{
			session.beginTransaction();
			theChosenAnswertoEdit.setAnswer(newAnswer);
	     	session.save(theChosenAnswertoEdit);
			session.flush();
			session.getTransaction().commit();
		}
	}
		
	public static String gettingEditedQuestion()
	{
		
		String theQuestion;
		theQuestion = theChosenQuestiontoEdit.getQuestion();
		return theQuestion;
	}
	
	public static void choosingCorrectAnswer(int id)
	{
		session.beginTransaction();
		theChosenQuestiontoEdit.setCorrectAnswer(id);
     	session.save(theChosenQuestiontoEdit);
		session.flush();
		session.getTransaction().commit();
	}
	
	public static String gettinQuestionbyID(int id)
	{
		List<Question> questions1 = new  ArrayList<Question>();
		questions1 = getAll(Question.class);
		for (Question question1 : questions1) 
		{
			  	if(question1.getId() == id)
			  	{
			  		theChosenQuestiontoEdit = question1;
			  	}
		}
		String theQuestion;
		theQuestion = theChosenQuestiontoEdit.getQuestion();
		return theQuestion;
	}
	
	public static String gettingAnswers()
	{
		int answerNum = 0;
		List<Answer> answers = new  ArrayList<Answer>();
		String theAnswers = "\n";
		answers = theChosenQuestiontoEdit.getAnswers();
		for (Answer answer : answers)
		{	answerNum++;
			theAnswers = theAnswers + "	- " + answerNum + ") " + answer.getAnswer() +"    -id:(" + answer.getId() + ")"  + "\n";
		}
		return theAnswers;
	}

	public static void CloseSession()
	{
		session.close();

	}

	public static List<Exam> getAllexams() {
		List<Exam> exams = new ArrayList<Exam>();
		exams = getAll(Exam.class);
		return exams;
	}
	
	public static List<Subject> getAllSubects() {
		List<Subject> subjects = new ArrayList<Subject>();
		subjects = getAll(Subject.class);
		return subjects;
	}
	
	public static List<Course> getallCourses() {
		List<Course> courses = new ArrayList<Course>();
		courses = getAll(Course.class);
		return courses;
	}
	
	public static void LoadData(String[] args,int flag) {
		try {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();

			if (flag == 1)
			{
			session.beginTransaction();
			initializeData();
			}
			else {		    
				session.beginTransaction();       //////love u session transicion
			}
			
			
		}
		catch (Exception exception) 
		{
			if (session != null)
			{
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			
			
		}
	}
	
	public static Session getSession()
	{
		return session;
	}

	
	
	
}

