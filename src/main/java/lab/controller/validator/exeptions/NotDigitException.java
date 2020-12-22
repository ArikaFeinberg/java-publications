package lab.controller.validator.exeptions;

public class NotDigitException extends ValidationException {
    NotDigitException() {
        super("This is not a digit.");
    }

    public NotDigitException(String message) {
        super(message);
    }

}