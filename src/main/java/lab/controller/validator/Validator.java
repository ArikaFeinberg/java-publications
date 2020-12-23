package lab.controller.validator;

import lab.controller.validator.exeptions.*;
import lab.model.dao.entities.enums.Theme;

public class Validator {
    private static final String userNameRegExp = "\\w{1,20}";

    private static final String emailRegExp = "\\w+@\\w+\\.+[a-zA-Z]{2,6}$";

    public static void isName(String userName) throws NotCurrentUsername {
        if (!userName.matches(userNameRegExp) || userName.length() > 64) {
            throw new NotCurrentUsername("Name isn't correct");
        }
    }

    public static void isNumber(String userName) throws NotDigitException {
        if (!userName.matches("\\d+")) {
            throw new NotDigitException("This is not a digit!");
        }
    }

    public static void inRange(int dit, int left, int right) throws WrongRangeException {
        if (left > dit) {
            throw new WrongRangeException("This digit is lower than " + left);
        } else if (dit > right) {
            throw new WrongRangeException("This digit is more than " + right);
        }
    }

    public static void isEmail(String str) throws NotEmailException {
        if (!str.matches(emailRegExp) || str.length() > 64) {
            throw new NotEmailException("This isn't email.");
        }
    }

    public static void isDouble(String str) {
        if (!str.matches("(\\d)|(\\d+\\.+\\d{2})")) {
            throw new NotDigitException("This digit not in correct form!");
        }
    }

}
