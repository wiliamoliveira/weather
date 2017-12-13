package com.as.weather.message;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.text.StringEscapeUtils;

public class WeatherRequest {

	@NotNull()
	@Size(min = 1)
	private String cityName;

	public WeatherRequest(String cityName) {
		this.cityName = cityName;
	}

	public WeatherRequest() {
	}

	public String getCityName() {
		return StringEscapeUtils.escapeHtml4(cityName);
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
