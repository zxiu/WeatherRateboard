package com.rateboard.weather.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.rateboard.weather.Application;
import com.rateboard.weather.entity.City;
import com.rateboard.weather.entity.WeatherForecast10Day;

public class WeatherForecast10DayDao {

	public static Integer addWeatherResult(City city, String result) {
		Session session = Application.getSessionFactory().openSession();
		Transaction tx = null;
		Integer id = null;
		try {
			tx = session.beginTransaction();
			WeatherForecast10Day weather = new WeatherForecast10Day();
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
	
	public static WeatherForecast10Day queryByCity(City city){
		Session session = Application.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(WeatherForecast10Day.class);
		criteria.createCriteria("city", "c");
		criteria.add(Restrictions.eq("c.id", city.getId()));
		WeatherForecast10Day weatherForecast10Day = (WeatherForecast10Day) criteria.uniqueResult();
		return weatherForecast10Day;
	}
	
	public static List<WeatherForecast10Day> listWeathers() {
		Session session = Application.getSessionFactory().openSession();
		Transaction tx = null;
		List<WeatherForecast10Day> weathers = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("deprecation")
			List cs = session.createQuery("FROM WeatherForecast10Day").list();
			for (Iterator iterator = cs.iterator(); iterator.hasNext();) {
				WeatherForecast10Day weatherForecast10Day = (WeatherForecast10Day) iterator.next();
				weathers.add(weatherForecast10Day);
				System.out.println(weatherForecast10Day);
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
