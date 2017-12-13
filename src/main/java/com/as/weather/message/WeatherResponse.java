package com.as.weather.message;

public class WeatherResponse {
	private String status;
	private Object data;

	public WeatherResponse() {
	}

	public WeatherResponse(String status, Object data) {
		this.status = status;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
