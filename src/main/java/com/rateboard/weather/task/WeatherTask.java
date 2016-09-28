package com.rateboard.weather.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.rateboard.weather.api.ApiManager;
import com.rateboard.weather.dao.CityDao;
import com.rateboard.weather.dao.Weather10DayDao;
import com.rateboard.weather.entity.City;
import com.rateboard.weather.util.NetUtils;
import com.rateboard.weather.validator.ResponseValidator;

@Component("taskJob")
public class WeatherTask {
	@Autowired
	CityDao cityDao;

	@Autowired
	Weather10DayDao weather10DayDao;

	@Scheduled(fixedRate = 5 * 60 * 1000, initialDelay = 10000)
	public void run() {
		System.out.println("running");
		for (City city : cityDao.listCities()) {
			run(city);
		}
	}

	public String run(City city) {
		System.out.println("running");
		String result = NetUtils.executeApi(ApiManager.make10DayApiUrl(city));
		if (ResponseValidator.validWeather10Days(result)) {
			weather10DayDao.addWeatherResult(city, result);
		}
		return result;
	}

}
