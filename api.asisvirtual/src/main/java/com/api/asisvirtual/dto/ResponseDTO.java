package com.api.asisvirtual.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
	
	private String data;
	private Boolean hasError;
	private String messageError;
}
