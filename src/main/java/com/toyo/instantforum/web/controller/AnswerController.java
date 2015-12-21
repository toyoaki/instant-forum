package com.toyo.instantforum.web.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toyo.instantforum.data.model.Answer;
import com.toyo.instantforum.data.model.User;
import com.toyo.instantforum.service.AnswerService;
import com.toyo.instantforum.web.controller.data.Answers;
import com.toyo.instantforum.web.security.SecurityUtil;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Long newAnswer(@RequestBody Answer answer) {
	answer.setUser(SecurityUtil.getUser());
	answer.setDate(new Date(System.currentTimeMillis()));
	Answer newAnswer = answerService.save(answer);
	return newAnswer.getId();
    }

    @RequestMapping(value = "/{questionId}/{language}", method = RequestMethod.GET)
    public @ResponseBody Answers getQuestionAnswers(@PathVariable Long questionId, @PathVariable String language) {
	List<Answer> answers = answerService.findByQuestion(questionId, language);
	clearStubs(answers);
	return new Answers(answers);
    }

    // TODO: melhorar, necessário pois vem com stub e jackson dá erro
    private void clearStubs(List<Answer> answers) {
	for (Answer answer : answers) {
	    answer.setQuestion(null);

	    User dbUser = answer.getUser();
	    answer.setUser(new User(dbUser.getId(), dbUser.getUsername()));
	}
    }

}
