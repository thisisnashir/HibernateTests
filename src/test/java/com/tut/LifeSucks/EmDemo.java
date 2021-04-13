package com.tut.LifeSucks;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmDemo {

	public static void main(String[] args) {
		 System.out.println("Running this");
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		Student student1 = new Student(11, "Abdul", "barisal", new Certificate("CSE", "week"));
		Student student2 = new Student(13, "Brovim", "chittagong", new Certificate("EEE", "month"));
		 System.out.println(student1);
		 System.out.println(student2);

		session.beginTransaction();
		session.save(student1);
		session.save(student2);

		session.getTransaction().commit();

		session.close();
		factory.close();
	}

}
