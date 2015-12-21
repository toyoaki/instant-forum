package com.toyo.instantforum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toyo.instantforum.data.model.Category;
import com.toyo.instantforum.data.model.Question;
import com.toyo.instantforum.data.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Question> findByCategoriesAndUser(List<Category> categories, Long userId) {
	return questionRepository.findByCategoriesAndUser(categories, userId);
    }

    @Override
    @Transactional
    public Question save(Question question) {
	return questionRepository.save(question);
    }

}
