package lab.controller.servlets;

import lab.controller.actions.Action;
import lab.controller.actions.user.GuestAction;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Guest")
public class GuestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = new GuestAction();
        String str = action.doAction(req, resp);
        ServletContext servletContext = getServletContext();
        servletContext
                .getRequestDispatcher(str)
                .forward(req, resp);
    }
}
