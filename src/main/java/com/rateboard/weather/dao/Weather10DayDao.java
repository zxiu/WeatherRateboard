package com.rateboard.weather.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.rateboard.weather.Application;
import com.rateboard.weather.entity.City;
import com.rateboard.weather.entity.Weather10Day;

public class Weather10DayDao {

	public static Integer addWeatherResult(City city, String result) {
		Session session = Application.getSessionFactory().openSession();
		Transaction tx = null;
		Integer id = null;
		try {
			tx = session.beginTransaction();
			Weather10Day weather = new Weather10Day();
			weather.setCity(city);
			weather.setResult(result);
			id = (Integer) session.save(weather);
			System.out.println("add weather=" + city);
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

	public static Weather10Day queryByCity(City city) {
		Session session = Application.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Weather10Day.class);
		criteria.createCriteria("city", "c");
		criteria.add(Restrictions.eq("c.id", city.getId()));
		Weather10Day weather10Day = null;
		if (criteria.list().size() > 0) {
			weather10Day = (Weather10Day) criteria.list().get(0);
		}
		return weather10Day;
	}

	public static List<Weather10Day> listWeathers() {
		Session session = Application.getSessionFactory().openSession();
		Transaction tx = null;
		List<Weather10Day> weathers = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			for (Object o : session.createQuery("FROM Weather10Day").getResultList()) {
				if (o instanceof Weather10Day) {
					weathers.add((Weather10Day) o);
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
		return weathers;
	}

}
