package lab.controller.validator.exeptions;


public class NotCurrentUsername extends ValidationException {
    NotCurrentUsername() {
        super("Not current username. There are is enable symbols.");
    }

    public NotCurrentUsername(String message) {
        super(message);
    }

}
