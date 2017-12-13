package com.as.weather.model;

import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.param.Main;
import net.aksingh.owmjapis.model.param.System;
import net.aksingh.owmjapis.model.param.Wind;

public class Weather {

	private String cityName;
	private String country;
	private String icon;
	private String description;
	private Integer humidity;
	private Double temp;
	private Double windSpeed;
	private Integer respCode;

	public Weather(CurrentWeather cwd) {
		if (cwd == null)
			return;

		cityName = cwd.getCityName();
		respCode = cwd.getRespCode();

		if (cwd.hassystemData()) {
			System sys = cwd.getSystemData();
			country = sys.getCountryCode();
		}

		if (cwd.hasWeatherList()) {
			net.aksingh.owmjapis.model.param.Weather weather = cwd.getWeatherList().get(0);
			icon = weather.getIconCode();
			description = weather.getDescription();
		}

		if (cwd.hasMainData()) {
			Main main = cwd.getMainData();
			humidity = main.getHumidity();
			temp = main.getTemp();
		}

		if (cwd.hasWindData()) {
			Wind wind = cwd.getWindData();
			windSpeed = wind.getSpeed();
		}
	}

	public Weather() {

	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Integer getRespCode() {
		return respCode;
	}

	public void setRespCode(Integer respCode) {
		this.respCode = respCode;
	}

}
