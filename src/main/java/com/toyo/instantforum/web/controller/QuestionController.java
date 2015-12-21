package com.toyo.instantforum.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.toyo.instantforum.data.model.Question;
import com.toyo.instantforum.data.model.User;
import com.toyo.instantforum.service.QuestionService;
import com.toyo.instantforum.web.controller.data.Questions;
import com.toyo.instantforum.web.security.SecurityUtil;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Long newQuestion(@RequestBody Question question) {
	question.setUser(SecurityUtil.getUser());
	Question newQuestion = questionService.save(question);
	return newQuestion.getId();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Questions getUserQuestions(HttpSession session) {
	User user = SecurityUtil.getUser();
	List<Question> questions = questionService.findByCategoriesAndUser(user.getCategories(), user.getId());
	clearStubs(questions);
	return new Questions(questions);
    }

    // TODO: melhorar, necessário pois vem com stub e jackson dá erro
    private void clearStubs(List<Question> questions) {
	for (Question question : questions) {
	    question.getCategory().setUsers(null);
	    question.getUser().setCategories(null);
	}
    }

}
