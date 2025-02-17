package com.weatherapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {

	private String temperaturaActual;
}
