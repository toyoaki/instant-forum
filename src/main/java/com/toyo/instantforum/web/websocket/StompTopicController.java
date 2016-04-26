package com.toyo.instantforum.web.websocket;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.toyo.instantforum.data.model.Answer;
import com.toyo.instantforum.service.AnswerService;
import com.toyo.instantforum.web.security.SecurityUtil;
import com.toyo.instantforum.web.websocket.data.AnswerMessage;

@Controller
public class StompTopicController {

    @Autowired
    private AnswerService answerService;

    // Quando cliente envia mensagem para app/<x>, passa por aqui e redireciona
    // para /topic/<x>
    @MessageMapping("/**")
    public AnswerMessage onMessage(Answer answer, SimpMessageHeaderAccessor headerAccessor) {
	AnswerMessage returnMessage;
	try {
	    answer.setUser(SecurityUtil.getWebSocketSessionUser(headerAccessor));
	    answer.setDate(new Timestamp(System.currentTimeMillis()));
	    Answer newAnswer = answerService.save(answer);
	    returnMessage = new AnswerMessage(newAnswer);
	} catch (Exception e) {
	    e.printStackTrace();
	    returnMessage = new AnswerMessage(answer);
	    returnMessage.setError("Ocorreu um erro: " + e.getMessage());
	}
	return returnMessage;
    }

}
