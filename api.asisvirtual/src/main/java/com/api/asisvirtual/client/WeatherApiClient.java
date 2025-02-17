package com.api.asisvirtual.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import com.api.asisvirtual.client.dto.RequestDTO;
import com.api.asisvirtual.client.dto.ResponseDTO;
import com.api.asisvirtual.config.FeignClientConfig;

@FeignClient(name = "WEATHER-API", url = "${external.weatherapi.base-url}", configuration = FeignClientConfig.class)
public interface WeatherApiClient {

	@PostMapping(value = "/weatherapi/v1/", consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseDTO getWeather(RequestDTO request);
}