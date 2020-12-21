package lab.controller.servlets.admin;

import lab.controller.actions.Action;
import lab.controller.actions.admin.AddPublicationAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Admin/Insert")
public class AddPublicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher("/jsp/admin/insert.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = new AddPublicationAction();
        String way = action.doAction(req, resp);

        if (way == null) doGet(req, resp);
        else resp.sendRedirect(way);
    }
}
