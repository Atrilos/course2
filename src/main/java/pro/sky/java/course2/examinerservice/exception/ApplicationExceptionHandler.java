package pro.sky.java.course2.examinerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(QuestionAndAnswerAreSameException.class)
    public void handleQuestionAndAnswerAreSameException(QuestionAndAnswerAreSameException ex,
                                                        HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(QuestionDoesntExistException.class)
    public void handleQuestionDoesntExistException(QuestionDoesntExistException ex,
                                                   HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(RequestAmountIsExcessiveException.class)
    public void handleRequestAmountIsExcessiveException(RequestAmountIsExcessiveException ex,
                                                        HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(QuestionAlreadyAddedException.class)
    public void handleQuestionAlreadyAddedException(QuestionAlreadyAddedException ex,
                                                    HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
