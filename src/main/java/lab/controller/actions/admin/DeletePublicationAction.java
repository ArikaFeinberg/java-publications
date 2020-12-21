package lab.controller.actions.admin;

import lab.controller.actions.Action;
import lab.model.service.PublicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePublicationAction extends Action {
    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("delete") != null) {
            PublicationService publicationService = new PublicationService();
            publicationService.deletePublication(Integer.parseInt(req.getParameter("delete")));
        }
        return null;
    }
}
