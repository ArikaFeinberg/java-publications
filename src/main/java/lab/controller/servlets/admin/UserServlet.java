package lab.controller.servlets.admin;

import lab.controller.actions.Action;
import lab.controller.actions.admin.BlockUserAction;
import lab.controller.actions.admin.GetUsersAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Admin/Users")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = new GetUsersAction();
        String str = action.doAction(req, resp);
        getServletContext()
                .getRequestDispatcher(str)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = new BlockUserAction();
        action.doAction(req, resp);
        doGet(req, resp);
    }
}
