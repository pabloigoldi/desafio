package com.api.asisvirtual;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.api.asisvirtual.exception.BusinessException;
import com.api.asisvirtual.model.RequestChat;
import com.api.asisvirtual.model.ResponseChat;
import com.api.asisvirtual.service.IAsistenteVirtualService;
import com.api.asisvirtual.util.StringUtils;

@SpringBootTest
public class AsistenteVirtualTest {
	
	private static final String RESPUESTA_NO_DISPONIBLE ="No entiendo la pregunta.";

	@Autowired
	private IAsistenteVirtualService asistenteVirtual;
    
    @Test
    @DisplayName("Prueba palabra clave inexistente.")
    public void test1() throws Exception{
    	String pregunta = "nada";
        Exception exception = assertThrows(BusinessException.class, () -> {
        	asistenteVirtual.gestionarChat(RequestChat.builder().question(pregunta).build());
        });
        assertEquals(RESPUESTA_NO_DISPONIBLE, exception.getMessage());
        assertNotSame(null, exception.getMessage());
        assertNotSame(StringUtils.EMPTY, exception.getMessage());
    }
    
    @Test
    @DisplayName("Prueba palabra clave existente: Maradona.")
    public void test2() throws Exception{
    	String pregunta = "Maradona";
    	ResponseChat response = asistenteVirtual.gestionarChat(RequestChat.builder().question(pregunta).build());
    	assertNotSame(RESPUESTA_NO_DISPONIBLE, response.getData());
    	assertNotSame(null, response.getData());
    	assertNotSame(StringUtils.EMPTY, response.getData());
    }
    
    @Test
    @DisplayName("Prueba oracion con palabra clave existente.")
    public void test3() throws Exception{
    	String pregunta = "Hablame de Messi";
    	ResponseChat response = asistenteVirtual.gestionarChat(RequestChat.builder().question(pregunta).build());
    	assertNotSame(RESPUESTA_NO_DISPONIBLE, response.getData());
    	assertNotSame(null, response.getData());
    	assertNotSame(StringUtils.EMPTY, response.getData());
    }
    

    
    @Test
    @DisplayName("Prueba palabra clave existente: clima.")
    public void test4() throws Exception{
    	String pregunta = "clima";
    	ResponseChat response = asistenteVirtual.gestionarChat(RequestChat.builder().question(pregunta).build());
    	assertNotSame(RESPUESTA_NO_DISPONIBLE, response.getData());
    	assertNotSame(null, response.getData());
    	assertNotSame(StringUtils.EMPTY, response.getData());
    }
    
    @Test
    @DisplayName("Prueba palabra clave existente: hora.")
    public void test5() throws Exception{
    	String pregunta = "hora";
    	ResponseChat response = asistenteVirtual.gestionarChat(RequestChat.builder().question(pregunta).build());
    	assertNotSame(RESPUESTA_NO_DISPONIBLE, response.getData());
    	assertNotSame(null, response.getData());
    	assertNotSame(StringUtils.EMPTY, response.getData());
    }

}