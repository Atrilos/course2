package pro.sky.java.course2.examinerservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamQuestionController {

    private final ExaminerService examinerService;

    @GetMapping("/get/{amount}")
    public ResponseEntity<Collection<Question>> getQuestions(@PathVariable int amount) {
        return ResponseEntity.ok(examinerService.getQuestions(amount));
    }
}
