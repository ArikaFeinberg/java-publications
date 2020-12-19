package lab.controller.servlets.admin;

import lab.controller.actions.Action;
import lab.controller.actions.admin.ChangePublicationAction;
import lab.controller.actions.admin.GetPublicationAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/Admin/Change")
public class ChangePublicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = new GetPublicationAction();
        String way = action.doAction(req, resp);
        if(way!=null)
            getServletContext()
                .getRequestDispatcher(way)
                .forward(req, resp);
        else resp.sendRedirect("Publication");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = new ChangePublicationAction();
        String way = action.doAction(req, resp);

        if(way == null) doGet(req, resp);
        else resp.sendRedirect(way);
    }
}
