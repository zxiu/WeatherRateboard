package com.rateboard.weather.dao;

import static org.junit.Assert.assertTrue;

import javax.enterprise.inject.New;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mysql.cj.mysqlx.protobuf.MysqlxCrud.Insert;
import com.rateboard.weather.entity.City;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/index-servlet.xml" })
@WebAppConfiguration
public class CityDaoTest {

	@Autowired
	CityDao cityDao;
	City simCity = new City("Sim City", "Sim Country");

	@Test
	public void insert() {
		assertTrue(cityDao.addCity("new City", "new Country") > 0);
	}

	@Test
	public void insertDuplicated() {
		cityDao.addCity(simCity);
		assertTrue(cityDao.addCity(simCity) == null);
	}

	@Test
	public void select() {
		cityDao.addCity(simCity);
		assertTrue(cityDao.findCity("Sim City", "Sim Country").equals(new City("Sim City", "Sim Country")));
	}
	
	@Test
	public void list() {
		cityDao.addCity(simCity);
		assertTrue(cityDao.listCities().size() > 0);
	}
}
