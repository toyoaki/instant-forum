package com.toyo.instantforum.web.security;

import java.util.Map;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;

import com.toyo.instantforum.data.model.User;

public class SecurityUtil {

    private static final String WEBSOCKET_SESSION_ATTR_PRINCIPAL = "principal";

    public static User getUser() {
	return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static void setWebSocketSessionUser(Map<String, Object> webSocketSessionAttributes) {
	User user = SecurityUtil.getUser();
	User sessionUser = new User(user.getId(), user.getUsername());
	webSocketSessionAttributes.put(WEBSOCKET_SESSION_ATTR_PRINCIPAL, sessionUser);
    }

    public static User getWebSocketSessionUser(SimpMessageHeaderAccessor headerAccessor) {
	Map<String, Object> sessionAttrs = headerAccessor.getSessionAttributes();
	return (User) sessionAttrs.get(WEBSOCKET_SESSION_ATTR_PRINCIPAL);
    }

}
