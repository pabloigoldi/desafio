package com.api.asisvirtual.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.asisvirtual.model.Usuario;

@Service
public class UserServiceImpl implements IUserService {

	@Value("${spring.security.user.name}")
	private String username;

	@Value("${spring.security.user.password}")
	private String pass;
	
	@Value("${spring.security.user.roles}")
	private String rol;

	@Override
	public Usuario findUserByUsername(String username) {
        if (this.username.equals(username)) {
        	PasswordEncoder pe = new BCryptPasswordEncoder();
        	String passEncoded = pe.encode(this.pass);
        	return Usuario.builder().username(this.username).password(passEncoded).role(Arrays.asList(this.rol)).build();
       }
        return null;
	}

}
