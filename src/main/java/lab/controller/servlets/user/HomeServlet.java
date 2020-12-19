package lab.controller.servlets.user;

import lab.controller.actions.Action;
import lab.controller.actions.user.HomeAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/User/Home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = new HomeAction();
        String str = action.doAction(req, resp);
        getServletContext()
                .getRequestDispatcher(str)
                .forward(req, resp);
    }
}
