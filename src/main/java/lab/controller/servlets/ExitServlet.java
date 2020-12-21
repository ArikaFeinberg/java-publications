package lab.controller.servlets;

import lab.controller.actions.Action;
import lab.controller.actions.ExitAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Exit")
public class ExitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = new ExitAction();
        String result = action.doAction(req, resp);
        resp.sendRedirect(result);
    }
}
