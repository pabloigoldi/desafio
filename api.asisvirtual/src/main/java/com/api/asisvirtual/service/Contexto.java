package com.api.asisvirtual.service;

public class Contexto {

	private IEstrategiaGestionRespuesta estrategia;

	public Contexto(IEstrategiaGestionRespuesta estrategia) {
		this.estrategia = estrategia;
	}

	public String ejecutar(String pregunta) throws Exception {
		return this.estrategia.procesarRespuesta(pregunta);
	}
}