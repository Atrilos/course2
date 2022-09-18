package pro.sky.java.course2.examinerservice.exception;

public class QuestionAlreadyAddedException extends RuntimeException{
    public QuestionAlreadyAddedException() {
        super();
    }

    public QuestionAlreadyAddedException(String message) {
        super(message);
    }
}
