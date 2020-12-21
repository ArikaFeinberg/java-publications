package lab.controller.actions;

import lab.controller.validator.Validator;
import lab.model.dao.entities.User;
import lab.model.dao.entities.enums.Role;
import lab.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction extends Action {
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
                    return "Admin/Home";
                }
                if (!user.getBlocked()) {
                    req.getSession().setAttribute("user", user);
                    return "User/Home";
                } else {
                    req.setAttribute("error", "This client is blocked!");
                    return null;
                }
            }
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
        }
        return null;
    }
}
