package com.rateboard.weather.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.rateboard.weather.api.ApiFactory;
import com.rateboard.weather.dao.CityDaoImp;
import com.rateboard.weather.dao.WeatherForecast10DayDao;
import com.rateboard.weather.entity.City;
import com.rateboard.weather.entity.WeatherForecast10Day;
import com.rateboard.weather.service.ApiService;

@Controller
public class WeatherController {
	public static String result;

	@PostConstruct
	public void initialize() {
		// executeTask();
	}

	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value = "country", required = false) String country,
	    @RequestParam(value = "city", required = false) String city) {
		ModelAndView modelAndView = new ModelAndView("index");
		WeatherForecast10DayDao.listWeathers();
		if (CityDaoImp.getCount() == 0) {
			CityDaoImp.fillInSampleCities();
		}
		if (country != null && city != null) {
			City cityObj = CityDaoImp.getCityByNameAndCountry(city, country);
			if (cityObj != null) {
				WeatherForecast10Day weather = WeatherForecast10DayDao.queryByCity(cityObj);
				String result = null;
				if (weather != null) {
					result = weather.getResult();
				}
				
				System.out.println("result="+result);
				if (result == null) {
					result = ApiService.executeTask(ApiFactory.make10DayApiUrl(cityObj));
					WeatherForecast10DayDao.addWeatherResult(cityObj, result);
				}
				modelAndView.addObject("weather", result);
			}
		}

		modelAndView.addObject("cities", CityDaoImp.listCities());
		return modelAndView;
	}

}
