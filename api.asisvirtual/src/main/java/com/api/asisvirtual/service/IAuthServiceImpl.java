package com.api.asisvirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.asisvirtual.model.Usuario;
import com.api.asisvirtual.security.JwtTokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IAuthServiceImpl implements IAuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public String buildToken(Usuario user) {
		log.info("INGRESA A IAuthServiceImpl.buildToken().");
		String jwt = "";
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			log.info("GESTIONA EXITOSAMENTE EL authenticationManager.");
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(user.getUsername());
			log.info("GENERA EL userDetails.");
			jwt = this.jwtTokenProvider.generateToken(userDetails, user);
			log.info("GENERA EL JWT.");

		} catch (UsernameNotFoundException e) {
			log.error("Error en IAuthServiceImpl.buildToken(): " + e);
			throw e;
		} catch (BadCredentialsException e) {
			log.error("Error en IAuthServiceImpl.buildToken(): " + e);
			throw e;
		} catch (Exception e) {
			log.error("Error en IAuthServiceImpl.buildToken(): " + e);
			throw e;
		}
		return jwt;
	}

}
