package lab.controller.validator.exeptions;

public class WrongRangeException extends ValidationException {
    WrongRangeException() {
        super("This digit not in range.");
    }

    public WrongRangeException(String message) {
        super(message);
    }
}
