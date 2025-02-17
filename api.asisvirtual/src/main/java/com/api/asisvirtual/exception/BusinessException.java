package com.api.asisvirtual.exception;

import lombok.Getter;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	@Getter
	private String message;

	public BusinessException(String message) {
		this.message = message;
	}
}
