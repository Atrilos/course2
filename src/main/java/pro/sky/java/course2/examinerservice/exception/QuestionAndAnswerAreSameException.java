package pro.sky.java.course2.examinerservice.exception;

public class QuestionAndAnswerAreSameException extends RuntimeException{
    public QuestionAndAnswerAreSameException() {
        super();
    }

    public QuestionAndAnswerAreSameException(String message) {
        super(message);
    }
}
