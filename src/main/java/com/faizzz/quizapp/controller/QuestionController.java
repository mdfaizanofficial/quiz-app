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

    @GetMapping("/")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @PostMapping("/")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @PostMapping("/addMultiple")
    public ResponseEntity<String> addMultipleQuestion(@RequestBody List<Question> questions){
        // ResponseEntity<String> res = null;
        // for(int i = 0; i < questions.size(); i++) {
        //     res = questionService.addQuestion(questions.get(i));
        // }
        // return res;

        return questionService.addMultipleQuestion(questions);
    }

    @PutMapping("/")
    private ResponseEntity<String> modifyQuestion(@RequestBody Question question){
        return questionService.modifyQuestion(question);
    }

    @GetMapping("/getQuestionsByCategory")
    public ResponseEntity<List<Question>> getQuestionsUsingCaregory(@RequestParam String category){
        return questionService.getQuestionsUsingCaregory(category);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id){
        return questionService.getQuestionById(id);
    }
}
