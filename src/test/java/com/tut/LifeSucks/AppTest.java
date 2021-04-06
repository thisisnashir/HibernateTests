package com.tut.LifeSucks;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }
    
    public static void main(String[] args)
    {
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
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
