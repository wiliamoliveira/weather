package com.as.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.as.weather.message.WeatherRequest;
import com.as.weather.message.WeatherResponse;
import com.as.weather.model.Weather;
import com.as.weather.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);	
	
	@Autowired
	private WeatherService weatherService;

	@PostMapping(value = "/current/city")
	public WeatherResponse getCurrentWeather(@RequestBody @Validated WeatherRequest weatherRequest) {
		try {
			Weather weather = weatherService.getCurrentWeatherByCity(weatherRequest.getCityName());
	
			if (weather.getRespCode() == 200) {
				return new WeatherResponse("DONE", weather);
			} else {
				String message = "OWM service rejected the request with code: " + weather.getRespCode();
				logger.warn(message);
				return new WeatherResponse("FAIL", message);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new WeatherResponse("FAIL", e.getMessage());
		}
	}
}
