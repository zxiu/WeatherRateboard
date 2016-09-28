package com.rateboard.weather.controller;

import static org.junit.Assert.assertEquals;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/index-servlet.xml" })
@WebAppConfiguration
public class WeatherControllerTest {
	@Autowired
	WeatherController weatherController;

	@Autowired
	ServletContext context;

	MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
	   InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
     viewResolver.setPrefix("/WEB-INF/jsp/view/");
     viewResolver.setSuffix(".jsp");

     mockMvc = MockMvcBuilders.standaloneSetup(weatherController)
                              .setViewResolvers(viewResolver)
                              .build();
	}

	@Test
	public void testInnsbruck() throws Exception {
		ResultActions resultActions = mockMvc
		    .perform(MockMvcRequestBuilders.get("/index").param("city", "Innsbruck").param("country", "Austria"));
		MvcResult mvcResult = resultActions.andReturn();
		assertEquals(mvcResult.getResponse().getStatus(), 200);
	}
}
