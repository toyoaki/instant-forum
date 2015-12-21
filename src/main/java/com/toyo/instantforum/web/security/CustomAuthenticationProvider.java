package com.toyo.instantforum.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.toyo.instantforum.data.model.User;
import com.toyo.instantforum.service.UserService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	String username = authentication.getName();
	String password = (String) authentication.getCredentials();

	User dbUser = userService.findByUsername(username);
	if (dbUser == null || !dbUser.getPassword().equals(password)) {
	    throw new BadCredentialsException("usuário / senha inváido");
	}

	return new UsernamePasswordAuthenticationToken(dbUser, password, null);
    }

    public boolean supports(Class<?> arg0) {
	return true;
    }

}
