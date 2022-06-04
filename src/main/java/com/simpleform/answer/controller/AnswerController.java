package com.simpleform.answer.controller;


import com.simpleform.answer.AnswerService;
import com.simpleform.answer.model.Answer;
import com.simpleform.answer.repository.AnswerRepository;
import com.simpleform.question.model.Question;
import com.simpleform.question.repository.QuestionRepository;
import com.simpleform.question.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AnswerController {

    private final AnswerRepository repository;
    private final AnswerService answerService;
    private final QuestionRepository questionRepository;

    public AnswerController(
        AnswerRepository repository,
        AnswerService answerService,
        QuestionRepository questionRepository
    ) {
        this.repository = repository;
        this.answerService = answerService;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/answers")
    public String list(Model model) {
        List<Answer> answers = this.repository.findAll();

        model.addAttribute("answers", answers);

        return  "answer/answer";
    }

    @GetMapping("/question/")
    public String view(Model model, @PathVariable Integer id) {
        Answer answer = this.repository.findById(id).orElse(null);
        if (answer == null) {
            return "404";
        }
        model.addAttribute("answer", answer);

        return  "redirect:/questions";
    }
    ///////////////////////////////////////////////////////////
    private List <Question> getAllQuestion(){
        return  this.questionRepository.findAll();
    }

     @GetMapping("/answer/create")
     public String showForm( Answer answer, Model model){
         model.addAttribute("questions", this.getAllQuestion());
         return "answer/create";
       }

     @PostMapping("/answer/create")
      public String create(@Valid Answer answer, BindingResult result, Model model){
         if (result.hasErrors()) {
             model.addAttribute("questions", this.getAllQuestion());
             return "answer/create";
         }
         answerService.create(answer);
         return "redirect:/subjects";

       }



}
