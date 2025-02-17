package com.api.asisvirtual.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.asisvirtual.model.Usuario;
import com.api.asisvirtual.service.IUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private final IUserService userService;

    public UserDetailsServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Usuario user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado.");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().getFirst())
                .build();
    }
}
