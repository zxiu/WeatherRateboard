package com.rateboard.weather.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rateboard.weather.Application;
import com.rateboard.weather.entity.City;

public class CityDaoImp {
	public static void listCities() {
		Session session = Application.getSessionFactory().openSession();
		Transaction tx = null;
		System.out.print("CityDaoImp: ");
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

	public static Integer addCity(String name, String country) {
		Session session = Application.getSessionFactory().openSession();
		Transaction tx = null;
		Integer id = null;
		try {
			tx = session.beginTransaction();
			City city = new City();
			city.setName(name);
			city.setCountry(country);
			id = (Integer) session.save(city);
			System.out.println("add city=" + city);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return id;
	}
	//76d7b6a0e22e37bb
	public static void fillInSampleCities(){
		addCity("San_Francisco", "CA");
		addCity("Saarbruecken", "Germany");
		addCity("Berlin", "Germany");
		addCity("Hamburg", "Germany");
		addCity("Frankfurt", "Germany");
		addCity("Munich", "Germany");
		addCity("Kaiserslautern", "Germany");
		addCity("Innsbruck", "Austria");
		addCity("Vienna", "Austria");
		addCity("Salzburg", "Austria");
		addCity("Graz", "Austria");
		addCity("London", "United_Kingdom");
		addCity("Paris", "France");
		
	}
}
