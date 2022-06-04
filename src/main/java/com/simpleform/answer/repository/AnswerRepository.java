package com.simpleform.answer.repository;

import com.simpleform.answer.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnswerRepository extends JpaRepository <Answer,Integer> {
    List<Answer> findByQuestionId(Integer questionId);
}
