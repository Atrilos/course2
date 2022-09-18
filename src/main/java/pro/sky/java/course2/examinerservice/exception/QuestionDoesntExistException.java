package pro.sky.java.course2.examinerservice.exception;

public class QuestionDoesntExistException extends RuntimeException{
    public QuestionDoesntExistException() {
        super();
    }

    public QuestionDoesntExistException(String message) {
        super(message);
    }
}
