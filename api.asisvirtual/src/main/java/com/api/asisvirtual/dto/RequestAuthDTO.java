package com.api.asisvirtual.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@ApiModel(description = "Modelo de Autenticacion.")
public class RequestAuthDTO {

	@ApiModelProperty(notes = "usuario", required = true)
	@NotBlank
	@Getter
	private String username;
	@ApiModelProperty(notes = "clave", required = true)
	@NotBlank
	@Getter
	private String password;
}
