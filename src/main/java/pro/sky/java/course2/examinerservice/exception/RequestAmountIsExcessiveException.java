package pro.sky.java.course2.examinerservice.exception;

public class RequestAmountIsExcessiveException extends RuntimeException{
    public RequestAmountIsExcessiveException() {
        super();
    }

    public RequestAmountIsExcessiveException(String message) {
        super(message);
    }
}
