package com.faizzz.quizapp.service;

import com.faizzz.quizapp.model.Question;
import com.faizzz.quizapp.model.Quiz;
import com.faizzz.quizapp.model.ShowQuiz;
import com.faizzz.quizapp.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.faizzz.quizapp.repository.QuizRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try{

            List<Question> questions  = questionRepo.findRandomQuestionByCategory(category, numQ);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizRepo.save(quiz);

            return new ResponseEntity<>("Quiz added!!", HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<ShowQuiz>> getQuizByTitle(Integer id) {
        try {
            Optional<Quiz> quizDb = quizRepo.findById(id);

            List<Question> questions = quizDb.get().getQuestions();
            List<ShowQuiz> showQuiz = new ArrayList<>();

            for(Question question : questions){
                ShowQuiz quiz = new ShowQuiz();
                quiz.setId(question.getId());
                quiz.setQuestionTitle(question.getQuestionTitle());
                quiz.setOption1(question.getOption1());
                quiz.setOption2(question.getOption2());
                quiz.setOption3(question.getOption3());
                quiz.setOption4(question.getOption4());
                showQuiz.add(quiz);
            }

            return new ResponseEntity<>(showQuiz, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

}
