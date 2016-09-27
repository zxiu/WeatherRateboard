package com.rateboard.weather.controller;

import javax.annotation.PostConstruct;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.rateboard.weather.api.ApiFactory;
import com.rateboard.weather.dao.CityDaoImp;
import com.sun.istack.internal.Nullable;

@Controller
public class WeatherController {
	public static String result;

	@PostConstruct
	public void initialize() {
		// executeTask();
	}

	@RequestMapping("/")
	public ModelAndView index(@Nullable @RequestParam(value = "country", required = false) String country,
	    @RequestParam(value = "city", required = false) String city) {
		System.out.println("city=" + city);
		if (CityDaoImp.getCount() == 0) {
			CityDaoImp.fillInSampleCities();
		}
		if (country != null && city != null) {
			System.out.println(ApiFactory.make10DayApiUrl(country, city));
		}
		return new ModelAndView("index", "cities", CityDaoImp.listCities());
	}

	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
		String message = "<br><div style='text-align:center;'>"
		    + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		// executeTask();
		// CityDaoImp.listCities();

		return new ModelAndView("welcome", "message", message);
	}

	@RequestMapping("/weatherforecast10days")
	public ModelAndView weatherForecast10Days() throws InterruptedException {
		// executeTask();
		return new ModelAndView("weatherforecast10day", "result", result);
	}

	@RequestMapping("/initiatecities")
	public RedirectView initiateCities() {
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
