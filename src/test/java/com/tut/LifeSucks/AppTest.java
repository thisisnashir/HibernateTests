package com.tut.LifeSucks;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("something is going on here");
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		System.out.println(factory.isClosed());

		Student st = new Student();
		st.setId(12);
		st.setName("dsfr");
		st.setCity("afsdka");

		Session session = factory.openSession();

		session.beginTransaction();
		session.save(st);

		session.getTransaction().commit();

		session.close();

		// lets save Address object

		Address address = new Address();
		address.setOpen(true);
		address.setCity("Dhaka");
		address.setAddedDate(new Date());
		address.setX(5.00);

		FileInputStream fis = new FileInputStream("src/test/java/meme.jpg");
		byte[] data = new byte[fis.available()];
		fis.read(data);
		address.setImage(data);

		session = factory.openSession();
		session.beginTransaction();
		session.save(address);

		session.getTransaction().commit();

		session.close();

		System.out.println("DONE ADDING ADDRESS WITH THE IMAGE");

	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
}
