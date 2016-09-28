
package com.rateboard.weather.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.rateboard.weather.Application;

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

}
