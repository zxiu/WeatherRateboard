package com.rateboard.weather.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.rateboard.weather.Application;
import com.rateboard.weather.entity.City;

public class CityDaoImp {
	public static List<City> listCities() {
		Session session = Application.getSessionFactory().openSession();
		Transaction tx = null;
		List<City> cities = new ArrayList<City>();
		System.out.print("CityDaoImp: ");
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("deprecation")
			List cs = session.createQuery("FROM City").list();
			for (Iterator iterator = cs.iterator(); iterator.hasNext();) {
				City city = (City) iterator.next();
				cities.add(city);
				System.out.println(city);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cities;
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

	// 76d7b6a0e22e37bb
	public static void fillInSampleCities() {
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

	public static Long getCount() {
		Session session = Application.getSessionFactory().openSession();
		Long count = (Long) session.createQuery("select count(*) from City").uniqueResult();
		session.close();
		return count;
	}

	public static City getCityByNameAndCountry(String name, String country) {
		Session session = Application.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(City.class);
		criteria.add(Restrictions.eq("name", name)).add(Restrictions.eq("country", country));
		City city = (City) criteria.uniqueResult();

		return city;
	}

}
