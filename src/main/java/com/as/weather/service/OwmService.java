package com.as.weather.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.as.weather.controller.WeatherController;
import com.as.weather.exception.WeatherException;
import com.as.weather.model.Weather;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.core.OWM.Accuracy;
import net.aksingh.owmjapis.core.OWM.Unit;

@Service
public class OwmService implements WeatherService {
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

	private static final String APIID = "18900b7b8ba656c1ab71fbdfffe1bdcd";

	public OwmService() {
	}

	public Weather getCurrentWeatherByCity(String name) throws WeatherException {
		return getCurrentWeatherByCity(name, Unit.METRIC);
	}

	public Weather getCurrentWeatherByCity(String name, Unit unit) throws WeatherException {
		OWM owm = new OWM(APIID);
		owm.setUnit(unit);
		owm.setAccuracy(Accuracy.LIKE);
		try {
			return new Weather(owm.currentWeatherByCityName(name));
		} catch (APIException e) {
			String message = e.getMessage();
			
			if (e.getCode() == 404) {
				 message = "City [" + name + "] not found!";
			}
			
			logger.error(message, e);
			throw new WeatherException(message, e);
		}
	}
}
