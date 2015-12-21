package com.toyo.instantforum.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.toyo.instantforum.data.model.Answer;
import com.toyo.instantforum.data.model.Question;
import com.toyo.instantforum.data.repository.AnswerRepository;
import com.toyo.instantforum.service.translation.TranslationService;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TranslationService translationService;

    @Override
    @Transactional
    public Answer save(Answer answer) {
	return answerRepository.save(answer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Answer> findByQuestion(Long questionId, String language) {
	List<Answer> answers = answerRepository.findByQuestionOrderByDateAsc(new Question(questionId));

	if (translationService.isAcceptedLanguage(language) && !answers.isEmpty()) {
	    List<String> tranletedTexts = getTranslatedTexts(answers, language);
	    applyTranslatedTexts(answers, tranletedTexts);
	}

	return answers;
    }

    private List<String> getTranslatedTexts(List<Answer> answers, String language) {
	List<String> texts = new ArrayList<>();
	for (Answer answer : answers)
	    texts.add(answer.getText());

	return translationService.translateFromPt(texts, language);
    }

    private void applyTranslatedTexts(List<Answer> answers, List<String> texts) {
	int i = 0;
	for (Answer answer : answers)
	    answer.setText(texts.get(i++));
    }

}
