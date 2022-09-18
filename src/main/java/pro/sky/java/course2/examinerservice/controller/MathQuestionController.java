package pro.sky.java.course2.examinerservice.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/math")
public class MathQuestionController {

    private final QuestionService questionService;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestParam("q") String question,
                                                @RequestParam("a") String answer) {
        return ResponseEntity.ok(questionService.add(question, answer));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Question> removeQuestion(@RequestParam("q") String question,
                                                   @RequestParam("a") String answer) {
        return ResponseEntity.ok(questionService.remove(new Question(question, answer)));
    }

    @GetMapping()
    public ResponseEntity<Collection<Question>> getQuestions() {
        return ResponseEntity.ok(questionService.getAll());
    }
}