package com.weatherapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weatherapi.dto.RequestDTO;
import com.weatherapi.dto.ResponseDTO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/weatherapi/v1/")
public class WeatherController {
	
	@PostMapping()
	public ResponseEntity<ResponseDTO> post(@RequestBody RequestDTO request) {
		log.info("INGRESANDO A WeatherController.post(). CIUDAD: " + request.getCity()+ ".");

		return ResponseEntity.status(HttpStatus.OK)
							 .body(ResponseDTO.builder().temperaturaActual("25.9 º").build());
	}

}
