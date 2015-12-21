package com.toyo.instantforum.service;

import java.util.List;

import com.toyo.instantforum.data.model.Category;
import com.toyo.instantforum.data.model.Question;

public interface QuestionService {

    List<Question> findByCategoriesAndUser(List<Category> categories, Long userId);

    Question save(Question question);

}
