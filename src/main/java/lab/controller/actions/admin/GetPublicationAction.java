package lab.controller.actions.admin;

import lab.controller.Pagination;
import lab.controller.actions.Action;
import lab.model.service.PublicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetPublicationAction extends Action {
    @Override
    public String doAction(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getParameter("publication_id")!=null){
            PublicationService publicationService = new PublicationService();
            int id = Integer.parseInt(req.getParameter("publication_id"));
            req.setAttribute("publication",publicationService.getById(id));

            return "/jsp/admin/update.jsp";
        }
        return null;
    }
}
