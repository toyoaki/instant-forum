package com.toyo.instantforum.web.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.toyo.instantforum.data.model.User;

public class SecurityUtil {

    public static User getUser() {
	return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
