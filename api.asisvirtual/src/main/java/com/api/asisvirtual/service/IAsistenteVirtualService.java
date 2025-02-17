package com.api.asisvirtual.service;

import com.api.asisvirtual.exception.BusinessException;
import com.api.asisvirtual.model.RequestChat;
import com.api.asisvirtual.model.ResponseChat;

public interface IAsistenteVirtualService {

	ResponseChat gestionarChat(RequestChat request) throws BusinessException, Exception;
}