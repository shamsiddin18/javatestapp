package com.simpleform.answer;


import com.simpleform.answer.model.Answer;
import com.simpleform.answer.repository.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository){
        this.answerRepository=answerRepository;
    }

    public Answer create(Answer answer){
        return answerRepository.save(answer);
    }

}
