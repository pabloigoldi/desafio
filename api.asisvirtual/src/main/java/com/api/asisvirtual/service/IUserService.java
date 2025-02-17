package com.api.asisvirtual.service;

import com.api.asisvirtual.model.Usuario;

public interface IUserService {
	
	public Usuario findUserByUsername(String username);
}
