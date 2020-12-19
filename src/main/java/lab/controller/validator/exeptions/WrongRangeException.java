package lab.controller.validator.exeptions;

public class WrongRangeException extends RuntimeException{
    WrongRangeException() {
        super("This digit not in range.");
    }

    public WrongRangeException(String message) {
        super(message);
    }
}
