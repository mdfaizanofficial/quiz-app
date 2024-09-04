package com.faizzz.quizapp.repository;

import com.faizzz.quizapp.model.Question;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    @Query("SELECT q FROM Question q WHERE q.category = :category")
    public List<Question> findQuestionsUsingCategory(@Param("category") String category);

}
