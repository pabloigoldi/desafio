package com.weatherapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class WeatherapiApplication {

	public static void main(String[] args) {
		try {
			log.info("INICIANDO WeatherapiApplication.");
			SpringApplication.run(WeatherapiApplication.class, args);
		} catch (Exception e) {
			log.error("ERROR INTENTANDO INICIAR APLICACION WeatherapiApplication.");
			throw e;
		}
	}
}
