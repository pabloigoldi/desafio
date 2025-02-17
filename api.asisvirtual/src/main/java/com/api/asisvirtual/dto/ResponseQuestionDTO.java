package com.api.asisvirtual.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(description = "Modelo de respuesta.")
@Data
@Builder
public class ResponseQuestionDTO {
	
	@ApiModelProperty(notes = "ID de respuesta.")
	private Long id;
	@ApiModelProperty(notes = "Horario de respuesta.")
	private String horario;
	@ApiModelProperty(notes = "Respuesta.")
	private String data;
	@ApiModelProperty(notes = "Mensaje de error, si lo hubiera.")
	private String messageError;
	@ApiModelProperty(notes = "Marca si hay error.")
	private Boolean hasError;
}
