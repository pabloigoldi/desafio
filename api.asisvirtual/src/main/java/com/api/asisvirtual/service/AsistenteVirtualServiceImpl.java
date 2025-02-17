package com.api.asisvirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.asisvirtual.client.WeatherApiClient;
import com.api.asisvirtual.exception.BusinessException;
import com.api.asisvirtual.model.RequestChat;
import com.api.asisvirtual.model.ResponseChat;
import com.api.asisvirtual.util.MessageUtils;
import com.api.asisvirtual.util.PalabraClaveUtils;
import com.api.asisvirtual.util.StringUtils;
import com.api.asisvirtual.util.TimeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsistenteVirtualServiceImpl implements IAsistenteVirtualService {

	private static final String TIPO_PREGUNTA_CLIMA = "tipoPreguntaClima";
	private static final String TIPO_PREGUNTA_HORARIO = "tipoPreguntaHorario";
	
	@Autowired
	private WeatherApiClient weatherApiClient;

	@Override
	public ResponseChat gestionarChat(RequestChat request) throws BusinessException, Exception {
		log.info("INGRESANDO A AsistenteVirtualImpl.gestionarChat().");
		try {
			Contexto contexto = this.selectContextoSegunPalabraClave(request);
			String respuesta = contexto.ejecutar(request.getQuestion());
			if(respuesta.isEmpty())	throw new BusinessException(MessageUtils.MSG_ERROR_001);
			return ResponseChat.builder().data(respuesta).id(TimeUtils.getTimestampMillis()).horario(TimeUtils.getFechaActual()).build();
		} catch (BusinessException be) {
			log.error("ERROR EN AsistenteVirtualImpl.gestionarChat(). CONSULTA: "+ request.toString() +". ERROR: " + be.getMessage() +".");
			throw be;			
		} catch (Exception e) {
			log.error("ERROR EN AsistenteVirtualImpl.gestionarChat(). CONSULTA: "+ request.toString() +". ERROR: " + e.getLocalizedMessage() +".");
			throw e;
		}
	}

	private Contexto selectContextoSegunPalabraClave(RequestChat request) {
		String tipoPregunta = this.analizarPregunta(request);
		Contexto contexto = null;
		if (tipoPregunta.equals(TIPO_PREGUNTA_CLIMA)) {
			contexto = new Contexto(new EstrategiaClima(weatherApiClient));
		} else if (tipoPregunta.equals(TIPO_PREGUNTA_HORARIO)) {
			contexto = new Contexto(new EstrategiaHorario());
		} else {
			contexto = new Contexto(new EstrategiaArchivo());
		}
		return contexto;
	}

	private String analizarPregunta(RequestChat request) {
		String pregunta = request.getQuestion();
		String tipo = StringUtils.EMPTY;
		if (pregunta.toLowerCase().contains(PalabraClaveUtils.PALABRA_CLAVE_CLIMA.toLowerCase()) 
						|| pregunta.toLowerCase().contains( PalabraClaveUtils.PALABRA_CLAVE_TIEMPO.toLowerCase())) {
			tipo = TIPO_PREGUNTA_CLIMA;
		} else if (pregunta.toLowerCase().contains(PalabraClaveUtils.PALABRA_CLAVE_HORA.toLowerCase())) {
			tipo = TIPO_PREGUNTA_HORARIO;
		}
		return tipo;
	}
}
