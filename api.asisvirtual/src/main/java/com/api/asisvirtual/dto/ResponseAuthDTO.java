package com.api.asisvirtual.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel(description = "Modelo de respuesta existosa de Autenticacion.")
@Data
@Builder
public class ResponseAuthDTO {
	
	@ApiModelProperty(notes = "Token.")
	private String token;
}
