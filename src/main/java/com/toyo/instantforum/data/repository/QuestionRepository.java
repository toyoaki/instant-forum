package com.toyo.instantforum.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.toyo.instantforum.data.model.Category;
import com.toyo.instantforum.data.model.Question;

public interface QuestionRepository extends Repository<Question, Long> {

    @Query("SELECT DISTINCT q FROM Question q WHERE ((q.category in (:categories)) or (q.user.id = (:userId))) ORDER BY q.category")
    List<Question> findByCategoriesAndUser(@Param("categories") List<Category> categories, @Param("userId") Long userId);

    Question save(Question question);

}