package com.rateboard.weather.api;

import com.rateboard.weather.entity.City;

public class ApiFactory {
	private static String key = "76d7b6a0e22e37bb";
	private static String WeatherForecast10DaysApi = "http://api.wunderground.com/api/%s/forecast10day/q/%s/%s.json";

	public static String make10DayApiUrl(City city) {
		return String.format(WeatherForecast10DaysApi, key, city.getCountry(), city.getName());
	}
}