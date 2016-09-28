package com.rateboard.weather.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rateboard.weather.Application;
import com.rateboard.weather.entity.City;

@Repository
@Transactional
public class CityDao {

	public List<City> listCities() {
		Session session = Application.getSessionFactory().openSession();
		Transaction tx = null;
		List<City> cities = new ArrayList<City>();
		try {
			tx = session.beginTransaction();
			for (Object o : session.createQuery("FROM City").getResultList()) {
				if (o instanceof City) {
					cities.add((City) o);
				}
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

	public Integer addCity(City city) {
		return addCity(city.getName(), city.getCountry());
	}

	public Integer addCity(String name, String country) {
		if (findCity(name, country) != null) {
			return null;
		}
		Session session = Application.getSessionFactory().openSession();
		Transaction tx = null;
		Integer id = null;
		try {
			tx = session.beginTransaction();
			City city = new City();
			city.setName(name);
			city.setCountry(country);
			id = (Integer) session.save(city);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	public void fillInSampleCities() {
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
		addCity("Rome", "Italy");
		addCity("Peking", "China");
		addCity("Shanghai", "China");
	}

	public Long getCount() {
		Session session = Application.getSessionFactory().openSession();
		Long count = (Long) session.createQuery("select count(*) from City").uniqueResult();
		session.close();
		return count;
	}

	public City findCity(String name, String country) {
		Session session = Application.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(City.class);
		criteria.add(Restrictions.eq("name", name)).add(Restrictions.eq("country", country));
		City city = (City) criteria.uniqueResult();
		return city;
	}

}
