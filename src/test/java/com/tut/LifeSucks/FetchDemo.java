package com.tut.LifeSucks;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FetchDemo {
	public static void main(String[] args) {
		System.out.println("Running fetchDemo");
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession(); // not idea , using singletone is ideal

		Student student = (Student)session.get(Student.class, 12);//Takes the classname and primary key parameter
			//Explict typecast because it returns an object class
		System.out.println(student);
		

		session.close();
		factory.close();
	}
}
