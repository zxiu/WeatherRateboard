package com.rateboard.weather.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rateboard.weather.api.ApiFactory;
import com.rateboard.weather.dao.CityDaoImp;
import com.rateboard.weather.dao.Weather10DayDaoImp;
import com.rateboard.weather.entity.City;
import com.rateboard.weather.service.ApiService;
import com.rateboard.weather.validator.ResponseValidator;

@Component("taskJob")
public class WeatherTask {
	@Scheduled(fixedRate = 5 * 60 * 1000, initialDelay = 5000)
	public void run() {
		System.out.println("running");
		for (City city : CityDaoImp.listCities()) {
			String result = ApiService.executeTask(ApiFactory.make10DayApiUrl(city));
			if (ResponseValidator.validWeather10Days(result)) {
				Weather10DayDaoImp.addWeatherResult(city, result);
			}
		}
	}
}
