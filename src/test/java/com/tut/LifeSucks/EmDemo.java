package com.tut.LifeSucks;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EmDemo {

	public static void main(String[] args) {
		 System.out.println("Running this");
		 
		 Student student1 = new Student(11, "Abdul", "barisal", new Certificate("CSE", "week"));
			Student student2 = new Student(13, "Brovim", "chittagong", new Certificate("EEE", "month"));
			//transient state
		 
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		
		 System.out.println(student1);
		 System.out.println(student2);

		session.beginTransaction();
		session.save(student1);
		session.save(student2);
		//Persistent state
		student1.setName("John");//the change will be reflected in DB because its in Persistent state

		session.getTransaction().commit();

		session.close();
		//Detached state
		student1.setName("Jamil");//the change will NOT be reflected in DB because its in Detached state
		
		factory.close();
	}

}
