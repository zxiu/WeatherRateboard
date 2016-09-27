package com.rateboard.weather;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import com.rateboard.weather.entity.City;
import com.rateboard.weather.entity.WeatherForecast10Day;

@Configuration
@ComponentScan
public class Application {
	static SessionFactory sessionFactory;
    @Bean
    MessageService mockMessageService() {
        return new MessageService() {
            public String getMessage() {
              return "Hello World!";
            }
        };
    }

  public static void main(String[] args) {
      ApplicationContext context = 
          new AnnotationConfigApplicationContext(Application.class);
      MessagePrinter printer = context.getBean(MessagePrinter.class);
      printer.printMessage();
      Log log = LogFactory.getLog(Application.class);
      log.debug("debug");
      System.out.println(context.getBean(WeatherForecast10Day.class));
  }
  
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration().configure();
			configuration.addAnnotatedClass(City.class);
			configuration.addAnnotatedClass(WeatherForecast10Day.class);
			sessionFactory = configuration.buildSessionFactory();
		}
		return sessionFactory;
	}
}