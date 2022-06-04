package com.simpleform.question.service;

import com.simpleform.question.model.Question;
import com.simpleform.question.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    public QuestionService (QuestionRepository questionRepository) {
        this.questionRepository=questionRepository;
    }

    public Question save(Question question){
        return  questionRepository.save(question);
    }
}
