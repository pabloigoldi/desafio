package com.api.asisvirtual.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.asisvirtual.model.DatoDialogo;
import com.api.asisvirtual.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EstrategiaArchivo implements IEstrategiaGestionRespuesta {

	private static final String FILE_NAME = "DatosDialogo.txt";

	@Override
	public String procesarRespuesta(String pregunta) throws Exception {
		String respuesta = StringUtils.EMPTY;
		try {
			List<String> palabrasPregunta = Arrays.stream(pregunta.split(" ")).map(String::toLowerCase).collect(Collectors.toCollection(ArrayList::new));
			List<DatoDialogo> datosArchivo = this.getDatosFromFile();
			List<DatoDialogo> datosCoincidente = datosArchivo.stream().filter(a -> palabrasPregunta.contains(a.getClave().toLowerCase())).collect(Collectors.toList());
			if(!datosCoincidente.isEmpty()) {
				respuesta= datosCoincidente.getFirst().getRepuesta();
			}
		} catch (Exception e) {
			log.error("ERROR EN EstrategiaArchivo.procesarRespuesta(). ERROR: " + e.getLocalizedMessage() +".");
			throw e;
		}
		return respuesta;
	}

	private List<DatoDialogo> getDatosFromFile() throws IOException, URISyntaxException {
		List<DatoDialogo> datos = null;
		try {
			URL urlFile = getClass().getClassLoader().getResource(FILE_NAME);
			datos = Files.lines(Paths.get(urlFile.toURI())).map(linea -> {
																String[] partes = linea.split(StringUtils.PIPE);
																return DatoDialogo.builder().clave(partes[0]).repuesta(partes[1]).build();
																		}).collect(Collectors.toList());
		} catch (IOException | URISyntaxException e) {
			log.error("ERROR EN EstrategiaArchivo.getDatosFromFile(). ERROR: " + e.getLocalizedMessage() +".");
			throw e;
		}
		return datos;
	}

}
