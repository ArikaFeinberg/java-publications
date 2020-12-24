package lab.controller.actions.admin;

import lab.controller.Pagination;
import lab.controller.actions.Action;
import lab.model.dao.DAOFactory;
import lab.model.dao.entities.User;
import lab.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class GetUsersAction extends Action {
    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = new UserService(DAOFactory.FACTORY);

        ArrayList<User> users = userService.getAll();

        Pagination.pagination(req, users, "users", 5);

        return "/jsp/admin/users.jsp";
    }
}
