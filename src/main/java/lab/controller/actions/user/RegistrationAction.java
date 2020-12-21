package lab.controller.actions.user;

import lab.controller.actions.Action;
import lab.controller.validator.Validator;
import lab.model.dao.entities.User;
import lab.model.service.AccountsService;
import lab.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationAction extends Action {
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
            return "Login";
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
        return null;
    }
}
