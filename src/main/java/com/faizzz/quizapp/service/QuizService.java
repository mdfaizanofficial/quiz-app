package com.faizzz.quizapp.service;

import com.faizzz.quizapp.dto.QuizDTO;
import com.faizzz.quizapp.model.Question;
import com.faizzz.quizapp.model.Quiz;
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

    public ResponseEntity<List<QuizDTO>> getQuizById(Integer id) {
        try{

        Optional<Quiz> quiz = quizRepo.findById(id);

        List<QuizDTO> quizDTOList = new ArrayList<>();

        List<Question> questions = quiz.get().getQuestions();

        int quizQuestionId = 1;
        for(Question question : questions){
            QuizDTO quizDTO = new QuizDTO(
                    quizQuestionId++,
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4()
            );

            quizDTOList.add(quizDTO);
        }

        return new ResponseEntity<>(quizDTOList,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
