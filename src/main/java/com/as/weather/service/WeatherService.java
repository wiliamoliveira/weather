package com.as.weather.service;

import com.as.weather.exception.WeatherException;
import com.as.weather.model.Weather;


public interface WeatherService {

	Weather getCurrentWeatherByCity(String name) throws WeatherException;
}
