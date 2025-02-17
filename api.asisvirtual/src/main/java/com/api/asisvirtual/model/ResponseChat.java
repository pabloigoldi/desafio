package com.api.asisvirtual.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseChat {

	private Long id;
	private String horario;
	private String data;
}