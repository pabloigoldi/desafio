package com.api.asisvirtual.service;

import com.api.asisvirtual.model.Usuario;

public interface IAuthService {
	
	String buildToken(Usuario user);
}
