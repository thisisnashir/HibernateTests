package com.map;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tut.LifeSucks.Certificate;
import com.tut.LifeSucks.Student;

public class MapDemo {

	public static void main(String[] args) {
		System.out.println("Running this");
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Answer answer1 = new Answer(22, "Dhaka");
		Answer answer2 = new Answer(88, "Delhi");
		List<Answer> answers = new ArrayList<Answer>();
		answers.add(answer1);
		answers.add(answer2);
		Question question1 = new Question(11, "Name a capital!", answers);
		answer1.setQuestion(question1);
		answer2.setQuestion(question1);

		System.out.println(question1);
		System.out.println(answer1);
		System.out.println(answer2);

		session.beginTransaction();
		session.save(question1);
		session.save(answer1);
		session.save(answer2);

		session.getTransaction().commit();

		// Lets see if we can get all the answers of the question and vice versa

		Question question = session.get(Question.class, 11);
		System.out.println("Printing question : " + question);
		answers = question.getAnswers();
		System.out.println("Printing answers ... ");
		for (Answer each : answers) {
			System.out.println(each);
		}
		
		Answer answer = (Answer)session.get(Answer.class, 22);
		System.out.println("Getting question from answer: " + answer.getQuestion() );

		session.close();
		factory.close();
	}
}
