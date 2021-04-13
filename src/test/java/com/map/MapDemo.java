package com.map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tut.LifeSucks.Certificate;
import com.tut.LifeSucks.Student;

public class MapDemo {
	
	public static void main(String[] args)
	{
		 System.out.println("Running this");
			SessionFactory factory = new Configuration().configure().buildSessionFactory();
			Session session = factory.openSession();

			Answer answer1 = new Answer(22,"Dhaka");
			Question question1 = new Question(11, "What is the capital of bangladesh?",answer1);
			answer1.setQuestion(question1);
			
			Answer answer2 = new Answer(88,"Delhi");
			Question question2 = new Question(10, "What is the capital of india?",answer2);
			answer2.setQuestion(question2);
			
			
			 System.out.println(question1);
			 System.out.println(answer1);
			 
			 System.out.println(question2);
			 System.out.println(answer2);


			session.beginTransaction();
			session.save(question1);
			session.save(answer1);
			
			session.save(question2);
			session.save(answer2);

			session.getTransaction().commit();

			session.close();
			factory.close();
	}
}
