package lab.controller.actions.user;

import lab.controller.actions.Action;
import lab.controller.validator.Validator;
import lab.controller.validator.exeptions.ValidationException;
import lab.model.dao.entities.User;
import lab.model.service.AccountsService;
import lab.model.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationAction extends Action {

    private static final Logger log = LogManager.getLogger(RegistrationAction.class);

    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        try {
            UserService userService = new UserService();

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            Validator.isName(username);
            String email = req.getParameter("email");
            Validator.isEmail(email);
            userService.registration(
                    new User.UserBuilder()
                            .setUserName(username)
                            .setPassword(password)
                            .setEmail(email)
                            .build()
            );
            User user = userService.getUser(username, password);
            AccountsService accountsService = new AccountsService();
            accountsService.createAccount(user.getUser_id());
            log.info("New user " + username + "has registered");
            return "Login";
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            log.error("Validation exception at registration action. Username: " + req.getParameter("username"));
        } catch (Exception e) {
            req.setAttribute("error", "Database error");
            log.error("Database error at registration action. Username: " + req.getParameter("username"));
        }
        return null;
    }
}
