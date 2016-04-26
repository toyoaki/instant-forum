package com.toyo.instantforum.web.websocket;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import com.toyo.instantforum.web.security.SecurityUtil;

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
	    ServerHttpResponse response, WebSocketHandler wsHandler,
	    Map<String, Object> attributes) throws Exception {
	SecurityUtil.setWebSocketSessionUser(attributes);
	return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
	    ServerHttpResponse response, WebSocketHandler wsHandler,
	    Exception ex) {
	super.afterHandshake(request, response, wsHandler, ex);
    }

}
