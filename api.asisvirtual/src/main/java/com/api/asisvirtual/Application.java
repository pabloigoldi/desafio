package com.api.asisvirtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class Application {

	public static void main(String[] args) {
		log.info("INICIANDO APLICACION: ASISTENTE VIRTUAL.");
		try {
			SpringApplication.run(Application.class, args);
		} catch (Exception e) {
			log.error("ERROR INICIANDO APLICACION: ASISTENTE VIRTUAL:" + e);
			throw e;
		}
	}
}