package com.faizzz.quizapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    
    private int id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
