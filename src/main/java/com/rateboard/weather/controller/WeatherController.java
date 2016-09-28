package com.rateboard.weather.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rateboard.weather.api.ApiFactory;
import com.rateboard.weather.dao.CityDao;
import com.rateboard.weather.dao.Weather10DayDao;
import com.rateboard.weather.entity.City;
import com.rateboard.weather.entity.Weather10Day;
import com.rateboard.weather.service.ApiService;
import com.rateboard.weather.validator.ResponseValidator;

@Controller
public class WeatherController {

	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value = "country", required = false) String country,
	    @RequestParam(value = "city", required = false) String city) {
		ModelAndView modelAndView = new ModelAndView("index");
		Weather10DayDao.listWeathers();
		if (CityDao.getCount() == 0) {
			CityDao.fillInSampleCities();
		}
		if (country != null && city != null) {
			City cityObj = CityDao.getCityByNameAndCountry(city, country);
			if (cityObj != null) {
				Weather10Day weather = Weather10DayDao.queryByCity(cityObj);
				String result = null;
				if (weather != null) {
					result = weather.getResult();
					modelAndView.addObject("message",
					    "Load " + city + " from Cache at " + weather.getCreatedAt().toLocaleString());
				}
				if (result == null) {
					result = ApiService.executeTask(ApiFactory.make10DayApiUrl(cityObj));
					if (ResponseValidator.validWeather10Days(result)) {
						Weather10DayDao.addWeatherResult(cityObj, result);
						modelAndView.addObject("message", "Load " + city + " from internet at " + new Date().toLocaleString());
					} else {
						modelAndView.addObject("error", "Sth wrong :(");
					}
				}
				modelAndView.addObject("city", cityObj);
				modelAndView.addObject("items",
				    new String[] { "high", "low", "avehumidity", "qpf_allday", "qpf_day", "qpf_night", "maxwind" });
				modelAndView.addObject("weather", result);
			}
		}

		modelAndView.addObject("cities", CityDao.listCities());
		return modelAndView;
	}

}
