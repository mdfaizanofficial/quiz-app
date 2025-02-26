package com.faizzz.quizapp.service;

import com.faizzz.quizapp.model.Question;
import com.faizzz.quizapp.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    public ResponseEntity<List<Question>> getAllQuestion() {
        try{
            List<Question> questions = questionRepo.findAll();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionRepo.save(question);
            return new ResponseEntity<>("Question Added successfully",HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<List<Question>> getQuestionsUsingCaregory(String category) {
        try {
            List<Question> questions = questionRepo.findQuestionsByCategory(category);
            return new ResponseEntity<>(questions,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Question> getQuestionById(int id) {
        try {
            Question question = questionRepo.findById(id).get();
            return new ResponseEntity<>(question, HttpStatus.FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> modifyQuestion(Question question) {
        try {
            Question get_question = questionRepo.findById(question.getId()).get();
            get_question.setQuestionTitle(question.getQuestionTitle());
            get_question.setOption1(question.getOption1());
            get_question.setOption2(question.getOption2());
            get_question.setOption3(question.getOption3());
            get_question.setOption4(question.getOption4());
            get_question.setCategory(question.getCategory());
            get_question.setDifficulty(question.getDifficulty());
            get_question.setCorrectOption(question.getCorrectOption());
            questionRepo.save(get_question);
            return new ResponseEntity<>("Modified..", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    public ResponseEntity<String> addMultipleQuestion(List<Question> questions) {
        try {
            questionRepo.saveAll(questions);
            return new ResponseEntity<>("Questions saved", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
