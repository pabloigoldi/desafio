package com.api.asisvirtual.service;

import com.api.asisvirtual.client.WeatherApiClient;
import com.api.asisvirtual.client.dto.RequestDTO;
import com.api.asisvirtual.client.dto.ResponseDTO;

public class EstrategiaClima implements IEstrategiaGestionRespuesta {

	private final WeatherApiClient weatherApiClient;

	public EstrategiaClima(WeatherApiClient weatherApiClient) {
		this.weatherApiClient = weatherApiClient;
	}

	@Override
	public String procesarRespuesta(String pregunta) throws Exception {
		ResponseDTO response = weatherApiClient.getWeather(RequestDTO.builder().city("BUENOS AIRES").build());
		return "La temperatura actual es: " + response.getTemperaturaActual() + ".";
	}
}