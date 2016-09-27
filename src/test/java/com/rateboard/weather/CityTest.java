package com.rateboard.weather;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.rateboard.weather.entity.City;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CityTest extends TestCase {
	static SessionFactory sessionFactory;

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public CityTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(CityTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}

	/*
	 * "http://api.wunderground.com/api/76d7b6a0e22e37bb/forecast10day/q/CA/
	 * San_Francisco.json
	 */
	public static void main(String[] argc) {
		Session session = Application.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		City city = new City();
		city.setCountry("CA");
		city.setName("San_Francisco");
		session.save(city);
		transaction.commit();
		session.close();
		System.out.println("test main");
	}


	/* Method to READ all the employees */
	public void listEmployees() {
		Session session = Application.getSessionFactory().openSession();
		Transaction tx = null;
		System.out.print("listEmployees: ");
		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM com.rateboard.weather.entries.City").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				City city = (City) iterator.next();
				System.out.print("First Name: " + city.getName());
				System.out.print("  Last Name: " + city.getCountry());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
