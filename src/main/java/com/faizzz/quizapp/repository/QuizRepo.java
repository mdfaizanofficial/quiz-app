package com.faizzz.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faizzz.quizapp.model.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer>{



}