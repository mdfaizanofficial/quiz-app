package com.faizzz.quizapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowQuiz {
    
    private int id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
