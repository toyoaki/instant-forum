package com.toyo.instantforum.web.websocket.data;

import java.sql.Timestamp;

import com.toyo.instantforum.data.model.Answer;
import com.toyo.instantforum.data.model.Question;
import com.toyo.instantforum.data.model.User;

public class AnswerMessage {

    protected Timestamp date;
    protected String text;
    protected User user;
    protected Question question;

    private String error;

    public AnswerMessage(Answer answer) {
	this.date = answer.getDate();
	this.text = answer.getText();
	this.question = answer.getQuestion();

	User answerUser = answer.getUser();
	this.user = new User(answerUser.getId(), answerUser.getUsername());
    }

    public Long getDateInMillis() {
	return date == null ? null : date.getTime();
    }

    public Timestamp getDate() {
	return date;
    }

    public void setDate(Timestamp date) {
	this.date = date;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Question getQuestion() {
	return question;
    }

    public void setQuestion(Question question) {
	this.question = question;
    }

    public String getError() {
	return error;
    }

    public void setError(String error) {
	this.error = error;
    }

}
