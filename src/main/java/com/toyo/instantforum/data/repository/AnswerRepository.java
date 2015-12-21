package com.toyo.instantforum.data.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.toyo.instantforum.data.model.Answer;
import com.toyo.instantforum.data.model.Question;

public interface AnswerRepository extends Repository<Answer, Long> {

    List<Answer> findByQuestionOrderByDateAsc(Question question);

    Answer save(Answer answer);

}
