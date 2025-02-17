package com.api.asisvirtual.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.asisvirtual.dto.ResponseDTO;
import com.api.asisvirtual.exception.BusinessException;
import com.api.asisvirtual.util.MessageUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
		log.error("GlobalExceptionHandler.handleValidationExceptions().");
		return new ResponseEntity<>(ResponseDTO.builder().messageError(MessageUtils.MSG_ERROR_002).hasError(Boolean.TRUE).build(),HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ResponseDTO> handleBusinessException(BusinessException be) {
		log.error("GlobalExceptionHandler.handleBusinessException().");
		return new ResponseEntity<>(ResponseDTO.builder().messageError(be.getMessage()).data(be.getMessage()).hasError(Boolean.TRUE).build(),HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDTO> handleException(Exception e) {
		log.error("GlobalExceptionHandler.handleException(): " + e);
		return new ResponseEntity<>(ResponseDTO.builder().messageError(MessageUtils.MSG_ERROR_003).hasError(Boolean.TRUE).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ResponseDTO> handleUsernameNotFoundException(UsernameNotFoundException e) {
		log.error("GlobalExceptionHandler.handleUsernameNotFoundException(): " + e);
		return new ResponseEntity<>(
				ResponseDTO.builder().messageError(MessageUtils.MSG_ERROR_006).hasError(Boolean.TRUE).build(),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ResponseDTO> handleBadCredentialsException(BadCredentialsException e) {
		log.error("GlobalExceptionHandler.handleBadCredentialsException(): " + e);
		return new ResponseEntity<>(
				ResponseDTO.builder().messageError(MessageUtils.MSG_ERROR_007).hasError(Boolean.TRUE).build(),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ResponseDTO> handleAccessDeniedException(AccessDeniedException e) {
		log.error("GlobalExceptionHandler.handleAccessDeniedException(): " + e.getMessage());
		return new ResponseEntity<>(ResponseDTO.builder().messageError(MessageUtils.MSG_ERROR_004)
				.hasError(Boolean.TRUE).build(), HttpStatus.FORBIDDEN);
	}

}
