package lab.controller.validator.exeptions;

public class NotEmailException extends ValidationException {
    NotEmailException() {
        super("This is not email.");
    }

    public NotEmailException(String message) {
        super(message);
    }
}