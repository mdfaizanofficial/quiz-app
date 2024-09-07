package com.faizzz.quizapp.repository;

import com.faizzz.quizapp.model.Question;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    public List<Question> findQuestionsByCategory(String category);

    @Query(
            value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RAND() LIMIT :numQ",
            nativeQuery = true
    )
    List<Question> findRandomQuestionByCategory(String category, int numQ);
}
