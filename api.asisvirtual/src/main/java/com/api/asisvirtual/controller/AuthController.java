package com.api.asisvirtual.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.asisvirtual.dto.RequestAuthDTO;
import com.api.asisvirtual.dto.ResponseAuthDTO;
import com.api.asisvirtual.dto.ResponseDTO;
import com.api.asisvirtual.model.Usuario;
import com.api.asisvirtual.service.IAuthService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/v1/auth")
@RestController
@Validated
public class AuthController {
    @Autowired
    private IAuthService authService;
    
    @ApiOperation(value = "Login", 
            notes = "Crea un token valido que servira de identificacion para consultar recursos de la API.")
    @ApiResponses(value = {
    @ApiResponse(code = 200, response= ResponseDTO.class, message = "Respuesta exitosa."),
    @ApiResponse(code = 400, response= ResponseDTO.class, message = "Requerimiento erroneo."),
    @ApiResponse(code = 401, response= ResponseDTO.class, message = "Usuario no autenticado."),
    @ApiResponse(code = 500, response= ResponseDTO.class, message = "Error interno de la API.")})    
    @PostMapping("/login")
    public ResponseEntity<ResponseAuthDTO> login(@Valid @RequestBody RequestAuthDTO authDTO) {
    	log.info("INTENTO DE AUTENTICACION DEL USUARIO: " + authDTO.getUsername() + ".");
        String token = authService.buildToken(Usuario.builder().username(authDTO.getUsername()).password(authDTO.getPassword()).build());
        return ResponseEntity.status(HttpStatus.OK).body(ResponseAuthDTO.builder().token(token).build());
    }

}