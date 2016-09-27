package com.rateboard.weather.controller;

import javax.annotation.PostConstruct;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.rateboard.weather.dao.CityDaoImp;

@Controller
public class WeatherController {
	public static String result;

	@PostConstruct
	public void initialize() {
		// executeTask();
	}

	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		// executeTask();
		// CityDaoImp.listCities();
		CityDaoImp.addCity("city ", "helloland"+Math.random());
		return new ModelAndView("welcome", "message", message);
	}

	@RequestMapping("/weatherforecast10days")
	public ModelAndView weatherForecast10Days() throws InterruptedException {
		// executeTask();
		return new ModelAndView("weatherforecast10day", "result", result);
	}
	
	@RequestMapping("/initiatecities")
	public RedirectView initiateCities(){
		System.out.println(" hahhahahahah");
		CityDaoImp.fillInSampleCities();
		return new RedirectView("");
	}

	public void executeTask() {
		AsyncHttpClient client = new DefaultAsyncHttpClient();
		client.prepareGet("http://api.wunderground.com/api/76d7b6a0e22e37bb/forecast10day/q/CA/San_Francisco.json")
				.execute(new AsyncCompletionHandler<Response>() {

					@Override
					public Response onCompleted(Response arg0) throws Exception {
						// TODO Auto-generated method stub
						System.out.println(arg0.getResponseBody());
						result = arg0.getResponseBody();
						return arg0;
					}

					@Override
					public void onThrowable(Throwable t) {
						super.onThrowable(t);
					}
				});
	}
}
