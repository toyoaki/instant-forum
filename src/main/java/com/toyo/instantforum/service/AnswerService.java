package com.toyo.instantforum.service;

import java.util.List;

import com.toyo.instantforum.data.model.Answer;

public interface AnswerService {

    Answer save(Answer answer);

    List<Answer> findByQuestion(Long questionId, String language);

}
