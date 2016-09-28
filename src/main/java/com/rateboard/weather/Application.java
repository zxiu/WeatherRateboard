package com.rateboard.weather;


import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rateboard.weather.entity.City;
import com.rateboard.weather.entity.Weather10Day;

@Configuration
@EnableWebMvc
@EnableAsync
@EnableScheduling
@ComponentScan
public class Application {
	static SessionFactory sessionFactory;

  public static void main(String[] args) {
      
      
  }
  
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
			configuration.addAnnotatedClass(City.class);
			configuration.addAnnotatedClass(Weather10Day.class);
			sessionFactory = configuration.buildSessionFactory();
		}
		return sessionFactory;
	}
}