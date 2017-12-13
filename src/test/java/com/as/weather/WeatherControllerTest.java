package com.as.weather;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.as.weather.controller.WeatherController;
import com.as.weather.model.Weather;
import com.as.weather.service.WeatherService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WeatherController.class, secure = false)
public class WeatherControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WeatherService weatherService;

	@Test
	public void testGetCurrentWeatherByCity() throws Exception {
		Weather mockWeather = new Weather();
		mockWeather.setRespCode(200);
		mockWeather.setCityName("Barcelona");
		mockWeather.setCountry("ES");
		mockWeather.setTemp(10D);

		Mockito.when(weatherService.getCurrentWeatherByCity("Barcelona")).thenReturn(mockWeather);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/weather/current/city").accept(MediaType.APPLICATION_JSON)
				.content("{ \"cityName\" : \"Barcelona\" }").contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String expectedContent = "{\"status\":\"DONE\","
				+ "\"data\":{"
				+ "\"cityName\":\"Barcelona\","
				+ "\"country\":\"ES\","
				+ "\"icon\":null,"
				+ "\"description\":null,"
				+ "\"humidity\":null,"
				+ "\"temp\":10.0,"
				+ "\"windSpeed\":null,"
				+ "\"respCode\":200}}";

		assertEquals(response.getStatus(), HttpStatus.OK.value());
		assertEquals(result.getResponse().getContentAsString(), expectedContent);
	}

}
