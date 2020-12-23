import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lab.controller.validator.Validator;
import lab.controller.validator.exeptions.NotEmailException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestValidator {

    @Test
    void test_email_ok() {
        assertThrows(NotEmailException.class, () -> Validator.isEmail("ididid@gmail.com"));
    }

}
