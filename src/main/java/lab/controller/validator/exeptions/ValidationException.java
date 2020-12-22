package lab.controller.validator.exeptions;

public class ValidationException extends RuntimeException {
    ValidationException(String message) {
        super(message);
    }

}
