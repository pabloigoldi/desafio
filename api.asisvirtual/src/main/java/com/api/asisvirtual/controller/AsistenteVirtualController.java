package com.api.asisvirtual.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.asisvirtual.dto.RequestDTO;
import com.api.asisvirtual.dto.ResponseDTO;
import com.api.asisvirtual.dto.ResponseQuestionDTO;
import com.api.asisvirtual.exception.BusinessException;
import com.api.asisvirtual.model.RequestChat;
import com.api.asisvirtual.model.ResponseChat;
import com.api.asisvirtual.service.IAsistenteVirtualService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/questions")
@Validated
public class AsistenteVirtualController {
	
	
	@Autowired
	private IAsistenteVirtualService asistenteVirtualService;

    @ApiOperation(value = "Respuesta a una consulta.", 
            notes = "Proporciona información clara sobre una consulta del usuario.")
    @ApiResponses(value = {
    @ApiResponse(code = 200, response= ResponseDTO.class, message = "Respuesta encontrada."),
    @ApiResponse(code = 400, response= ResponseDTO.class, message = "Requerimiento erroneo."),
    @ApiResponse(code = 401, response= ResponseDTO.class, message = "Usuario no autenticado."),
    @ApiResponse(code = 403, response= ResponseDTO.class, message = "Usuario sin permiso para el recurso."),
    @ApiResponse(code = 404, response= ResponseDTO.class, message = "Recurso no encontrada."),
    @ApiResponse(code = 500, response= ResponseDTO.class, message = "Error interno de la API.")})    
	@PostMapping()
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ResponseQuestionDTO> post(@Valid @RequestBody RequestDTO request) throws BusinessException, Exception {
		log.info("INGRESANDO A AsistenteVirtualController.post(). PREGUNTA: " + request.getPregunta() + ".");
		ResponseChat result = this.asistenteVirtualService.gestionarChat(RequestChat.builder().question(request.getPregunta()).build());
		return ResponseEntity.status(HttpStatus.OK)
							 .body(ResponseQuestionDTO.builder().data(result.getData()).id(result.getId()).horario(result.getHorario()).build());
	}	
}
