package com.simpleform.subject.controller;

import com.simpleform.answer.model.Answer;
import com.simpleform.answer.repository.AnswerRepository;
import com.simpleform.question.model.Question;
import com.simpleform.question.repository.QuestionRepository;
import com.simpleform.subject.SubjectService;
import com.simpleform.subject.model.Subject;
import com.simpleform.subject.repository.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class SubjectController {
    private final SubjectRepository repository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final SubjectService subjectService;

    public SubjectController(
            SubjectRepository repository,
            QuestionRepository questionRepository,
            AnswerRepository answerRepository,
            SubjectService subjectService
    ) {
        this.repository = repository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.subjectService=subjectService;
    }

    @GetMapping("/subjects")
    public String list(Model model) {
        List<Subject> subjects = this.repository.findAll();
        System.out.println("subjects: " + subjects);
        model.addAttribute("subjects", subjects);

        return  "subject/list";
    }

    @GetMapping("/subject/view/{id}")
    public String view(Model model, @PathVariable Integer id) {
        Subject subject = this.repository.findById(id).orElse(null);
        if (subject == null) {
            return "404";
        }
        model.addAttribute("subject", subject);
        List<Question> questions = this.questionRepository.findBySubjectId(subject.getId());
        HashMap<Integer, List<Answer>> answersMap = new HashMap<Integer, List<Answer>>();
        for (Question question: questions) {
            answersMap.put(question.getId(), this.answerRepository.findByQuestionId(question.getId()));
        }
        model.addAttribute("questions", questions);
        model.addAttribute("answers", answersMap);

        return  "question/list";
    }

    @PostMapping("/testing")
    public String check(Model model, @RequestParam HashMap<String, String>results){
       Integer totalCorrect = 0;
     //Optional<Question> Correct = questionRepository.findByTrueAnswer(totalCorrect);
        Integer totalIncorrect = 0;
        HashMap<String, Answer> correctAnswers = new HashMap<>();
        HashMap<String, Answer> selectedAnswers = new HashMap<>();
        HashMap<String, Question> questions = new HashMap<>();
        for (Map.Entry<String, String> map : results.entrySet()){
            Integer questionId = Integer.parseInt(map.getKey());
            Question question = this.questionRepository.findById(questionId).orElse(null);
            if (question == null) {
                totalIncorrect++;
                continue;
            }
            questions.put(questionId.toString(), question);
            Integer answerId = Integer.parseInt(map.getValue());
            List<Answer> answers = this.answerRepository.findByQuestionId(questionId);

            for (Answer answer : answers) {
                if (answer.isCorrect()) {
                    correctAnswers.put(questionId.toString(), answer);
                }

                if (Objects.equals(answer.getId(), answerId)) {
                    selectedAnswers.put(questionId.toString(), answer);
                    if(answer.isCorrect()) {
                        totalCorrect++;
                        continue;
                    }

                    totalIncorrect++;
                }
            }

            Answer answer= this.answerRepository.findById(answerId).orElse(null);
        }
//     Integer Correct = (questionRepository.findByTrueAnswer(totalCorrect));
       // Integer Correctp=Question.setTrueAnswer(totalCorrect);
       // Integer Correctp=Question.setTrueAnswer(questionRepository.findByTrueAnswer(totalCorrect));

        model.addAttribute("totalCorrect", totalCorrect);
        model.addAttribute("totalIncorrect", totalIncorrect);
        model.addAttribute("questions", questions);
        model.addAttribute("correctAnswers", correctAnswers);
        model.addAttribute("selectedAnswers", selectedAnswers);

        return "testing/result";
    }

    ///////////////////////////////////

      @GetMapping("/add")
    public String goAdd(Model model){
        model.addAttribute("addRequest", new Subject());
        return "subject/add";
      }


      @PostMapping("add")
    public String add(@ModelAttribute Subject subject){
        System.out.println("add request " + subject);
        Subject addSubject= subjectService.creatSubject( subject.getTitle());
//        return addSubject==null ? "redirect:user/404" : "redirect:/question/list";
          return "redirect:subjects";

      }



}
