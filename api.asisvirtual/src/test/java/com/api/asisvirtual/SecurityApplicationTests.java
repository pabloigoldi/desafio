package com.api.asisvirtual;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.asisvirtual.model.Usuario;
import com.api.asisvirtual.service.IAuthService;

@SpringBootTest
class SecurityApplicationTests {
	
	@Autowired
	private IAuthService authService;

	@Test
	void buildTokenTest() {
		Usuario user = Usuario.builder().username("pablo").password("1234").build();
		String token = authService.buildToken(user);
		System.out.print(token);
	}

}
