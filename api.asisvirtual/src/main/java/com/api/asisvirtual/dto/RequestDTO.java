package com.api.asisvirtual.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "Modelo de pregunta.")
public class RequestDTO {
	
	@ApiModelProperty(notes = "pregunta", required = true)
	@NotBlank
	@Getter	
	private String pregunta;
}