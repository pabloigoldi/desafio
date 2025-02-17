package com.api.asisvirtual.service;

import com.api.asisvirtual.util.TimeUtils;

public class EstrategiaHorario implements IEstrategiaGestionRespuesta {

	@Override
	public String procesarRespuesta(String pregunta) throws Exception {
		return "La hora actual es " + TimeUtils.getHoraActual() + ".";
	}
}