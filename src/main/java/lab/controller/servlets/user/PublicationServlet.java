package lab.controller.servlets.user;

import lab.controller.actions.Action;
import lab.controller.actions.user.PublicationAction;
import lab.controller.actions.user.SubscribeAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/User/Publication")
public class PublicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = new PublicationAction();
        String str = action.doAction(req, resp);
        getServletContext()
                .getRequestDispatcher(str)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = new SubscribeAction();
        action.doAction(req, resp);
        doGet(req, resp);
    }
}
