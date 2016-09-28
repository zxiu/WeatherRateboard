package com.rateboard.weather.dao;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:WebContent/WEB-INF/applicationContext.xml" })
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)  
public class CityDaoTest {
	
  @Test    
  @Transactional    
  @Rollback(false) 
  public void insert( ) {  
      assertTrue(1>0);   
      
  }
}
