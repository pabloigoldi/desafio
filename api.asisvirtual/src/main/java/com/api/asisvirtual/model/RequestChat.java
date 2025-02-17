package com.api.asisvirtual.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@ToString
public class RequestChat {

	private String question;
}
