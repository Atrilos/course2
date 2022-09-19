package pro.sky.java.course2.examinerservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Question {
    private String question;
    private String answer;
}
