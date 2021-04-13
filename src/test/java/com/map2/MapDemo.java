package com.map2;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tut.LifeSucks.Certificate;
import com.tut.LifeSucks.Student;

public class MapDemo {

	public static void main(String[] args) {
		System.out.println("Running MapDemo ManyToMany..");
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		List<Project> projects1 = new ArrayList<Project>();
		List<Project> projects2 = new ArrayList<Project>();
		List<Employee> employees1 = new ArrayList<Employee>();
		List<Employee> employees2 = new ArrayList<Employee>();

		Project project1 = new Project(22, "Hibernate", employees1);
		Project project2 = new Project(88, "Sprint",employees2);
		
		Employee employee1 = new Employee(23, "Nahid", projects1);
		Employee employee2 = new Employee(82, "Nabil",projects2);
		
		projects1.add(project1);
		projects2.add(project1);
		projects2.add(project2);
		
		employees1.add(employee1);
		employees2.add(employee1);
		employees2.add(employee2);
		
		project1.setEmployee(employees1);
		project2.setEmployee(employees2);
		
		employee1.setProjects(projects1);
		employee2.setProjects(projects2);


		session.beginTransaction();
		session.save(project1);
		session.save(project2);
		session.save(employee1);
		session.save(employee2);

		session.getTransaction().commit();

		session.close();
		factory.close();
	}
}
