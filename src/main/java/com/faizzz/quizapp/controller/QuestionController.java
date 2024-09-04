package com.faizzz.quizapp.controller;
import com.faizzz.quizapp.model.Question;
import com.faizzz.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("/getQuestionsUsingCategory")
    public ResponseEntity<List<Question>> getQuestionsUsingCaregory(@RequestParam String category){
        return questionService.getQuestionsUsingCaregory(category);
    }
}
