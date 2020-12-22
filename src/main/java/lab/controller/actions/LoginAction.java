package lab.controller.actions;

import lab.controller.validator.Validator;
import lab.controller.validator.exeptions.ValidationException;
import lab.model.dao.entities.User;
import lab.model.dao.entities.enums.Role;
import lab.model.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class LoginAction extends Action {
    private static final Logger log = LogManager.getLogger(LoginAction.class);

    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        try {
            UserService userService = new UserService();
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            Validator.isName(username);
            User user = userService.getUser(username, password);
            if (user == null) {
                req.setAttribute("error", "Wrong username or password");
                return null;
            } else {
                if (user.getRole() == Role.ADMIN) {
                    req.getSession().setAttribute("user", user);
                    log.info("Admin " + user.getUserName() + " has logged in");
                    return "Admin/Home";
                }
                if (!user.getBlocked()) {
                    req.getSession().setAttribute("user", user);
                    log.info("User " + user.getUserName() + " has logged in");
                    return "User/Home";
                } else {
                    req.setAttribute("error", "This client is blocked!");
                    log.info("Blocked user " + username + " login attempt");
                    return null;
                }
            }
        } catch (ValidationException e) {
            req.setAttribute("error", e.getMessage());
            log.error("Validation exception at login action. Username: " + req.getParameter("username"));
        } catch (Exception e) {
            req.setAttribute("error", "Database error");
            log.error("Database error at login action. Username: " + req.getParameter("username"));
        }
        return null;
    }
}
